package digitalgame.service;

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
    List<AdminInfo> queryAdminInfoByPage();
}
