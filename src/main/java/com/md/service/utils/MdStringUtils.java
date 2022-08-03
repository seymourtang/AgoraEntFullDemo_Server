package com.md.service.utils;

import cn.hutool.json.JSONObject;

import java.util.Random;

public class MdStringUtils {


    public static String randomDelete(String str,int num){
        if(str.length() < num){
            return str;
        }
        char[] chars = str.toCharArray();
        String randomStr = "ABCDEFGHIZKLMNOPQRSTUVWXYZ";
        for(int i = 0;i < num;i++){
            Random random = new Random();
            char c = randomStr.charAt((int)(Math.random() * 26));
            chars[random.nextInt(str.length() - 1)] = c;
        }
        return String.valueOf(chars);
    }

    public static String verificationCode(){
        Random random = new Random();
        String result  = String.format("%04d",random.nextInt(9999));
        return result;
    }
}
