package server;

import server.dao.likes.LikeDao;
import server.dao.likes.MySqlUserLikeDao;
import server.dao.messages.MessagesDao;
import server.dao.messages.MySqlMessagesDao;
import server.dao.users.MySQLUserDao;
import server.dao.users.UserDao;

public class DaoGetter {
    public static final UserDao userDaoSql = new MySQLUserDao();
    public static final LikeDao userLikeDao = new MySqlUserLikeDao();
    public static final MessagesDao userMsgDao = new MySqlMessagesDao();
}
