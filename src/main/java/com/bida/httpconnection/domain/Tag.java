package com.bida.httpconnection.domain;

public class Tag {
    private String name;
    private int id;

    public Tag(){
    }

    public Tag(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\"id\":" + id + ",\"name\":"+name;
    }
}
