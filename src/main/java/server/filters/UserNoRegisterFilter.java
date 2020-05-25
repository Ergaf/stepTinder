package server.filters;

import freemarker.template.Template;
import server.TemplateConfig;
import server.login.SessionDao;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class UserNoRegisterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String logUser = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                if(cookies[i].getName().equals("sessionId")){
                    for(int o = 0; o < SessionDao.activeHash.size(); o++){
                        if(SessionDao.activeHash.get(0).getSessionId().equals(cookies[i].getValue())){
                            logUser = SessionDao.activeHash.get(0).getUser();
                        }
                    }
                }
            }
        }

        if(logUser != null){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.setCharacterEncoding("UTF-8");
            Template template = TemplateConfig.getConfig().getTemplate("reload.ftl");
            Map<String, Object> templateData = new HashMap<>();

            try(Writer out = response.getWriter()){
                template.process(templateData, out);
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
