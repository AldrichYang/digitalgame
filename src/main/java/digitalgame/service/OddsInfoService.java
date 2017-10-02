package digitalgame.service;

import digitalgame.model.po.OddsInfo;

import java.util.List;

public interface OddsInfoService {

    OddsInfo saveOddsInfo(OddsInfo oddsInfo);

    List<OddsInfo> selectOddsList();
}
