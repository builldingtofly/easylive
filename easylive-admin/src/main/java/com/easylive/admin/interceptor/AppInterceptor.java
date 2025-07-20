package com.easylive.admin.interceptor;

import com.easylive.component.RedisComponent;
import com.easylive.entity.constans.Constans;
import com.easylive.enums.ResponseCodeEnum;
import com.easylive.exception.BusinessException;
import com.easylive.utils.StringTools;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class AppInterceptor implements HandlerInterceptor {
    private static final String URL_ACCOUNT="/account";
    private static final String URL_FILE="/file";
    @Autowired
    private  RedisComponent redisComponent;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        if(handler instanceof HandlerMethod){
           return true;
       }
       //对前缀有account的请求放行。
        if(request.getRequestURI().contains(URL_ACCOUNT)){
            return true;
        }
        String token=request.getHeader(Constans.TOKEN_ADMIN);
        if(request.getRequestURI().contains(URL_FILE)){
            token = getTokenFromCookie(request);
        }
        if(StringTools.isEmpty(token)){
            throw new BusinessException(ResponseCodeEnum.CODE_901);
        }
        redisComponent.cleanTokenInfo(token);
        return false;
    }
    private String getTokenFromCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals(Constans.TOKEN_ADMIN)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
