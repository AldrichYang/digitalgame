package digitalgame.controller;

import com.google.common.base.Strings;
import digitalgame.model.po.AdminInfo;
import digitalgame.model.po.UserInfo;
import digitalgame.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public String  getAllAdminList(@ModelAttribute AdminInfo adminInfo, Model model, HttpServletRequest request){
        int currentPageNo = 1;
        if(request != null ){
            String pageNo = request.getParameter("pageNo");
            if(!Strings.isNullOrEmpty(pageNo)) currentPageNo = Integer.parseInt(pageNo);
        }
        int userInfoListCount = adminInfoService.queryAdminInfoByPage(adminInfo,0).size();
        List<AdminInfo> adminInfoList = adminInfoService.queryAdminInfoByPage(adminInfo,currentPageNo);
        int pageNo = userInfoListCount/10 + (userInfoListCount % 10 == 0 ? 0 : 1);
        model.addAttribute("adminList", adminInfoList);
        model.addAttribute("queryCond",adminInfo);
        model.addAttribute("inallPageDesc","总条数："+userInfoListCount+",当前第"+currentPageNo+"页,总共" + pageNo + "页");
        model.addAttribute("currentPage",currentPageNo);
        model.addAttribute("inallPage",pageNo);
        return "adminList";
    }

    @RequestMapping(value = "/addAdmin.html", method = RequestMethod.GET)
    public String redirectToAdd() {

        return "addAdmin";
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public String addUser(AdminInfo adminInfo, Model model) {
        adminInfoService.saveAdminInfo(adminInfo);
        return this.getAllAdminList(adminInfo,model,null);
    }

    @RequestMapping(value = "/editAdminInfo", produces = "application/json")
    public @ResponseBody
    AdminInfo redirectToEdit(@RequestParam int adminId, Model model) {
        AdminInfo adminInfo = adminInfoService.selectByPrimaryKey(adminId);
        return adminInfo;
    }

    @RequestMapping(value = "/editAdmin", method = RequestMethod.POST)
    public String editUser(AdminInfo adminInfo,Model model) {
        adminInfoService.updateByPrimaryKeySelective(adminInfo);
        return this.getAllAdminList(adminInfo,model,null);
    }

}
