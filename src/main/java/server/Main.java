package server;

import server.esenses.User;

public class Main {
    public static void main(String[] args) throws Exception {
        ServerConfig server = new ServerConfig();

        //первоначальное наполнение рандомами для теста
        User user2 = new User("youtube", "https://i2.wp.com/al-awdany.org/wp/wp-content/uploads/2011/12/youtube-logo.jpg?resize=620%2C300&ssl=1");
        User user3 = new User("обезьянка", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/Homo_habilis-2.JPG/275px-Homo_habilis-2.JPG");
        User user4 = new User("девушка", "https://cdnimg.rg.ru/img/content/177/18/63/1000s_d_850.jpg");
        User user5 = new User("типичный пользователь тиндер", "https://img.gazeta.ru/files3/723/11725723/urod-pic685-685x390-29509.jpg");
        User user6 = new User("ня", "https://i.ytimg.com/vi/1P2ov_oidRQ/maxresdefault.jpg");
        User user7 = new User("кот", "https://i.pinimg.com/280x280_RS/37/27/de/3727de01c54394c170622f54ca4c53ff.jpg");
        User user8 = new User("дирижабль", "https://i.ytimg.com/vi/TnT9mcwveQI/hqdefault.jpg");
//        DaoGetter.userDaoSql.addUser(user2);
//        DaoGetter.userDaoSql.addUser(user3);
//        DaoGetter.userDaoSql.addUser(user4);
//        DaoGetter.userDaoSql.addUser(user5);
//        DaoGetter.userDaoSql.addUser(user6);
//        DaoGetter.userDaoSql.addUser(user7);
//        DaoGetter.userDaoSql.addUser(user8);
        //---------------------

        server.start();
    }
}
