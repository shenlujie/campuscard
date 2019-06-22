package org.slj.service;

import org.slj.dto.UserMail;

import java.util.List;

/**
 * @Description: 用户邮件业务类
 * @create: 2019/4/1
 * @Author: SLJ
 */
public interface UserMailService {

    /**
     * 查询所有中间类
     * @param status 用户邮件的状态
     * @return 所有中间类的集合
     */
    List<UserMail> listByCondition(int status);

    /**
     * 按id查找一个
     * @param id id
     * @return 一个实体类
     */
    UserMail detail(int id);
}
