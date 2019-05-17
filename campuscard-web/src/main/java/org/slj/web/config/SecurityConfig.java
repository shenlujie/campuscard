package org.slj.web.config;

import org.slj.domain.FrontUserStudent;
import org.slj.domain.Permission;
import org.slj.domain.Role;
import org.slj.service.FrontUserStudentService;
import org.slj.service.PermissionService;
import org.slj.service.RoleService;
import org.slj.web.bo.CampusUserDetails;
import org.slj.web.components.JwtAuthenticationTokenFilter;
import org.slj.web.components.MyFilterSecurityInterceptor;
import org.slj.web.components.RestAuthenticationEntryPoint;
import org.slj.web.components.RestfulAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * @Description: spring security 相关配置
 * @create: 2019/5/12
 * @Author: SLJ
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FrontUserStudentService frontUserStudentService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    /**
     * 认证
     *
     * @param auth 设置用户信息以及密码加密方式
     * @throws Exception 异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 授权
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()// 由于使用的是JWT，我们这里不需要csrf
                .disable()
                .sessionManagement()// 基于token，所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //跨域请求会先进行一次options请求
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                // 所有静态资源访问没有权限
                .antMatchers(HttpMethod.GET,
                        // 入口
                        "/",
                        // 一卡通简介
                        "/file/introRead",
                        // 管理条例
                        "/campusCardRule/list",
                        // 使用指南
                        "/file/guideRead",
                        // 信息公告
                        "/campusCardAnnouncement/list",
                        // 公告详情
                        "/campusCardAnnouncement/detail"
                )
                // permitAll()就是允许所有角色|权限来访问
                .permitAll()
                // 允许匿名登录
                .antMatchers("/frontUserStudent/login")
                .permitAll()
                .anyRequest()
                // 除去以上所有请求全部需要鉴权
                .authenticated();

        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT components
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 添加自定义的权限过滤器
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    /**
     * @return 选择使用BCryptPasswordEncoder方式对密码进行加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @return 自定义用户详情
     */
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return username -> {
            FrontUserStudent student = frontUserStudentService.getUserByUsername(username);
            if (student != null) {
                // 数据库固定了，一个用户就对应一个角色
                Role role = roleService.findById(student.getRoleId());
                return new CampusUserDetails(student, role);
            }
            throw new UsernameNotFoundException("该用户不存在");
        };
    }

    /**
     * 自定义JwtAuthenticationTokenFilter
     *
     * @return JwtAuthenticationTokenFilter
     */
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    /**
     * 允许跨域调用的过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return new CorsFilter(source);
    }
}
