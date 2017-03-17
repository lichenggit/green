package cn.cbbhy.schoolshare.logic.shiro.realm;

import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by suruijia on 2015/11/2.
 */
public class MyShrioRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("in doGetAuthorizationInfo");
        String username = (String) principalCollection.fromRealm(getName()).iterator().next();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = accountService.selectRolesByUsername(username);
        simpleAuthorizationInfo.setRoles(roles);
        Set<String> pers = accountService.selectPermissionsByUsername(username);
        simpleAuthorizationInfo.setStringPermissions(pers);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("in doGetAuthenticationInfo");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = accountService.selectUserByUsername(username);
        if (user == null) {
            return null;//未知用户
        }
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userId",user.getUserId());
        return new SimpleAuthenticationInfo(
                user.getUsername(),//用户名
                user.getPassword(),//密码
                ByteSource.Util.bytes(user.getUsername() + user.getSalt()),//salt = username+salt
                getName()  //realm name
        );
    }
}