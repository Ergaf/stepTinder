package server;

import server.dao.MySQLUserDao;

public class DaoGetter {
    public static MySQLUserDao userDaoSql = new MySQLUserDao();
}
