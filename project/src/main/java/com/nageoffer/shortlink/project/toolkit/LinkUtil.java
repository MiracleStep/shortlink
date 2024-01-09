package com.nageoffer.shortlink.project.toolkit;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.Optional;

import static com.nageoffer.shortlink.project.common.constant.ShortLinkConstant.DEFAULT_CACHE_VALID_TIME;

/**
 * 短链接g工具类
 */
public class LinkUtil {

    /**
     * 获取短链接有效期时间工具方法
     * @param validDate
     * @return
     */
    public static long getLinkCacheValidDate(Date validDate) {
        //如果不为空就判断两个日期相差的时长（得到毫秒差值），否则设置默认值一个月。
        return Optional.ofNullable(validDate)
                .map(each -> DateUtil.between(new Date(), each, DateUnit.MS))
                .orElse(DEFAULT_CACHE_VALID_TIME);
    }
}
