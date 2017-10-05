package digitalgame.service;

import digitalgame.model.po.UserAccountVo;
import digitalgame.model.po.UserFinanceAccount;

public interface UserFinanceAccountService {
    int insertSelective(UserFinanceAccount record);

    int updateByPrimaryKeySelective(UserAccountVo UserAccountVo);



}
