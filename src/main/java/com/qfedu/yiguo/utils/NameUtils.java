package com.qfedu.yiguo.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;


public class NameUtils {
    public static String creatName() {
        StringBuilder ret = new StringBuilder();

        //自动生成名字（中文）
        for (int i = 0; i < 3; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret.append(str);
        }
        return ret.toString();
    }
}

