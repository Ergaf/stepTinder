package server.servlets;

import freemarker.template.Template;
import server.DaoGetter;
import server.TemplateConfig;
import server.esenses.Message;
import server.login.SessionDao;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                if(cookies[i].getName().equals("sessionId")){
                    for(int o = 0; o < SessionDao.activeHash.size(); o++){
                        if(SessionDao.activeHash.get(o).getSessionId().equals(cookies[i].getValue())){
                            System.out.println("зашел user: "+SessionDao.activeHash.get(o).getUser());
                            List<Message> messages = DaoGetter.userMsgDao.ReadMessageFromDialog(SessionDao.activeHash.get(o).getUserId(), SessionDao.activeHash.get(o).getChatUserId());

                            resp.setCharacterEncoding("UTF-8");
                            Template template = TemplateConfig.getConfig().getTemplate("messages.ftl");
                            Map<String, Object> templateData = new HashMap<>();
                            templateData.put("message", messages);
                            messages.forEach(System.out::println);

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
        super.doPost(req, resp);
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
