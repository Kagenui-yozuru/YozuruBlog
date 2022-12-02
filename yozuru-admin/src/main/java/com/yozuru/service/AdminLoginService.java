package com.yozuru.service;

import com.yozuru.domain.ResponseResult;
import com.yozuru.domain.dto.LoginDto;

import java.util.Map;

/**
 * @author Yozuru
 * @since 2022-12-02 19:58:30
 * &#064;description  后台登录服务的接口
 */
public interface AdminLoginService {
   /**
    * 登录操作的方法
    * @param loginDto 登录的数据传输对象
    * @return 返回token
    */
   ResponseResult<Map<String,String>> login(LoginDto loginDto);

   /**
    * 退出当前用户的方法
    * @return 返回操作的结果
    */
   ResponseResult<Object> loginOut();
}
