package com.gg.utils.exception;

public class note {
    /**
     * 第一章：环境搭建，简单的增删改查
     * 第二章：1、分布式文件管理系统FastDFS（专门管理文件的，大量数据存在不同的空间的分配），用于文件上传、下载、删除，文件的缓存控制等
     *     2、搭建文件上传微服务（相册管理，规格参数管理，商品分类管理）
     *  fastDFS：
     *      两个组件：tracker（负责文件管理负载均衡操作，只有调度的作用，属于注册中心），storage（干活的，真正实现文件的增删改查。。。）
     *      工作原理及流程：storage定时注册到tracker-》用户发请求-》fastDFS从tracker找可用的或空闲的storage-》
     *          返回可用的storage的ip给controller-》controller根据返回的storage信息将自己的文件及请求发给这个storage实现文件管理操作
     *          返回值：
     *     fastDFS安装：虚拟机安装
     *          docker pull morunchang/fastdfs：拉取镜像
     *          docker images：查看拉取到的镜像
     *          docker run -d --name tracker --net=host morunchang/fastdfs sh tracker.sh：运行tracker
     *          docker run -d --name storage --net=host -e TRACKER_IP=虚拟机ip:22122 -e GROUP_NAME=group1 morunchang/fastdfs sh storage.sh：运行storage
     *          docker ps：查看运行成功的服务
     *          docker update --restart=always tracker：设置tracker自启动
     *          docker update --restart=always storage：设置storage自启动
     *     配置nginx（本项目主要提供对fastdfs图片访问的支持）访问图片必须通过nginx，以上操作自动安装了nginx
     *          结合项目实现文件上传：FastDFSUtil   FileUploadController
     *          docker exec -it storage /bin/bash：进入storage
     *          上传的文件在data/fast_data/data/...
     * SPU与SKU
     *      SPU:标准产品单位，是商品信息聚合的最小单位，是一组可复用易检索的标准化信息集合，该集合表述了产一个品的特性
     *          即：属性值、特性相同的货品就可以称为一个SPU
     *          同款商品的公共属性抽取
     *      SKU:库存量单位，物理上不可分割的最小存货单元
     *           同款商品中某类或某个sp独有的属性
     * 代码生成器自动生成pojo对应的增删改查所有代码
     *
     * id生成器
     *      在gougou_service_goods启动类中加入如下代码
     *      @Bean
     *      public IdWorker idWorker(){
     *          return new IdWorker(0,0);
     *      }
     * ######################################################################################
     * 每个分类项都有一个模板id，不同的分类有不同的规格、参数，也有不同的品牌
     *      （eg：分类1下有规格1，2，3...,每个规格的模板id=这个分类的模板id。分类1下有a,b,c...品牌，分类品牌关系表中添加对应的关系）
     * 商品信息导航：
     *      分类表中根据parent_id分类，parent_id，1及分类的pid是0，--》根据分类id查询对应的品牌集合
     *      根据分类id查询模板id，再通过模板id查询对应的规格参数（条件是前一步选择的分类的模板id）（商品属性）
     *      根据分类id查询模板id，再通过模板id查询对应的参数para
     *
     * 商品添加：goods表
     *      sku：同款商品的独有属性，跟spu合起来就是商品表
     *      spu：属性值、特性相同的货品就可以称为一个SPU ,即 同款商品的公共属性抽取，跟sku合起来就是商品表
     * 修改商品：spu修改，sku列表先删除，后添加
     * 商品的审核上架：删除的商品不能审核，修改上架参数
     * 商品的下架，上架、、、
     * 表的关系图：spu--sku(spuid)一对多 ,brand--spu(brandid)一对多，category--spu(categoryid)一对多
     *      sku--para(sku中的paraItems来自para),sku--spec(sku中的specs来自spec)
     *      template--para(tid)一对多，template--spec(tid)一对多
     *
     * Lua:
     *      语法学习
     * （重点）openResty:解决高并发，封装nginx，提供lua
     * （重点，会并理解）广告缓存载入与读取
     * （掌握）nginx：解决高并发
     * （掌握理解工作原理）canal：实现数据同步操作--mysql同步到redis 。。。.
     *
     * 首页广告缓存访问流程：用户通过id分类访问openResty+Nginx--》
     *      进入nginx缓存模块（nginx-cache）寻找是否有缓存--》有缓存数据就返回
     *      如果没有缓存数据会进入Lua脚本，通过Lua脚本查找redis缓存，如果redis有数据则会将数据存入到nginx的缓存，并返回查询到的数据
     *      如果redis没有缓存数据则会通过Lua脚本查找mysql数据库，如果MySQL有数据则会将数据存入到redis的缓存，并返回查询到的数据
     */
}
