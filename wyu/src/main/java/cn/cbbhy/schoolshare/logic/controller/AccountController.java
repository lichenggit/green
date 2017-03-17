package cn.cbbhy.schoolshare.logic.controller;

import cn.cbbhy.schoolshare.base.util.VerifyCode;
import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.model.UserCategory;
import cn.cbbhy.schoolshare.logic.model.vo.JsonModel;
import cn.cbbhy.schoolshare.logic.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String getLoginPage(Model model) {
        String url = "login";
        model.addAttribute("jrebel","ss");
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            url = "redirect:/table/admin.html";
        }
        return url;
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String login(User user, RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            subject.login(token);
            subject.getSession().setAttribute("user", accountService.selectUserByUsername(user.getUsername()));
            return "redirect:/table/admin.html";
        } catch (UnknownAccountException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute(user);
            redirectAttributes.addFlashAttribute("errMsg", "账号不存在");
        } catch (IncorrectCredentialsException e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute(user);
            redirectAttributes.addFlashAttribute("errMsg", "用户名/密码错误");
        } catch (Exception e3) {
            e3.printStackTrace();
            redirectAttributes.addFlashAttribute(user);
            redirectAttributes.addFlashAttribute("errMsg", "登录失败");
        }
        return "redirect:/user/login.html";
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping(value = "/logout.html")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/user/login.html";
    }

    /**
     * 获取验证码
     *
     * @param session
     * @param response
     * @throws IOException
     */
    @RequestMapping("/verifyCode.pic")
    public void verifyCode(HttpSession session, HttpServletResponse response) throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        VerifyCode.output(verifyCode.getImage(), response.getOutputStream());
        session.setAttribute("verifyCode", verifyCode.getText());
    }

    /**
     * 校验验证码
     *
     * @param session
     * @param verifyCode
     * @return
     */
    @RequestMapping("/checkVerifyCode.json")
    @ResponseBody
    public boolean checkVerifyCode(HttpSession session, String verifyCode) {
        String verifyCodePure = (String) session.getAttribute("verifyCode");
        if (verifyCode == null || verifyCode.equals("")) {
            return false;
        } else if (verifyCode.equalsIgnoreCase(verifyCodePure)) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/listAllUser.html")
    public String listAllUser(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("users", accountService.listAllUser(user));
        return "userManage";
    }

    /**
     * 注册页面
     *
     * @return
     */
    @RequestMapping(value = "/addUser.html", method = RequestMethod.GET)
    public String getRegisterPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("roles", accountService.listAllRoles());
        return "addUser";
    }


    @RequestMapping(value = "/addUser.html", method = RequestMethod.POST)
    public String register(HttpSession session, User user) {
        String categoryLevel = ((User) session.getAttribute("user")).getUserCategory().getCategoryLevel();
        accountService.addUser(categoryLevel, user);
        return "redirect:/user/listAllUser.html";
    }

    @RequestMapping(value = "/isExistUsername.json")
    @ResponseBody
    public boolean isExistUsername(String username) {
        return !accountService.isExistUsername(username);
    }

    @RequestMapping(value = "/rolePermission.html", method = RequestMethod.GET)
    public String rolePermission(Model model) {
        model.addAttribute("roles", accountService.listAllRoles());
        return "rolePermissionManage";
    }

    @RequestMapping(value = "/userDetails.html", method = RequestMethod.GET)
    public String userDetails(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("roles", accountService.selectRolesByUserId(user.getUserId()));
        return "userDetails";
    }

    @RequestMapping(value = "/addUserCategory.html", method = RequestMethod.GET)
    public String addUserCategory() {
        return "addUserCategory";
    }

    @RequestMapping(value = "/getSubCategories.html", method = RequestMethod.GET)
    @ResponseBody
    public JsonModel getSubCategories(HttpSession session) {
        User user = (User) session.getAttribute("user");
        JsonModel jsonModel = new JsonModel(0);
        jsonModel.setData(accountService.listSubCategories(user.getCategoryId()));
        return jsonModel;
    }

    @RequestMapping(value = "/addUserCategory.html", method = RequestMethod.POST)
    @ResponseBody
    public String addUserCategorySubmit(HttpSession session, UserCategory userCategory) {
        User user = (User) session.getAttribute("user");
        JsonModel jsonModel = new JsonModel(0, "添加成功");
        accountService.addSubCategory(user, userCategory);
        return "addUserCategory";
    }
}
