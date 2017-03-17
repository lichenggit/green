package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);
    int insert(User record);
    int insertSelective(User record);
    User selectByPrimaryKey(String userId);
    int updateByPrimaryKeySelective(User record);
    int updateByPrimaryKey(User record);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User selectUserByUsername(String username);

    /**
     * 用户名是否存在
     * @param username
     * @return
     */
    int countByUsername(String username);

    /**
     * 查找所有用户（包含角色）
     *
     * @param user
     * @return
     */
    List<User> selectAllUser(User user);
}