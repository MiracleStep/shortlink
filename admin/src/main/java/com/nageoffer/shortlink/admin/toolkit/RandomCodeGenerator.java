package com.nageoffer.shortlink.admin.toolkit;

import java.security.SecureRandom;

/**
 * 分组ID随机生成器
 */
public class RandomCodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 6;

    /**
     * 生成随机分组ID
     * @return
     */
    public static String generateRandomCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }

    public static void main(String[] args) {
        String randomCode = generateRandomCode();
        System.out.println("Random Code: " + randomCode);
    }
}
