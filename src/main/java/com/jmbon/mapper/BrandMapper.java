package com.jmbon.mapper;

import com.jmbon.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {

    //查询所有
    @Select("select * from tb_brand")
    List<Brand> selectAll();

    //添加数据
    @Select("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    //批量删除
    void deleteByIds(@Param("ids") int[] ids);

    //查询总计记录数
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    //分页查询
    @Select("select * from tb_brand limit #{begin},#{size}")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size")int size);

    //分页条件查询
    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("size")int size, @Param("brand") Brand brand);

    //查询条件总计记录数
    int selectTotalCountByCondition(Brand brand);

}
