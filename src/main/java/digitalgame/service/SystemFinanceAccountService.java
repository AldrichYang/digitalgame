package digitalgame.service;

import digitalgame.model.po.SystemFinanceAccountReport;
import digitalgame.model.po.UserInfo;

import java.util.List;

public interface SystemFinanceAccountService {

    /****
     * 分页查询
     * @return
     */
    List<SystemFinanceAccountReport> selectByPage(int cueerntPage, String bigDate, String endDate);

    int addSystemFinanceAccount(SystemFinanceAccountReport systemFinanceAccountReport);

    int deleteSystemFinanceAccount(String brgDate,String endDate);

    void createReportByDate(String brgDate,String endDate);

}
;