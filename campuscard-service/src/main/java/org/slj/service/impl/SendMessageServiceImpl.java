package org.slj.service.impl;

import org.slj.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Description: 发送邮件业务接口实现类
 * @create: 2019/4/1
 * @Author: SLJ
 */
@Service
public class SendMessageServiceImpl implements SendMessageService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean sendMessage(String from, String to, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);

        mailSender.send(message);
        return true;
    }
}
