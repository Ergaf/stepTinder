package server.servlets;

import freemarker.template.Template;
import server.DaoGetter;
import server.TemplateConfig;
import server.esenses.User;

import javax.servlet.ServletException;
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
//        Stringify name = gson.fromJson(reqData, Stringify.class);
//        ThisBaseSaver.thisBaseName = name.getStr();
        PrintWriter out = resp.getWriter();
        List<User> profiles = DaoGetter.userDaoSql.readAllUsers();

        if(profileNumber >= profiles.size()-1){
            out.print("/liked");
            profileNumber = 0;
        } else {
            out.print("/user");
            profileNumber++;
        }

//        out.print(reqData);
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
