package com.bida.httpconnection.domain;

public enum Status {
    AVAILABLE("available"), PENDING("pending"), SOLD("sold");
    private final String name;
    private Status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
