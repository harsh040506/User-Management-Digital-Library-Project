package com.example.AddUserbyAdminApplication.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String fullname;

    public User() {
    }

    public User(String username, String password, String fullname) {
        super();
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    @Override
    public String toString() {
        return "User [id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", fullname=" + fullname + "]";
    }
}