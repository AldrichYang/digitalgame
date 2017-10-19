package digitalgame.service.impl;

import digitalgame.dao.BetResultMapper;
import digitalgame.model.po.BetResult;
import digitalgame.service.BetResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetResultServiceImpl implements BetResultService {

    @Autowired
    protected BetResultMapper betResultMapper;

    @Override
    public List<BetResult> selectBetSultByPage(int cueerntPage, BetResult betResult) {
        String whereCond = " where 1=1 ";
//        if(!Strings.isNullOrEmpty(userInfo.getUserName())){
//            whereCond +=  " and  user_name like '%"+userInfo.getUserName()+"%'";
//        }
//        if(!Strings.isNullOrEmpty(userInfo.getNickName())){
//            whereCond +=  " and nick_name like '%"+userInfo.getNickName()+"%'";
//        }
        if(cueerntPage != 0){
            whereCond += "limit " +((cueerntPage -1) *10) +","+(cueerntPage) * 10;
        }
        return betResultMapper.selectByPage(whereCond);
    }
}
