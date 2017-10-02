package digitalgame.controller;

import digitalgame.model.po.AdminInfo;
import digitalgame.model.po.UserInfo;
import digitalgame.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/***
 * admin后台的操作
 * 目前只要操作就是操作用户的基本信息
 * 和用户的账户信息
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminInfoService adminInfoService;


    @RequestMapping(value = "/adminList", method = {RequestMethod.GET,RequestMethod.POST})
    public String  getAllAdminList(@ModelAttribute AdminInfo adminInfo, Model model){
        List<AdminInfo> adminInfoList = adminInfoService.queryAdminInfoByPage(adminInfo);
        model.addAttribute("adminList", adminInfoList);
        model.addAttribute("queryCond",adminInfo);
        return "adminList";
    }

    @RequestMapping(value = "/addAdmin.html", method = RequestMethod.GET)
    public String redirectToAdd() {

        return "addAdmin";
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public String addUser(AdminInfo adminInfo, Model model) {
        adminInfoService.saveAdminInfo(adminInfo);
        return this.getAllAdminList(adminInfo,model);
    }

    @RequestMapping(value = "/editAdmin.html", method = RequestMethod.GET)
    public String redirectToEdit(@RequestParam int adminId, Model model) {
        AdminInfo adminInfo = adminInfoService.selectByPrimaryKey(adminId);
        model.addAttribute("oldAdminInfo", adminInfo);
        return "editAdmin";
    }

    @RequestMapping(value = "/editAdmin", method = RequestMethod.POST)
    public String editUser(AdminInfo adminInfo,Model model) {
        adminInfoService.updateByPrimaryKeySelective(adminInfo);
        return this.getAllAdminList(adminInfo,model);
    }

}
