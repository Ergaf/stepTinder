package server;


import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import server.filters.UserNoRegisterFilter;
import server.servlets.*;

public class ServerConfig {
    private Server server;

    public void start() throws Exception {
        server = new Server();

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(90);
        server.setConnectors(new Connector[]{connector});

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(UserServlet.class, "/user");
        servletHandler.addServletWithMapping(LoginServlet.class, "/login");
        servletHandler.addServletWithMapping(RegisterServlet.class, "/registration");
        servletHandler.addServletWithMapping(LikedServlet.class, "/liked");
        servletHandler.addServletWithMapping(MessagesServlet.class, "/messages");


        servletHandler.addFilterWithMapping(UserNoRegisterFilter.class, "/user", 1);
        servletHandler.addFilterWithMapping(UserNoRegisterFilter.class, "/liked", 1);
        servletHandler.addFilterWithMapping(UserNoRegisterFilter.class, "/messages", 1);

        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }
}