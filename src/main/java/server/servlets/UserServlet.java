package server.servlets;

import freemarker.template.Template;
import server.DaoGetter;
import server.SoftGetter;
import server.TemplateConfig;
import server.esenses.forGson.LikedIdBoolean;
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
        //поиск к какой сессии пренадлежит данный запрос
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                if(cookies[i].getName().equals("sessionId")){
                    for(int o = 0; o < SessionDao.activeHash.size(); o++){
                        if(SessionDao.activeHash.get(o).getSessionId().equals(cookies[i].getValue())){
                            resp.setCharacterEncoding("UTF-8");
                            Map<String, Object> templateData = new HashMap<>();
                            Template template;

                            if(SessionDao.activeHash.get(o).getLikeNumber() <= 0){
                                template = TemplateConfig.getConfig().getTemplate("noUsers.ftl");
                            } else {
                                template = TemplateConfig.getConfig().getTemplate("users.ftl");
                                List<User> users = SessionDao.activeHash.get(o).getUsers();
                                //считывания с базы юзеров по очереди на лайк. увы костыльно(
                                //они просто отображаются все (новые первыми)
                                //не нашел/не понял как выбрать только тех кого еще НЕ лайкнули и НЕ посмотрели
                                templateData.put("profiles", users.get(SessionDao.activeHash.get(o).getLikeNumber()-1));
                            }

                            try(Writer out = resp.getWriter()){
                                template.process(templateData, out);
                            }catch(Exception ex){
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
            }
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
                        if(SessionDao.activeHash.get(o).getSessionId().equals(cookies[i].getValue())){
//                            System.out.println("лайкнул user: "+SessionDao.activeHash.get(0).getUser()
//                            +" с айдишкой: "+SessionDao.activeHash.get(0).getUserId());
                            user = SessionDao.activeHash.get(o);
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
            if(!DaoGetter.userLikeDao.LikeThisForThisUser(user.getUserId(), liked.getId())){
                DaoGetter.userLikeDao.userAddLike(user.getUserId(), liked.getId());
            }
        }

        //костыль ибо я не понял как выбрать из базы юзеров которых не лайкнул текущий юзер =(
        user.setLikeNumber(user.getLikeNumber()-1);
        //нда(

        PrintWriter out = resp.getWriter();

        if(user.getLikeNumber() <= 0){
            out.print("/liked");
        } else {
            out.print("/user");
        }
        out.flush();
    }
}
