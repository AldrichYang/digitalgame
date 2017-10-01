package digitalgame.service;

import digitalgame.model.po.AdminInfo;

public interface AdminInfoService {

    /***
     * 增加admin信息
     * @param adminInfo
     * @return
     */
    int saveAdminInfo(AdminInfo adminInfo);
}
