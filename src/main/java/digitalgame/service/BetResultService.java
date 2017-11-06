package digitalgame.service;

import digitalgame.model.po.OddsBetResultVo;
import digitalgame.model.po.OpenInfo;

import java.util.List;

public interface BetResultService {

    List<OddsBetResultVo> selectBetSultByPage(int cueerntPage,OddsBetResultVo oddsBetResultVo);

    OpenInfo selectOpenInfoLast();

    int selectBetNumberSum(String openNO);
}
