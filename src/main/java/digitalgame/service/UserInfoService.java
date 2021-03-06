package digitalgame.service;

import digitalgame.model.po.UserAccountVo;
import digitalgame.model.po.UserInfo;

import java.util.List;

/**
 * Created by yh on 17/9/29.
 */
public interface UserInfoService {

     UserInfo saveUser(UserInfo user);

     /****
      * 增加用户信息
      * @param user
      * @return
      */
     UserInfo editUser(UserInfo user);

     /****
      * 分页查询
      * @return
      */
     List<UserInfo> selectByPage(int cueerntPage,UserInfo userInfo);

     UserInfo  selectByPrimaryKey(Integer id);

     List<UserAccountVo> selectUserAccountByPage(int cueerntPage,UserInfo userInfo);

}
