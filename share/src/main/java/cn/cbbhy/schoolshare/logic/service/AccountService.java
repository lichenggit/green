package cn.cbbhy.schoolshare.logic.service;

import cn.cbbhy.schoolshare.logic.model.User;

import java.util.Set;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
public interface AccountService {
    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User selectUserByUsername(String username);

    /**
     * 根据用户名查找角色
     *
     * @param username
     * @return
     */
    Set<String> selectRolesByUsername(String username);

    /**
     * 根据用户名查找权限
     *
     * @param username
     * @return
     */
    Set<String> selectPermissionsByUsername(String username);

    /**
     * 新建一个用户（注册）
     *
     * @param user
     */
    void addUser(User user);
}
