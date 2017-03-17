package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.base.util.StatusUtil;
import cn.cbbhy.schoolshare.logic.model.Need;
import cn.cbbhy.schoolshare.logic.model.condition.NeedFilterCondition;
import cn.cbbhy.schoolshare.logic.service.NeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/11/17 0017.
 * <p>
 * 物品需求模块
 */
@Controller
@RequestMapping("/need")
public class NeedController {
    @Autowired
    private NeedService needService;

    @RequestMapping("/index.html")
    public String index(Model model, NeedFilterCondition condition) {
        model.addAttribute("list", needService.selectByConditions(condition));
        return "need/need";
    }

    @RequestMapping(value = "/addNeed.html", method = RequestMethod.GET)
    public String addArticle() {
        return "need/addNeed";
    }

    @RequestMapping(value = "/addNeed.html", method = RequestMethod.POST)
    public String addArticleSubmit(HttpSession session, Need need) {
        String userId = (String) session.getAttribute("user");
        need.setUserId(userId);
        needService.addNeed(need);
        return "redirect:/index.html";
    }

    @RequestMapping("/adminNeed.html")
    public String adminNeed(HttpSession session, Model model, String status) {
        String userId = (String) session.getAttribute("user");
        model.addAttribute("list", needService.searchByUserId(userId, status));
        model.addAttribute("statusList", StatusUtil.getStatusListForPart(StatusUtil.XQWP));
        model.addAttribute("status", status);
        return "need/myNeed";
    }

    @RequestMapping("/updateNeedStatus.html")
    public String updateNeedStatus(HttpSession session, Model model, String needId, String status) {
        needService.updateNeedStatus(needId, status);
        return "redirect:/need/adminNeed.html";
    }
}
