package server.dao.messages;

import server.esenses.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static server.dao.SqlConnection.getConnection;

public class MySqlMessagesDao implements MessagesDao{
    @Override
    public List<Message> ReadMessageFromDialog(int idThisUser, int idUserToWrite) {
        List<Message> messages = new ArrayList<>();
        System.out.println("юзер "+idThisUser+" хочет посмотреть сообщения с юзером "+idUserToWrite);
        try(Connection con = getConnection()){
            String sql = "SELECT * FROM tinder_step.messages WHERE (userid=? AND touserid=?) OR (userid=? AND touserid=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, idThisUser);
            ps.setObject(2, idUserToWrite);
            ps.setObject(3, idUserToWrite);
            ps.setObject(4, idThisUser);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int userid = rs.getInt("userid");
                int touserid = rs.getInt("touserid");
                String text = rs.getString("text");
                String time = rs.getString("time");

                Message message = new Message(id, userid, touserid, text, time);
                messages.add(message);
            }
            return messages;
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean addMessage(Message message) {
        try(Connection con = getConnection()){
            String sql = "INSERT INTO tinder_step.messages (userid, touserid, text, time) values (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, message.getUserid());
            ps.setObject(2, message.getTouserid());
            ps.setObject(3, message.getText());
            ps.setObject(4, message.getTime());
            ps.execute();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean deleteMessage(int id) {
        return false;
    }
}
