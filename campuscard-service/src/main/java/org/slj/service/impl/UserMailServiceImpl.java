package org.slj.service.impl;

import org.slj.dao.FrontUserStudentMapper;
import org.slj.dao.LeaderMailBoxMapper;
import org.slj.domain.FrontUserStudent;
import org.slj.domain.LeaderMailBox;
import org.slj.dto.UserMail;
import org.slj.service.UserMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 用户邮件业务接口实现类
 * @create: 2019/4/1
 * @Author: SLJ
 */
@Service
public class UserMailServiceImpl implements UserMailService {

    @Autowired
    FrontUserStudentMapper frontUserStudentMapper;
    @Autowired
    LeaderMailBoxMapper leaderMailBoxMapper;

    @Override
    public List<UserMail> listByCondition(int status) {
        Condition condition = new Condition(LeaderMailBox.class);
        condition.createCriteria().andEqualTo("status", status);
        List<LeaderMailBox> leaderMailBoxList = leaderMailBoxMapper.selectByCondition(condition);
        List<UserMail> userMailList = new ArrayList<>();
        for (LeaderMailBox leaderbox  : leaderMailBoxList) {
            int stId = leaderbox.getStId();
            FrontUserStudent frontUserStudent = frontUserStudentMapper.selectByPrimaryKey(stId);
            UserMail userMail = new UserMail();
            userMail.setId(leaderbox.getId());
            userMail.setStName(frontUserStudent.getStName());
            userMail.setStEmail(frontUserStudent.getStEmail());
            userMail.setTitle(leaderbox.getTitle());
            userMail.setContent(leaderbox.getContent());
            userMail.setStatus(leaderbox.getStatus());
            userMailList.add(userMail);
        }
        return userMailList;
    }

    @Override
    public UserMail detail(int id) {
        UserMail userMail = new UserMail();
        LeaderMailBox  leaderMailBox = leaderMailBoxMapper.selectByPrimaryKey(id);
        int stId = leaderMailBox.getStId();
        FrontUserStudent frontUserStudent = frontUserStudentMapper.selectByPrimaryKey(stId);
        userMail.setId(id);
        userMail.setStName(frontUserStudent.getStName());
        userMail.setStEmail(frontUserStudent.getStEmail());
        userMail.setTitle(leaderMailBox.getTitle());
        userMail.setContent(leaderMailBox.getContent());
        return userMail;

    }
}
