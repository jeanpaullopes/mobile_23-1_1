package br.com.uniritter.app1_2023_1.models;

import androidx.annotation.NonNull;

public class User {
    private int id;
    private String name;
    private String userName;
    private String email;

    private Address address;

    public User(int id, String name, String userName, String email) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
    }

    public User(int id, String name, String userName, String email, Address address) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public CharSequence getNameCS() {
        return name;
    }
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @NonNull
    @Override
    public String toString() {
        return "Nome:"+name+"; id: "+id;
    }
}
