package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.logic.model.Article;
import cn.cbbhy.schoolshare.logic.model.Need;
import cn.cbbhy.schoolshare.logic.model.condition.ArticleFilterCondition;
import cn.cbbhy.schoolshare.logic.model.condition.NeedFilterCondition;
import cn.cbbhy.schoolshare.logic.service.ArticleService;
import cn.cbbhy.schoolshare.logic.service.NeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 *
 * 门户首页模块
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private NeedService needService;

    @RequestMapping("/index.html")
    public String index(Model model) {
        List<Article> articles = articleService.selectByConditions(new ArticleFilterCondition());
        List<Need> needs = needService.selectByConditions(new NeedFilterCondition());
        model.addAttribute("articles", articles);
        model.addAttribute("needs", needs);
        return "home/home";
//        return "<script>we();</script>";
    }
}
