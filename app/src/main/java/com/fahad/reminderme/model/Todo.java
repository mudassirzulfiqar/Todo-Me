package com.fahad.reminderme.model;

/**
 * Created by moodi on 26/01/2018.
 */

public class Todo {

    private String title;
    private String time;
    private String id;

    public Todo(String title, String time, String id) {
        this.title = title;
        this.time = time;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
