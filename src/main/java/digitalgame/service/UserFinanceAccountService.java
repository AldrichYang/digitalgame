package digitalgame.service;

import digitalgame.model.po.*;

import java.util.List;

public interface UserFinanceAccountService {
    int insertSelective(UserFinanceAccount record);

    int updateByPrimaryKeySelective(UserAccountVo UserAccountVo,AccountParam accountParam);

    /****
     *
     * @param userId 用户ID
     * @param money  金额
     * @param type   类型 ，2充值，3位扣减
     * @return
     */
    int updateBalanceByUserId(int userId,double money,int type,AccountParam accountParam);

    /****
     * 根据昵称增余额
     * @param nickName
     * @param money
     * @return
     */
    int addUserBalanceByNickName(String nickName,double money,AccountParam accountParam);

    /****
     * 根据昵称减余额，返回-1则是扣钱失败，余额不足
     * @param nickName
     * @param money
     * @return
     */
    int reduceUserBalanceByNickName(String nickName,double money,AccountParam accountParam);


    /****
     * 通过昵称获取用户信息
     * @param nickName
     * @return 对象里面的balance就是余额
     */
    UserFinanceAccount queryUserFinanceAccountByNickName(String nickName);

    /***
     * 根据用户名和用户昵称获取用户账户的历史记录
     * @param userInfo
     * @return
     */
    public List<UserAccountHisVo> queryUserAccountHisVoByUserInfo(int currentPage,UserInfo userInfo);

    /***
     * 撤销返奖金额
     * @param nickName
     * @param money
     * @param accountParam
     * @return
     */
    public int revocationUserBalanceByNickName(String nickName, double money,AccountParam accountParam);

}
