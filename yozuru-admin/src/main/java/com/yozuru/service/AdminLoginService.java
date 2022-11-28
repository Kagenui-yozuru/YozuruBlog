package com.yozuru.service;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.BlogLoginDto;

import java.util.Map;

/**
 * @author Yozuru
 */

public interface AdminLoginService {
   ResponseResult<Map<String,String>> login(BlogLoginDto loginDto);
}
