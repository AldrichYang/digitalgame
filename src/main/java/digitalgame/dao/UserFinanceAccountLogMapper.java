package digitalgame.dao;

import digitalgame.model.po.SystemFinanceAccountReport;
import digitalgame.model.po.UserAccountHisVo;
import digitalgame.model.po.UserFinanceAccountLog;
import digitalgame.model.po.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserFinanceAccountLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFinanceAccountLog record);

    int insertSelective(UserFinanceAccountLog record);

    UserFinanceAccountLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFinanceAccountLog record);

    int updateByPrimaryKey(UserFinanceAccountLog record);

    public List<UserAccountHisVo> queryUserAccountHisVoByUserInfo(@Param("whereCond") String whereCond);

    List<SystemFinanceAccountReport> querySystemFinanceAccountReportByDate(@Param("whereCond") String whereCond);
}