package digitalgame.controller;

import digitalgame.model.po.*;
import com.google.common.base.Strings;
import digitalgame.service.BetResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/betResult")
public class BetResultController {

    @Autowired
    private BetResultService betResultService;

    @RequestMapping(value = "/betResultList", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllUserList(@ModelAttribute OddsBetResultVo oddsBetResultVo, Model model, HttpServletRequest request) {
        int currentPageNo = 1;
        if(request != null ){
            String pageNo = request.getParameter("pageNo");
            if(!Strings.isNullOrEmpty(pageNo)) currentPageNo = Integer.parseInt(pageNo);
        }

        int count = betResultService.selectBetSultByPage(0, oddsBetResultVo).size();

        List<OddsBetResultVo> betResultsList = new ArrayList();
        if(Strings.isNullOrEmpty(oddsBetResultVo.getResultDate())){
            OpenInfo openInfo = betResultService.selectOpenInfoLast();
            if(openInfo != null){
                oddsBetResultVo.setResultDate(openInfo.getOpenNo() + "");
                betResultsList = betResultService.selectBetSultByPage(currentPageNo, oddsBetResultVo);
                int sum = betResultService.selectBetNumberSum(oddsBetResultVo.getResultDate());
                OddsBetResultVo newBetVo = new OddsBetResultVo();
                newBetVo.setBetUser("");
                newBetVo.setResultDate("汇总");
                newBetVo.setResultNumber(sum);
                betResultsList.add(newBetVo);
            }
        }

        int pageNo = count/10 + (count % 10 == 0 ? 0 : 1);
        model.addAttribute("betResultsList", betResultsList);
        model.addAttribute("queryCond",oddsBetResultVo);
        model.addAttribute("inallPageDesc","总条数："+count+",当前第"+currentPageNo+"页,总共" + pageNo + "页");
        model.addAttribute("currentPage",currentPageNo);
        model.addAttribute("inallPage",pageNo);
        return "betResultList";
    }
}
