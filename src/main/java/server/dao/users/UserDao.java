package server.dao.users;

import server.esenses.User;

import java.util.List;

public interface UserDao {
    List<User> readAllUsers();
    List<User> readAllUsersExceptThisUser(int userId);
    User readUserForName(String userName);
    User readUserForId(int userId);
    void addUser(User user);
    void deleteUserForName(String userName);
    void clearBase();
}
