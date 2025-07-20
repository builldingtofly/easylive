package com.easylive.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.digester.Digester;
import org.springframework.util.DigestUtils;

public class StringTools {
    public final static String getRandomString(int length) {
        return RandomStringUtils.random(length,true,true);
    }
    public final static String getRandomNumber(int length) {
        return RandomStringUtils.random(length,false,true);
    }
    /*
    * 对密码进行加密
    * */
    public final static String encodeByMD5(String originstr) {
        return isEmpty(originstr)?null: DigestUtils.md5DigestAsHex(originstr.getBytes());
    }
    public final static boolean isEmpty(String str) {
        if (null==str ||"".equals(str)||"null".equals(str)||"\u0000".equals(str)) {
            return true;
        } else if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }
    public final static boolean pathIsOk(String path) {
        if(StringTools.isEmpty(path)){
            return false;
        }
        if(path.contains("../")||path.contains("..\\")||path.contains("/../")||path.contains("\\..\\")||path.contains("..\\")){
            return false;
        }
        return true;
    }
    public final static String getFileSuffix(String fileName) {
        if(StringTools.isEmpty(fileName)||!fileName.contains(".")){
          return null;
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
