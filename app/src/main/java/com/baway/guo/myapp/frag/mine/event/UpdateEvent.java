package com.baway.guo.myapp.frag.mine.event;

public class UpdateEvent {
    private String name;

    public UpdateEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
