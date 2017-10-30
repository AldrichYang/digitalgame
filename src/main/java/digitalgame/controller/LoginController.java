package digitalgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yh on 17/10/11.
 */
@Controller
@RequestMapping("")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

//    @RequestMapping(value = "/error", method = RequestMethod.GET)
//    public String error(){
//        return "404";
//    }

    @RequestMapping("/")
    public String index() {
        return "forward:login";
    }

}
