package digitalgame.controller;

import digitalgame.model.po.OddsInfo;
import digitalgame.service.OddsInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/odds")
public class OddsController {

    @Autowired
    private OddsInfoService oddsInfoService;

    @RequestMapping(value = "/oddsList", method = {RequestMethod.GET,RequestMethod.POST})
    public String getAllOddsList(@ModelAttribute OddsInfo oddsInfo, Model model) {
        List<OddsInfo> oddsInfoList = oddsInfoService.selectOddsList();
        System.out.println(oddsInfoList.size());
        model.addAttribute("oddsList", oddsInfoList);
        return "oddsList";
    }
}
