package com.nageoffer.shortlink.project.controller;

import com.nageoffer.shortlink.project.common.convention.result.Result;
import com.nageoffer.shortlink.project.common.convention.result.Results;
import com.nageoffer.shortlink.project.service.UrlTittleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * URL标题控制层
 */
@RestController
@RequiredArgsConstructor
public class UrlTittleController {
    private final UrlTittleService urlTittleService;

    /**
     * 根据url获取对应的网站标题
     * @param url
     * @return
     */
    @GetMapping("/api/short-link/title")
    public Result<String> getTitleByUrl(@RequestParam("url") String url) {
        return Results.success(urlTittleService.getTitleByUrl(url));
    }
}
