package com.jmbon.service;

import com.jmbon.pojo.Brand;
import com.jmbon.pojo.PageBean;

import java.util.List;

public interface BrandService {
    /*查询所有*/
    List<Brand> selectAll();
    void add(Brand brand);
    void deleteByIds(int[] ids);

    PageBean<Brand> selectByPage(int currentPage,int pageSize);

    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);

}
