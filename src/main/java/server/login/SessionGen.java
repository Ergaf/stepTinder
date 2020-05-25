package server.login;

import java.util.Objects;

public class SessionGen {
    static public String createSessionId(String name){
        String time = String.valueOf(System.currentTimeMillis());
        return String.valueOf(Objects.hash(time, name));
    }


}
