package digitalgame.service.impl;

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
    public List<UserInfo> selectByPage(int pageNo,int size) {
        return userInfoMapper.selectByPage(pageNo,size);
    }
}
