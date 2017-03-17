package cn.cbbhy.schoolshare.logic.dao.impl;

import cn.cbbhy.schoolshare.logic.dao.UserDao;
import cn.cbbhy.schoolshare.logic.mapping.PermissionMapper;
import cn.cbbhy.schoolshare.logic.mapping.RoleMapper;
import cn.cbbhy.schoolshare.logic.mapping.UserMapper;
import cn.cbbhy.schoolshare.logic.model.Permission;
import cn.cbbhy.schoolshare.logic.model.Role;
import cn.cbbhy.schoolshare.logic.model.User;
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
}
