package digitalgame.service;

import digitalgame.model.po.OddsInfo;
import digitalgame.model.po.UserBetInfo;

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

    List<UserBetInfo> oddsNumber(int i, int j, int k, List<UserBetInfo> userBetInfoList, String resultDate);
}
