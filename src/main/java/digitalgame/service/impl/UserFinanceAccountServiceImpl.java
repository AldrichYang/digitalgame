package digitalgame.service.impl;

import com.google.common.base.Strings;
import digitalgame.common.Util;
import digitalgame.dao.UserFinanceAccountLogMapper;
import digitalgame.dao.UserFinanceAccountMapper;
import digitalgame.dao.UserInfoMapper;
import digitalgame.model.po.*;
import digitalgame.service.UserFinanceAccountService;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserFinanceAccountServiceImpl implements UserFinanceAccountService {

    @Autowired
    UserFinanceAccountMapper userFinanceAccountMapper;

    @Autowired
    private UserFinanceAccountLogMapper userFinanceAccountLogMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int insertSelective(UserFinanceAccount record) {
        return userFinanceAccountMapper.insertSelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(UserAccountVo userAccountVo ,int periods) {
        UserFinanceAccount record = userFinanceAccountMapper.selectByPrimaryKey(userAccountVo.getAccountId());
        //余额不足直接返回失败
        if((3 == userAccountVo.getOperType() || 5 == userAccountVo.getOperType()) && userAccountVo.getMoney() > record.getBalance()){
            return  -1;
        }
        if(2 == userAccountVo.getOperType() || 4 == userAccountVo.getOperType()){
            record.setBalance(record.getBalance() + userAccountVo.getMoney());
        }else if(3 == userAccountVo.getOperType() || 5 == userAccountVo.getOperType()){
            record.setBalance(record.getBalance() - userAccountVo.getMoney());
        }else{
          //TODO throw Exception
        }
        UserFinanceAccountLog userFinanceAccountLog = new UserFinanceAccountLog();
        userFinanceAccountMapper.updateByPrimaryKeySelective(record);
        userFinanceAccountLog.setOperType(userAccountVo.getOperType());
        userFinanceAccountLog.setMoney(userAccountVo.getMoney());
        userFinanceAccountLog.setUfcId(record.getId());
        userFinanceAccountLog.setBalance(record.getBalance());
        userFinanceAccountLog.setCreateTime(Util.dataForMat(new Date(),"yyyyMMdd"));
        userFinanceAccountLogMapper.insertSelective(userFinanceAccountLog);

        return userFinanceAccountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBalanceByUserId(int userId, double money, int type,int periods) {
        UserFinanceAccount ufa = userFinanceAccountMapper.selectByUserId(userId);
        UserAccountVo userAccountVo = new UserAccountVo();
        userAccountVo.setAccountId(ufa.getId());
        userAccountVo.setMoney(money);
        userAccountVo.setOperType(type);
        return this.updateByPrimaryKeySelective(userAccountVo,periods);
    }

    @Override
    public int addUserBalanceByNickName(String nickName, double money,int periods) {
        UserInfo userInfo = userInfoMapper.selectByNickName(nickName);
        return this.updateBalanceByUserId(userInfo.getId(),money,4,periods);
    }

    @Override
    public int reduceUserBalanceByNickName(String nickName, double money,int periods) {
        UserInfo userInfo = userInfoMapper.selectByNickName(nickName);
        return this.updateBalanceByUserId(userInfo.getId(),money,5,periods);
    }

    @Override
    public UserFinanceAccount queryUserFinanceAccountByNickName(String nickName) {
        UserInfo userInfo = userInfoMapper.selectByNickName(nickName);
        UserFinanceAccount record = userFinanceAccountMapper.selectByUserId(userInfo.getId());
        return record;
    }

    @Override
    public List<UserAccountHisVo> queryUserAccountHisVoByUserInfo(int currentPage,UserInfo userInfo){

        String whereCond = " ";
        if(!Strings.isNullOrEmpty(userInfo.getUserName())){
            whereCond +=  " and  ui.user_name like '%"+userInfo.getUserName()+"%'";
        }
        if(!Strings.isNullOrEmpty(userInfo.getNickName())){
            whereCond +=  " and ui.nick_name like '%"+userInfo.getNickName()+"%'";
        }
        if(currentPage != 0){
            whereCond += "limit " +((currentPage -1) *10) +","+(currentPage) * 10;
        }
        return userFinanceAccountLogMapper.queryUserAccountHisVoByUserInfo(whereCond);
    }



}
