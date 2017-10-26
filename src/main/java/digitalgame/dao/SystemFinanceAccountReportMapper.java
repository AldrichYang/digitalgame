package digitalgame.dao;

import digitalgame.model.po.SystemFinanceAccountReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemFinanceAccountReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemFinanceAccountReport record);

    int insertSelective(SystemFinanceAccountReport record);

    SystemFinanceAccountReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemFinanceAccountReport record);

    int updateByPrimaryKey(SystemFinanceAccountReport record);

    int deleteReportByDate(@Param("queryCond") String queryCond);

    List<SystemFinanceAccountReport> selectByPage(@Param("queryCond") String queryCond);
}