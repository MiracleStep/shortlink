package com.nageoffer.shortlink.admin.controller;

import com.nageoffer.shortlink.admin.common.convention.result.Result;
import com.nageoffer.shortlink.admin.common.convention.result.Results;
import com.nageoffer.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.nageoffer.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.nageoffer.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.nageoffer.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.nageoffer.shortlink.admin.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理控制层
 */
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    /**
     * 新增短链接分组
     * @param shortLinkGroupSaveReqDTO
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO shortLinkGroupSaveReqDTO){
        groupService.saveGroup(shortLinkGroupSaveReqDTO.getName());
        return Results.success();
    }

    /**
     * 查询短链接分组
     */
    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup(){
        List<ShortLinkGroupRespDTO> list = groupService.listGroup();
        return Results.success(list);
    }

    /**
     * 修改短链接分组名称
     * @param shortLinkGroupUpdateReqDTO
     * @return
     */
    @PutMapping("/api/short-link/admin/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO shortLinkGroupUpdateReqDTO){
        groupService.updateGroup(shortLinkGroupUpdateReqDTO);
        return Results.success();
    }


    /**
     * 删除短链接分组
     */
    @DeleteMapping("/api/short-link/admin/v1/group")
    public Result<Void> deleteGroup(@RequestParam("gid") String gid){
        groupService.deleteGroup(gid);
        return Results.success();
    }

    /**
     * 短链接分组排序
     * @param requestParams
     * @return
     */
    @PostMapping("/api/short-link/admin/v1/group/sort")
    public Result<Void> sortGroup(@RequestBody List<ShortLinkGroupSortReqDTO> requestParams){
        groupService.sortGroup(requestParams);
        return Results.success();
    }
}
