package server.esenses;

public class Session {
    String user;
    int userId;
    String sessionId;

    public Session(String user, String sessionId) {
        this.user = user;
        this.sessionId = sessionId;
    }

    public Session(String user, int userId, String sessionId) {
        this.user = user;
        this.userId = userId;
        this.sessionId = sessionId;
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

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
