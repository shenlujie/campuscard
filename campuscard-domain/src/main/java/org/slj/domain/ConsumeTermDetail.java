package org.slj.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 消费详情
 * @create: 2019/5/12
 * @Author: SLJ
 */
@Table(name = "consume_term_detail")
public class ConsumeTermDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学生id
     */
    @Column(name = "st_num")
    private String stNum;

    /**
     * 发生时间
     */
    @Column(name = "happen_time")
    private Date happenTime;

    /**
     * 消费或充值项目
     */
    @Column(name = "consume_recharge")
    private String consumeRecharge;

    /**
     * 金额
     */
    private Float sum;

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
    public String getStNum() {
        return stNum;
    }

    /**
     * 设置学生id
     *
     * @param stNum 学生id
     */
    public void setStNum(String stNum) {
        this.stNum = stNum;
    }

    /**
     * 获取发生时间
     *
     * @return happen_time - 发生时间
     */
    public Date getHappenTime() {
        return happenTime;
    }

    /**
     * 设置发生时间
     *
     * @param happenTime 发生时间
     */
    public void setHappenTime(Date happenTime) {
        this.happenTime = happenTime;
    }

    /**
     * 获取消费或充值项目
     *
     * @return consume_recharge - 消费或充值项目
     */
    public String getConsumeRecharge() {
        return consumeRecharge;
    }

    /**
     * 设置消费或充值项目
     *
     * @param consumeRecharge 消费或充值项目
     */
    public void setConsumeRecharge(String consumeRecharge) {
        this.consumeRecharge = consumeRecharge;
    }

    /**
     * 获取金额
     *
     * @return sum - 金额
     */
    public Float getSum() {
        return sum;
    }

    /**
     * 设置金额
     *
     * @param sum 金额
     */
    public void setSum(Float sum) {
        this.sum = sum;
    }
}