package com.nan.day06_apt_pay_compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

public class WXPayEntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {

    private Filer mFiler;
    private String mPackageName;
    private TypeMirror mTypeMirror;

    public WXPayEntryVisitor(Filer filer) {
        mFiler = filer;
    }

    @Override
    public Void visitString(String s, Void aVoid) {
        mPackageName = s;
        return aVoid;
    }

    @Override
    public Void visitType(TypeMirror typeMirror, Void aVoid) {
        mTypeMirror = typeMirror;
        generateWXPayCode();
        return aVoid;
    }

    private void generateWXPayCode() {
        TypeSpec.Builder wxPayEntryActivityClassBuilder = TypeSpec.classBuilder("WXPayEntryActivity")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror));

        try {
            String packageName = mPackageName + ".wxapi";
            JavaFile.builder(packageName, wxPayEntryActivityClassBuilder.build())
                    .addFileComment("微信支付自动生成的代码，请不要修改")
                    .build()
                    .writeTo(mFiler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
