package digitalgame.service;

import digitalgame.model.po.OddsBetResultVo;

import java.util.List;

public interface BetResultService {

    List<OddsBetResultVo> selectBetSultByPage(int cueerntPage,OddsBetResultVo oddsBetResultVo);
}
