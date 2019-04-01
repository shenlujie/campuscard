package org.slj.dto;

/**
 * @Description: 学生 邮件中间实体类
 * @create: 2019/4/1
 * @Author: SLJ
 */
public class UserMail {
    /**
     * ID
     */
    private int id;

    /**
     * 发信人
     */
    private String stName;

    /**
     * 电子邮箱
     */
    private String stEmail;


    /**
     * 邮件标题
     */
    private String title;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 业务办理状态
     */
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStEmail() {
        return stEmail;
    }

    public void setStEmail(String stEmail) {
        this.stEmail = stEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
