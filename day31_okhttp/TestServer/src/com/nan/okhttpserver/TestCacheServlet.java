package com.nan.okhttpserver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * http://localhost:8080/TestServer/testcache
 */
@WebServlet("/testcache")
public class TestCacheServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        System.out.println("Response");
        printWriter.write("Response");
        printWriter.flush();
        printWriter.close();
    }

    /**
     * 与请求头里面的If-Modified-Since字段值比较
     */
    @Override
    protected long getLastModified(HttpServletRequest req) {
        String filePath = getServletContext().getRealPath("index.jsp");
        File file = new File(filePath);
        return file.lastModified();
    }
}
