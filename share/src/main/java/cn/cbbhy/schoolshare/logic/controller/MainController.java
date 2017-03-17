package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        model.addAttribute("jrebel","可以了55");
        Subject subject = SecurityUtils.getSubject();
        //自动登录
        if (subject.isRemembered() && !subject.isAuthenticated()) {
            User user = accountService.selectUserByUsername((String) subject.getPrincipal());
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            try {
                subject.login(token);
            } catch (Exception e) {

            }
        }
        return "index";
    }

    @RequestMapping("/admin.html")
    public String admin() {
        return "admin";
    }
}
