package org.slj.web.components;

import org.slj.web.utils.json.MsgJson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 当未登录或者token失效访问接口时，自定义的返回结果
 * @create: 2019/5/12
 * @Author: SLJ
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        MsgJson msgJson = MsgJson.unauthorized("未得到认证");
        response.getWriter().println(msgJson.toJson());
        response.getWriter().flush();
    }
}
