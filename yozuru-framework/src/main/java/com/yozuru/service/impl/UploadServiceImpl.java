package com.yozuru.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.enums.HttpCodeEnum;
import com.yozuru.exception.BusinessException;
import com.yozuru.service.UploadService;
import com.yozuru.utils.PathUtils;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Yozuru
 */
@Service
@Setter
@ConfigurationProperties(prefix = "oss")
public class UploadServiceImpl implements UploadService {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String cdn;

    @Override
    public ResponseResult<Object> uploadImg(MultipartFile img) {
        String filename = img.getOriginalFilename();
        //判断文件类型、大小
        if(!filename.endsWith(".png")&& filename.endsWith(".jpg")&& filename.endsWith(".jpeg"))
            throw new BusinessException(HttpCodeEnum.FILE_TYPE_ERROR);

        //判断通过则上传OSS
        String filePath = PathUtils.generateFilePath(filename);
        String url = uploadOss(img, filePath);
        return ResponseResult.success(url);
    }


    private String uploadOss(MultipartFile file, String filePath) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            InputStream inputStream = file.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, filePath, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //
        }
        return cdn+"/"+filePath;
    }
}
