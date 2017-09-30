package digitalgame.service.impl;

import digitalgame.dao.UserInfoMapper;
import digitalgame.model.po.UserInfo;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
