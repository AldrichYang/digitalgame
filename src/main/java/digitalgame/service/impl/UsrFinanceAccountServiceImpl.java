package digitalgame.service.impl;

import digitalgame.dao.UserFinanceAccountMapper;
import digitalgame.model.po.UserFinanceAccount;
import digitalgame.service.UsrFinanceAccountService;

public class UsrFinanceAccountServiceImpl implements UsrFinanceAccountService {

    UserFinanceAccountMapper userFinanceAccountMapper;
    @Override
    public int insertSelective(UserFinanceAccount record) {
        return userFinanceAccountMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(UserFinanceAccount record) {
        return userFinanceAccountMapper.updateByPrimaryKeySelective(record);
    }
}
