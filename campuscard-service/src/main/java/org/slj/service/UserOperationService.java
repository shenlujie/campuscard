package org.slj.service;

import org.slj.dto.UserOperation;

import java.util.List;

/**
 * @Description: 学生用户和业务办理中间业务接口
 * @create: 2019/4/1
 * @Author: SLJ
 */
public interface UserOperationService {

    /**
     * 查询所有的中间类
     * @return 中间类的集合
     */
    List<UserOperation> list();
}
