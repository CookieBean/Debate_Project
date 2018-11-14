package com.simplism.iwillhealyou.Data;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Debate {

    public String rm;
    public boolean p1;
    public boolean p2;
    public boolean p3;
    public boolean p4;
    public String p1uid;
    public String p2uid;
    public String p3uid;
    public String p4uid;
    public String roomname;
    public String topic;

    public Debate() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Debate(String rm, boolean p1, boolean p2, boolean p3, boolean p4, String p1uid, String p2uid, String p3uid, String p4uid, String roomname, String topic) {
        this.rm = rm;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p1uid = p1uid;
        this.p2uid = p2uid;
        this.p3uid = p3uid;
        this.p4uid = p4uid;
        this.roomname = roomname;
        this.topic = topic;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public boolean isP1() {
        return p1;
    }

    public void setP1(boolean p1) {
        this.p1 = p1;
    }

    public boolean isP2() {
        return p2;
    }

    public void setP2(boolean p2) {
        this.p2 = p2;
    }

    public boolean isP3() {
        return p3;
    }

    public void setP3(boolean p3) {
        this.p3 = p3;
    }

    public boolean isP4() {
        return p4;
    }

    public void setP4(boolean p4) {
        this.p4 = p4;
    }

    public String getP1uid() {
        return p1uid;
    }

    public void setP1uid(String p1uid) {
        this.p1uid = p1uid;
    }

    public String getP2uid() {
        return p2uid;
    }

    public void setP2uid(String p2uid) {
        this.p2uid = p2uid;
    }

    public String getP3uid() {
        return p3uid;
    }

    public void setP3uid(String p3uid) {
        this.p3uid = p3uid;
    }

    public String getP4uid() {
        return p4uid;
    }

    public void setP4uid(String p4uid) {
        this.p4uid = p4uid;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
