package com.easylive.web.controller;

import com.easylive.component.RedisComponent;
import com.easylive.entity.constans.Constans;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.vo.ResponseVO;
import com.easylive.exception.BusinessException;
import com.easylive.service.UserInfoService;
import com.easylive.utils.StringTools;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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

    private final UserInfoService userInfoService;
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


    @PostMapping("/register")
    public ResponseVO register(@NotEmpty @Email @Size(max = 150) String email,
                               @NotEmpty @Size(max = 20) String nickName,
                               @NotEmpty @Pattern(regexp = Constans.REGEX_PASSWORD) String registerPwd,
                               @NotEmpty String checkCodeKey,
                               @NotEmpty String checkCode) {
        try {
            if (!checkCode.equalsIgnoreCase(redisComponent.getCheckCode(checkCodeKey, checkCode))) {
                throw new BusinessException("图片验证码错误");
            }
            //TODO 添加用户
            boolean result = userInfoService.register(email, nickName, registerPwd);
            return getSuccessResponseVO(result);
        } finally {
            //清除图片验证码
            redisComponent.cleanCheckCode(checkCodeKey);
        }
    }

    @PostMapping("/login")
    public ResponseVO login(@NotEmpty @Email @Size(max = 150) String email,
                            @NotEmpty String password,
                            @NotEmpty String checkCodeKey,
                            @NotEmpty String checkCode,
                            HttpServletResponse response,
                            HttpServletRequest request) {

        try {
            if (!checkCode.equalsIgnoreCase(redisComponent.getCheckCode(checkCodeKey, checkCode))) {
                throw new BusinessException("图片验证码错误");
            }
            //TODO 获取ip 时间
            String ip = getIpAddr();
            TokenUserInfoDto login = userInfoService.login(email, password, ip);
            saveToken2Cookie(response, login.getToken());
            // TODO 获取粉丝、硬币、关注的数量
            return getSuccessResponseVO(login);
        } finally {
            //清除图片验证码
            redisComponent.cleanCheckCode(checkCodeKey);
            String token=null;
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(Constans.TOKEN_WEB)) {
                    token = cookie.getValue();
                }
            }
            if(!StringTools.isEmpty(token)){
                redisComponent.cleanTokenInfo(token);
            }
        }

    }

    @PostMapping("/autoLogin")
    public ResponseVO autologin(
            HttpServletResponse response) {
        //TODO 获取ip 时间
        String ip = getIpAddr();
        TokenUserInfoDto tokenUserInfoDtoFromHeader = getTokenUserInfoDtoFromHeader();
        //更新ip与最后登录时间
        userInfoService.updateUserInfo(tokenUserInfoDtoFromHeader, ip);
        if(tokenUserInfoDtoFromHeader.getExpireAt()-System.currentTimeMillis()<Constans.REDIS_KEY_EXPIRES_ONE_DAY){
            //延长token有效期
            redisComponent.saveTokenInfo(tokenUserInfoDtoFromHeader);
        }
        saveToken2Cookie(response,tokenUserInfoDtoFromHeader.getToken());

        // TODO 获取粉丝、硬币、关注的数量
        return getSuccessResponseVO(null);

    }
    @PostMapping("/logout")
    public ResponseVO logout(HttpServletResponse response){
        cleanCookie(response);
        return getSuccessResponseVO(null);
    }


	/*
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(UserInfoQuery query) {
		return getSuccessResponseVO(userInfoService.findListByPage(query));
	}

	*//**
     * 新增
     *//*
	@RequestMapping("add")
	public ResponseVO add(UserInfo bean) {
		userInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	*//**
     * 新增批量
     *//*
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<UserInfo> listBean) {
		userInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	*//**
     * 新增批量或修改
     *//*
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<UserInfo> listBean) {
		userInfoService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	*//**
     * 根据UserId查询
     *//*
	@RequestMapping("selectByUserId")
	public ResponseVO selectByUserId(String userId) {
		return getSuccessResponseVO(userInfoService.selectByUserId(userId));
	}

	*//**
     * 根据UserId更新
     *//*
	@RequestMapping("updateByUserId")
	public ResponseVO updateByUserId(UserInfo bean, String userId) {
		userInfoService.updateByUserId(bean, userId);
		return getSuccessResponseVO(null);
	}

	*//**
     * 根据UserId删除
     *//*
	@RequestMapping("deleteByUserId")
	public ResponseVO deleteByUserId(String userId) {
		userInfoService.deleteByUserId(userId);
		return getSuccessResponseVO(null);
	}

	*//**
     * 根据Email查询
     *//*
	@RequestMapping("selectByEmail")
	public ResponseVO selectByEmail(String email) {
		return getSuccessResponseVO(userInfoService.selectByEmail(email));
	}

	*//**
     * 根据Email更新
     *//*
	@RequestMapping("updateByEmail")
	public ResponseVO updateByEmail(UserInfo bean, String email) {
		userInfoService.updateByEmail(bean, email);
		return getSuccessResponseVO(null);
	}

	*//**
     * 根据Email删除
     *//*
	@RequestMapping("deleteByEmail")
	public ResponseVO deleteByEmail(String email) {
		userInfoService.deleteByEmail(email);
		return getSuccessResponseVO(null);
	}

	*//**
     * 根据NickName查询
     *//*
	@RequestMapping("selectByNickName")
	public ResponseVO selectByNickName(String nickName) {
		return getSuccessResponseVO(userInfoService.selectByNickName(nickName));
	}

	*//**
     * 根据NickName更新
     *//*
	@RequestMapping("updateByNickName")
	public ResponseVO updateByNickName(UserInfo bean, String nickName) {
		userInfoService.updateByNickName(bean, nickName);
		return getSuccessResponseVO(null);
	}

	*//**
     * 根据NickName删除
     *//*
	@RequestMapping("deleteByNickName")
	public ResponseVO deleteByNickName(String nickName) {
		userInfoService.deleteByNickName(nickName);
		return getSuccessResponseVO(null);
	}
	*/

}