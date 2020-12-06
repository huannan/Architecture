package com.nan.okhttpserver;

import com.nan.okhttpserver.base.BaseJsonServlet;
import com.nan.okhttpserver.response.ResponseCode;
import com.nan.okhttpserver.response.ResponseEntity;
import com.nan.okhttpserver.response.UserInfoEntity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http://localhost:8080/OkHttpServer/login?name=huannan&pwd=123456
 */
@WebServlet("/login")
public class LoginServlet extends BaseJsonServlet {
    @Override
    protected ResponseEntity onHandle(HttpServletRequest req, HttpServletResponse resp) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.code = ResponseCode.LOGIN_ERROR;
        responseEntity.msg = "用户名或者密码错误";

        if ("huannan".equals(req.getParameter("name")) && "123456".equals(req.getParameter("pwd"))) {
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setName("huannan");
            userInfoEntity.setPwd("123456");
            userInfoEntity.setSex("男");

            responseEntity.code = ResponseCode.OK;
            responseEntity.msg = "登录成功";
            responseEntity.data = userInfoEntity.toString();

            Cookie cookie = new Cookie("userinfo", userInfoEntity.getName());
            cookie.setMaxAge(10);
            resp.addCookie(cookie);
        }

        return responseEntity;
    }
}
