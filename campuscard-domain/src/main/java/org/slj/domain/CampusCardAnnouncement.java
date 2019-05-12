package org.slj.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 一卡通公告
 * @create: 2019/5/12
 * @Author: SLJ
 */
@Table(name = "campus_card_announcement")
public class CampusCardAnnouncement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 管理员id
     */
    @Column(name = "ad_id")
    private Integer adId;

    /**
     * 发布时间
     */
    @Column(name = "release_time")
    private Date releaseTime;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告详情
     */
    private String details;

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
     * 获取管理员id
     *
     * @return ad_id - 管理员id
     */
    public Integer getAdId() {
        return adId;
    }

    /**
     * 设置管理员id
     *
     * @param adId 管理员id
     */
    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    /**
     * 获取发布时间
     *
     * @return release_time - 发布时间
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * 设置发布时间
     *
     * @param releaseTime 发布时间
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * 获取公告标题
     *
     * @return title - 公告标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置公告标题
     *
     * @param title 公告标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取公告详情
     *
     * @return details - 公告详情
     */
    public String getDetails() {
        return details;
    }

    /**
     * 设置公告详情
     *
     * @param details 公告详情
     */
    public void setDetails(String details) {
        this.details = details;
    }
}