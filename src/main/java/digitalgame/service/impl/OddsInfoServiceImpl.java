package digitalgame.service.impl;

import digitalgame.dao.OddsInfoMapper;
import digitalgame.model.po.OddsInfo;
import digitalgame.service.OddsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OddsInfoServiceImpl implements OddsInfoService{

    @Autowired
    private OddsInfoMapper oddsInfoMapper;

    @Override
    public OddsInfo saveOddsInfo(OddsInfo oddsInfo) {
        oddsInfo.setCreateTime(new Date().toString());
        oddsInfo.setUpdateTime(oddsInfo.getCreateTime());
        oddsInfoMapper.insert(oddsInfo);
        return oddsInfo;
    }

    @Override
    public List<OddsInfo> selectOddsList() {
        return oddsInfoMapper.selectOddsList();
    }
}
