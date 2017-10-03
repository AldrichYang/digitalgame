package digitalgame.service.impl;

import com.google.common.base.Strings;
import digitalgame.dao.UserInfoMapper;
import digitalgame.model.po.UserAccountVo;
import digitalgame.model.po.UserInfo;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by yh on 17/9/29.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo saveUser(UserInfo user) {
        user.setIsEnable(1);
        user.setCreateTime(new Date().toString());
        user.setUpdateTime(user.getCreateTime());
        userInfoMapper.insert(user);
        return user;
    }

    @Override
    public UserInfo editUser(UserInfo user) {
        userInfoMapper.updateByPrimaryKeySelective(user);
        return user;
    }

    @Override
    public List<UserInfo> selectByPage(int pageNo,int size,UserInfo userInfo) {
        String whereCond = " where 1=1 ";
        if(!Strings.isNullOrEmpty(userInfo.getUserName())){
            whereCond +=  " and  user_name like '%"+userInfo.getUserName()+"%'";
        }
        if(!Strings.isNullOrEmpty(userInfo.getNickName())){
            whereCond +=  " and nick_name like '%"+userInfo.getNickName()+"%'";
        }
        return userInfoMapper.selectByPage(whereCond);
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return  userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserAccountVo> selectUserAccountByPage(int cueerntPage, int size, UserInfo userInfo) {
        String whereCond = "  ";
        if(!Strings.isNullOrEmpty(userInfo.getUserName())){
            whereCond +=  " and  u.user_name like '%"+userInfo.getUserName()+"%'";
        }
        if(!Strings.isNullOrEmpty(userInfo.getNickName())){
            whereCond +=  " and u.nick_name like '%"+userInfo.getNickName()+"%'";
        }
        return userInfoMapper.selectUserAccountByPage(whereCond)   ;
    }
}
