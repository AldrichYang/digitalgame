package digitalgame.service.impl;

import com.google.common.base.Strings;
import digitalgame.common.Util;
import digitalgame.dao.SystemFinanceAccountReportMapper;
import digitalgame.dao.UserFinanceAccountLogMapper;
import digitalgame.dao.UserFinanceAccountMapper;
import digitalgame.dao.UserInfoMapper;
import digitalgame.model.po.*;
import digitalgame.service.UserFinanceAccountService;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserFinanceAccountServiceImpl implements UserFinanceAccountService {

    @Autowired
    UserFinanceAccountMapper userFinanceAccountMapper;

    @Autowired
    private UserFinanceAccountLogMapper userFinanceAccountLogMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SystemFinanceAccountReportMapper systemFinanceAccountReportMapper;

    @Override
    public int insertSelective(UserFinanceAccount record) {
        return userFinanceAccountMapper.insertSelective(record);
    }

    @Override
    @Transactional
    public synchronized int updateByPrimaryKeySelective(UserAccountVo userAccountVo ,AccountParam accountParam) {
        boolean update = true;
        UserFinanceAccount record = userFinanceAccountMapper.selectByPrimaryKey(userAccountVo.getAccountId());
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(record.getUserId());
        if(5 == userAccountVo.getOperType() && record.getBalance() == 0.0) {
            return 0;
        } else  if(5 == userAccountVo.getOperType() && (record.getBalance() <= userAccountVo.getMoney())){
            userAccountVo.setMoney(record.getBalance());
        }
        //余额不足直接返回失败
        if((3 == userAccountVo.getOperType() || 5 == userAccountVo.getOperType()) && userAccountVo.getMoney() > record.getBalance()){
            return  -1;
        }
        if(2 == userAccountVo.getOperType() || 4 == userAccountVo.getOperType()){
            record.setBalance(record.getBalance() + userAccountVo.getMoney());
        }else if(3 == userAccountVo.getOperType() || 5 == userAccountVo.getOperType()){
            record.setBalance(record.getBalance() - userAccountVo.getMoney());
        }else{
          //TODO throw Exception
        }
        UserFinanceAccountLog userFinanceAccountLog = new UserFinanceAccountLog();
        userFinanceAccountMapper.updateByPrimaryKeySelective(record);
        if(Objects.nonNull(accountParam)){
            if(Objects.nonNull(accountParam.getOrderId())){
                userFinanceAccountLog.setOrderId(accountParam.getOrderId());
            }
            if(Objects.nonNull(accountParam.getPeriods())){
                userFinanceAccountLog.setOrderId(accountParam.getPeriods());
            }
        }
        userFinanceAccountLog.setOperType(userAccountVo.getOperType());
        userFinanceAccountLog.setMoney(userAccountVo.getMoney());
        userFinanceAccountLog.setUfcId(record.getId());
        userFinanceAccountLog.setGroup(userInfo.getGroup());
        userFinanceAccountLog.setBalance(record.getBalance());
        userFinanceAccountLog.setCreateTime(Util.dataForMat(new Date(),"yyyyMMdd"));
        userFinanceAccountLogMapper.insertSelective(userFinanceAccountLog);
        int a = userFinanceAccountMapper.updateByPrimaryKeySelective(record);
        if(5 == userAccountVo.getOperType() || 4 == userAccountVo.getOperType()){
            String queryCond = " where report_date = '" + Util.dataForMat(new Date(),"yyyyMMdd") + "'";
            if(Objects.nonNull(userInfo.getGroup()) && userInfo.getGroup().trim().length() != 0){
                queryCond = queryCond + " and group = '"+userInfo.getGroup()+"'";
            }else{
                queryCond = queryCond + " and (group = '' or group is null) ";
            }
            SystemFinanceAccountReport systemFinanceAccountReport = systemFinanceAccountReportMapper.selectByReportDate(,userInfo.getGroup());
            if(systemFinanceAccountReport == null){
                update = false;
                systemFinanceAccountReport = new SystemFinanceAccountReport();
                systemFinanceAccountReport.setReportDate(Util.dataForMat(new Date(),"yyyyMMdd"));
            }
            systemFinanceAccountReport.setGroup(userInfo.getGroup());
            if(4 == userAccountVo.getOperType()){
                systemFinanceAccountReport.setWinningMoney(systemFinanceAccountReport.getWinningMoney() + userAccountVo.getMoney());
                systemFinanceAccountReport.setPlatformMoney(systemFinanceAccountReport.getPlatformMoney() - userAccountVo.getMoney());
            }else{
                systemFinanceAccountReport.setBettingMoney(systemFinanceAccountReport.getPlatformMoney() + userAccountVo.getMoney());
                systemFinanceAccountReport.setPlatformMoney(systemFinanceAccountReport.getPlatformMoney() + userAccountVo.getMoney());
            }
            if(!update){
                systemFinanceAccountReportMapper.insert(systemFinanceAccountReport);
            }else{
                systemFinanceAccountReportMapper.updateByPrimaryKeySelective(systemFinanceAccountReport);
            }



        }
        if(5 == userAccountVo.getOperType()) return Integer.parseInt(String.valueOf(userAccountVo.getMoney()));
        return a;
    }

    @Override
    public int updateBalanceByUserId(int userId, double money, int type,AccountParam accountParam) {
        UserFinanceAccount ufa = userFinanceAccountMapper.selectByUserId(userId);
        UserAccountVo userAccountVo = new UserAccountVo();
        userAccountVo.setAccountId(ufa.getId());
        userAccountVo.setMoney(money);
        userAccountVo.setOperType(type);
        return this.updateByPrimaryKeySelective(userAccountVo,accountParam);
    }

    @Override
    public int addUserBalanceByNickName(String nickName, double money,AccountParam accountParam) {
        UserInfo userInfo = userInfoMapper.selectByNickName(nickName);
        if(userInfo == null) return  -2;
        return this.updateBalanceByUserId(userInfo.getId(),money,4,accountParam);
    }

    @Override
    public int reduceUserBalanceByNickName(String nickName, double money,AccountParam accountParam) {
        UserInfo userInfo = userInfoMapper.selectByNickName(nickName);
        if(userInfo == null) return  -2;
        return this.updateBalanceByUserId(userInfo.getId(),money,5,accountParam);
    }

    @Override
    public UserFinanceAccount queryUserFinanceAccountByNickName(String nickName) {
        UserInfo userInfo = userInfoMapper.selectByNickName(nickName);
        UserFinanceAccount record = userFinanceAccountMapper.selectByUserId(userInfo.getId());
        return record;
    }

    @Override
    public List<UserAccountHisVo> queryUserAccountHisVoByUserInfo(int currentPage,UserInfo userInfo){

        String whereCond = " ";
        if(!Strings.isNullOrEmpty(userInfo.getUserName())){
            whereCond +=  " and  ui.user_name like '%"+userInfo.getUserName()+"%'";
        }
        if(!Strings.isNullOrEmpty(userInfo.getNickName())){
            whereCond +=  " and ui.nick_name like '%"+userInfo.getNickName()+"%'";
        }
        if(currentPage != 0){
            whereCond += "limit " +((currentPage -1) *10) +","+(currentPage) * 10;
        }
        return userFinanceAccountLogMapper.queryUserAccountHisVoByUserInfo(whereCond);
    }



}
