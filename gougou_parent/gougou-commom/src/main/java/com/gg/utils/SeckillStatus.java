package com.gg.utils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 戴金华
 * @date 2019-12-30 15:17
 * 用户排队枪弹信息封装
 */
public class SeckillStatus implements Serializable {

    //秒杀用户名
    private String username;
    //创建时间
    private Date createTime;
    //秒杀状态  1 排队中   2 秒杀等待支付(已下单) 3 支付超时 4 秒杀失败 5 支付完成
    private Integer status;
    //秒杀的商品ID
    private Long goodsId;
    //应付金额
    private Float money;
    //秒杀时间段
    private String time;
    //订单号
    private Long orderId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public SeckillStatus() {
    }

    public SeckillStatus(String username, Date createTime, Integer status, Long goodsId, String time) {
        this.username = username;
        this.createTime = createTime;
        this.status = status;
        this.goodsId = goodsId;
        this.time = time;
    }
}