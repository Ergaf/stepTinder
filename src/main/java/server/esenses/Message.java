package server.esenses;

public class Message {
    int id;
    int userid;
    int touserid;
    String text;
    String time;
    boolean msgForThisUser;

    public Message(int id, int userid, int touserid, String text, String time) {
        this.id = id;
        this.userid = userid;
        this.touserid = touserid;
        this.text = text;
        this.time = time;
    }

    public Message(int userid, int touserid, String text, String time) {
        this.userid = userid;
        this.touserid = touserid;
        this.text = text;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getTouserid() {
        return touserid;
    }

    public void setTouserid(int touserid) {
        this.touserid = touserid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isMsgForThisUser() {
        return msgForThisUser;
    }

    public void setMsgForThisUser(boolean msgForThisUser) {
        this.msgForThisUser = msgForThisUser;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userid=" + userid +
                ", touserid=" + touserid +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", msgForThisUser=" + msgForThisUser +
                '}';
    }
}
