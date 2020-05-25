package server.esenses;

public class Session {
    String user;
    String sessionId;

    public Session(String user, String sessionId) {
        this.user = user;
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
}
