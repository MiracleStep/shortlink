package com.nageoffer.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * 回收站短链接分页请求参数
 */
@Data
public class ShortLinkRecycleBinPageReqDTO extends Page {
    //继承Page去接收请求参数current当前页和size每页显示个数。

    /**
     * 分组标识
     */
    private List<String> gidList;
}
