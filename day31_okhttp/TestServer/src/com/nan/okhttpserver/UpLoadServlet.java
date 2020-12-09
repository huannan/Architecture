package com.nan.okhttpserver;

import com.nan.okhttpserver.base.BaseJsonServlet;
import com.nan.okhttpserver.response.ResponseCode;
import com.nan.okhttpserver.response.ResponseEntity;

import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

/**
 * 使用MultipartConfig注解标注改servlet能够接受文件上传的请求
 * https://blog.csdn.net/xingfei_work/article/details/72683131
 */
@MultipartConfig
@WebServlet("/upload")
public class UpLoadServlet extends BaseJsonServlet {
    @Override
    protected ResponseEntity onHandle(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.code = ResponseCode.UPLOAD_SUCCESS;
        responseEntity.msg = "文件上传成功";

        // 获取request的所有的请求参数(将请求参数转换为Part)
        Collection<Part> list = req.getParts();
        for (Part p : list) {
            if ("file".equals(p.getName())) {
                // 获取上传的文件名称
                String filename = p.getSubmittedFileName();
                // 创建要保存的文件对象
                File file = new File(createDir(getServletContext()), createName(filename));
                // 保存文件
                p.write(file.getAbsolutePath());
            } else if ("os".equals(p.getName())) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String os = reader.readLine();
                reader.close();
                responseEntity.msg = "文件上传成功，操作系统是：" + os;
            }
        }

        return responseEntity;
    }

    // 创建目录---以日期，一天一个文件夹
    private File createDir(ServletContext context) {
        String p1 = context.getRealPath("/upload");
        File file = new File(p1, new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    // 创建文件名--区分同名文件,在文件名前加上当前的时间
    private String createName(String name) {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()) + "_" + name;
    }
}
