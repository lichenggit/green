package cn.cbbhy.schoolshare.logic.dao.impl;

import cn.cbbhy.schoolshare.base.util.IdGenerator;
import cn.cbbhy.schoolshare.logic.dao.UserDao;
import cn.cbbhy.schoolshare.logic.mapping.PermissionMapper;
import cn.cbbhy.schoolshare.logic.mapping.RoleMapper;
import cn.cbbhy.schoolshare.logic.mapping.StudentAuthMapper;
import cn.cbbhy.schoolshare.logic.mapping.UserMapper;
import cn.cbbhy.schoolshare.logic.model.Permission;
import cn.cbbhy.schoolshare.logic.model.Role;
import cn.cbbhy.schoolshare.logic.model.StudentAuth;
import cn.cbbhy.schoolshare.logic.model.User;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private StudentAuthMapper studentAuthMapper;

    @Override
    public User selectUserByUserId(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public List<Role> selectRolesByUserId(String userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

    @Override
    public List<Permission> selectPermissionsByRoleId(String roleId) {
        return permissionMapper.selectPermissionsByRoleId(roleId);
    }

    @Override
    public void addUser(User user) {
        userMapper.insertSelective(user);
        roleMapper.addRoleToUser(IdGenerator.generateId(), user.getUserId(), "role1");
    }

    @Override
    public void addStudentAuth(StudentAuth studentAuth) {
        studentAuthMapper.insertSelective(studentAuth);
    }

    @Override
    public int countAuthByUser(String userId) {
        return studentAuthMapper.countAuthByUser(userId);
    }
}
