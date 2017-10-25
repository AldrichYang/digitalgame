package digitalgame.controller;

import com.google.common.base.Strings;
import digitalgame.model.po.SystemFinanceAccountReport;
import digitalgame.model.po.UserAccountHisVo;
import digitalgame.model.po.UserInfo;
import digitalgame.service.SystemFinanceAccountService;
import digitalgame.service.UserFinanceAccountService;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by yh on 17/9/29.
 */

@Controller
@RequestMapping("/systemFinanceAccount")
public class SystemFinanceAccountController {

    @Autowired
    private SystemFinanceAccountService systemFinanceAccountService;

    @Autowired
    private UserFinanceAccountService userFinanceAccountService;




    @RequestMapping(value = "/querySystemFinanceAccount", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllUserList(@ModelAttribute UserInfo userInfo, Model model, HttpServletRequest request) {
        int currentPageNo = 1;
        if(request != null ){
            String pageNo = request.getParameter("pageNo");
            if(!Strings.isNullOrEmpty(pageNo)) currentPageNo = Integer.parseInt(pageNo);
        }
        String begTime = request.getParameter("begTime");
        String endTime = request.getParameter("endTime");
        model.addAttribute("begTime",begTime);
        model.addAttribute("endTime",endTime);
        if(begTime != null) begTime = begTime.replaceAll("-","");

        if(endTime != null) endTime = endTime.replaceAll("-","");
        String flag = request.getParameter("flag");
        //生成报表
        if("2".equals(flag)){
            systemFinanceAccountService.createReportByDate(begTime,endTime);
        }
        int userInfoListCount = systemFinanceAccountService.selectByPage(0,begTime,endTime).size();
                //userFinanceAccountService.queryUserAccountHisVoByUserInfo(0,userInfo).size();
        List<SystemFinanceAccountReport> sfarList = systemFinanceAccountService.selectByPage(0,begTime,endTime);
        int pageNo = userInfoListCount/10 + (userInfoListCount % 10 == 0 ? 0 : 1);
        model.addAttribute("sfarList", sfarList);
        model.addAttribute("queryCond",userInfo);
        model.addAttribute("inallPageDesc","总条数："+userInfoListCount+",当前第"+currentPageNo+"页,总共" + pageNo + "页");
        model.addAttribute("currentPage",currentPageNo);
        model.addAttribute("inallPage",pageNo);
        return "systemFinanceAccountReport";
    }

    public String createHtml(){
        String html = "";
        html += "<ul class=\"pagination\">";
        html += "<li class=\"paginate_button previous disabled\" id=\"example1_previous\"><a href=\"#\" aria-controls=\"example1\" data-dt-idx=\"0\" tabindex=\"0\">Previous</a></li>";
        html += "<li class=\"paginate_button previous disabled\" id=\"example1_previous\"><a href=\"#\" aria-controls=\"example1\" data-dt-idx=\"0\" tabindex=\"0\">Previous</a></li>";
        html += "<li class=\"paginate_button \"><a href=\"#\" aria-controls=\"example1\" data-dt-idx=\"1\" tabindex=\"0\">1</a></li>";
        html += "<li class=\"paginate_button next\" id=\"example1_next\"><a href=\"#\" aria-controls=\"example1\" data-dt-idx=\"7\" tabindex=\"0\">Next</a></li>";
        html += "</ul>";
        return html;
    }

}
