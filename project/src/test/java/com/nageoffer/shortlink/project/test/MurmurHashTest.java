package com.nageoffer.shortlink.project.test;

import cn.hutool.core.lang.hash.MurmurHash;

/**
 * @author Mirac
 * @date 8/8/2024
 */
public class MurmurHashTest {

    public static void main(String[] args) {
        String str = "https://www.baidu.com/";
        int hash = MurmurHash.hash32(str);
        System.out.println(hash);
    }
}
