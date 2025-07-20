package com.easylive.admin.controller;

import com.easylive.component.RedisComponent;
import com.easylive.config.Appconfig;
import com.easylive.entity.constans.Constans;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.exception.BusinessException;
import com.easylive.utils.StringTools;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashMap;

/**
 * 用户信息Controller
 *
 * @auther: ccy
 * @date: 2025-07-05 00:19
 */
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Validated
public class AccountController extends BaseController {

    private final Appconfig appconfig;
    private final RedisComponent redisComponent;

    @RequestMapping("/checkCode")
    public ResponseVO checkCode() {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100, 42);
        String code = captcha.text();
        String checkCode = redisComponent.saveCheckCode(code);
        String checkCodeBese64 = captcha.toBase64();
        HashMap<String, String> map = new HashMap<>();
        map.put("checkCodeKey", checkCode);
        map.put("checkCode", checkCodeBese64);
        return getSuccessResponseVO(map);
    }

    @PostMapping("/login")
    public ResponseVO login(@NotEmpty @Size(max = 150) String account,
                            @NotEmpty String password,
                            @NotEmpty String checkCodeKey,
                            @NotEmpty String checkCode,
                            HttpServletResponse response,
                            HttpServletRequest request) {
        try {
            if (!checkCode.equalsIgnoreCase(redisComponent.getCheckCode(checkCodeKey, checkCode))) {
                throw new BusinessException("图片验证码错误");
            }
            if (null==appconfig
                    ||!appconfig.getAdminAcount().equals(account)
                    ||!StringTools.encodeByMD5(appconfig.getAdminPassword()).equals(password)) {
                throw new BusinessException("账号或密码错误");
            }
            String token = redisComponent.saveTokenInfo4Admin(account);
            saveToken2Cookie(response, token);
            // TODO 获取粉丝、硬币、关注的数量
            return getSuccessResponseVO(account);
        } finally {
            //清除图片验证码
            redisComponent.cleanCheckCode(checkCodeKey);
            Cookie[] cookies = request.getCookies();
            if(cookies!=null){
                String token=null;
                for(Cookie cookie: cookies){
                    if(cookie.getName().equals(Constans.TOKEN_ADMIN)){
                        redisComponent.cleanTokenInfo(cookie.getValue());
                        token=cookie.getValue();
                    }
                }
                if(!StringTools.isEmpty(token)){
                    redisComponent.cleanTokenInfo4Admin(token);
                }
            }
        }
    }

    @PostMapping("/logout")
    public ResponseVO logout(HttpServletResponse response){
        cleanCookie(response);
        return getSuccessResponseVO(null);
    }

}