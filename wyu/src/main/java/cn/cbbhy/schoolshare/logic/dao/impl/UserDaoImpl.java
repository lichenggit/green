package cn.cbbhy.schoolshare.logic.dao.impl;

import cn.cbbhy.schoolshare.logic.dao.UserDao;
import cn.cbbhy.schoolshare.logic.mapping.PermissionMapper;
import cn.cbbhy.schoolshare.logic.mapping.RoleMapper;
import cn.cbbhy.schoolshare.logic.mapping.UserCategoryMapper;
import cn.cbbhy.schoolshare.logic.mapping.UserMapper;
import cn.cbbhy.schoolshare.logic.model.Permission;
import cn.cbbhy.schoolshare.logic.model.Role;
import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.model.UserCategory;
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
    private UserCategoryMapper userCategoryMapper;

    @Override
    public boolean isExistUsername(String username) {
        return userMapper.countByUsername(username) > 0;
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
    }

    @Override
    public void addRoleToUser(String id, String userId, String roleId) {
        roleMapper.addRoleToUser(id, userId, roleId);
    }

    @Override
    public List<User> selectAllUser(User user) {
        return userMapper.selectAllUser(user);
    }

    @Override
    public List<Role> selectAllRoles() {
        return roleMapper.selectAllRoles();
    }

    @Override
    public List<UserCategory> selectSubCategories(String categoryId) {
        return userCategoryMapper.selectSubCategories(categoryId);
    }

    @Override
    public void addSubCategory(UserCategory userCategory) {
        userCategoryMapper.insertSelective(userCategory);
    }


}
