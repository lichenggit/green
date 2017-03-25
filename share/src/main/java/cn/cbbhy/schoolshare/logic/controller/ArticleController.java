package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.logic.model.Article;
import cn.cbbhy.schoolshare.logic.model.condition.ArticleFilterCondition;
import cn.cbbhy.schoolshare.logic.model.vo.JsonModel;
import cn.cbbhy.schoolshare.logic.service.ArticleService;
import cn.cbbhy.schoolshare.logic.service.CategoryService;
import cn.cbbhy.schoolshare.logic.websocket.WebsocketHandler;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


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
    @Resource
    private WebsocketHandler websocketHandler;


    /**
     * 物品列表页
     *
     * @param model
     * @param condition
     * @return
     */
    @RequestMapping(value = "/index.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model, ArticleFilterCondition condition) throws IOException {
        model.addAttribute("condition", condition);
        condition.setStatus("NORMAL");
        model.addAttribute("list", articleService.selectByConditions(condition));
        TextMessage textMessage = new TextMessage("test");
        websocketHandler.sendMessageToUsers(textMessage);
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
    public String details( HttpSession session,Model model, String articleId) {
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("item", articleService.searchArticleById(userId,articleId));
        return "article/articleDetails";
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
        articleService.addArticle(userId,article);
        return "redirect:/index.html";
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
