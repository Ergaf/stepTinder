package server.dao.likes;

import server.DaoGetter;
import server.esenses.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static server.dao.SqlConnection.getConnection;

public class MySqlUserLikeDao implements LikeDao{
    @Override
    public List<User> readAllLikeThisUser(int userId) {
        List<User> users = new ArrayList<>();
        try(Connection con = getConnection()){
            String sql = "SELECT tinder_step.users.id, tinder_step.users.name, tinder_step.users.pass, tinder_step.users.photo FROM tinder_step.users JOIN tinder_step.liked_for_users ON tinder_step.users.id=tinder_step.liked_for_users.likedid WHERE wholikeid=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String pass = rs.getString("pass");
                String photo = rs.getString("photo");

                User LikedUser = new User(id, name, pass, photo);
                users.add(LikedUser);
            }
            return users;
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User readAllNotLikeThisUser(int userId) {
        try(Connection con = getConnection()){
            Random random = new Random();
            List<User> likedUsers = new ArrayList<>();
//            List<User> likedUsers = DaoGetter.userDaoSql.readAllUsers();

            String sql = "SELECT DISTINCT tinder_step.users.id, tinder_step.users.name, tinder_step.users.pass, tinder_step.users.photo FROM tinder_step.users JOIN tinder_step.liked_for_users ON tinder_step.users.id=tinder_step.liked_for_users.likedid WHERE tinder_step.liked_for_users.wholikeid!=? AND tinder_step.users.id!=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String pass = rs.getString("pass");
                String photo = rs.getString("photo");

                User LikedUser = new User(id, name, pass, photo);
                likedUsers.add(LikedUser);
            }
            System.out.println(likedUsers.size());
            int randomUser = random.nextInt(likedUsers.size());
            return likedUsers.get(randomUser);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean LikeThisForThisUser(int userId, int likedId) {
        try(Connection con = getConnection()){
            String sql = "SELECT * FROM tinder_step.liked_for_users where wholikeid=? and likedid=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, likedId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if(rs.getInt("wholikeid") == userId){
                return true;
            } else {
                return false;
            }
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    public void userAddLike(int userId, int likedId) {
        try(Connection con = getConnection()){
            String sql = "INSERT INTO tinder_step.liked_for_users (wholikeid, likedid) VALUES(?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, likedId);
            ps.execute();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
