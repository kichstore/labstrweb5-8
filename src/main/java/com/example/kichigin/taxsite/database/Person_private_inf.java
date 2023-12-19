package com.example.kichigin.taxsite.database;

import jakarta.persistence.*;

@Entity
public class Person_private_inf {
    @Id
    @SequenceGenerator(name = "per_seq_new", sequenceName = "per_sequence_new", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "per_seq_new")
    private Long inf_id;
    private String person_name, person_lastname, person_patronymic, phone, email;

    public Person_private_inf(Long inf_id, String person_name, String person_lastname, String person_patronymic, String phone, String email) {
        this.inf_id = inf_id;
        this.person_name = person_name;
        this.person_lastname = person_lastname;
        this.person_patronymic = person_patronymic;
        this.phone = phone;
        this.email = email;
    }

    public Person_private_inf () {

    }

    public Person_private_inf(String person_name, String person_lastname, String person_patronymic, String phone, String email) {
        this.person_name = person_name;
        this.person_lastname = person_lastname;
        this.person_patronymic = person_patronymic;
        this.phone = phone;
        this.email = email;
    }

    public Long getInf_id() {
        return inf_id;
    }

    public void setInf_id(Long inf_id) {
        this.inf_id = inf_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_lastname() {
        return person_lastname;
    }

    public void setPerson_lastname(String person_lastname) {
        this.person_lastname = person_lastname;
    }

    public String getPerson_patronymic() {
        return person_patronymic;
    }

    public void setPerson_patronymic(String person_patronymic) {
        this.person_patronymic = person_patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
