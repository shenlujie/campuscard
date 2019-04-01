package org.slj.dto;

/**
 * @Description: 学生用户和业务办理中间实体类
 * @create: 2019/4/1
 * @Author: SLJ
 */
public class UserOperation {

    /**
     * ID
     */
    private int id;

    /**
     * 学生学号
     */
    private String stNum;

    /**
     * 学生姓名
     */
    private String stName;

    /**
     * 申请的业务
     */
    private String apply;

    /**
     * 业务办理状态
     */
    private String status;

    public String getStNum() {
        return stNum;
    }

    public void setStNum(String stNum) {
        this.stNum = stNum;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
