package server.login;

import server.esenses.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionDao {
    static public List<Session> activeHash = new ArrayList<>();

//    static public boolean isTrue(String hash){
//        boolean is = false;
//        for(int i = 0; i < activeHash.size(); i++){
//            if(activeHash.get(i).equals(hash)){
//                is = true;
//            }
//        }
//        return is;
//    }
}
