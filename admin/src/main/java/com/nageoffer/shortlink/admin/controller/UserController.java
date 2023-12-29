package com.nageoffer.shortlink.admin.controller;

import com.nageoffer.shortlink.admin.common.convention.result.Result;
import com.nageoffer.shortlink.admin.dto.resp.UserRespDTO;
import com.nageoffer.shortlink.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理控制层
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *  根据用户名查找用户信息
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username){
        Result<UserRespDTO> userRespDTOResult = new Result<>();
        UserRespDTO userRespDTO = userService.getUserByUsername(username);
        return userRespDTOResult.setCode("0").setData(userRespDTO);
    }
}
