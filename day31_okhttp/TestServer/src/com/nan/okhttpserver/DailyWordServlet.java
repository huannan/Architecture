package com.nan.okhttpserver;

import com.nan.okhttpserver.base.BaseJsonServlet;
import com.nan.okhttpserver.response.ResponseCode;
import com.nan.okhttpserver.response.ResponseEntity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dailyword")
public class DailyWordServlet extends BaseJsonServlet {
    @Override
    protected ResponseEntity onHandle(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.code = ResponseCode.OK;
        responseEntity.msg = "成功";
        responseEntity.data = "每天都是好心情";
        return responseEntity;
    }
}
