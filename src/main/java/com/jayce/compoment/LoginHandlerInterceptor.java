package com.jayce.compoment;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 老邓 on 2018/6/28.
 * 登陆拦截器
 */
public class LoginHandlerInterceptor implements HandlerInterceptor{
    // 目标方法执行之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null){
            // 未登录，返回到登录页面
            request.setAttribute("msg", "没有权限");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
