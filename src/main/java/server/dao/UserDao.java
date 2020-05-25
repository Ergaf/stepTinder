package server.dao;

import server.esenses.User;

import java.util.List;

public interface UserDao {
    List<User> readAllUsers();
    User readUserForName(String userName);
    void addUser(User user);
    void deleteUserForId(int id);
    void clearBase();
    List<Integer> readAllLike(User user);
    void userLike(User user);
}
