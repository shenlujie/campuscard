package org.slj.service.impl;

import org.slj.dao.FrontUserStudentMapper;
import org.slj.domain.FrontUserStudent;
import org.slj.service.AbstractService;
import org.slj.service.FrontUserStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
*@create: 2019/3/14
*@Author: SLJ
*/
@Service
public class FrontUserStudentServiceImpl extends AbstractService<FrontUserStudent> implements FrontUserStudentService {

    @Autowired
    private FrontUserStudentMapper frontUserStudentMapper;

    /**
     * 冗余代码提取成方法
     * @param stNum 学号|用户名
     * @return frontUserStudent实体类
     */
    public FrontUserStudent getUser (String stNum) {
        Condition condition = new Condition(FrontUserStudent.class);
        condition.createCriteria().andEqualTo("stNum", stNum);
        List<FrontUserStudent> list = frontUserStudentMapper.selectByCondition(condition);
        if (0 == list.size()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public FrontUserStudent getUserByUsername(String username) {
        return getUser(username);
    }
}
