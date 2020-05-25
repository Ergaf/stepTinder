package server.servlets;

import freemarker.template.Template;
import server.DaoGetter;
import server.TemplateConfig;
import server.esenses.User;
import server.login.SessionDao;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserServlet extends HttpServlet {
    int profileNumber = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //проверка на наличие синхронизированной сессии
        String logUser = null;
        Cookie[] cookies = req.getCookies();
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


        System.out.println(profileNumber);
        resp.setCharacterEncoding("UTF-8");
        Template template = TemplateConfig.getConfig().getTemplate("users.ftl");
        Map<String, Object> templateData = new HashMap<>();
        List<User> profiles = DaoGetter.userDaoSql.readAllUsers();

        templateData.put("profiles", profiles.get(profileNumber));

        try(Writer out = resp.getWriter()){
            template.process(templateData, out);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String reqData = req.getReader()
                .lines()
                .collect(Collectors.joining());
        System.out.println("пришел json: "+reqData);
        PrintWriter out = resp.getWriter();
        List<User> profiles = DaoGetter.userDaoSql.readAllUsers();

        //проверка на то каких юзеров лайкнули
        if(profileNumber >= profiles.size()-1){
            out.print("/liked");
            profileNumber = 0;
        } else {
            out.print("/user");
            profileNumber++;
        }
        out.flush();
        //---------------------
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
