package com.gg.service.impl;

import com.gg.dao.CategoryMapper;
import com.gg.pojo.Category;
import com.gg.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(Category category) {
        categoryMapper.insertSelective(category);
    }

    @Override
    public List<Category> selAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public Category selCategoryById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    public Example createExample(Category category) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        if (category != null) {
            if (!StringUtils.isEmpty(category.getName())){
                criteria.andLike("name","%"+category.getName()+"%");
            }
        }
        return example;
    }

    /**
     * 条件搜索
     * @param category
     * @return
     */
    @Override
    public List<Category> findList(Category category) {
        Example example = createExample(category);
        List<Category> categories = categoryMapper.selectByExample(example);
        return categories;
    }

    /**
     * 分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @Override
    public PageInfo<Category> findByPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Category> categories = categoryMapper.selectAll();
        return new PageInfo<>(categories);
    }

    /**
     * 分页+条件按 查询
     * @param category
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @Override
    public PageInfo<Category> findByPage(Category category, Integer page, Integer size) {
        Example example = createExample(category);
        PageHelper.startPage(page,size);
        List<Category> categories = categoryMapper.selectByExample(example);
        return new PageInfo<>(categories);
    }

    /**
     * 按照分类的parent_id查询所有子节点
     * @param pid parent_id，1及分类的pid是0
     * @return
     */
    @Override
    public List<Category> findByParentId(Integer pid) {
        //select * from tb_category where id =#{pid}
        Category category =new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
    }


}
