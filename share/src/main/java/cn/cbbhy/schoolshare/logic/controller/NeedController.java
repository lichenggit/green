package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.base.util.MailSend;
import cn.cbbhy.schoolshare.base.util.StatusUtil;
import cn.cbbhy.schoolshare.logic.model.Need;
import cn.cbbhy.schoolshare.logic.model.NeedHave;
import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.model.condition.NeedFilterCondition;
import cn.cbbhy.schoolshare.logic.model.vo.JsonModel;
import cn.cbbhy.schoolshare.logic.service.AccountService;
import cn.cbbhy.schoolshare.logic.service.NeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private AccountService accountService;

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
        String userId = (String) session.getAttribute("userId");
        need.setUserId(userId);
        needService.addNeed(need);
        return "redirect:/index.html";
    }

    @RequestMapping("/adminNeed.html")
    public String adminNeed(HttpSession session, Model model, String status) {
        String userId = (String) session.getAttribute("userId");
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

    @RequestMapping("/adminNeedHave.html")
    public String adminNeedHave(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("list", needService.selectByNeederId(userId));
        return "need/myNeedHave";
    }

    /**
     * 我有此物
     *
     * @param needHave
     * @param session
     * @return
     */
    @RequestMapping("/addNeedHave.json")
    @ResponseBody
    public JsonModel addNeedHave(NeedHave needHave, HttpServletRequest request, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        needHave.setUserId(userId);
        needService.addNeedHave(needHave);
        JsonModel jsonModel = new JsonModel(0, "操作成功");

        User user = accountService.selectUserByUserId(needHave.getNeederId());
        if (user != null && user.getEmail() != null) {
            String url = request.getLocalAddr() + ":" + request.getLocalPort() + request.getContextPath() + "/article/details.html?articleId=" + needHave.getNeedId();
            String mailText = "亲爱的用户，您需要的" + needHave.getNeedName() + "已经有了，"
                    + "<a href = \"" + url + "\">" + "点击查看</a>";
            MailSend mailSend = new MailSend(user.getEmail());
            try {
                mailSend.sendMail("需求用品通知", mailText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonModel;
    }

}
