package digitalgame.dao;

import digitalgame.model.po.BetResult;
import digitalgame.model.po.OddsBetResultVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BetResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BetResult record);

    int insertSelective(BetResult record);

    BetResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BetResult record);

    int updateByPrimaryKey(BetResult record);

    List<BetResult> selectByPage(@Param("whereCond") String whereCond);

    List<OddsBetResultVo> selectoddsInfoByPage(@Param("whereCond") String whereCond);
}