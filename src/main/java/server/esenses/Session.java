package server.esenses;

import java.util.List;

public class Session {
    String user;
    int userId;
    String sessionId;
    List<User> users;
    int likeNumber;

    public Session(String user, String sessionId) {
        this.user = user;
        this.sessionId = sessionId;
    }

    public Session(String user, int userId, String sessionId) {
        this.user = user;
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public Session(String user, int userId, String sessionId, List<User> users) {
        this.user = user;
        this.userId = userId;
        this.sessionId = sessionId;
        this.users = users;
        this.likeNumber = users.size();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getUserId() {
        return userId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        this.likeNumber = users.size();
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }
}
