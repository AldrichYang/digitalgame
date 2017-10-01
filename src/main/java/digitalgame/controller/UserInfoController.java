package digitalgame.controller;

import digitalgame.dao.UserInfoMapper;
import digitalgame.model.po.UserInfo;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by yh on 17/9/29.
 */

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping(value = "/userList", method = {RequestMethod.GET,RequestMethod.POST})
    public String getAllUserList(@ModelAttribute UserInfo userInfo, Model model) {
        List<UserInfo> userInfoList = userInfoService.selectByPage(0,0,userInfo);
        model.addAttribute("userList", userInfoList);
        return "userList";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(UserInfo userInfo) {
        userInfoService.saveUser(userInfo);
        return "userList";
    }

    @RequestMapping(value = "/editUser",method = RequestMethod.POST)
    public  String editUser(UserInfo userInfo){
        userInfo.setId(1);
        userInfoService.editUser(userInfo);
        return  "userList";
    }

}
