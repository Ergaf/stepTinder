package server.servlets;

import freemarker.template.Template;
import server.DaoGetter;
import server.SoftGetter;
import server.TemplateConfig;
import server.esenses.Session;
import server.esenses.User;
import server.esenses.forGson.IntegerJson;
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

public class LikedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> likedUsers = null;
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                if(cookies[i].getName().equals("sessionId")){
                    for(int o = 0; o < SessionDao.activeHash.size(); o++){
                        if(SessionDao.activeHash.get(o).getSessionId().equals(cookies[i].getValue())){
                            System.out.println("в список лайкнутых зашел user: "+SessionDao.activeHash.get(o).getUser());
                            //считывание юзеров получивших лайк
                            likedUsers = DaoGetter.userLikeDao.readAllLikeThisUser(SessionDao.activeHash.get(o).getUserId());
                        }
                    }
                }
            }
        }

        resp.setCharacterEncoding("UTF-8");
        Template template = TemplateConfig.getConfig().getTemplate("liked.ftl");
        Map<String, Object> templateData = new HashMap<>();
        List<User> profiles = likedUsers;


        templateData.put("profile", profiles);

        try(Writer out = resp.getWriter()){
            template.process(templateData, out);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                if(cookies[i].getName().equals("sessionId")){
                    for(int o = 0; o < SessionDao.activeHash.size(); o++){
                        if(SessionDao.activeHash.get(o).getSessionId().equals(cookies[i].getValue())){
                            System.out.println("чат с юзером хочет user: "+SessionDao.activeHash.get(o).getUser());

                            resp.setCharacterEncoding("UTF-8");
                            resp.setContentType("application/json");

                            String reqData = req.getReader()
                                    .lines()
                                    .collect(Collectors.joining());
                            System.out.println("пришел json: "+reqData);
                            IntegerJson userId = SoftGetter.gson.fromJson(reqData, IntegerJson.class);

                            //запись в память текущей сессии о том в какой чат стучиться данная сессия(для сервлета сообщений)
                            SessionDao.activeHash.get(o).setChatUserId(userId.getId());

                            //передача клиенту о том что все ок)
                            PrintWriter out = resp.getWriter();
                            out.print(SoftGetter.gson.toJson(true));
                            out.flush();
                        }
                    }
                }
            }
        }
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
