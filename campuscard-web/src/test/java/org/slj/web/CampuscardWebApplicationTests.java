package org.slj.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slj.service.SendMessageService;
import org.slj.web.utils.sensitive.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        // 使用默认单例（加载默认词典）
        SensitiveFilter filter = new SensitiveFilter();
        // 向过滤器增加一个词
        //components.put("婚礼上唱春天在哪里");

        // 待过滤的句子
        String sentence = "然后，市长在婚礼上唱春天在哪里。";
        // 进行过滤
        String filted = filter.filter(sentence, '*');

        // 如果未过滤，则返回输入的String引用
        if(!sentence.equals(filted) ){
            // 句子中有敏感词
            System.out.println(filted);
        }
    }

}
