package com.zuhlke.apachecalcitedemo.dto;

import java.io.Serializable;

public class OrderDto implements Serializable {

    private static final long serialVersionUID = 5772608656074129388L;

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
