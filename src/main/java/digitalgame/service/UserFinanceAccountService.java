package digitalgame.service;

import digitalgame.model.po.UserAccountVo;
import digitalgame.model.po.UserFinanceAccount;

public interface UserFinanceAccountService {
    int insertSelective(UserFinanceAccount record);

    int updateByPrimaryKeySelective(UserAccountVo UserAccountVo);

    /****
     *
     * @param userId 用户ID
     * @param money  金额
     * @param type   类型 ，2充值，3位扣减
     * @return
     */
    int updateBalanceByUserId(int userId,double money,int type);



}
