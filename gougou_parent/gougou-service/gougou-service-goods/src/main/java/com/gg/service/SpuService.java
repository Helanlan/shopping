package com.gg.service;

import com.gg.pojo.Goods;
import com.gg.pojo.Spu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Spu业务层接口
 */
public interface SpuService {

    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(Spu spu, int page, int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(int page, int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    List<Spu> findList(Spu spu);

    /***
     * 删除Spu
     * @param id
     */
    void delete(Integer id);

    /***
     * 修改Spu数据
     * @param spu
     */
    void update(Spu spu);

    /***
     * 新增Spu
     * @param spu
     */
    void add(Spu spu);

    /**
     * 根据ID查询Spu
     *
     * @param id
     * @return
     */
    Spu findById(Integer id);

    /***
     * 查询所有Spu
     * @return
     */
    List<Spu> findAll();


    /**
     * 商品增加
     * @param goods
     */
    void addGoods(Goods goods);

    /**
     * 根据spuid搜索商品
     * @param spuId
     * @return
     */
    Goods findGoodsBySpuId(Long spuId);

    /**
     * 修改商品信息
     * @param goods
     */
    void updateGoode(Goods goods);

    /**
     * 商品审核
     * @param spuId
     */
    void examineGoods(Long spuId);

    /**
     * 商品下架
     * @param spuId
     */
    void pullGoods(Long spuId);

    /**
     * 商品上架
     * @param spuId
     */
    void putGoods(Long spuId);

    /**
     * 批量上架
     */
    void putManyGoods(Long[] ids);

    /**
     * 找回商品
     */
    void refindGoods(Long spuId);
}
