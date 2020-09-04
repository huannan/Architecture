package com.nan.day05_butterknife_compiler;

import com.google.auto.service.AutoService;
import com.nan.day05_butterknife_annotations.BindView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

@AutoService(Processor.class)
public class ButterKnifeProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();
    }

    /**
     * 指定处理源代码的版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 提供需要处理的注解
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : getSupportedAnnotations()) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    /**
     * 有注解，都会进来；注意代码没有改动的情况下不会执行
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);

        /*
        // 测试代码
        for (Element element : elements) {
            Element enclosingElement = element.getEnclosingElement();
            System.out.println("---->" + element.getSimpleName() + " " + enclosingElement.getSimpleName());
        }
        */

        // 解析属性 Activity - List<Element>
        Map<Element, List<Element>> elementsMap = new HashMap<>();
        for (Element element : elements) {
            Element enclosingElement = element.getEnclosingElement();
            List<Element> viewBindingElements = elementsMap.get(enclosingElement);
            if (null == viewBindingElements) {
                viewBindingElements = new ArrayList<>();
                elementsMap.put(enclosingElement, viewBindingElements);
            }
            viewBindingElements.add(element);
        }

        // 生成代码
        for (Map.Entry<Element, List<Element>> entry : elementsMap.entrySet()) {
            Element enclosingElement = entry.getKey();
            List<Element> viewBindingElements = entry.getValue();

            String classNameStr = enclosingElement.getSimpleName().toString();
            ClassName className = ClassName.bestGuess(classNameStr);
            ClassName callSuperClassName = ClassName.get("android.support.annotation", "CallSuper");
            ClassName uiThreadClassName = ClassName.get("android.support.annotation", "UiThread");
            ClassName utilsClassName = ClassName.get("com.nan.day05_butterknife_core", "Utils");

            // 组装类
            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(classNameStr + "_ViewBinding")
                    .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                    .addSuperinterface(ClassName.get("com.nan.day05_butterknife_core", "Unbinder"));

            // 组装方法
            MethodSpec.Builder unBindMethodBuilder = MethodSpec.methodBuilder("unbind")
                    .addAnnotation(Override.class)
                    .addAnnotation(callSuperClassName)
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("if (target == null) throw new IllegalStateException(\"Bindings already cleared.\")");

            // 组装构造方法
            MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                    .addAnnotation(uiThreadClassName)
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(className, "target")
                    .addStatement("this.target = target");

            // 组装构造方法里面的赋值语句
            for (Element viewBindingElement : viewBindingElements) {
                int viewId = viewBindingElement.getAnnotation(BindView.class).value();
                String fieldName = viewBindingElement.getSimpleName().toString();
                constructorBuilder.addStatement("target.$L = $T.findViewById(target, $L)", fieldName, utilsClassName, viewId);
                unBindMethodBuilder.addStatement("target.$L = null", fieldName);
            }
            unBindMethodBuilder.addStatement("target = null");

            classBuilder.addField(className, "target", Modifier.PRIVATE);
            classBuilder.addMethod(constructorBuilder.build());
            classBuilder.addMethod(unBindMethodBuilder.build());

            try {
                String packageName = mElementUtils.getPackageOf(enclosingElement).getQualifiedName().toString();
                JavaFile.builder(packageName, classBuilder.build())
                        .addFileComment("Butter Knife自动生成的代码，请不要改动")
                        .build()
                        .writeTo(mFiler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(BindView.class);
        return annotations;
    }
}
