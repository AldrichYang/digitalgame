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

    /****
     * 根据昵称增余额
     * @param nickName
     * @param money
     * @return
     */
    int addUserBalanceByNickName(String nickName,double money);

    /****
     * 根据昵称减余额，返回-1则是扣钱失败，余额不足
     * @param nickName
     * @param money
     * @return
     */
    int reduceUserBalanceByNickName(String nickName,double money);


    /****
     * 通过昵称获取用户信息
     * @param nickName
     * @return 对象里面的balance就是余额
     */
    UserFinanceAccount queryUserFinanceAccountByNickName(String nickName);

}
