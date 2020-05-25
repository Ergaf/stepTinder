package server.dao;

import server.esenses.User;

import java.util.List;

public interface UserDao {
    List<User> readAllUsers();
    User readUserForId(int id);
    void addUser(User user);
    void deleteUserForId(int id);
    void clearBase();
    List<Integer> readAllLike(User user);
    void userLike(User user);
}
