package digitalgame.controller;

import digitalgame.model.po.OddsInfo;
import digitalgame.service.OddsInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/odds")
public class OddsController {

    @Autowired
    private OddsInfoService oddsInfoService;

    @RequestMapping(value = "/oddsList", method = {RequestMethod.GET,RequestMethod.POST})
    public String getAllOddsList(@ModelAttribute OddsInfo oddsInfo, Model model) {
        List<OddsInfo> oddsInfoList = oddsInfoService.selectOddsList();
        model.addAttribute("oddsList", oddsInfoList);
        return "oddsList";
    }

    @RequestMapping(value = "/editOddsInfo", method = RequestMethod.POST)
    public String editOddsInfo(@ModelAttribute OddsInfo oddsInfo,Model model) {
        oddsInfo.setUpdateTime(new Date().toString());
        oddsInfoService.editOddsInfo(oddsInfo);
        return this.getAllOddsList(oddsInfo,model);
    }

    @RequestMapping(value = "/oddsInfoHtml", method = {RequestMethod.GET,RequestMethod.POST})
    public String oddsInfoHtml(int oddsId,Model model) {
        model.addAttribute("oddsInfo", oddsInfoService.selectOddsInfo(oddsId));
        return "editOdds";
    }

    @RequestMapping(value = "/delOddsInfo", method = {RequestMethod.GET,RequestMethod.POST})
    public String delOddsInfo(int oddsId,Model model) {
        oddsInfoService.deleteOddsInfo(oddsId);
        return this.getAllOddsList(null,model);
    }

    @RequestMapping(value = "/addOddsInfo", method = {RequestMethod.POST})
    public String addOddsInfo(@ModelAttribute OddsInfo oddsInfo,Model model) {
        oddsInfo.setCreateTime(new Date().toString());
        oddsInfoService.addOddsInfo(oddsInfo);
        return this.getAllOddsList(null,model);
    }

    @RequestMapping(value = "/addOddshtml", method = {RequestMethod.GET})
    public String addOddsHtml(@ModelAttribute OddsInfo oddsInfo,Model model) {
        return "addOdds";
    }
}
