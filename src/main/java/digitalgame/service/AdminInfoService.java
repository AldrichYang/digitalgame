package digitalgame.service;

import digitalgame.model.po.AdminInfo;

import java.util.List;

public interface AdminInfoService {

    /***
     * 增加admin信息
     * @param adminInfo
     * @return
     */
    int saveAdminInfo(AdminInfo adminInfo);

    /***
     * 修改admin信息
     * @param adminInfo
     * @return
     */
    AdminInfo editAdminInfo(AdminInfo adminInfo);

    /***
     * 分页查询admin信息
     * @return
     */
    List<AdminInfo> queryAdminInfoByPage(AdminInfo adminInfo,int pageNo);

    AdminInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminInfo record);
}
