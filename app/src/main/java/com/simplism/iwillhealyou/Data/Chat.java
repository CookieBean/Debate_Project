package com.simplism.iwillhealyou.Data;

public class Chat {
    public String person;
    public String cont;

    public Chat() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Chat(String person, String cont) {
        this.person = person;
        this.cont = cont;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }
}
