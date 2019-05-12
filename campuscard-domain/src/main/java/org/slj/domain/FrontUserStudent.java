package org.slj.domain;

import javax.persistence.*;

/**
 * @Description: 前台用户
 * @create: 2019/5/12
 * @Author: SLJ
 */
@Table(name = "front_user_student")
public class FrontUserStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学号
     */
    @Column(name = "st_num")
    private String stNum;

    /**
     * 姓名
     */
    @Column(name = "st_name")
    private String stName;

    /**
     * 性别
     */
    @Column(name = "st_sex")
    private String stSex;

    /**
     * 学院
     */
    @Column(name = "st_department")
    private String stDepartment;

    /**
     * 专业
     */
    @Column(name = "st_major")
    private String stMajor;

    /**
     * edu邮箱
     */
    @Column(name = "st_email")
    private String stEmail;

    /**
     * 登陆密码
     */
    @Column(name = "st_password")
    private String stPassword;

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
     * 获取学号
     *
     * @return st_num - 学号
     */
    public String getStNum() {
        return stNum;
    }

    /**
     * 设置学号
     *
     * @param stNum 学号
     */
    public void setStNum(String stNum) {
        this.stNum = stNum;
    }

    /**
     * 获取姓名
     *
     * @return st_name - 姓名
     */
    public String getStName() {
        return stName;
    }

    /**
     * 设置姓名
     *
     * @param stName 姓名
     */
    public void setStName(String stName) {
        this.stName = stName;
    }

    /**
     * 获取性别
     *
     * @return st_sex - 性别
     */
    public String getStSex() {
        return stSex;
    }

    /**
     * 设置性别
     *
     * @param stSex 性别
     */
    public void setStSex(String stSex) {
        this.stSex = stSex;
    }

    /**
     * 获取学院
     *
     * @return st_department - 学院
     */
    public String getStDepartment() {
        return stDepartment;
    }

    /**
     * 设置学院
     *
     * @param stDepartment 学院
     */
    public void setStDepartment(String stDepartment) {
        this.stDepartment = stDepartment;
    }

    /**
     * 获取专业
     *
     * @return st_major - 专业
     */
    public String getStMajor() {
        return stMajor;
    }

    /**
     * 设置专业
     *
     * @param stMajor 专业
     */
    public void setStMajor(String stMajor) {
        this.stMajor = stMajor;
    }

    /**
     * 获取edu邮箱
     *
     * @return st_email - edu邮箱
     */
    public String getStEmail() {
        return stEmail;
    }

    /**
     * 设置edu邮箱
     *
     * @param stEmail edu邮箱
     */
    public void setStEmail(String stEmail) {
        this.stEmail = stEmail;
    }

    /**
     * 获取登陆密码
     *
     * @return st_password - 登陆密码
     */
    public String getStPassword() {
        return stPassword;
    }

    /**
     * 设置登陆密码
     *
     * @param stPassword 登陆密码
     */
    public void setStPassword(String stPassword) {
        this.stPassword = stPassword;
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