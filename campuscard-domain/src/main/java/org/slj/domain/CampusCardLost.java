package org.slj.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 失卡招领
 * @create: 2019/5/12
 * @Author: SLJ
 */
@Table(name = "campus_card_lost")
public class CampusCardLost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 上传用户id
     */
    @Column(name = "up_st_num")
    private Integer upStNum;

    /**
     * 失者学号
     */
    @Column(name = "lost_st_num")
    private String lostStNum;

    /**
     * 失者姓名
     */
    @Column(name = "lost_st_name")
    private String lostStName;

    /**
     * 一卡通照片链接
     */
    @Column(name = "card_pic")
    private String cardPic;

    /**
     * 捡到时间
     */
    @Column(name = "found_time")
    private Date foundTime;

    /**
     * 上传用户联系方式
     */
    @Column(name = "up_st_tel")
    private String upStTel;

    /**
     * 管理员推送状态
     */
    @Column(name = "status")
    private int status;

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
     * 获取上传用户id
     *
     * @return up_st_id - 上传用户id
     */
    public Integer getUpStNum() {
        return upStNum;
    }

    /**
     * 设置上传用户id
     *
     * @param upStNum 上传用户id
     */
    public void setUpStNum(Integer upStNum) {
        this.upStNum = upStNum;
    }

    /**
     * 获取失者学号
     *
     * @return lost_st_num - 失者学号
     */
    public String getLostStNum() {
        return lostStNum;
    }

    /**
     * 设置失者学号
     *
     * @param lostStNum 失者学号
     */
    public void setLostStNum(String lostStNum) {
        this.lostStNum = lostStNum;
    }

    /**
     * 获取失者姓名
     *
     * @return lost_st_name - 失者姓名
     */
    public String getLostStName() {
        return lostStName;
    }

    /**
     * 设置失者姓名
     *
     * @param lostStName 失者姓名
     */
    public void setLostStName(String lostStName) {
        this.lostStName = lostStName;
    }

    /**
     * 获取一卡通照片链接
     *
     * @return card_pic - 一卡通照片链接
     */
    public String getCardPic() {
        return cardPic;
    }

    /**
     * 设置一卡通照片链接
     *
     * @param cardPic 一卡通照片链接
     */
    public void setCardPic(String cardPic) {
        this.cardPic = cardPic;
    }

    /**
     * 获取捡到时间
     *
     * @return found_time - 捡到时间
     */
    public Date getFoundTime() {
        return foundTime;
    }

    /**
     * 设置捡到时间
     *
     * @param foundTime 捡到时间
     */
    public void setFoundTime(Date foundTime) {
        this.foundTime = foundTime;
    }

    /**
     * 获取上传用户联系方式
     *
     * @return up_st_tel - 上传用户联系方式
     */
    public String getUpStTel() {
        return upStTel;
    }

    /**
     * 设置上传用户联系方式
     *
     * @param upStTel 上传用户联系方式
     */
    public void setUpStTel(String upStTel) {
        this.upStTel = upStTel;
    }

    /**
     * 获取管理员推送状态
     *
     * @return status - 管理员推送状态
     */
    public int getStatus() {
        return status;
    }

    /**
     * 设置管理员推送状态
     *
     * @param status 管理员推送状态
     */
    public void setStatus(int status) {
        this.status = status;
    }
}