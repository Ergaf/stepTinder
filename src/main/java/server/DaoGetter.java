package server;

import server.dao.MySQLUserDao;
import server.dao.ProfileDao;

public class DaoGetter {
    public static ProfileDao profileDao = new ProfileDao();
    public static UserDao userDao = new UserDao();
    public static MySQLUserDao userDaoSql = new MySQLUserDao();
}
