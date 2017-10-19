package digitalgame.dao;

import digitalgame.model.po.BetResult;

import java.util.List;

public interface BetResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BetResult record);

    int insertSelective(BetResult record);

    BetResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BetResult record);

    int updateByPrimaryKey(BetResult record);

    List<BetResult> selectByPage(String whereCond);

}