package org.slj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: web模块启动类
 * @create: 2019/3/16
 * @Author: SLJ
 */
@tk.mybatis.spring.annotation.MapperScan(basePackages = "org.slj.dao")
@SpringBootApplication
public class CampuscardWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampuscardWebApplication.class, args);
    }

}
