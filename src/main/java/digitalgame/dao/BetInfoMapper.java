package digitalgame.dao;

import digitalgame.model.po.BetInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BetInfoMapper {

    /**
     * 批量新增投注内容
     * @param list
     * @return
     */
    int addBatch(List<BetInfo> list);

    /**
     * 查询开奖投注信息
     * @param openId
     * @return
     */
    List<BetInfo> selectByOpenId(int openId);

    /**
     * 按照开奖期数查询投注信息
     * @param openNo
     * @return
     */
    List<BetInfo> selectByOpenNo(int openNo);

    /**
     * 删除某一期投注内容
     * @param openId 期数
     * @return
     */
    int deleteByOpenId(int openId);
}