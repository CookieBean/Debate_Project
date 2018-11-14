package com.simplism.iwillhealyou.Data;

public class Mode {
    public String mode;
    public String cont;

    public Mode() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Mode(String mode, String cont) {
        this.mode = mode;
        this.cont = cont;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }
}
