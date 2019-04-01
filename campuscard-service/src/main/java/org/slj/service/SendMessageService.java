package org.slj.service;

/**
* @Description: 发送邮件业务类
* @create: 2019/4/1
* @Author: SLJ
*/
public interface SendMessageService {

    /**
     * 发送邮件
     * @param from 起始地址
     * @param to 发送地址
     * @param title 邮件标题
     * @param content 邮件内容
     * @return 是否发送成功
     */
    boolean sendMessage(String from,String to,String title,String content);

}
