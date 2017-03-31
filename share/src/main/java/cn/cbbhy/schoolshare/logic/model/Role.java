package cn.cbbhy.schoolshare.logic.model;

import java.util.Set;

public class Role {
    private String roleId;

    private String roleName;

    /*一个角色可以有多个权限*/
    private Set<Permission> permissions;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}