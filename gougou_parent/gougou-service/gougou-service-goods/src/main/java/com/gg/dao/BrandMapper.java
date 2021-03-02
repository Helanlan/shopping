package com.gg.dao;

import com.gg.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用通用mapper要继承tk.mybatis.mapper.common.Mapper
 */
public interface BrandMapper extends Mapper<Brand> {


    /**
     * 根据分类id查询品牌集合
     */
    @Select("select * from tb_brand where id in (select brand_id from tb_category_brand where category_id=#{category_id})")
    List<Brand> findBrandByCategoryId(Integer categoryId);
}
