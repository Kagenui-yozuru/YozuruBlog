package com.yozuru.service;

import com.yozuru.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yozuru
 */

public interface UploadService {
    ResponseResult<Object> uploadImg(MultipartFile img);
}
