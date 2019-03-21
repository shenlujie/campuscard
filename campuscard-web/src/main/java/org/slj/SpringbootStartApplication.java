package org.slj;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @Description: 启动spring上下文(用于war包形式打包发布)
 * @create: 2019/3/18
 * @Author: SLJ
 */
public class SpringbootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //指向springboot 启动类
        return builder.sources(CampuscardWebApplication.class);
    }
}
