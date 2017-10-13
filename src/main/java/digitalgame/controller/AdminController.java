package digitalgame.controller;

import com.google.common.base.Strings;
import digitalgame.model.po.AdminInfo;
import digitalgame.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @RequestMapping(value = "/adminList", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllAdminList(@ModelAttribute AdminInfo adminInfo, Model model, HttpServletRequest request) {
        int currentPageNo = 1;
        if (request != null) {
            String pageNo = request.getParameter("pageNo");
            if (!Strings.isNullOrEmpty(pageNo)) currentPageNo = Integer.parseInt(pageNo);
        }
        int userInfoListCount = adminInfoService.queryAdminInfoByPage(adminInfo, 0).size();
        List<AdminInfo> adminInfoList = adminInfoService.queryAdminInfoByPage(adminInfo, currentPageNo);
        int pageNo = userInfoListCount / 10 + (userInfoListCount % 10 == 0 ? 0 : 1);
        model.addAttribute("adminList", adminInfoList);
        model.addAttribute("queryCond", adminInfo);
        model.addAttribute("inallPageDesc", "总条数：" + userInfoListCount + ",当前第" + currentPageNo + "页,总共" + pageNo + "页");
        model.addAttribute("currentPage", currentPageNo);
        model.addAttribute("inallPage", pageNo);
        return "adminList";
    }

    @RequestMapping(value = "/addAdmin.html", method = RequestMethod.GET)
    public String redirectToAdd() {

        return "addAdmin";
    }

    @RequestMapping(value = "/validAdmin", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, String> hello(@RequestBody AdminInfo AdminInfo) {
        // 返回数据的Map集合
        Map<String, String> result = new HashMap<>();
        // 返回当前时间
        result.put("time", "2017");
        return result;
    }


    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public String addUser(AdminInfo adminInfo, Model model) {
        int resInt = adminInfoService.saveAdminInfo(adminInfo);
        //TODO 这里需要一个错误处理页面
//        if(resInt == -1) return "errorHtml";
        Map<String, Object> map = new HashMap<>();
        map.put("result", resInt);
//        ModelAndView mav=new ModelAndView("adminList",map);
        return this.getAllAdminList(adminInfo, model, null);
//        return mav;
    }

    @RequestMapping(value = "/editAdmin.html", method = RequestMethod.GET)
    public String redirectToEdit(@RequestParam int adminId, Model model) {
        AdminInfo adminInfo = adminInfoService.selectByPrimaryKey(adminId);
        model.addAttribute("oldAdminInfo", adminInfo);
        return "editAdmin";
    }

    @RequestMapping(value = "/editAdmin", method = RequestMethod.POST)
    public String editUser(AdminInfo adminInfo, Model model) {
        int resInt = adminInfoService.updateByPrimaryKeySelective(adminInfo);
        //TODO 这里需要一个错误处理页面
        if (resInt == -1) return "errorHtml";
        return this.getAllAdminList(adminInfo, model, null);
    }

}
