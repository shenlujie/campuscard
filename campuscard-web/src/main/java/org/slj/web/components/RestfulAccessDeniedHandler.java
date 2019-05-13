package org.slj.web.components;

import org.slj.web.utils.json.MsgJson;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 访问接口没有权限
 * @create: 2019/5/12
 * @Author: SLJ
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        MsgJson msgJson = MsgJson.forbidden("没有权限");
        response.getWriter().println(msgJson.toJson());
        response.getWriter().flush();
    }
}
