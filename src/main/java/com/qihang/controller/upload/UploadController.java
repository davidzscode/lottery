package com.qihang.controller.upload;

import com.qihang.service.upload.IUploadService;
import com.qihang.common.vo.BaseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author: qihang
 * @description:
 * @time: 2022-07-17 18:36
 */
@Api(tags = "文件集合")
@RequestMapping("/file")
@RestController
public class UploadController {
    @Resource
    private IUploadService uploadService;

    @ApiOperation(value = "上传文件接口")
    @PostMapping(value = "/upload")
    public BaseVO upload(MultipartFile file) {
        return uploadService.upload(file);
    }
}
