package server.dao.users;

import server.esenses.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static server.dao.SqlConnection.getConnection;

public class MySQLUserDao implements UserDao {
    @Override
    public List<User> readAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection con = getConnection()){
            String sql = "SELECT * FROM tinder_step.users";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String photo = rs.getString("photo");
                String pass = rs.getString("pass");

                User user = new User(id, name, pass, photo);
                users.add(user);
            }
            return users;
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<User> readAllUsersExceptThisUser(int userId) {
        List<User> users = new ArrayList<>();
        try(Connection con = getConnection()){
            String sql = "SELECT * FROM tinder_step.users WHERE tinder_step.users.id!=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String photo = rs.getString("photo");
                String pass = rs.getString("pass");

                User user = new User(id, name, pass, photo);
                users.add(user);
            }
            return users;
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User readUserForName(String userName) {
        try(Connection con = getConnection()){
            User user = new User("\n", "\n");
            String sql = "SELECT * FROM tinder_step.users WHERE name=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, userName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String photo = rs.getString("photo");
                String pass = rs.getString("pass");
                user = new User(id, name, pass, photo);
            }
            return user;
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User readUserForId(int userId) {
        try(Connection con = getConnection()){
            User user = new User("\n", "\n");
            String sql = "SELECT * FROM tinder_step.users WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, userId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String photo = rs.getString("photo");
                String pass = rs.getString("pass");
                user = new User(id, name, pass, photo);
            }
            return user;
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void addUser(User user) {
        try(Connection con = getConnection()){
            String sql = "INSERT INTO tinder_step.users (name, pass, photo) VALUES(?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPass());
            ps.setString(3, user.getPhoto());
            ps.execute();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteUserForName(String userName) {

    }

    @Override
    public void clearBase() {
        try(Connection con = getConnection()){
            String sql = "TRUNCATE tinder_step.users";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
