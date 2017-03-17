package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.logic.model.AccumulatePoint;
import cn.cbbhy.schoolshare.logic.model.Article;
import cn.cbbhy.schoolshare.logic.model.condition.ArticleFilterCondition;
import cn.cbbhy.schoolshare.logic.model.vo.JsonModel;
import cn.cbbhy.schoolshare.logic.service.AccumulatePointService;
import cn.cbbhy.schoolshare.logic.service.ArticleService;
import cn.cbbhy.schoolshare.logic.service.CategoryService;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by Administrator on 2016/11/26 0026.
 * <p>
 * 物品模块
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AccumulatePointService accumulatePointService;

    /**
     * 物品列表页
     *
     * @param model
     * @param condition
     * @return
     */
    @RequestMapping(value = "/index.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model, ArticleFilterCondition condition) {
        System.out.println("+++" + JSON.toJSONString(condition));
        model.addAttribute("condition", condition);
        model.addAttribute("list", articleService.selectByConditions(condition));
        return "article/article";
    }

    /**
     * 物品详情页
     *
     * @param model
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/details.html", method = RequestMethod.GET)
    public String details(Model model, String articleId) {
        model.addAttribute("item", articleService.searchArticleById(articleId));
        return "article/articleDetails";
    }

    /**
     * 获取物品的联系方式
     *
     * @param model
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/gainContacts.html", method = RequestMethod.GET)
    public String gainContacts(HttpServletRequest request, HttpSession session, String articleId, Model model) {
        String userId = (String) session.getAttribute("userId");
        Article article = articleService.searchArticleById(articleId);
        model.addAttribute("accessEnable", article.getAccessEnable());
        switch (article.getAccessEnable()) {
            case "NONE":
                break;
            case "AUTHC":
                Subject subject = SecurityUtils.getSubject();
                if (!subject.isAuthenticated()) {
                    WebUtils.saveRequest(request);
                    return "redirect:/user/login.html";
                }
                break;
            case "SHARER":
                subject = SecurityUtils.getSubject();
                if (!subject.isAuthenticated()) {
                    WebUtils.saveRequest(request);
                    return "redirect:/user/login.html";
                }
                int total = articleService.countArticleByUserId(userId);
                if (0 == total) {
                    model.addAttribute("gainContactsResultMsg", "很抱歉，该物品所有者要求只有分享过自己物品的用户才能获取该物品。");
                    return "article/contacts-wait";
                }
                break;
            case "REQUEST":
                model.addAttribute("gainContactsResultMsg", "我们以将您的请求转发给了该物品所有者，请耐心等候，一有消息，我们会马上通知您。");
                return "article/contacts-wait";
        }
        model.addAttribute("article", article);
        model.addAttribute("user", articleService.searchContacts(articleId, article.getUserId()));
        return "article/contacts";
    }

    /**
     * 获取物品类别数据
     *
     * @return
     */
    @RequestMapping(value = "/getCategorys.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonModel getCategorys() {
        JsonModel model = new JsonModel(0);
        try {
            model.setData(categoryService.searchAll());
        } catch (Exception e) {
            model.setCode(1);
            model.setDesc(e.getMessage());
        }
        return model;
    }

    /**
     * 发布闲置物品页
     *
     * @return
     */
    @RequestMapping(value = "/addArticle.html", method = RequestMethod.GET)
    public String addArticle() {
        return "article/addArticle";
    }

    /**
     * 发布闲置物品
     *
     * @return
     */
    @RequestMapping(value = "/addArticle.html", method = RequestMethod.POST)
    public String addArticleSubmit(HttpSession session, Article article) {
        String userId = (String) session.getAttribute("userId");
        article.setUserId(userId);
        articleService.addArticle(article);

        //积分
        AccumulatePoint accumulatePoint = new AccumulatePoint();
        accumulatePoint.setUserId(userId);
        accumulatePoint.setPointType("PUBLISH");
        accumulatePoint.setPoints(50);
        accumulatePoint.setRemark("发布闲置物品");
        accumulatePointService.addPointItem(accumulatePoint);

        return "redirect:/admin.html";
    }

    /**
     * 管理我的物品
     *
     * @param model
     * @param status
     * @return
     */
    @RequestMapping(value = "/adminArticles.html")
    public String adminArticles(HttpSession session, Model model, String status) {
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("list", articleService.searchArticleByUserId(userId, status));
        model.addAttribute("status", status);
        return "article/myArticles";
    }

}
