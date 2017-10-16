package digitalgame.controller;

import digitalgame.model.po.BetInfo;
import digitalgame.model.po.UserBetInfo;
import digitalgame.service.GuessService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 用于处理和竞猜相关的控制跳转
 *
 * 处理流程：
 * 1、解析竞猜内容，判断积分是否足够
 * 2、开奖（开奖前先下注，然后返奖）
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
    @ResponseBody
    @RequestMapping(value="/analysis", method=RequestMethod.POST)
    public String analysisBet(@RequestParam(value = "betContent",required = true)String betContent){
        List<UserBetInfo> list = guessService.analysisBetContent(betContent);
        String result = JSONArray.fromObject(list).toString();
        System.out.println(result);
        return result;
    }

    /**
     * 下注
     * @return
     */
    public String doBet(){
        return "";
    }
}
