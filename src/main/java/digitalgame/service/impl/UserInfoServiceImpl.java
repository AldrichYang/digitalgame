package digitalgame.service.impl;

import com.google.common.base.Strings;
import digitalgame.dao.UserFinanceAccountLogMapper;
import digitalgame.dao.UserFinanceAccountMapper;
import digitalgame.dao.UserInfoMapper;
import digitalgame.model.po.UserAccountVo;
import digitalgame.model.po.UserFinanceAccount;
import digitalgame.model.po.UserFinanceAccountLog;
import digitalgame.model.po.UserInfo;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by yh on 17/9/29.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserFinanceAccountMapper userFinanceAccountMapper;

    @Autowired
    private UserFinanceAccountLogMapper userFinanceAccountLogMapper;


    @Override
    @Transactional
    public UserInfo saveUser(UserInfo user) {
        UserFinanceAccount userFinanceAccount = new UserFinanceAccount();
        UserFinanceAccountLog userFinanceAccountLog= new UserFinanceAccountLog();
        user.setIsEnable(1);
        user.setCreateTime(new Date().toString());
        user.setUpdateTime(user.getCreateTime());
        userInfoMapper.insert(user);
        userFinanceAccount.setUserId(user.getId());
        userFinanceAccountMapper.insertSelective(userFinanceAccount);
        userFinanceAccountLog.setMoney(0.0);
        userFinanceAccountLog.setOperType(1);
        userFinanceAccountLog.setBalance(0.0);
        userFinanceAccountLog.setUfcId(userFinanceAccount.getId());
        userFinanceAccountLogMapper.insertSelective(userFinanceAccountLog);
        return user;
    }

    @Override
    public UserInfo editUser(UserInfo user) {
        userInfoMapper.updateByPrimaryKeySelective(user);
        return user;
    }

    @Override
    public List<UserInfo> selectByPage(int pageNo,UserInfo userInfo) {
        String whereCond = " where 1=1 ";
        if(!Strings.isNullOrEmpty(userInfo.getUserName())){
            whereCond +=  " and  user_name like '%"+userInfo.getUserName()+"%'";
        }
        if(!Strings.isNullOrEmpty(userInfo.getNickName())){
            whereCond +=  " and nick_name like '%"+userInfo.getNickName()+"%'";
        }
        if(pageNo != 0){
            whereCond += "limit " +((pageNo -1) *10) +","+(pageNo) * 10;
        }
        return userInfoMapper.selectByPage(whereCond);
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return  userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserAccountVo> selectUserAccountByPage(int cueerntPage, UserInfo userInfo) {
        String whereCond = "  ";
        if(!Strings.isNullOrEmpty(userInfo.getUserName())){
            whereCond +=  " and  u.user_name like '%"+userInfo.getUserName()+"%'";
        }
        if(!Strings.isNullOrEmpty(userInfo.getNickName())){
            whereCond +=  " and u.nick_name like '%"+userInfo.getNickName()+"%'";
        }
        if(userInfo.getId() != null && userInfo.getId() != 0L){
            whereCond +=  " and u.id = "+userInfo.getId();
        }
        if(cueerntPage != 0){
            whereCond += "limit " +((cueerntPage -1) *10) +","+(cueerntPage) * 10;
        }
        return userInfoMapper.selectUserAccountByPage(whereCond)   ;
    }
}
