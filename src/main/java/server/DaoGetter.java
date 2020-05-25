package server;

import server.dao.likes.MySqlUserLikeDao;
import server.dao.users.MySQLUserDao;

public class DaoGetter {
    public static final MySQLUserDao userDaoSql = new MySQLUserDao();
    public static final MySqlUserLikeDao userLikeDao = new MySqlUserLikeDao();
}
