package org.slj.domain;

import javax.persistence.*;

/**
 * @Description: 权限
 * @create: 2019/5/12
 * @Author: SLJ
 */
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private int roleId;

    /**
     * 该角色访问资源的权限
     */
    @Column(name = "uri")
    private String uri;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色id
     *
     * @return roleId - 角色id
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取该角色访问资源的权限
     *
     * @return uri - 该角色访问资源的权限
     */
    public String getUri() {
        return uri;
    }

    /**
     * 设置该角色访问资源的权限
     *
     * @param uri 该角色访问资源的权限
     */
    public void setUri(String uri) {
        this.uri = uri;
    }
}
