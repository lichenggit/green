package cn.cbbhy.schoolshare.logic.dao;

import cn.cbbhy.schoolshare.logic.model.Permission;
import cn.cbbhy.schoolshare.logic.model.Role;
import cn.cbbhy.schoolshare.logic.model.User;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public interface UserDao {
    /**
     * 根据用户Id查找用户
     *
     * @param userId
     * @return
     */
    User selectUserByUserId(String userId);

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
     * 新建一个用户
     *
     * @param user
     */
    void addUser(User user);
}
