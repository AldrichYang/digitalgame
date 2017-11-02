package digitalgame.service.impl;

import digitalgame.dao.BetResultMapper;
import digitalgame.model.po.BetResult;
import digitalgame.model.po.OddsBetResultVo;
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
    public List<OddsBetResultVo> selectBetSultByPage(int cueerntPage, OddsBetResultVo oddsBetResultVo) {
        String whereCond = " ";
        if(!Strings.isNullOrEmpty(oddsBetResultVo.getBetUser())){
            whereCond +=  " and  br.betuser like '%"+oddsBetResultVo.getBetUser()+"%'";
        }
        if(!Strings.isNullOrEmpty(oddsBetResultVo.getResultDate())){
            whereCond +=  " and br.resultdate like '%"+oddsBetResultVo.getResultDate()+"%'";
        }
        whereCond += " order by br.id desc ";
        if(cueerntPage != 0){
            whereCond += " limit " +((cueerntPage -1) *10) +","+(cueerntPage) * 10;
        }
        return betResultMapper.selectoddsInfoByPage(whereCond);
    }
}
