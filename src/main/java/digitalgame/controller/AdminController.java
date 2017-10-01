package digitalgame.controller;

import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 * admin后台的操作
 * 目前只要操作就是操作用户的基本信息
 * 和用户的账户信息
 */
@Controller
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping(value = "/adminList", method = RequestMethod.GET)
    public String  addAdminInfo(){

        return "adminList";
    }

}
