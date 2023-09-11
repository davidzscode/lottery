package com.qihang.common.util.upload;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * @author: qihang
 * @description: s3工具栏
 * @time: 2022-07-17 10:14
 */
@Component
public class S3Util {
    @Value("${s3.file_bucket}")
    private String fileBucket;

    @Value("${s3.url}")
    private String url;

    @Resource
    private AmazonS3 amazonS3;

    /**
     * 前端上传文件
     *
     * @param file
     * @return
     */
    public String upload(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            amazonS3.putObject(new PutObjectRequest(fileBucket, fileName, file.getInputStream(), objectMetadata)
                    //配置文件访问权限
                    .withCannedAcl(CannedAccessControlList.PublicReadWrite));
            //文件访问路径
            String address = url + fileBucket + "/" + fileName;
            return address;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 后端上传图片
     *
     * @param is
     * @return
     */
    public String upload(InputStream is) {
        try {
            String fileName = IdWorker.get32UUID() + ".png";
            amazonS3.putObject(new PutObjectRequest(fileBucket, fileName, is, null)
                    //配置文件访问权限
                    .withCannedAcl(CannedAccessControlList.PublicReadWrite));
            //文件访问路径
            String address = url + fileBucket + "/" + fileName;
            return address;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param fileKey
     */
    public void delete(String fileKey) {
        amazonS3.deleteObject(new DeleteObjectRequest(fileBucket, fileKey));
    }
}
