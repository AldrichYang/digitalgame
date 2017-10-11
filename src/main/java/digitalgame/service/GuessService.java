package digitalgame.service;

import digitalgame.model.po.BetInfo;

import java.util.List;

/**
 * 投注&开奖
 * @author simon
 * @version  1.0 creat@2017/10/08
 */
public interface GuessService {

    /**
     * 解析下注内容为标准投注内容
     * @param betContent 下注的文本
     * @return 标准投注内容
     */
    public List<BetInfo> analysisBetContent(String betContent);

    /**
     * 投注
     * @param betInfoList
     */
    public void doBet(List<BetInfo> betInfoList);
}
