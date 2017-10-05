package digitalgame.controller;

import digitalgame.model.po.UserAccountVo;
import digitalgame.model.po.UserInfo;
import digitalgame.service.UserFinanceAccountService;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/userAccount")
public class UserAccountController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserFinanceAccountService userFinanceAccountService;

    @RequestMapping(value = "/userAccountList", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllUserAccountList(@ModelAttribute UserInfo userInfo, Model model) {
        List<UserAccountVo> userAccountVoList = userInfoService.selectUserAccountByPage(0, 0, userInfo);
        model.addAttribute("userAccountList", userAccountVoList);
        model.addAttribute("queryCond",userInfo);
        return "userAccountList";
    }


    @RequestMapping(value = "/editUserAccount.html", method = RequestMethod.GET)
    public String redirectToEdit(@RequestParam int userId, Model model) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        List<UserAccountVo> userInfoList = userInfoService.selectUserAccountByPage(0,0,userInfo);
        if(userInfoList.size() != 1 ){
            //TODU trhow Exception
        }
        model.addAttribute("userAccountVo", userInfoList.get(0));
        return "editUserAccount";
    }

    @RequestMapping(value = "/editUserAccount", method = RequestMethod.POST)
    public String editUser(UserAccountVo userAccountVo,Model model) {
        userFinanceAccountService.updateByPrimaryKeySelective(userAccountVo);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userAccountVo.getUserName());
        userInfo.setNickName(userAccountVo.getNickName());
        //return this.getAllUserList(userInfo,model);
        return  this.getAllUserAccountList(userInfo,model);
    }



}
