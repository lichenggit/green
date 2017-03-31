package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1 0001.
 * <p>
 * 对外开放入口
 */
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/index.html")
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        //自动登录
        if (subject.isRemembered() && !subject.isAuthenticated()) {
            User user = accountService.selectUserByUsername((String) subject.getPrincipal());
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            try {
                subject.login(token);
                subject.getSession().setAttribute("userId", user.getUserId());
            } catch (Exception e) {

            }
        }
        return "index";
    }

    @RequestMapping("/admin.html")
    public String admin(String type, Model model) {
        model.addAttribute("url", getAdminUrl(type));
        return "admin";
    }

    private static Map<String, String> URL_MAP = new HashMap<>();

    static {
        URL_MAP.put("articles", "/article/adminArticles.html");
        URL_MAP.put("needs", "/need/adminNeed.html");
        URL_MAP.put("attentions", "/attention/adminAttentions.html");
        URL_MAP.put("scans", "/attention/adminscan.html");
        URL_MAP.put("sharepools", "/order/adminsharepools.html");
        URL_MAP.put("shareorders", "/order/adminshareorders.html");
    }

    private String getAdminUrl(String type) {
        if (type == null || "".equals(type)) {
            return "";
        }
        if (!URL_MAP.containsKey(type)) {
            return "";
        }
        return URL_MAP.get(type);
    }

    @RequestMapping("/test.json")
    @ResponseBody
    public String test(@RequestBody String json,String age){
        System.out.println(json+age);
        return json+age;
    }
}
