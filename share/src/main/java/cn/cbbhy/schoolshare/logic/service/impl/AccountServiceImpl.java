package cn.cbbhy.schoolshare.logic.service.impl;

import cn.cbbhy.schoolshare.base.util.IdGenerator;
import cn.cbbhy.schoolshare.logic.dao.UserDao;
import cn.cbbhy.schoolshare.logic.model.*;
import cn.cbbhy.schoolshare.logic.model.constant.Status;
import cn.cbbhy.schoolshare.logic.service.AccountService;
import cn.cbbhy.schoolshare.logic.service.AccumulatePointService;
import cn.cbbhy.schoolshare.logic.shiro.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    private AccumulatePointService accumulatePointService;

    @Override
    public User selectUserByUsername(String username) {
        return userDao.selectUserByUsername(username);
    }

    @Override
    public User selectUserByUserId(String userId) {
        return userDao.selectUserByUserId(userId);
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

    @Override
    public void addUser(User user) {
        PasswordHelper passwordHelper = new PasswordHelper();
        //passwordHelper.encryptPassword(user);
        user.setUserId(IdGenerator.generateId());
        user.setStatus(Status.NORMAL);
        user.setCreateTime(new Date());
        userDao.addUser(user);
        //积分
        AccumulatePoint accumulatePoint = new AccumulatePoint();
        accumulatePoint.setUserId(user.getUserId());
        accumulatePoint.setPointType("REGISTER");
        accumulatePoint.setPoints(10);
        accumulatePoint.setRemark("注册");
        accumulatePointService.addPointItem(accumulatePoint);
    }

    @Override
    public void addStudentAuth(StudentAuth studentAuth) {
        studentAuth.setId(IdGenerator.generateId());
        studentAuth.setStatus("HAS_AUTH");
        studentAuth.setCreataTime(new Date());
        userDao.addStudentAuth(studentAuth);
    }

    @Override
    public int countAuthByUser(String userId) {
        return userDao.countAuthByUser(userId);
    }
}
