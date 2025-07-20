package com.easylive.entity.constans;

public class Constans {

    public static final String REDIS_KEY_PREFIX = "easylive:";
    public static final String REDIS_KEY_CHECK_CODE =REDIS_KEY_PREFIX+ "checkcode:";
    public static final Integer REDIS_KEY_EXPIRES_ONE_MIN= 60*1000;
    public static final Integer REDIS_KEY_EXPIRES_ONE_HOUR= 60*60*1000;
    public static final Integer REDIS_KEY_EXPIRES_ONE_DAY= 60*60*24*1000;
    public static final String REDIS_KEY_TOKEN_WEB = REDIS_KEY_PREFIX+"token:web:";
    public static final String REDIS_KEY_TOKEN_ADMIN = REDIS_KEY_PREFIX+"token:admin:";
    public static final String REDIS_KEY_CATEGORY_LIST =REDIS_KEY_PREFIX+"category:list";
    public static final String REDIS_KEY_UPLOADING_FILE =REDIS_KEY_PREFIX+"uploading:";
    public static final String REDIS_KEY_SYS_SETTING =REDIS_KEY_PREFIX+"sysSetting:";

    public static final String REGEX_PASSWORD= "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
    public static final String REGEX_PHONE= "^1[3456789]\\d{9}$";

    public static final String TOKEN_WEB = "token";
    public static final String TOKEN_ADMIN = "adminToken";

    public static final Integer USERID_RANDOM_LENGTH = 10;
    public static final Integer MB_SIZE = 1024*1024;
    public static final Integer LENGTH_10 = 10;
    public static final Integer LENGTH_15 = 15;
    public static final Integer LENGTH_30 = 30;
    public static final Integer DEFAULT_THEME = 1;

    public static final Integer COOKIE_SESSION=-1;
    public static final Integer ZERO = 0;
    public static final Integer TIME_SECONDS_DAY =  (60*60*24);

    public static final String FILE_FOLDER = "/file";
    public static final String FILE_FOLDER_TEMP = "/temp";
    public static final String FILE_FOLDER_CONVER = "/conver";
    public static final String FILE_FOLDER_VIDEO = "/vider";

    public static final Object IMAGE_THUMBNAIL_SUFFIX = "_thumbnail.jpg";
}
