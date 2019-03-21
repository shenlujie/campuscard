package org.slj.domain;

import javax.persistence.*;

@Table(name = "campus_card_account")
public class CampusCardAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学生id
     */
    @Column(name = "st_id")
    private Integer stId;

    /**
     * 余额
     */
    private Integer balance;

    /**
     * 待领补款
     */
    @Column(name = "wait_access")
    private Integer waitAccess;

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
     * 获取余额
     *
     * @return balance - 余额
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * 设置余额
     *
     * @param balance 余额
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /**
     * 获取待领补款
     *
     * @return wait_access - 待领补款
     */
    public Integer getWaitAccess() {
        return waitAccess;
    }

    /**
     * 设置待领补款
     *
     * @param waitAccess 待领补款
     */
    public void setWaitAccess(Integer waitAccess) {
        this.waitAccess = waitAccess;
    }
}