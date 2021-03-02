package com.gg.controller;

import com.gg.pojo.Album;
import com.gg.utils.Result;
import com.gg.utils.StatusCode;
import com.gg.service.AlbumService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/album")
@CrossOrigin //跨域。就是a域名访问b域名的数据，使用场景：域名、请求端口、协议不一致的时候就表示跨域了，不加@CrossOrigin就会禁止跨域
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping
    public Result addAlbum(@RequestBody Album album) {
        albumService.addAlbum(album);
        return new Result(true, StatusCode.OK, "相册添加成功");
    }

    @GetMapping(value = "/selAll")
    public Result<List<Album>> selAllAlbum() {
        List<Album> albums = albumService.selAll();
        return new Result(true, StatusCode.OK, "相册查询成功", albums);
    }

    @GetMapping(value = "/{id}")
    public Result<Album> selAlbumById(@PathVariable(value = "id") Integer id) {
        Album album = albumService.selAlbumById(id);
        return new Result<Album>(true, StatusCode.OK, "相册查询成功", album);
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable(value = "id") Integer id) {
        albumService.deleteById(id);
        return new Result(true, StatusCode.OK, "相册删除成功");
    }

    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id") Integer id, @RequestBody Album album) {
        album.setId(id);
        albumService.update(album);
        return new Result(true, StatusCode.OK, "相册修改成功");
    }

    /**
     * 条件查询
     *
     * @param album
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Album>> findList(@RequestBody Album album) {
        List<Album> albumList = albumService.findList(album);
        return new Result<List<Album>>(true, StatusCode.OK, "条件查询成功", albumList);
    }

    /**
     * 分页查询
     *
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Album>> findByPage(@PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Album> byPage = albumService.findByPage(page, size);
        return new Result<PageInfo<Album>>(true, StatusCode.OK, "分页查询成功", byPage);
    }

    /**
     * 条件 分页查询
     *
     * @param album
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Album>> findByPage(@RequestBody Album album, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size) {
        PageInfo<Album> byPage = albumService.findByPage(album, page, size);
        return new Result<PageInfo<Album>>(true, StatusCode.OK, "条件分页查询成功", byPage);
    }
}
