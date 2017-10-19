package digitalgame.service;

import digitalgame.model.po.BetResult;

import java.util.List;

public interface BetResultService {
    List<BetResult> selectBetSultByPage(int cueerntPage,BetResult betResult);
}
