package digitalgame.controller;

import digitalgame.model.po.UserAccountVo;
import digitalgame.model.po.UserInfo;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/userAccount")
public class UserAccountController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/userAccountList", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllUserAccountList(@ModelAttribute UserInfo userInfo, Model model) {
        List<UserAccountVo> userAccountVoList = userInfoService.selectUserAccountByPage(0, 0, userInfo);
        model.addAttribute("userList", userAccountVoList);
        model.addAttribute("queryCond",userInfo);
        return "userList";
    }

}
