package digitalgame.service.impl;

import digitalgame.dao.SystemFinanceAccountReportMapper;
import digitalgame.dao.UserFinanceAccountLogMapper;
import digitalgame.model.po.SystemFinanceAccountReport;
import digitalgame.service.SystemFinanceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class SystemFinanceAccountServiceImpl implements SystemFinanceAccountService {

    @Autowired
    private SystemFinanceAccountReportMapper systemFinanceAccountReportMapper;

     @Autowired
     private UserFinanceAccountLogMapper userFinanceAccountLogMapper;

    @Override
    public List<SystemFinanceAccountReport> selectByPage(int cueerntPage, String bigDate, String endDate) {
        return null;
    }

    @Override
    public int addSystemFinanceAccount(SystemFinanceAccountReport systemFinanceAccountReport) {
        return 0;
    }

    @Override
    public int deleteSystemFinanceAccount(String brgDate, String endDate) {
        return 0;
    }

    @Override
    @Transactional
    public void createReportByDate(String begDate, String endDate) {
        systemFinanceAccountReportMapper.deleteReportByDate(begDate,endDate);
        String queryCond = "";
        if(Objects.nonNull(begDate) && begDate.trim().length() !=0 ){
            queryCond = queryCond + " AND create_time >= '" + begDate + "'";
        }
        if(Objects.nonNull(endDate) && begDate.trim().length() !=0){
            queryCond = queryCond + " AND create_time <= '" + endDate + "'";
        }
        List<SystemFinanceAccountReport> list =userFinanceAccountLogMapper.querySystemFinanceAccountReportByDate(queryCond);
        for (SystemFinanceAccountReport sar : list){
            systemFinanceAccountReportMapper.insert(sar);
        }

    }
}
