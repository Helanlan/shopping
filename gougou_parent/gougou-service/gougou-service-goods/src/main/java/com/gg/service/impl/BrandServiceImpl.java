package com.gg.service.impl;

import com.gg.dao.BrandMapper;
import com.gg.pojo.Brand;
import com.gg.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> selAll() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand selByBrandId(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addBrand(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void deleteBrand(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据多条件查询
     */
    @Override
    public List<Brand> findList(Brand brand) {
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    /**
     * example方法提取
     * @param brand
     * @return
     */
    public Example createExample(Brand brand){
        Example example =new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();//条件构造器
        if (brand!=null){
            //根据名字模糊查询
            if (!StringUtil.isEmpty(brand.getName())){ //如果输入的名字name不是空，则开始模糊查询
                criteria.andLike("name","%"+brand.getName()+"%");
            }
            if (!StringUtil.isEmpty(brand.getLetter())){//按首字母查询
                criteria.andEqualTo("letter",brand.getLetter());
            }
        }
        return example;
    }

    /**
     * PageHelper.startPage(page,size)进行分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Brand> brands = brandMapper.selectAll();
        return new PageInfo<Brand>(brands);//将brands赋值给了pageInfo的list
    }

    /**
     * 条件+分页搜索
     * @param brand
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        //条件搜索， name不为空则安name模糊搜索，letter部位空则按letter搜索
        Example example = createExample(brand);
        List<Brand> brands = brandMapper.selectByExample(example);
        return new PageInfo<Brand>(brands);//将brands赋值给了pageInfo的list
    }


    /**
     * 根据分类id查询品牌集合
     * categoryId 分类id
     */
    @Override
    public List<Brand> findBrandByCategoryId(Integer categoryId) {
        return brandMapper.findBrandByCategoryId(categoryId);
    }
}
