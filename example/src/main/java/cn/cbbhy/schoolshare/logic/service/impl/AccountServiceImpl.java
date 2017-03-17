package cn.cbbhy.schoolshare.logic.service.impl;

import cn.cbbhy.schoolshare.logic.dao.UserDao;
import cn.cbbhy.schoolshare.logic.model.Permission;
import cn.cbbhy.schoolshare.logic.model.Role;
import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserDao userDao;

    @Override
    public User selectUserByUsername(String username) {
        return userDao.selectUserByUsername(username);
    }

    @Override
    public Set<String> selectRolesByUsername(String username) {
        Set<String> result = new HashSet<>();
        User user = userDao.selectUserByUsername(username);
        List<Role> roles = userDao.selectRolesByUserId(user.getUserId());
        for (Role role : roles) {
            result.add(role.getRoleName());
        }
        return result;
    }

    @Override
    public Set<String> selectPermissionsByUsername(String username) {
        Set<String> result = new HashSet<>();
        User user = userDao.selectUserByUsername(username);
        List<Role> roles = userDao.selectRolesByUserId(user.getUserId());
        for (Role role : roles) {
            List<Permission> permissions = userDao.selectPermissionsByRoleId(role.getRoleId());
            for (Permission permission : permissions) {
                result.add(permission.getPerName());
            }
        }
        return result;
    }
}
