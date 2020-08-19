package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository("messageDao")
public class MessageDao {
    private static final Logger log = LoggerFactory.getLogger(MessageDao.class);
    private final JdbcTemplate jdbc;

    @Autowired
    public MessageDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public static class messageMap implements RowMapper<Message> {

        @Override
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            Message msg = new Message();
            msg.setContent(rs.getString("content"));
            msg.setMid(rs.getInt("mid"));
            msg.setReceiver(rs.getInt("receiver"));
            msg.setStatus(rs.getInt("status"));
            msg.setSender(rs.getInt("sender"));
            msg.setTime(rs.getTimestamp("time").getTime());
            return msg;
        }
    }

    public boolean generateNewMsg(int sender,int receiver,String content){
        try {
            log.warn("try to insert message");
            String sql ="insert  into  message(sender, receiver, content,time) values(?,?,?,?) ";
            int i=jdbc.update(sql,sender,receiver,content,new Timestamp(new Date().getTime()));

            return  true;
        }catch (Exception e){
            return false;
        }
    }
    /**
     * get sorted msg for a user
     * */
    public List<Message> getSortedMsg(int receiver){
        String sql ="select * from message where receiver = ?";
        List<Message> messages= jdbc.query(sql,new messageMap(),receiver);
        if (!messages.isEmpty()) {
            Collections.sort(messages);
        }
        return messages;
    }

    public boolean markAsRead(int mid){
        try {
            String sql = "update  message set status=1 where mid=?";
            jdbc.update(sql,mid);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
