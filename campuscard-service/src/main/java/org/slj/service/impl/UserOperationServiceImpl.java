package org.slj.service.impl;

import org.slj.dao.CampusCardOperationMapper;
import org.slj.dao.FrontUserStudentMapper;
import org.slj.domain.CampusCardOperation;
import org.slj.domain.FrontUserStudent;
import org.slj.dto.UserOperation;
import org.slj.service.UserOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @create: 2019/4/1
 * @Author: SLJ
 */
@Service
public class UserOperationServiceImpl implements UserOperationService {

    @Autowired
    FrontUserStudentMapper frontUserStudentMapper;

    @Autowired
    CampusCardOperationMapper operationMapper;

    @Override
    public List<UserOperation> list() {
        List<UserOperation> userOperationList = new ArrayList<>();
        List<CampusCardOperation> operationList = operationMapper.selectAll();

        //遍历operationList，取出每一个中frontUserStudent的id，根据这个id查出frontUserStudent的学号和姓名
        for (CampusCardOperation campusCardOperation : operationList) {
            int id = campusCardOperation.getStId();
            FrontUserStudent frontUserStudent = frontUserStudentMapper.selectByPrimaryKey(id);

            //将两个实体类中的部分数据封装给userOperation
            UserOperation userOperation = new UserOperation();
            userOperation.setId(campusCardOperation.getId());
            userOperation.setStNum(frontUserStudent.getStNum());
            userOperation.setStName(frontUserStudent.getStName());
            userOperation.setApply(campusCardOperation.getApply());
            userOperation.setStatus(campusCardOperation.getStatus());
            userOperationList.add(userOperation);
        }
        return userOperationList;
    }
}
