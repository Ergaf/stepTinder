package server.login;

public class SessionGen {
    static public String createSessionId(){
        String time = String.valueOf(System.currentTimeMillis());
        return String.valueOf(time.hashCode());
    }
}
