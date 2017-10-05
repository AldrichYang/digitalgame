package digitalgame.service;

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
     List<UserInfo> selectByPage(int cueerntPage,int size,UserInfo userInfo);

     UserInfo  selectByPrimaryKey(Integer id);
}
