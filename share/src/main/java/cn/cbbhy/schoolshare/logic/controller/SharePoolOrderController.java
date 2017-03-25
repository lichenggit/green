package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.logic.model.ShareOrderDetails;
import cn.cbbhy.schoolshare.logic.model.SharePool;
import cn.cbbhy.schoolshare.logic.model.StudentAuth;
import cn.cbbhy.schoolshare.logic.model.vo.JsonModel;
import cn.cbbhy.schoolshare.logic.service.AccountService;
import cn.cbbhy.schoolshare.logic.service.ArticleService;
import cn.cbbhy.schoolshare.logic.service.SharePoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by duoyi on 17-3-18.
 */
@Controller
@RequestMapping("/order")
public class SharePoolOrderController {
    @Autowired
    private SharePoolService sharePoolService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AccountService accountService;

    /**
     * 加入共享池
     *
     * @param sharePool
     * @param session
     */
    @RequestMapping("/addtosharepool.json")
    @ResponseBody
    public JsonModel addToSharePool(SharePool sharePool, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        JsonModel jsonModel = new JsonModel(0, "添加成功");
        sharePool.setUserId(userId);
        sharePoolService.addToSharePool(sharePool);
        return jsonModel;
    }

    /**
     * 查看我的共享池
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/adminsharepools.html")
    public String listMySharePool(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("sharepools", sharePoolService.listSharePoolByUser(userId));
        return "order/sharepool";
    }

    /**
     * 查看我的订单
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/adminshareorders.html")
    public String listMyShareOrders(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("orders", sharePoolService.listShareOrderByUser(userId));
        return "order/orders";
    }

    /**
     * 生成订单
     *
     * @param orderDetailsList
     * @param session
     * @return
     */
    @RequestMapping("/addorder.json")
    @ResponseBody
    public JsonModel addorder(@RequestBody List<ShareOrderDetails> orderDetailsList, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        return sharePoolService.addOneOrder(userId, orderDetailsList);
    }

    /**
     * 订单支付页面
     *
     * @param shareOrderId
     * @param session
     * @return
     */
    @RequestMapping(value = "/readypayorder.html", method = RequestMethod.GET)
    public String readypayorder(String shareOrderId, HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("order", sharePoolService.findShareOrder(userId, shareOrderId));
        boolean isFreeUserIdentify = judgeUserIdentify(userId);
        if (isFreeUserIdentify) {
            model.addAttribute("isStudent", "true");
        } else {
            model.addAttribute("isStudent", "false");
        }

        return "order/pay_order";
    }

    @RequestMapping(value = "/payorder.html")
    public String payorder(String shareOrderId, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        boolean isFreeUserIdentify = judgeUserIdentify(userId);
        if (isFreeUserIdentify) {
            boolean isSuccessPay = sharePoolService.addPayOneOrder(userId, shareOrderId);
            if(isSuccessPay){
                redirectAttributes.addAttribute("shareOrderId", shareOrderId);
                return "redirect:/order/gainContacts.html";
            }
            return "/order/pay_fail";
        }
        model.addAttribute("shareOrderId", shareOrderId);
        return "order/pay";
    }

    @RequestMapping(value = "/payorderForNoFree.html")
    public String payorderForNoFree(String shareOrderId, HttpSession session,RedirectAttributes redirectAttributes){
        String userId = (String) session.getAttribute("userId");
        boolean isSuccessPay = sharePoolService.addPayOneOrder(userId, shareOrderId);
        if(isSuccessPay){
            redirectAttributes.addAttribute("shareOrderId", shareOrderId);
            return "redirect:/order/gainContacts.html";
        }
        return "/order/pay_fail";
    }

    private boolean judgeUserIdentify(String userId) {
        return accountService.countAuthByUser(userId)>0;
    }

    /**
     * 获取物品的联系方式
     *
     * @param model
     * @param shareOrderId
     * @return
     */
    @RequestMapping(value = "/gainContacts.html", method = RequestMethod.GET)
    public String gainContacts(String shareOrderId, HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("order", sharePoolService.findPayShareOrder(userId, shareOrderId));
        return "order/contacts";
    }

}
