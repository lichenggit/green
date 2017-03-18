package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.logic.model.ShareOrder;
import cn.cbbhy.schoolshare.logic.model.ShareOrderDetails;
import cn.cbbhy.schoolshare.logic.model.SharePool;
import cn.cbbhy.schoolshare.logic.model.vo.JsonModel;
import cn.cbbhy.schoolshare.logic.service.SharePoolService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 加入共享池
     *
     * @param sharePool
     * @param session
     */
    @RequestMapping("/addtosharepool.json")
    @ResponseBody
    public void addToSharePool(SharePool sharePool, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        JsonModel jsonModel = new JsonModel(0, "添加成功");
        sharePool.setUserId(userId);
        sharePoolService.addToSharePool(sharePool);
    }

    /**
     * 查看我的共享池
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/sharepools.html")
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
    @RequestMapping("/shareorders.html")
    public String listMyShareOrders(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("orders", sharePoolService.listShareOrderByUsere(userId));
        return "order/orders";
    }

    /**
     * 生成订单
     * @param body
     * @param session
     * @return
     */
    @RequestMapping("/addorder.json")
    @ResponseBody
    public JsonModel addorder(@RequestBody String body, HttpSession session) {
        List<ShareOrderDetails> orderDetailsList = JSON.parseArray(body.trim(),ShareOrderDetails.class);
        String userId = (String) session.getAttribute("userId");
        String shareOrderId=sharePoolService.addOneOrder(userId,orderDetailsList);
        JsonModel jsonModel = new JsonModel(0, shareOrderId);
        return jsonModel;
    }

    /**
     * 订单支付页面
     * @param shareOrderId
     * @param session
     * @return
     */
    @RequestMapping(value = "/payorder.html",method = RequestMethod.GET)
    public String payorder(String shareOrderId,HttpSession session,Model model) {
        String userId = (String) session.getAttribute("userId");
        model.addAttribute("order",sharePoolService.findShareOrder(shareOrderId));
        System.out.println(JSON.toJSONString(sharePoolService.findShareOrder(shareOrderId)));
        return "order/pay_order";
    }

}
