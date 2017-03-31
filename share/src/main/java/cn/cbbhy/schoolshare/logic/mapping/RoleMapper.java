package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 根据用户ID查找角色
     *
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(String userId);

    /**
     * 为用户分配角色
     *
     * @param id
     * @param userId
     * @param roleId
     */
    void addRoleToUser(@Param("id") String id, @Param("userId") String userId, @Param("roleId") String roleId);
}