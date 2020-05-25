package server.servlets;

import freemarker.template.Template;
import server.DaoGetter;
import server.SoftGetter;
import server.TemplateConfig;
import server.esenses.LikedIdBoolean;
import server.esenses.Session;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userNotLiked = null;
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                if(cookies[i].getName().equals("sessionId")){
                    for(int o = 0; o < SessionDao.activeHash.size(); o++){
                        if(SessionDao.activeHash.get(0).getSessionId().equals(cookies[i].getValue())){
                            System.out.println("зашел user: "+SessionDao.activeHash.get(0).getUser());
                            userNotLiked = DaoGetter.userLikeDao.readAllNotLikeThisUser(SessionDao.activeHash.get(0).getUserId());

                            System.out.println(userNotLiked.getName());
                        }
                    }
                }
            }
        }



        resp.setCharacterEncoding("UTF-8");
        Template template = TemplateConfig.getConfig().getTemplate("users.ftl");
        Map<String, Object> templateData = new HashMap<>();


        templateData.put("profiles", userNotLiked);

        try(Writer out = resp.getWriter()){
            template.process(templateData, out);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session user = null;
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                if(cookies[i].getName().equals("sessionId")){
                    for(int o = 0; o < SessionDao.activeHash.size(); o++){
                        if(SessionDao.activeHash.get(0).getSessionId().equals(cookies[i].getValue())){
                            System.out.println("лайкнул user: "+SessionDao.activeHash.get(0).getUser()
                            +" с айдишкой: "+SessionDao.activeHash.get(0).getUserId());
                            user = SessionDao.activeHash.get(0);
                        }
                    }
                }
            }
        }




        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String reqData = req.getReader()
                .lines()
                .collect(Collectors.joining());
        System.out.println("пришел json: "+reqData);
        LikedIdBoolean liked = SoftGetter.gson.fromJson(reqData, LikedIdBoolean.class);
        if(liked.getStr()){
            assert user != null;
            DaoGetter.userLikeDao.userAddLike(user.getUserId(), liked.getId());
        }



        PrintWriter out = resp.getWriter();

        //проверка на то каких юзеров лайкнули
//        if(profileNumber >= profiles.size()-1){
//            out.print("/liked");
//            profileNumber = 0;
//        } else {
//            out.print("/user");
//            profileNumber++;
//        }
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
