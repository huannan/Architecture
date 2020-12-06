package com.nan.okhttpserver;

import com.nan.okhttpserver.base.BaseJsonServlet;
import com.nan.okhttpserver.response.ResponseCode;
import com.nan.okhttpserver.response.ResponseEntity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http://localhost:8080/OkHttpServer/comment
 */
@WebServlet("/comment")
public class CommentServlet extends BaseJsonServlet {
    @Override
    protected ResponseEntity onHandle(HttpServletRequest req, HttpServletResponse resp) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.code = ResponseCode.COMMENT_ERROR;
        responseEntity.msg = "评论失败，请重新登录";

        Cookie[] cookies = req.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if ("userinfo".equals(cookie.getName())) {
                    responseEntity.code = ResponseCode.OK;
                    responseEntity.msg = "评论成功";
                    break;
                }
            }
        }

        return responseEntity;
    }
}
