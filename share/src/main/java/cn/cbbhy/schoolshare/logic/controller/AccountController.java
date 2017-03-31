package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.base.util.VerifyCode;
import cn.cbbhy.schoolshare.logic.model.StudentAuth;
import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.model.vo.JsonModel;
import cn.cbbhy.schoolshare.logic.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/17 0017.
 * <p>
 * 用户账号模块
 */
@Controller
@RequestMapping("/user")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String getLoginPage() {
        String url = "login";
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            url = "redirect:/index.html";
        }
        HttpServlet servlet;
        return url;
    }

    @RequestMapping("/login.json")
    @ResponseBody
    public JsonModel loginDo(String username, String password) {
        JsonModel jsonModel = new JsonModel(0, "登录成功");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (Exception e) {
            jsonModel.setCode(1);
            jsonModel.setDesc("登录失败");
        }
        return jsonModel;
    }

    /**
     * 登录
     * 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
     *
     * @param request
     */
    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String login(User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(user);
        // 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        // 根据shiro返回的异常类路径判断，抛出指定异常信息
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                redirectAttributes.addFlashAttribute("errMsg", "账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                redirectAttributes.addFlashAttribute("errMsg", "用户名/密码错误");
            } else if ("validateError".equals(exceptionClassName)) {
                redirectAttributes.addFlashAttribute("errMsg", "验证码错误");
            } else {
                redirectAttributes.addFlashAttribute("errMsg", "未知错误");
            }
        }
        return "redirect:/user/login.html";
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping(value = "/logout.html")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/index.html";
    }

    /**
     * 获取验证码
     *
     * @param session
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/verifyCode.pic", method = RequestMethod.GET)
    public void verifyCode(HttpSession session, HttpServletResponse response) throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        VerifyCode.output(verifyCode.getImage(), response.getOutputStream());
        session.setAttribute("verifyCode", verifyCode.getText());
    }

    /**
     * 验证码校验
     *
     * @param session
     * @param verifyCode
     * @return
     */
    @RequestMapping(value = "/checkVerifyCode.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel checkVerifyCode(HttpSession session, String verifyCode) {
        JsonModel jsonModel = new JsonModel(0, "验证码正确");
        String verifyCodePure = (String) session.getAttribute("verifyCode");
        if (verifyCode == null || verifyCode.equals("")) {
            jsonModel.setCode(1);
            jsonModel.setDesc("验证码为空");
        } else if (!verifyCode.equalsIgnoreCase(verifyCodePure)) {
            jsonModel.setCode(1);
            jsonModel.setDesc("验证码错误");
        }
        return jsonModel;
    }

    /**
     * 注册页面
     *
     * @return
     */
    @RequestMapping(value = "/register.html", method = RequestMethod.GET)
    public String getRegisterPage() {
        return "register";
    }

    @RequestMapping(value = "/register.html", method = RequestMethod.POST)
    public String register(User user, RedirectAttributes redirectAttributes) {
        accountService.addUser(user);
        return "redirect:/user/login.html";
    }
    @RequestMapping(value = "/addStudentAuthc.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel studentAuthc(HttpSession session,StudentAuth studentAuth) {
        String userId = (String) session.getAttribute("userId");
        JsonModel jsonModel = new JsonModel(0,"success");
        studentAuth.setUserId(userId);
        accountService.addStudentAuth(studentAuth);
        return jsonModel;
    }

}
