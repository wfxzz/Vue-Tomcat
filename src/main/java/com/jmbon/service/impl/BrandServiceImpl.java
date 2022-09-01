package com.jmbon.service.impl;

import com.jmbon.mapper.BrandMapper;
import com.jmbon.pojo.Brand;
import com.jmbon.pojo.PageBean;
import com.jmbon.service.BrandService;
import com.jmbon.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<Brand> selectAll() {
        SqlSession openSession = sqlSessionFactory.openSession();
        BrandMapper mapper = openSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        openSession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        SqlSession openSession = sqlSessionFactory.openSession();
        BrandMapper mapper = openSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        openSession.commit();
        openSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession openSession = sqlSessionFactory.openSession();
        BrandMapper mapper = openSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(ids);
        openSession.commit();
        openSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession openSession = sqlSessionFactory.openSession();
        BrandMapper mapper = openSession.getMapper(BrandMapper.class);
        int begin = (currentPage-1)*pageSize;
        List<Brand> rows = mapper.selectByPage(begin, pageSize);
        int totalCount = mapper.selectTotalCount();
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession openSession = sqlSessionFactory.openSession();
        BrandMapper mapper = openSession.getMapper(BrandMapper.class);
        int begin = (currentPage-1)*pageSize;
        //处理模糊条件
        String brandName = brand.getBrandName();
        if (brandName!=null&&brandName.length()>0){
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();
        if (companyName!=null&&companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }
        List<Brand> rows = mapper.selectByPageAndCondition(begin, pageSize,brand);
        int totalCount = mapper.selectTotalCountByCondition(brand);
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        return pageBean;
    }
}
