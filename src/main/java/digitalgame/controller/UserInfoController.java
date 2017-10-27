package digitalgame.controller;

import com.google.common.base.Strings;
import digitalgame.dao.UserInfoMapper;
import digitalgame.model.po.UserInfo;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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


    @RequestMapping(value = "/userList", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllUserList(@ModelAttribute UserInfo userInfo, Model model, HttpServletRequest request) {
        int currentPageNo = 1;
        if (request != null) {
            String pageNo = request.getParameter("pageNo");
            if (!Strings.isNullOrEmpty(pageNo)) currentPageNo = Integer.parseInt(pageNo);
        }

        int userInfoListCount = userInfoService.selectByPage(0, userInfo).size();
        List<UserInfo> userInfoList = userInfoService.selectByPage(currentPageNo, userInfo);
        int pageNo = userInfoListCount / 10 + (userInfoListCount % 10 == 0 ? 0 : 1);
        model.addAttribute("userList", userInfoList);
        model.addAttribute("queryCond", userInfo);
        model.addAttribute("inallPageDesc", "总条数：" + userInfoListCount + ",当前第" + currentPageNo + "页,总共" + pageNo + "页");
        model.addAttribute("currentPage", currentPageNo);
        model.addAttribute("inallPage", pageNo);
        return "userList";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(UserInfo userInfo, Model model) {
        userInfoService.saveUser(userInfo);
        return this.getAllUserList(userInfo, model, null);
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String editUser(UserInfo userInfo, Model model) {
        userInfoService.editUser(userInfo);
        return this.getAllUserList(userInfo, model, null);
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

    @RequestMapping(value = "/validUserExist", produces = "application/json")
    public @ResponseBody
    UserInfo validUserExist(@ModelAttribute UserInfo userInfo) {
        UserInfo existUser = userInfoMapper.selectByNickName(userInfo.getNickName());
        return existUser != null ? existUser : new UserInfo();
    }

//    public String createHtml(){
//        String html = "";
//        html += "<ul class=\"pagination\">";
//        html += "<li class=\"paginate_button previous disabled\" id=\"example1_previous\"><a href=\"#\" aria-controls=\"example1\" data-dt-idx=\"0\" tabindex=\"0\">Previous</a></li>";
//        html += "<li class=\"paginate_button previous disabled\" id=\"example1_previous\"><a href=\"#\" aria-controls=\"example1\" data-dt-idx=\"0\" tabindex=\"0\">Previous</a></li>";
//        html += "<li class=\"paginate_button \"><a href=\"#\" aria-controls=\"example1\" data-dt-idx=\"1\" tabindex=\"0\">1</a></li>";
//        html += "<li class=\"paginate_button next\" id=\"example1_next\"><a href=\"#\" aria-controls=\"example1\" data-dt-idx=\"7\" tabindex=\"0\">Next</a></li>";
//        html += "</ul>";
//        return html;
//    }

}
