package digitalgame.service.impl;

import digitalgame.model.po.AdminInfo;
import digitalgame.service.AdminInfoService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public class AdminInfoServiceImpl implements AdminInfoService {
    @Override
    public int saveAdminInfo(AdminInfo adminInfo) {
        return 0;
    }

    @Override
    public AdminInfo editAdminInfo(AdminInfo adminInfo) {
        return null;
    }

    @Override
    public List<AdminInfo> queryAdminInfoByPage() {
        return null;
    }
}
