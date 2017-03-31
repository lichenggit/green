package cn.cbbhy.schoolshare.logic.mapping;

import cn.cbbhy.schoolshare.logic.model.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(String perId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String perId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    /**
     * 根据角色Id查找权限
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(String roleId);
}