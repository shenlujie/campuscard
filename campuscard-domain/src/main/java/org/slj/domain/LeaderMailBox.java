package org.slj.domain;

import javax.persistence.*;

/**
 * @Description: 领导邮箱
 * @create: 2019/5/12
 * @Author: SLJ
 */
@Table(name = "leader_mailbox")
public class LeaderMailBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学生用户id
     */
    @Column(name = "st_id")
    private int stId;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 邮件状态
     */
    @Column(name = "status")
    private int status;

    /**
     * 获取id
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取学生用户id
     *
     * @return 学生用户id
     */
    public int getStId() {
        return stId;
    }

    /**
     * 设置学生用户id
     *
     * @param stId 学生用户id
     */
    public void setStId(int stId) {
        this.stId = stId;
    }

    /**
     * 获取邮件标题
     *
     * @return 邮件标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置邮件标题
     *
     * @param title 邮件标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取邮件内容
     *
     * @return 邮件内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置邮件内容
     *
     * @param content 邮件内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取邮件状态
     *
     * @return 邮件状态
     */
    public int getStatus() {
        return status;
    }

    /**
     * 设置邮件状态
     *
     * @param status 邮件状态
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
