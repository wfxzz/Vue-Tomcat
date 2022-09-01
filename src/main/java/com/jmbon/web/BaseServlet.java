package com.jmbon.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


/**
 * 自定义servlet实现不同路径的方法分发
 */

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径的部分url
        String uri = req.getRequestURI();

        //从后截取url带‘/’的字符串
        int indexOf = uri.lastIndexOf('/');

        //获取方法名称
        String methodName = uri.substring(indexOf + 1);

        //获取字节码对象
        Class<? extends BaseServlet> Class = this.getClass();

        try {
            //获取方法对象
            Method method = Class.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //调用方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
