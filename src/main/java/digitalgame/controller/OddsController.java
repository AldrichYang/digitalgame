package digitalgame.controller;

import digitalgame.service.OddsInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/odds")
public class OddsController {

    public OddsInfoService oddsInfoService;

    @RequestMapping(value = "/oddsList", method = RequestMethod.GET)
    public String getAllOddsList(Model model) {
        // List<UserInfo> userInfoList = userInfoMapper.selectByPage(0,0);
        // model.addAttribute("userList", userInfoList);
        return "userList";
    }
}
