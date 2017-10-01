package digitalgame.dao;

import digitalgame.model.po.OddsInfo;

public interface OddsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OddsInfo record);

    int insertSelective(OddsInfo record);

    OddsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OddsInfo record);

    int updateByPrimaryKey(OddsInfo record);
}