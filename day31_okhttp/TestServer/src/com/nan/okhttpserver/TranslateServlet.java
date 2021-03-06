package com.nan.okhttpserver;

import com.nan.okhttpserver.base.BaseJsonServlet;
import com.nan.okhttpserver.response.ResponseCode;
import com.nan.okhttpserver.response.ResponseEntity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/translate")
public class TranslateServlet extends BaseJsonServlet {
    @Override
    protected ResponseEntity onHandle(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String input = req.getParameter("input");
        String translateResult = input + " -> " + "Good mood every day";

        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.code = ResponseCode.OK;
        responseEntity.msg = "Good mood every day";
        responseEntity.data = translateResult;

        return responseEntity;
    }
}
