package digitalgame.service;

import digitalgame.model.po.UserFinanceAccount;

public interface UsrFinanceAccountService {
    int insertSelective(UserFinanceAccount record);

    int updateByPrimaryKeySelective(UserFinanceAccount record);

}
