package digitalgame.service.impl;

import digitalgame.dao.UserFinanceAccountLogMapper;
import digitalgame.dao.UserFinanceAccountMapper;
import digitalgame.dao.UserInfoMapper;
import digitalgame.model.po.UserAccountVo;
import digitalgame.model.po.UserFinanceAccount;
import digitalgame.model.po.UserFinanceAccountLog;
import digitalgame.model.po.UserInfo;
import digitalgame.service.UserFinanceAccountService;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int updateByPrimaryKeySelective(UserAccountVo userAccountVo) {
        UserFinanceAccount record = userFinanceAccountMapper.selectByPrimaryKey(userAccountVo.getAccountId());
        //余额不足直接返回失败
        if(3 == userAccountVo.getOperType() && userAccountVo.getMoney() > record.getBalance()){
            return  -1;
        }
        if(2 == userAccountVo.getOperType()){
            record.setBalance(record.getBalance() + userAccountVo.getMoney());
        }else if(3 == userAccountVo.getOperType()){
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
        userFinanceAccountLogMapper.insertSelective(userFinanceAccountLog);

        return userFinanceAccountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBalanceByUserId(int userId, double money, int type) {
        UserFinanceAccount ufa = userFinanceAccountMapper.selectByUserId(userId);
        UserAccountVo userAccountVo = new UserAccountVo();
        userAccountVo.setAccountId(ufa.getId());
        userAccountVo.setMoney(money);
        userAccountVo.setOperType(type);
        return this.updateByPrimaryKeySelective(userAccountVo);
    }

    @Override
    public int addUserBalanceByNickName(String nickName, double money) {
        UserInfo userInfo = userInfoMapper.selectByNickName(nickName);
        return this.updateBalanceByUserId(userInfo.getId(),money,2);
    }

    @Override
    public int reduceUserBalanceByNickName(String nickName, double money) {
        UserInfo userInfo = userInfoMapper.selectByNickName(nickName);
        return this.updateBalanceByUserId(userInfo.getId(),money,3);
    }

    @Override
    public UserFinanceAccount queryUserFinanceAccountByNickName(String nickName) {
        UserInfo userInfo = userInfoMapper.selectByNickName(nickName);
        UserFinanceAccount record = userFinanceAccountMapper.selectByUserId(userInfo.getId());
        return record;
    }

}
