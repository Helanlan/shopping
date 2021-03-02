package com.gg.service;

import com.gg.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {
    List<Brand> selAll();
    Brand selByBrandId(Integer id);
    void addBrand(Brand brand);
    void updateBrand(Brand brand);
    void deleteBrand(Integer id);

    /**
     * 根据多条件查询
     */
    List<Brand> findList(Brand brand);


    /**
     * 分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    PageInfo<Brand> findPage(Integer page,Integer size);

    /**
     * 条件+分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    PageInfo<Brand> findPage(Brand brand,Integer page,Integer size);


    /**
     * 根据分类id查询品牌集合
     * categoryId 分类id
     */
    List<Brand> findBrandByCategoryId(Integer categoryId);

}
