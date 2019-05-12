package org.slj.domain;

import javax.persistence.*;

/**
 * @Description: 业务办理
 * @create: 2019/5/12
 * @Author: SLJ
 */
@Table(name = "campus_card_operation")
public class CampusCardOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学生id
     */
    @Column(name = "st_id")
    private Integer stId;

    /**
     * 申请业务
     */
    private String apply;

    /**
     * 办理状态
     */
    private String status;

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
     * 获取学生id
     *
     * @return st_id - 学生id
     */
    public Integer getStId() {
        return stId;
    }

    /**
     * 设置学生id
     *
     * @param stId 学生id
     */
    public void setStId(Integer stId) {
        this.stId = stId;
    }

    /**
     * 获取申请业务
     *
     * @return apply - 申请业务
     */
    public String getApply() {
        return apply;
    }

    /**
     * 设置申请业务
     *
     * @param apply 申请业务
     */
    public void setApply(String apply) {
        this.apply = apply;
    }

    /**
     * 获取办理状态
     *
     * @return status - 办理状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置办理状态
     *
     * @param status 办理状态
     */
    public void setStatus(String status) {
        this.status = status;
    }
}