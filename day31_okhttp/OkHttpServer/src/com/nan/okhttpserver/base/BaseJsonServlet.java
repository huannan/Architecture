package com.nan.okhttpserver.base;

import com.alibaba.fastjson.JSON;
import com.nan.okhttpserver.response.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class BaseJsonServlet extends BaseServlet {
    @Override
    protected void onResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        ResponseEntity entity;
        try {
            entity = onHandle(req, resp);
        } catch (Exception e) {
            entity = new ResponseEntity();
        }
        PrintWriter writer = resp.getWriter();
        String jsonString = JSON.toJSONString(entity);
        writer.write(jsonString);
        writer.flush();
        writer.close();
    }

    protected abstract ResponseEntity onHandle(HttpServletRequest req, HttpServletResponse resp);
}
