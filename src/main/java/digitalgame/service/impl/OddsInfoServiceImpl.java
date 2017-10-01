package digitalgame.service.impl;

import digitalgame.dao.OddsInfoMapper;
import digitalgame.model.po.OddsInfo;
import digitalgame.service.OddsInfoService;

import java.util.Date;

public class OddsInfoServiceImpl implements OddsInfoService{

    private OddsInfoMapper oddsInfoMapper;
    @Override
    public OddsInfo saveOddsInfo(OddsInfo oddsInfo) {
        oddsInfo.setCreateTime(new Date().toString());
        oddsInfo.setUpdateTime(oddsInfo.getCreateTime());
        oddsInfoMapper.insert(oddsInfo);
        return oddsInfo;
    }
}
