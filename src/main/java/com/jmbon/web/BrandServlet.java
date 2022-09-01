package com.jmbon.web;

import com.alibaba.fastjson.JSON;
import com.jmbon.pojo.Brand;
import com.jmbon.pojo.PageBean;
import com.jmbon.service.BrandService;
import com.jmbon.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用查询所有方法
        List<Brand> brands = brandService.selectAll();

        //把数据转为JSON
        String jsonString = JSON.toJSONString(brands);

        //响应数据
        //设置响应数据编码解决中文乱码
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter respWriter = resp.getWriter();
        respWriter.write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置post请求数组解码，解决中文乱码问题
        req.setCharacterEncoding("utf-8");
        //获取请求数据
        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        //转化为Brand对象
        Brand brand = JSON.parseObject(s, Brand.class);
        brandService.add(brand);

        //响应成功的标识
        resp.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
        public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取请求数据
        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        //JSON转化
        int[] ids = JSON.parseObject(s, int[].class);

        brandService.deleteByIds(ids);

        //响应成功的标识
        resp.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        PageBean<Brand> brandPageBean = brandService.selectByPage(currentPage, pageSize);

        String string = JSON.toJSONString(brandPageBean);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(string);
    }

    /**
     * 分页条件查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        req.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);

        PageBean<Brand> brandPageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);
        String string = JSON.toJSONString(brandPageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(string);
    }
}
