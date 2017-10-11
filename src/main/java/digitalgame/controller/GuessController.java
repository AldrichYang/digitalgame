package digitalgame.controller;

import digitalgame.model.po.BetInfo;
import digitalgame.service.GuessService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 用于处理和竞猜相关的控制跳转
 *
 * @author simon
 * @version 1.0 2017/10/01
 */
@Controller
@RequestMapping("/guess")
public class GuessController {

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(){
        return "guess";
    }

    @Autowired
    GuessService guessService;

    /**
     * 解析下注内容，返回标准格式下注内容
     * @return
     */
    @RequestMapping(value="/analysis", method=RequestMethod.POST)
    public String analysisBet(@RequestParam(value = "betContent",required = true)String betContent){
        List<BetInfo> list = guessService.analysisBetContent(betContent);
        return JSONObject.fromObject(list).toString();
    }

    /**
     * 下注
     * @return
     */
    public String doBet(){
        return "";
    }
}
