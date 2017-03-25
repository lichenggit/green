package cn.cbbhy.schoolshare.logic.shiro.filter;

import cn.cbbhy.schoolshare.logic.model.vo.JsonModel;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class FormValidateAuthenticationFilter extends FormAuthenticationFilter {
    private static final JsonModel NOLOGIN_JSON=new JsonModel(2,"没有登录");
    private static final String NOLOGIN_LOAD="<script>onNoLogin()</script>";

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //如果验证码错误，则终止
        if (request.getAttribute(getFailureKeyAttribute()) != null) {
            return false;
        }
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String ajaxHeader = req.getHeader("x-requested-with");
        //如果是ajax请求响应头会有x-requested-with
        if (ajaxHeader != null && "XMLHttpRequest".equalsIgnoreCase(ajaxHeader)) {
            String requestUri = req.getRequestURI();
            PrintWriter out = resp.getWriter();
            //普通的ajax请求
            if(requestUri!=null&&requestUri.endsWith(".json")){
                resp.setContentType("application/json; charset=UTF-8");
                out.write(JSON.toJSONString(NOLOGIN_JSON));
                out.flush();
                return false;
            }
            //ajax的load请求
            resp.setContentType("text/html; charset=UTF-8");
            out.write(NOLOGIN_LOAD);
            out.flush();
            return false;
        }
        //普通的href链接形式，交给父类来处理
        return super.onAccessDenied(request, response);
    }
}
