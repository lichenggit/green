package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.logic.model.vo.JsonModel;
import cn.cbbhy.schoolshare.logic.service.ArticleScanService;
import cn.cbbhy.schoolshare.logic.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/12/16 0016.
 * <p>
 * 关注物品模块
 */
@Controller
@RequestMapping("/attention")
public class AttentionController {

    @Autowired
    private AttentionService attentionService;
    @Autowired
    private ArticleScanService articleScanService;

    /**
     * 查看当前用户的收藏页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/adminAttentions.html", method = RequestMethod.GET)
    public String myAttention(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("list", attentionService.searchAttentionsByUserId(userId));
        return "attention/attentionArticle";
    }

    /**
     * 检查当前用户是否有关注articleId物品
     *
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/checkAttention.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonModel checkAttention(HttpSession session, String articleId) {
        JsonModel jsonModel = new JsonModel(1, "关注");
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            boolean flag = attentionService.checkAttention(userId, articleId);
            if (flag) {
                jsonModel.setCode(0);
                jsonModel.setDesc("已关注");
            }
        }
        return jsonModel;
    }

    /**
     * 当前用户添加收藏
     *
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/addAttention.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel addAttention(HttpSession session, String articleId) {
        JsonModel jsonModel = new JsonModel(0, "关注成功");
        try {
            String userId = (String) session.getAttribute("userId");
            attentionService.addAttention(userId, articleId);
        } catch (Exception e) {
            jsonModel.setCode(1);
            jsonModel.setDesc("关注失败");
        }
        return jsonModel;
    }

    /**
     * 取消关注
     * @param session
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/deleteAttention.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel removeAttention(HttpSession session, String articleId) {
        JsonModel jsonModel = new JsonModel(0, "取消关注成功");
        try {
            String userId = (String) session.getAttribute("userId");
            attentionService.deleteAttention(userId, articleId);
        } catch (Exception e) {
            jsonModel.setCode(1);
            jsonModel.setDesc("取消关注失败");
        }
        return jsonModel;
    }

    @RequestMapping(value = "/adminScan.html")
    public String listArticlesScan(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("list", articleScanService.listArticleScanByUserId(userId));
        return "attention/scanArticles";
    }
}
