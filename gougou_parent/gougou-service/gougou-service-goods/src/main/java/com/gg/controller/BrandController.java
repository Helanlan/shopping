package com.gg.controller;

import com.gg.pojo.Brand;
import com.gg.service.BrandService;
import com.gg.utils.Result;
import com.gg.utils.StatusCode;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
@CrossOrigin  //跨域。就是a域名访问b域名的数据，使用场景：域名、请求端口、协议不一致的时候就表示跨域了，不加@CrossOrigin就会禁止跨域
public class BrandController {
    @Autowired
    private BrandService brandService;


    /**
     * 条件分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@RequestBody Brand brand, @PathVariable(value = "page") Integer page, @PathVariable(value = "size") Integer size){
        PageInfo<Brand> pageList = brandService.findPage(brand,page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK,"条件分页查询成功",pageList);
    }

    /**
     * 分页查询
     * @param page 当前页
     * @param size 每一页要显示的数量
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "page") Integer page,@PathVariable(value = "size") Integer size){
        PageInfo<Brand> pageList = brandService.findPage(page, size);
        return new Result<PageInfo<Brand>>(true,StatusCode.OK,"分页查询成功",pageList);
    }

    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand){
        List<Brand> list = brandService.findList(brand);
        return new Result<List<Brand>>(true,StatusCode.OK,"条件搜索查询成功",list);
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteBrand(@PathVariable(value = "id") Integer id){
        brandService.deleteBrand(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @PutMapping(value = "/{id}")
    public Result updateBrand(@PathVariable(value = "id") Integer id,@RequestBody Brand brand){
        brand.setId(id);
        brandService.updateBrand(brand);
        return new Result(true,StatusCode.OK,"品牌信息更新成功");
    }

    @PostMapping
    public Result addBrand(@RequestBody Brand brand){
        brandService.addBrand(brand);
        return new Result(true,StatusCode.OK,"品牌添加成功");
    }

    @GetMapping(value = "/selAll")
    public Result<List<Brand>> selAll(){
        List<Brand> brands = brandService.selAll();
        return new Result(true, StatusCode.OK,"查询成功",brands);
    }

    ////@PathVariable可以用来映射URL中的占位符到目标方法的参数中
    @GetMapping(value = "/{id}")
    public Result<Brand> selByBrandId(@PathVariable(value = "id") Integer id){
        Brand brand = brandService.selByBrandId(id);
        return new Result(true, StatusCode.OK,"查询成功",brand);
    }

    /**
     *
     * 根据分类id查询品牌集合
     */
    @GetMapping(value = "/category/{id}")
    public Result<List<Brand>> findBrandByCategoryId(@PathVariable(value = "id") Integer categoryId){
        List<Brand> brands = brandService.findBrandByCategoryId(categoryId);
        return new Result<List<Brand>>(true,StatusCode.OK,"品牌查询成功",brands);
    }
}
