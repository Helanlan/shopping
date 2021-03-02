package com.gg.service;

import com.gg.pojo.Album;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AlbumService {
    void addAlbum(Album album);
    List<Album>selAll();
    Album selAlbumById(Integer id);
    void deleteById(Integer id);
    void update(Album album);


    /**
     * 根据多条件查询
     */
    List<Album> findList(Album album);

    /**
     * 分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    PageInfo<Album> findByPage(Integer page,Integer size);


    /**
     * 条件+分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    PageInfo<Album> findByPage(Album album,Integer page,Integer size);
}
