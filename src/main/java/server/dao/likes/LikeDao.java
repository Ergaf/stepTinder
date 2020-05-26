package server.dao.likes;

import server.esenses.User;

import java.util.List;

public interface LikeDao {
    List<User> readAllLikeThisUser(int userId);
    User readAllNotLikeThisUser(int userId);
    boolean LikeThisForThisUser(int userId, int likedId);
    void userAddLike(int userId, int likedId);
}
