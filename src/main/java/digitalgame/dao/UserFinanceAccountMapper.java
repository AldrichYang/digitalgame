package digitalgame.dao;

import digitalgame.model.po.UserFinanceAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFinanceAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFinanceAccount record);

    int insertSelective(UserFinanceAccount record);

    UserFinanceAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFinanceAccount record);

    int updateByPrimaryKey(UserFinanceAccount record);

    UserFinanceAccount selectByUserId(Integer userId);
}