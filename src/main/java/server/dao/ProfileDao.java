package server.dao;

import java.util.ArrayList;
import java.util.List;

public class ProfileDao {
    List<Profile> profiles = new ArrayList<>();

    public List<Profile> getAll(){
        return profiles;
    }

    public void addProfile(String name, String photo){
        Profile profile = new Profile(name, photo);
        profiles.add(profile);
    }

    public void addProfileAndId(int id, String name, String photo){
        Profile profile = new Profile(id, name, photo);
        profiles.add(profile);
    }
}
