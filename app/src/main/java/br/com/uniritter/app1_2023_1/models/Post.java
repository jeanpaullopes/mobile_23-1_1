package br.com.uniritter.app1_2023_1.models;

import androidx.annotation.NonNull;

public class Post {

    private int id;
    private String title;
    private String boby;
    private User user;

    public Post(int id, String title, String boby, User user) {
        this.id = id;
        this.title = title;
        this.boby = boby;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBoby() {
        return boby;
    }

    public void setBoby(String boby) {
        this.boby = boby;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NonNull
    @Override
    public String toString() {
        return this.id+" -> "+this.title+" user: "+this.user.getName();
    }
}
