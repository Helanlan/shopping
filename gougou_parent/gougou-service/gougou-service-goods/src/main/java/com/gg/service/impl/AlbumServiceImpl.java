package com.gg.service.impl;

import com.gg.dao.AlbumMapper;
import com.gg.pojo.Album;
import com.gg.service.AlbumService;
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
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public void addAlbum(Album album) {
        /**
         * 如果选择insert 那么所有的字段都会添加一遍即使没有值
         * 如果使用inserSelective就会只给有值的字段赋值（会对传进来的值做非空判断）
         */
        albumMapper.insertSelective(album);
    }

    @Override
    public List<Album> selAll() {
        return albumMapper.selectAll();
    }

    @Override
    public Album selAlbumById(Integer id) {  //selectByPrimaryKey
        return albumMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        albumMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKey(album);
    }

    /**
     * example方法提取 模糊查询
     * @param album
     * @return
     */
    public Example createExample(Album album){
        Example example =new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();//条件构造器
        if (album!=null){
            //根据名字模糊查询
            if (!StringUtil.isEmpty(album.getTitle())){ //如果输入的title不是空，则开始模糊查询
                criteria.andLike("title","%"+album.getTitle()+"%");
            }
            if (!StringUtil.isEmpty(album.getImage())){//按相册封面
                criteria.andEqualTo("image",album.getImage());
            }
        }
        return example;
    }

    /**
     * 模糊查询
     * @param album
     * @return
     */
    @Override
    public List<Album> findList(Album album) {
        Example example = createExample(album);
        return albumMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @Override
    public PageInfo<Album> findByPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Album> albums = albumMapper.selectAll();
        return new PageInfo<Album>(albums);
    }

    /**
     * 条件+分页搜索
     * @param album
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @Override
    public PageInfo<Album> findByPage(Album album, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = createExample(album);
        List<Album> albums = albumMapper.selectByExample(example);
        return new PageInfo<Album>(albums);
    }
}
