package cn.aezo.demo.jackson.bean;

import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(MyPropertyNamingStrategy.class)
public class HobbyBean {
    private String hobby;
    private int index;

    public HobbyBean() {
    }

    public HobbyBean(String hobby, int index) {
        super();
        this.hobby = hobby;
        this.index = index;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return this.hobby;
    }
}
