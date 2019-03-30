package org.slj.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Test
    public void contextLoads() throws SQLException {
        LOG.info("是否乱码");
        LOG.warn("是否乱码");
        LOG.error("是否乱码");
        LOG.debug("是否乱码");

        String fileName = "campuscard-introduce.txt";
        String res = TxtParseUtil.readFile(fileName);
        LOG.info(res);

        boolean result = TxtParseUtil.writeFile(fileName, "hahahah");
        LOG.info(String.valueOf(result));
    }

}
