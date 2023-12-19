package com.example.kichigin.taxsite.database;

import jakarta.persistence.*;

import javax.management.relation.Role;

@Entity
public class Roles {

    @Id
    @SequenceGenerator(name = "rol_seq_new", sequenceName = "rol_sequence_new", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "rol_seq_new")
    private Long user_id;
    private String role_name;

    public Roles(){
    }

    public Roles(Long user_id, String role_name) {
        this.user_id = user_id;
        this.role_name = role_name;
    }

    public Roles(String role_name) {
        this.role_name = role_name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
