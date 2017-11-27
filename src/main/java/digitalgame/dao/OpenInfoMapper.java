package digitalgame.dao;

import digitalgame.model.po.OpenInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface OpenInfoMapper {

    /**
     * 批量新增投注内容
     * @param info
     * @return
     */
    int insert(OpenInfo info);

    /**
     * 查询当天最新的一条开奖信息
     * @return
     */
    OpenInfo selectTodayLasted();

    /**
     * 查询当天数据
     * @return
     */
    List<OpenInfo> selectTop();

    /**
     * 更新开奖结果
     * @param openInfo
     * @return
     */
    int updateOpenInfo(OpenInfo openInfo);

    /**
     * 查询最后一条开过奖的记录
     * @return
     */
    OpenInfo selectLasted();

    /**
     * 根据开奖期数查询开奖信息
     * @param openNo
     * @return
     */
    OpenInfo selectByOpenNo(long openNo);

}