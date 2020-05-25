package server.servlets;

import freemarker.template.Template;
import server.DaoGetter;
import server.SoftGetter;
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

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Template template = TemplateConfig.getConfig().getTemplate("register.ftl");
        Map<String, Object> templateData = new HashMap<>();

//        List<String> databases = dao.readAll();
//        templateData.put("databases", databases);

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
        user.setPhoto("https://cdn0.iconfinder.com/data/icons/set-ui-app-android/32/8-512.png");


        //проверка на наличие такого имени в базе юзеров(мог быть емеил мб)
        System.out.println(user.getName());
        User testForName = DaoGetter.userDaoSql.readUserForName(user.getName());
        PrintWriter out = resp.getWriter();

        if(testForName.getName().equals("\n")){
          DaoGetter.userDaoSql.addUser(user);
            out.print(SoftGetter.gson.toJson(true));
        } else {
            out.print(SoftGetter.gson.toJson(false));
        }
        out.flush();
    }
}