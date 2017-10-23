package digitalgame.service.impl;

import digitalgame.dao.OddsInfoMapper;
import digitalgame.model.po.OddsInfo;
import digitalgame.service.OddsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class OddsInfoServiceImpl implements OddsInfoService{

    @Autowired
    private OddsInfoMapper oddsInfoMapper;

    @Override
    public OddsInfo saveOddsInfo(OddsInfo oddsInfo) {
        oddsInfo.setCreateTime(new Date().toString());
        oddsInfoMapper.insert(oddsInfo);
        return oddsInfo;
    }

    @Override
    public List<OddsInfo> selectOddsList() {
        return oddsInfoMapper.selectOddsList();
    }

    @Override
    public OddsInfo editOddsInfo(OddsInfo oddsInfo) {

        oddsInfoMapper.updateByPrimaryKeySelective(oddsInfo);
        return oddsInfo;
    }

    @Override
    public OddsInfo selectOddsInfo(int oddsInfoID) {
        return oddsInfoMapper.selectByPrimaryKey(oddsInfoID);
    }

    @Override
    public int deleteOddsInfo(int oddsInfoID) {
        return oddsInfoMapper.deleteByPrimaryKey(oddsInfoID);
    }

    @Override
    public int addOddsInfo(OddsInfo oddsInfo) {
        return oddsInfoMapper.insert(oddsInfo);
    }

    @Override
    public HashMap<String, Integer> selectOddsMap() {
        HashMap<String, Integer> oddsMap = new HashMap();
        List<OddsInfo> oddsList = oddsInfoMapper.selectOddsList();

        if(oddsList != null && oddsList.size() > 0){
            for(OddsInfo oddsInfo : oddsList){
                oddsMap.put(oddsInfo.getOddsName(),oddsInfo.getOddsNumber());
            }
        }
        return oddsMap;
    }
}
