package cn.cbbhy.schoolshare.logic.service;

import cn.cbbhy.schoolshare.logic.model.Permission;
import cn.cbbhy.schoolshare.logic.model.Role;
import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.model.UserCategory;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public interface AccountService {
    /**
     * 用户名是否存在
     * @param username
     * @return
     */
    boolean isExistUsername(String username);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User selectUserByUsername(String username);

    /**
     * 根据用户ID查找角色
     *
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(String userId);

    /**
     * 根据角色ID查找权限
     *
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(String roleId);

    /**
     * 添加用户
     *
     * @param categoryLevel
     * @param user
     */
    void addUser(String categoryLevel, User user);

    /**
     * 查找所有用户
     *
     * @param user
     * @return
     */
    List<User> listAllUser(User user);

    /**
     * 查找全部角色
     *
     * @return
     */
    List<Role> listAllRoles();

    /**
     * 查询所有子目录
     *
     * @param categoryId
     * @return
     */
    List<UserCategory> listSubCategories(String categoryId);

    /**
     * 添加子用户目录
     *
     * @param userCategory
     */
    void addSubCategory(User user, UserCategory userCategory);
}
