package digitalgame.dao;

import digitalgame.model.po.UserFinanceAccountLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFinanceAccountLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFinanceAccountLog record);

    int insertSelective(UserFinanceAccountLog record);

    UserFinanceAccountLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFinanceAccountLog record);

    int updateByPrimaryKey(UserFinanceAccountLog record);
}