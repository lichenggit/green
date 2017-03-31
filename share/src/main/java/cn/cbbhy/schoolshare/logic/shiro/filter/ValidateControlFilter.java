package cn.cbbhy.schoolshare.logic.shiro.filter;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class ValidateControlFilter extends AccessControlFilter {
    private boolean validateEnable = true;
    private String validateParam = "verifyCode";
    private String failureKeyAttribute = "shiroLoginFailure";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        //1、设置验证码是否开启属性，页面可以根据该属性来决定是否显示验证码
        request.setAttribute("validateEnable", validateEnable);
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        //2、判断验证码是否禁用 或不是表单提交（允许访问）
        if (!validateEnable || !"post".equalsIgnoreCase(httpServletRequest.getMethod())) {
            return true;
        }
        //3、此时是表单提交，验证验证码是否正确
        return this.matchVerifyCode(httpServletRequest);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //如果验证码失败了，存储失败key属性
        request.setAttribute(failureKeyAttribute, "validateError");
        return true;
    }

    private boolean matchVerifyCode(HttpServletRequest request) {
        String verifyCodeValue = request.getParameter(validateParam);
        HttpSession session = request.getSession();
        if (session != null) {
            String verifyCode = (String) session.getAttribute(validateParam);
            if (verifyCode != null) {
                return verifyCode.equalsIgnoreCase(verifyCodeValue);
            }
        }
        return false;
    }

    public boolean isValidateEnable() {
        return validateEnable;
    }

    public void setValidateEnable(boolean validateEnable) {
        this.validateEnable = validateEnable;
    }

    public String getValidateParam() {
        return validateParam;
    }

    public void setValidateParam(String validateParam) {
        this.validateParam = validateParam;
    }
}
