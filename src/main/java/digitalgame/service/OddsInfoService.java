package digitalgame.service;

import digitalgame.model.po.BetInfo;
import digitalgame.model.po.OddsInfo;

import java.util.HashMap;
import java.util.List;

public interface OddsInfoService {

    OddsInfo saveOddsInfo(OddsInfo oddsInfo);

    List<OddsInfo> selectOddsList();

    OddsInfo editOddsInfo(OddsInfo oddsInfo);

    OddsInfo selectOddsInfo(int oddsInfoID);

    int deleteOddsInfo(int oddsInfoID);

    int addOddsInfo(OddsInfo oddsInfo);

    HashMap<String,Double> selectOddsMap();

    List<BetInfo> oddsNumber(int i, int j, int k, List<BetInfo> betInfoList, String resultDate);

    String buildOddsType(int i, int j, int k);
}
