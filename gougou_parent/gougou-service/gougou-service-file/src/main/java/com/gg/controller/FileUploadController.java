package com.gg.controller;

import com.gg.file.FastDFSFile;
import com.gg.util.FastDFSUtil;
import com.gg.utils.Result;
import com.gg.utils.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "/upload")
@RestController
@CrossOrigin
public class FileUploadController {

    @PostMapping
    public Result upload(@RequestParam(value = "file")MultipartFile file) throws Exception {
        FastDFSFile fastDFSFile =new FastDFSFile(
                file.getOriginalFilename(),
                file.getBytes(),
                StringUtils.getFilenameExtension(file.getOriginalFilename()));
        String[] uploads = FastDFSUtil.upload(fastDFSFile);

        //拼接访问地址
        String url =FastDFSUtil.getTrackerInfo()+"/"+uploads[0]+"/"+uploads[1];
       return new Result(true, StatusCode.OK,"文件上传成功",url);
    }
}
