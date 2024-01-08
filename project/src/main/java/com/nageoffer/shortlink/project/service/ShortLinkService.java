package com.nageoffer.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nageoffer.shortlink.project.dao.entity.ShortLinkDO;
import com.nageoffer.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.nageoffer.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.nageoffer.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.nageoffer.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.nageoffer.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.nageoffer.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * 短链接接口层
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    //创建短链接
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    /**
     * 修改短链接
      * @param requestParam
     */
    void updateShortLink(ShortLinkUpdateReqDTO requestParam);


    /**
     * 分页查询短链接
     * @param requestParam
     * @return
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     * 查询短链接分组内的数量
     * @param requestParam
     * @return
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);

    /**
     * 短链接跳转
     * @param shortUri 短链接后缀
     * @param request
     * @param response
     * @return
     */
    void resoreUrl(String shortUri, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
