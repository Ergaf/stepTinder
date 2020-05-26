package server.servlets;

import freemarker.template.Template;
import server.DaoGetter;
import server.SoftGetter;
import server.TemplateConfig;
import server.esenses.Session;
import server.esenses.User;
import server.login.SessionDao;
import server.login.SessionGen;

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

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Cookie[] cookies = req.getCookies();
//        if(cookies != null){
//            for(int i = 0; i < cookies.length; i++){
//                if(cookies[i].getName().equals("sessionId")){
//                    for(int o = 0; o < SessionDao.activeHash.size(); o++){
//                        if(SessionDao.activeHash.get(0).getSessionId().equals(cookies[i].getValue())){
//
//                        }
//                    }
//                }
//            }
//        }


        resp.setCharacterEncoding("UTF-8");
        Template template = TemplateConfig.getConfig().getTemplate("login.ftl");
        Map<String, Object> templateData = new HashMap<>();

        List<User> users = DaoGetter.userDaoSql.readAllUsers();

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
        User user = SoftGetter.gson.fromJson(reqData.toLowerCase(), User.class);
        User testForNameAndPass = DaoGetter.userDaoSql.readUserForName(user.getName());


        //проверка на имя-пароль и создание сессии с последующей передачей ее браузеру в json
        PrintWriter out = resp.getWriter();
        if(user.getName().equals(testForNameAndPass.getName()) & user.getPass().equals(testForNameAndPass.getPass())){
            System.out.println("зашли под логином");
            Session session = new Session(testForNameAndPass.getName(), testForNameAndPass.getId(), SessionGen.createSessionId(user.getName()), DaoGetter.userDaoSql.readAllUsersExceptThisUser(testForNameAndPass.getId()));
            SessionDao.activeHash.add(session);
            out.print(SoftGetter.gson.toJson(session));
        } else {
            out.print(SoftGetter.gson.toJson(false));
        }
        out.flush();
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
