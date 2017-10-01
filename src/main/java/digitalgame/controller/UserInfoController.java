package digitalgame.controller;

import digitalgame.dao.UserInfoMapper;
import digitalgame.model.po.UserInfo;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by yh on 17/9/29.
 */

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public String getAllUserList(Model model) {
        List<UserInfo> userInfoList = userInfoMapper.selectByPage(0,0);
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
        userInfoService.editUser(userInfo);
        return  "userList";
    }

}
