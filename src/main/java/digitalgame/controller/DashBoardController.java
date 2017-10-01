package digitalgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yh on 17/9/29.
 */
@Controller
@RequestMapping("/")
public class DashBoardController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String dashboard() {
        return "starter";
    }
}
