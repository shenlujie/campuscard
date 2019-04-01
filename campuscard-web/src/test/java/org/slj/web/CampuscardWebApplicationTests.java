package org.slj.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slj.service.SendMessageService;
import org.slj.web.utils.parse.TxtParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CampuscardWebApplicationTests {

    /**
     * 日志工具
     */
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final String FROM = "18234175208@163.com";
    private final String TO = "528805688@qq.com";
    private final String TITLE = "简单邮件";
    private final String CONTENT = "为什么nuc邮箱发不过去";

    @Autowired
    SendMessageService sendMessageService;

    @Test
    public void contextLoads() {
        sendMessageService.sendMessage(FROM, TO, TITLE, CONTENT);
    }

}
