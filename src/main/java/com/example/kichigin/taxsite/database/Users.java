package com.example.kichigin.taxsite.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Users {

    @Id
    @SequenceGenerator(name = "usr_seq_new", sequenceName = "usr_sequence_new", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "usr_seq_new")
    private Long user_id;
    private String login, password;

    public Users() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users(Long users_id, String login, String password) {
        this.user_id = users_id;
        this.login = login;
        this.password = password;
    }

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
