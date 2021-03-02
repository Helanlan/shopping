package com.gg.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * spu,sku的组合对象，这两个合起来就是商品信息
 */
public class Goods implements Serializable {

    private Spu spu;//属性值、特性相同的货品就可以称为一个SPU

    private List<Sku> skuList;//同款商品的独有属性，跟spu合起来就是商品表

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }

    @Override
    public String toString() {
        return "Goods{" + "spu=" + spu + ", skuList=" + skuList + '}';
    }
}
