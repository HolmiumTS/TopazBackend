package GregTech.TopazBackend.metadata;

import GregTech.TopazBackend.tool.ToolClass;

import java.sql.Timestamp;

public class Message implements Comparable<Message> {
    int mid;
    int sender;
    int receiver;
    String content;
    int status;
    long time ;

    public String getStrTime(){
        return ToolClass.stamp2time(new Timestamp(this.time));
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getMid() {
        return mid;
    }

    public int getSender() {
        return sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public int getStatus() {
        return status;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int compareTo(Message o) {
        return o.getMid()-this.getMid();
    }
}
