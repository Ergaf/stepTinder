package server;

import freemarker.template.Configuration;
import freemarker.template.Version;
import server.servlets.UserServlet;

public class TemplateConfig {
    public static Configuration getConfig(){
        Configuration cfg = new Configuration();
        cfg.setIncompatibleImprovements(new Version("2.3.23"));
        cfg.setClassForTemplateLoading(Main.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }
}
