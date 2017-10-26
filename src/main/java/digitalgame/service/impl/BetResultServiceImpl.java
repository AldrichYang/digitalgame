package digitalgame.service.impl;

import digitalgame.dao.BetResultMapper;
import digitalgame.model.po.BetResult;
import digitalgame.service.BetResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import java.util.List;

@Service
public class BetResultServiceImpl implements BetResultService {

    @Autowired
    protected BetResultMapper betResultMapper;

    @Override
    public List<BetResult> selectBetSultByPage(int cueerntPage, BetResult betResult) {
        String whereCond = " where 1=1 order by id desc ";
        if(!Strings.isNullOrEmpty(betResult.getBetuser())){
            whereCond +=  " and  betuser like '%"+betResult.getBetuser()+"%'";
        }
        if(!Strings.isNullOrEmpty(betResult.getResultdate())){
            whereCond +=  " and resultdate like "+betResult.getResultdate();
        }
        if(cueerntPage != 0){
            whereCond += " limit " +((cueerntPage -1) *10) +","+(cueerntPage) * 10;
        }
        return betResultMapper.selectByPage(whereCond);
    }
}
