package com.nageoffer.shortlink.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum VailDateTypeEnum {
    /**
     * 永久有效
     */
    PERMANENT(0),
    /**
     * 自定义
     */
    CUSTOM(1);

    @Getter
    private final int type;
}
