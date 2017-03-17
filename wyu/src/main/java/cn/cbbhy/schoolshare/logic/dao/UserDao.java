package cn.cbbhy.schoolshare.logic.dao;

import cn.cbbhy.schoolshare.logic.model.Permission;
import cn.cbbhy.schoolshare.logic.model.Role;
import cn.cbbhy.schoolshare.logic.model.User;
import cn.cbbhy.schoolshare.logic.model.UserCategory;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public interface UserDao {
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
     * 根据角色Id查找权限
     *
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(String roleId);

    /**
     * 添加用户
     *
     * @param user
     */
    void addUser(User user);

    /**
     * 为用户分配一个角色
     * @param id
     * @param userId
     * @param roleId
     */
    void addRoleToUser(String id, String userId, String roleId);

    /**
     * 查找所有用户（包含角色）
     *
     * @param user
     * @return
     */
    List<User> selectAllUser(User user);

    /**
     * 查找全部角色(包含权限)
     *
     * @return
     */
    List<Role> selectAllRoles();

    /**
     * 查询所有子目录
     * @param categoryId
     * @return
     */
    List<UserCategory> selectSubCategories(String categoryId);

    /**
     * 添加子用户目录
     * @param userCategory
     */
    void addSubCategory(UserCategory userCategory);

}
