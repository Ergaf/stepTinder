package server.dao.messages;

import server.esenses.Message;
import server.esenses.User;

import java.util.List;

public interface MessagesDao {
    List<Message> ReadMessageFromDialog(int idThisUser, int idUserToWrite);
    boolean addMessage(Message message);
    boolean deleteMessage(int id);
}
