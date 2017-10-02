package digitalgame.controller;

import digitalgame.model.po.UserInfo;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.List;

/**
 * Created by yh on 17/9/29.
 */

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping(value = "/userList", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllUserList(@ModelAttribute UserInfo userInfo, Model model) {
        List<UserInfo> userInfoList = userInfoService.selectByPage(0, 0, userInfo);
        model.addAttribute("userList", userInfoList);
        model.addAttribute("queryCond",userInfo);
        return "userList";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(UserInfo userInfo, Model model) {
        userInfoService.saveUser(userInfo);
        return this.getAllUserList(userInfo,model);
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String editUser(UserInfo userInfo,Model model) {
        userInfoService.editUser(userInfo);
        return this.getAllUserList(userInfo,model);
    }

    @RequestMapping(value = "/editUser.html", method = RequestMethod.GET)
    public String redirectToEdit(@RequestParam int userId, Model model) {
        UserInfo userInfo = userInfoService.selectByPrimaryKey(userId);
        model.addAttribute("oldUserInfo", userInfo);
        return "editUser";
    }

    @RequestMapping(value = "/addUser.html", method = RequestMethod.GET)
    public String redirectToAdd(@ModelAttribute UserInfo userInfo, Model model) {

        return "addUser";
    }

}
