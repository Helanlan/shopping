package com.gg.service.impl;

import com.alibaba.fastjson.JSON;
import com.gg.dao.BrandMapper;
import com.gg.dao.CategoryMapper;
import com.gg.dao.SkuMapper;
import com.gg.dao.SpuMapper;
import com.gg.pojo.*;
import com.gg.service.SpuService;
import com.gg.utils.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.IFGE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/****
 * Spu业务层接口实现类
 *****/
@Service
@Transactional
public class SpuServiceImpl implements SpuService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private IdWorker idWorker;//商品id，因为商品id不属于自增
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BrandMapper brandMapper;


    /**
     * Spu条件+分页查询
     * @param spu 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Spu> findPage(Spu spu, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(spu);
        //执行搜索
        return new PageInfo<Spu>(spuMapper.selectByExample(example));
    }

    /**
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Spu> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Spu>(spuMapper.selectAll());
    }

    /**
     * Spu条件查询
     * @param spu
     * @return
     */
    @Override
    public List<Spu> findList(Spu spu){
        //构建查询条件
        Example example = createExample(spu);
        //根据构建的条件查询数据
        return spuMapper.selectByExample(example);
    }


    /**
     * Spu构建查询对象
     * @param spu
     * @return
     */
    public Example createExample(Spu spu){
        Example example=new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(spu!=null){
            // 
            if(!StringUtils.isEmpty(spu.getId())){
                    criteria.andEqualTo("id",spu.getId());
            }
            // 货号
            if(!StringUtils.isEmpty(spu.getSn())){
                    criteria.andEqualTo("sn",spu.getSn());
            }
            // SPU名

            if(!StringUtils.isEmpty(spu.getName())){
                    criteria.andLike("name","%"+spu.getName()+"%");
            }
            // 副标题

            if(!StringUtils.isEmpty(spu.getCaption())){
                    criteria.andEqualTo("caption",spu.getCaption());
            }
            // 品牌ID 
            if(!StringUtils.isEmpty(spu.getBrandId())){
                    criteria.andEqualTo("brandId",spu.getBrandId());
            }
            // 一级分类 
            if(!StringUtils.isEmpty(spu.getCategory1Id())){
                    criteria.andEqualTo("category1Id",spu.getCategory1Id());
            }
            // 二级分类 
            if(!StringUtils.isEmpty(spu.getCategory2Id())){
                    criteria.andEqualTo("category2Id",spu.getCategory2Id());
            }
            // 三级分类 
            if(!StringUtils.isEmpty(spu.getCategory3Id())){
                    criteria.andEqualTo("category3Id",spu.getCategory3Id());
            }
            // 模板ID 
            if(!StringUtils.isEmpty(spu.getTemplateId())){
                    criteria.andEqualTo("templateId",spu.getTemplateId());
            }
            // 运费模板id 
            if(!StringUtils.isEmpty(spu.getFreightId())){
                    criteria.andEqualTo("freightId",spu.getFreightId());
            }
            // 图片 
            if(!StringUtils.isEmpty(spu.getImage())){
                    criteria.andEqualTo(" image",spu.getImage());
            }
            // 图片列表
            if(!StringUtils.isEmpty(spu.getImages())){
                    criteria.andEqualTo("images",spu.getImages());
            }
            // 售后服务 
            if(!StringUtils.isEmpty(spu.getSaleService())){
                    criteria.andEqualTo("saleService",spu.getSaleService());
            }
            // 介绍 
            if(!StringUtils.isEmpty(spu.getIntroduction())){
                    criteria.andEqualTo("introduction",spu.getIntroduction());
            }
            // 规格列表
            if(!StringUtils.isEmpty(spu.getSpecItems())){
                    criteria.andEqualTo("specItems",spu.getSpecItems());
            }
            // 参数列表
            if(!StringUtils.isEmpty(spu.getParaItems())){
                    criteria.andEqualTo("paraItems",spu.getParaItems());
            }
            // 销量 
            if(!StringUtils.isEmpty(spu.getSaleNum())){
                    criteria.andEqualTo("saleNum",spu.getSaleNum());
            }
            // 评论数 
            if(!StringUtils.isEmpty(spu.getCommentNum())){
                    criteria.andEqualTo("commentNum",spu.getCommentNum());
            }
            // 是否启用规格 
            if(!StringUtils.isEmpty(spu.getIsEnableSpec())){
                    criteria.andEqualTo("isEnableSpec",spu.getIsEnableSpec());
            }
            // 是否上架 
            if(!StringUtils.isEmpty(spu.getIsMarketable())){
                    criteria.andEqualTo("isMarketable",spu.getIsMarketable());
            }
            // 是否删除 
            if(!StringUtils.isEmpty(spu.getIsDelete())){
                    criteria.andEqualTo("isDelete",spu.getIsDelete());
            }
            // 审核状态
            if(!StringUtils.isEmpty(spu.getStatus())){
                    criteria.andEqualTo("status",spu.getStatus());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        spuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Spu
     * @param spu
     */
    @Override
    public void update(Spu spu){
        spuMapper.updateByPrimaryKey(spu);
    }

    /**
     * 增加Spu
     * @param spu
     */
    @Override
    public void add(Spu spu){
        spuMapper.insert(spu);
    }

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
    @Override
    public Spu findById(Integer id){
        return  spuMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Spu全部数据
     * @return
     */
    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }



    /**
     * 商品增加
     * @param goods
     */
    @Override
    public void addGoods(Goods goods) {
        //spu增加一个，sku增加多个（list）
        Spu spu=goods.getSpu();
        spu.setId(idWorker.nextId());
        spuMapper.insertSelective(spu);

        //通过选中的三级分类的分类id查询分类信息，此处填充分类名称
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
        //通过spu的品牌id查询品牌信息，此处用来填充品牌名称
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
        Date date =new Date();
        for (Sku sku:goods.getSkuList()){
            sku.setId(idWorker.nextId());
            String name = spu.getName();
            if (StringUtils.isEmpty(sku.getSpec())){// 判断sku.getSpec()==null或者sku.getSpec()==""
                sku.setSpec("{}");
            }

            //sku.spec规格是以string形式存储的{1,2,3,4,5,56},每一项都是一个规格，sku的名字是以spu.name+所有规格
            //String类型转Map类型
            Map<String,String> specMap = JSON.parseObject(sku.getSpec(), Map.class);
            for (Map.Entry<String,String> entry:specMap.entrySet()){
                name+=" "+entry.getValue();
            }
            sku.setName(name);
            sku.setSpuId(spu.getId());
            sku.setCreateTime(date);
            sku.setUpdateTime(date);
            sku.setCategoryId(spu.getCategory3Id());
            sku.setCategoryName(category.getName());
            sku.setBrandName(brand.getName());//品牌名称
            //将sku添加到数据库
            skuMapper.insertSelective(sku);
        }
    }


    /**
     * 根据spuid搜索商品
     * @param spuId
     * @return
     */
    @Override
    public Goods findGoodsBySpuId(Long spuId) {
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        //skuList
        Sku sku =new Sku();
        sku.setSpuId(spuId);
        List<Sku> skuList = skuMapper.select(sku);
        Goods goods =new Goods();
        goods.setSkuList(skuList);
        goods.setSpu(spu);
        return goods;
    }

    /**
     * 修改商品信息
     * @param goods
     */
    @Override
    public void updateGoode(Goods goods) {
        Spu spu=goods.getSpu();
        //修改spu
        spuMapper.updateByPrimaryKeySelective(spu);


        //删除原来的sku
        Sku s =new Sku();
        s.setSpuId(spu.getId());
        skuMapper.delete(s);


        //新增新的sku
        //通过选中的三级分类的分类id查询分类信息，此处填充分类名称
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
        //通过spu的品牌id查询品牌信息，此处用来填充品牌名称
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
        Date date =new Date();
        for (Sku sku:goods.getSkuList()) {
            sku.setId(idWorker.nextId());
            String name = spu.getName();
            if (StringUtils.isEmpty(sku.getSpec())) {// 判断sku.getSpec()==null或者sku.getSpec()==""
                sku.setSpec("{}");
            }

            //sku.spec规格是以string形式存储的{1,2,3,4,5,56},每一项都是一个规格，sku的名字是以spu.name+所有规格
            //String类型转Map类型
            Map<String, String> specMap = JSON.parseObject(sku.getSpec(), Map.class);
            for (Map.Entry<String, String> entry : specMap.entrySet()) {
                name += " " + entry.getValue();
            }
            sku.setName(name);
            sku.setSpuId(spu.getId());
            sku.setUpdateTime(date);
            sku.setCategoryId(spu.getCategory3Id());
            sku.setCategoryName(category.getName());
            sku.setBrandName(brand.getName());//品牌名称
            //将sku添加到数据库
            skuMapper.insertSelective(sku);
        }
    }

    /**
     * 商品审核
     * @param spuId
     * 删除的无需审核
     */
    @Override
    public void examineGoods(Long spuId) {
        Spu spu1 = spuMapper.selectByPrimaryKey(spuId);
        if (spu1.getIsDelete().equalsIgnoreCase("1")){  //equalsIgnoreCase与equals区别是前者不区分大小写，而后者区分
            throw new RuntimeException("此商品已经删除");
        }
        spu1.setStatus("1");//通过
        spu1.setIsMarketable("1");//上架
        spuMapper.updateByPrimaryKeySelective(spu1);
    }

    /**
     * 商品下架
     * @param spuId
     */
    @Override
    public void pullGoods(Long spuId) {
        Spu spu1 = spuMapper.selectByPrimaryKey(spuId);
        if (spu1.getIsDelete().equalsIgnoreCase("1")){  //equalsIgnoreCase与equals区别是前者不区分大小写，而后者区分
            throw new RuntimeException("此商品已经删除");
        }
        spu1.setIsMarketable("0");//下架
        spuMapper.updateByPrimaryKeySelective(spu1);
    }

    /**
     * 商品上架
     * @param spuId
     */
    @Override
    public void putGoods(Long spuId) {
        Spu spu1 = spuMapper.selectByPrimaryKey(spuId);
        if (spu1.getIsDelete().equalsIgnoreCase("1")){  //equalsIgnoreCase与equals区别是前者不区分大小写，而后者区分
            throw new RuntimeException("此商品已经删除");
        }
        if (spu1.getStatus().equalsIgnoreCase("0")){
            throw new RuntimeException("此商品审核未通过");
        }
        spu1.setIsMarketable("1");//上架
        spuMapper.updateByPrimaryKeySelective(spu1);
    }

    /**
     * 批量上架
     * @param ids
     */
    @Override
    public void putManyGoods(Long[] ids) {
        //方法1
        for (Long spuId:ids) {
            Spu spu1 = spuMapper.selectByPrimaryKey(spuId);
            if (!spu1.getIsDelete().equalsIgnoreCase("1") && !spu1.getStatus().equalsIgnoreCase("0")) {  //equalsIgnoreCase与equals区别是前者不区分大小写，而后者区分
                spu1.setIsMarketable("1");//上架
                spuMapper.updateByPrimaryKeySelective(spu1);
            }
        }


        //方法2
        //update tb_spu set isMarketable=1 where isDelete=0 and status =1 and id in(ids)
        Example example =new Example(Spu.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andIn("id", Arrays.asList(ids));
        criteria.andEqualTo("isDelete","0");
        criteria.andEqualTo("status","1");
        //修改
        Spu spu =new Spu();
        spu.setIsMarketable("1");
        spuMapper.updateByExampleSelective(spu,example);
    }


    /**
     * 找回商品
     */
    @Override
    public void refindGoods(Long spuId) {
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        spu.setIsDelete("0");
        spuMapper.updateByPrimaryKeySelective(spu);
    }
}
