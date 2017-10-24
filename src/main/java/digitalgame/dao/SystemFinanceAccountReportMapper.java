package digitalgame.dao;

import digitalgame.model.po.SystemFinanceAccountReport;

public interface SystemFinanceAccountReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemFinanceAccountReport record);

    int insertSelective(SystemFinanceAccountReport record);

    SystemFinanceAccountReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemFinanceAccountReport record);

    int updateByPrimaryKey(SystemFinanceAccountReport record);
}