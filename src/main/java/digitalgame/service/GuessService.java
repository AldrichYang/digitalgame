package digitalgame.service;

import digitalgame.model.po.BetInfo;
import digitalgame.model.po.OpenInfo;
import digitalgame.model.po.UserBetInfo;

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
    public List<BetInfo> analysisBetContent(OpenInfo openInfo,String betContent);

    /**
     * 投注
     * @param betInfoList 投注信息
     * @param openInfo  开奖信息
     */
    public void doBet(List<BetInfo> betInfoList,OpenInfo openInfo);

    /**
     * 获取下一次开奖信息
     * @return
     */
    public OpenInfo getNextOpenInfo();


    /**
     * 开奖
     * @param openInfo 开奖信息
     * @return
     */
    public List<UserBetInfo> doOpen(OpenInfo openInfo) ;

    /**
     * 获取当天开奖结果
     * @return
     */
    public List<OpenInfo> getTodayOpenResult();

    /**
     * 根据开奖期号查询投注信息
     * @param openId
     * @return
     */
    public List<BetInfo> getBetInfoByOpenId(int openId);

    /**
     * 计算单局下注结果
     * @return
     */
    public List<UserBetInfo> caculateUserBetInfo(List<BetInfo> betInfoList);


    /**
     * 通过开奖期数获取投注信息
     * @param openNo
     * @return
     */
    public List<BetInfo> getBetInfoByOpenNo(int openNo);


    /**
     * 根据开奖期数获取开奖信息
     * @param openNo
     * @return
     */
    public OpenInfo getOpenInfoByOpenNo(long openNo);
}
