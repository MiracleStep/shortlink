package com.nageoffer.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nageoffer.shortlink.admin.dao.entity.UserDO;
import com.nageoffer.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.nageoffer.shortlink.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户响应实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    Boolean hasUserName(String username);

    /**
     * 用户注册
     * @param requestParam
     */
    void register(UserRegisterReqDTO requestParam);
}
