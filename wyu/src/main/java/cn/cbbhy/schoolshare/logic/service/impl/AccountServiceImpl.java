package cn.cbbhy.schoolshare.logic.service.impl;

import cn.cbbhy.schoolshare.base.util.IdGenerator;
import cn.cbbhy.schoolshare.logic.dao.UserDao;
import cn.cbbhy.schoolshare.logic.model.Permission;
import cn.cbbhy.schoolshare.logic.model.Role;
import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.model.UserCategory;
import cn.cbbhy.schoolshare.logic.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean isExistUsername(String username) {
        return userDao.isExistUsername(username);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userDao.selectUserByUsername(username);
    }

    @Override
    public List<Role> selectRolesByUserId(String userId) {
        return userDao.selectRolesByUserId(userId);
    }

    @Override
    public List<Permission> selectPermissionsByRoleId(String roleId) {
        return userDao.selectPermissionsByRoleId(roleId);
    }

    @Override
    public void addUser(String categoryLevel, User user) {
        int level = Integer.parseInt(categoryLevel);
        if (level > 1) {
            String userId = IdGenerator.generateId();
            user.setUserId(userId);
            userDao.addUser(user);
            userDao.addRoleToUser(IdGenerator.generateId(), userId, "role1");
        }
    }

    @Override
    public List<User> listAllUser(User user) {
        return userDao.selectAllUser(user);
    }

    @Override
    public List<Role> listAllRoles() {
        return userDao.selectAllRoles();
    }

    @Override
    public List<UserCategory> listSubCategories(String categoryId) {
        return userDao.selectSubCategories(categoryId);
    }

    @Override
    public void addSubCategory(User user, UserCategory userCategory) {
        UserCategory superCategory = user.getUserCategory();
        userCategory.setCategoryId(IdGenerator.generateId());
        userCategory.setCreator(user.getUserId());
        userCategory.setSuperCategoryId(superCategory.getCategoryId());
        userCategory.setCategoryLevel("" + (Integer.parseInt(superCategory.getCategoryLevel()) - 1));
        userCategory.setCreateTime(new Date());
        userDao.addSubCategory(userCategory);
    }

}
