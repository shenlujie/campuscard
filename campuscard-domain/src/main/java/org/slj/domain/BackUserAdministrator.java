package org.slj.domain;

import javax.persistence.*;

/**
 * @Description: 后台管理员
 * @create: 2019/5/12
 * @Author: SLJ
 */
@Table(name = "back_user_administrator")
public class BackUserAdministrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "ad_username")
    private String adUsername;

    /**
     * 登陆密码
     */
    @Column(name = "ad_password")
    private String adPassword;

    /**
     * 姓名
     */
    @Column(name = "ad_name")
    private String adName;

    /**
     * 联系方式
     */
    @Column(name = "tel_num")
    private String telNum;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private int roleId;

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
     * 获取用户名
     *
     * @return ad_username - 用户名
     */
    public String getAdUsername() {
        return adUsername;
    }

    /**
     * 设置用户名
     *
     * @param adUsername 用户名
     */
    public void setAdUsername(String adUsername) {
        this.adUsername = adUsername;
    }

    /**
     * 获取登陆密码
     *
     * @return ad_password - 登陆密码
     */
    public String getAdPassword() {
        return adPassword;
    }

    /**
     * 设置登陆密码
     *
     * @param adPassword 登陆密码
     */
    public void setAdPassword(String adPassword) {
        this.adPassword = adPassword;
    }

    /**
     * 获取姓名
     *
     * @return ad_name - 姓名
     */
    public String getAdName() {
        return adName;
    }

    /**
     * 设置姓名
     *
     * @param adName 姓名
     */
    public void setAdName(String adName) {
        this.adName = adName;
    }

    /**
     * 获取联系方式
     *
     * @return tel_num - 联系方式
     */
    public String getTelNum() {
        return telNum;
    }

    /**
     * 设置联系方式
     *
     * @param telNum 联系方式
     */
    public void setTelNum(String telNum) {
        this.telNum = telNum;
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
}