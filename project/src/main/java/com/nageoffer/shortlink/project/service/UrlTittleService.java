package com.nageoffer.shortlink.project.service;

/**
 * Url标题接口层
 */
public interface UrlTittleService {

    /**
     * 根据url获取网站标题
     * @param url
     * @return
     */
    String getTitleByUrl(String url);
}
