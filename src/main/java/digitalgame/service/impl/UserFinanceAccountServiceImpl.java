package digitalgame.service.impl;

import digitalgame.dao.UserFinanceAccountLogMapper;
import digitalgame.dao.UserFinanceAccountMapper;
import digitalgame.model.po.UserAccountVo;
import digitalgame.model.po.UserFinanceAccount;
import digitalgame.model.po.UserFinanceAccountLog;
import digitalgame.service.UserFinanceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFinanceAccountServiceImpl implements UserFinanceAccountService {

    @Autowired
    UserFinanceAccountMapper userFinanceAccountMapper;

    @Autowired
    private UserFinanceAccountLogMapper userFinanceAccountLogMapper;

    @Override
    public int insertSelective(UserFinanceAccount record) {
        return userFinanceAccountMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(UserAccountVo userAccountVo) {
        UserFinanceAccount record = userFinanceAccountMapper.selectByPrimaryKey(userAccountVo.getAccountId());

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
        userFinanceAccountLogMapper.insertSelective(userFinanceAccountLog);

        return userFinanceAccountMapper.updateByPrimaryKeySelective(record);
    }

}
