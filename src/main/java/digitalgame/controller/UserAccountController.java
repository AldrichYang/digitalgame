package digitalgame.controller;

import com.google.common.base.Strings;
import digitalgame.model.po.UserAccountVo;
import digitalgame.model.po.UserInfo;
import digitalgame.service.UserFinanceAccountService;
import digitalgame.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/userAccount")
public class UserAccountController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserFinanceAccountService userFinanceAccountService;

    @RequestMapping(value = "/userAccountList", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllUserAccountList(@ModelAttribute UserInfo userInfo, Model model, HttpServletRequest request) {
        int currentPageNo = 1;
        if(request != null ){
            String pageNo = request.getParameter("pageNo");
            if(!Strings.isNullOrEmpty(pageNo)) currentPageNo = Integer.parseInt(pageNo);
        }
        int userInfoListCount = userInfoService.selectUserAccountByPage( 0, userInfo).size();
        List<UserAccountVo> userAccountVoList = userInfoService.selectUserAccountByPage( currentPageNo, userInfo);
        int pageNo = userInfoListCount/10 + (userInfoListCount % 10 == 0 ? 0 : 1);
        model.addAttribute("userAccountList", userAccountVoList);
        model.addAttribute("queryCond",userInfo);
        model.addAttribute("inallPageDesc","总条数："+userInfoListCount+",当前第"+currentPageNo+"页,总共" + pageNo + "页");
        model.addAttribute("currentPage",currentPageNo);
        model.addAttribute("inallPage",pageNo);
        return "userAccountList";
    }


    @RequestMapping(value = "/editUserAccountInfo", method = RequestMethod.POST)
    public @ResponseBody
    UserAccountVo redirectToEdit(@RequestParam int userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        List<UserAccountVo> userInfoList = userInfoService.selectUserAccountByPage(0,userInfo);
        if(userInfoList.size() != 1 ){
            //TODU trhow Exception
        }
        return userInfoList.get(0);
    }

    @RequestMapping(value = "/editUserAccount", method = RequestMethod.POST)
    public String editUser(UserAccountVo userAccountVo,Model model) {
        userFinanceAccountService.updateByPrimaryKeySelective(userAccountVo,0);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userAccountVo.getUserName());
        userInfo.setNickName(userAccountVo.getNickName());
        //return this.getAllUserList(userInfo,model);
        return  this.getAllUserAccountList(userInfo,model,null);
    }



}
