package com.easylive.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.easylive.component.RedisComponent;
import com.easylive.entity.constans.Constans;
import com.easylive.entity.dto.TokenUserInfoDto;
import com.easylive.entity.po.UserInfo;
import com.easylive.entity.query.UserInfoQuery;
import com.easylive.enums.UserSexEnum;
import com.easylive.enums.UserStatusEnum;
import com.easylive.exception.BusinessException;
import com.easylive.mappers.UserInfoMapper;
import com.easylive.service.UserInfoService;
import com.easylive.utils.CopyTools;
import com.easylive.utils.StringTools;
import org.springframework.stereotype.Service;
import com.easylive.entity.query.SimplePage;
import com.easylive.entity.vo.PaginationResultVO;

/**
 * 用户信息ServiceImpl
 *
 * @auther: ccy
 * @date: 2025-07-05 00:19
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper<UserInfo, UserInfoQuery> userInfoMapper;
    @Resource
    private RedisComponent redisComponent;

    /**
     * 根据条件查询列表
     */
    public List<UserInfo> findListByParam(UserInfoQuery query) {
        return this.userInfoMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */
    public Integer findCountByParam(UserInfoQuery query) {
        return this.userInfoMapper.selectCount(query);
    }

    /**
     * 分页查询
     */
    public PaginationResultVO<UserInfo> findListByPage(UserInfoQuery query) {
        Integer count = this.findCountByParam(query);
        SimplePage page = new SimplePage(query.getPageNo(), query.getPageSize(), count);
        query.setSimplePage(page);
        List<UserInfo> list = this.findListByParam(query);
        PaginationResultVO<UserInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getCountPage(), list);
        return result;
    }

    /**
     * 新增
     */
    public Integer add(UserInfo bean) {
        return this.userInfoMapper.insert(bean);
    }

    /**
     * 新增批量
     */
    public Integer addBatch(List<UserInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.userInfoMapper.insertBatch(listBean);
    }

    /**
     * 新增批量或修改
     */
    public Integer addOrUpdateBatch(List<UserInfo> listBean) {
        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.userInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 根据UserId查询
     */
    public UserInfo selectByUserId(String userId) {
        return this.userInfoMapper.selectByUserId(userId);
    }

    /**
     * 根据UserId更新
     */
    public Integer updateByUserId(UserInfo bean, String userId) {
        return this.userInfoMapper.updateByUserId(bean, userId);
    }

    /**
     * 根据UserId删除
     */
    public Integer deleteByUserId(String userId) {
        return this.userInfoMapper.deleteByUserId(userId);
    }

    /**
     * 根据Email查询
     */
    public UserInfo selectByEmail(String email) {
        return this.userInfoMapper.selectByEmail(email);
    }

    /**
     * 根据Email更新
     */
    public Integer updateByEmail(UserInfo bean, String email) {
        return this.userInfoMapper.updateByEmail(bean, email);
    }

    /**
     * 根据Email删除
     */
    public Integer deleteByEmail(String email) {
        return this.userInfoMapper.deleteByEmail(email);
    }

    /**
     * 根据NickName查询
     */
    public UserInfo selectByNickName(String nickName) {
        return this.userInfoMapper.selectByNickName(nickName);
    }

    /**
     * 根据NickName更新
     */
    public Integer updateByNickName(UserInfo bean, String nickName) {
        return this.userInfoMapper.updateByNickName(bean, nickName);
    }

    /**
     * 根据NickName删除
     */
    public Integer deleteByNickName(String nickName) {
        return this.userInfoMapper.deleteByNickName(nickName);
    }

    @Override
    public boolean register(String email, String nickName, String registerPwd) {
        if (this.userInfoMapper.selectByEmail(email) != null) {
            throw new BusinessException("邮箱已注册");
        }
        if (this.userInfoMapper.selectByNickName(nickName) != null) {
            throw new BusinessException("昵称已注册");
        }
        UserInfo userInfo = new UserInfo();
        String userid = StringTools.getRandomNumber(Constans.USERID_RANDOM_LENGTH);
        userInfo.setUserId(userid);
        userInfo.setEmail(email);
        userInfo.setNickName(nickName);
        userInfo.setPassword(StringTools.encodeByMD5(registerPwd));
        userInfo.setJoinTime(new Date());
        userInfo.setStatus(UserStatusEnum.ENABLE.getStatus());
        //默认保密
        userInfo.setSex(UserSexEnum.SECRECY.getSexCode());
        userInfo.setTheme(Constans.DEFAULT_THEME);
        // TODO 初始化用户硬币
        userInfo.setCurrentCoinCount(10);
        userInfo.setTotalCoinCount(10);
        this.add(userInfo);
        return true;
    }

    @Override
    public TokenUserInfoDto login(String email, String loginPwd, String ip) {
        UserInfo userInfo = this.userInfoMapper.selectByEmail(email);
        System.out.println(loginPwd);
        System.out.println(userInfo.getPassword());
        if (userInfo == null || !loginPwd.equals(userInfo.getPassword())) {
            throw new BusinessException("账号或密码错误");
        }
        if (UserStatusEnum.DISABLE.getStatus().equals(userInfo.getStatus())) {
            throw new BusinessException("账号已被禁用");
        }
        //登录成功
        //TODO 更新IP与登陆时间
        UserInfo updateinfo = new UserInfo();
        updateinfo.setLastLoginTime(new Date());
        updateinfo.setLastLoginIp(ip);
        //更新信息
        this.userInfoMapper.updateByUserId(updateinfo, userInfo.getUserId());
        //处理token
        TokenUserInfoDto tokenUserInfoDto = CopyTools.copy(userInfo, TokenUserInfoDto.class);
        // TODO 设置粉丝数 关注数 硬币数 等
        //
        //存入redis
        try {
            redisComponent.saveTokenInfo(tokenUserInfoDto);
        } catch (Exception e) {
            System.out.println(e);
        }
        return tokenUserInfoDto;
    }

    @Override
    public void updateUserInfo(TokenUserInfoDto tokenUserInfoDtoFromHeader, String ip) {
        if(null==tokenUserInfoDtoFromHeader){
            throw new BusinessException("传递信息失效");
        }
        UserInfo updateinfo = new UserInfo();
        updateinfo.setLastLoginTime(new Date());
        updateinfo.setLastLoginIp(ip);
        //更新信息
        this.userInfoMapper.updateByUserId(updateinfo, tokenUserInfoDtoFromHeader.getUserId());
    }
}