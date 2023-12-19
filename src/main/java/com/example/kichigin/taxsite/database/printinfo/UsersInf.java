package com.example.kichigin.taxsite.database.printinfo;

public class UsersInf {

    private Long user_id;

    private String login, password, role_name, person_name, person_lastname, person_patronymic, phone, email;

    public UsersInf() {
    }

    public UsersInf(Long user_id, String login, String password, String role_name, String person_name, String person_lastname, String person_patronymic, String phone, String email) {
        this.user_id = user_id;
        this.login = login;
        this.password = password;
        this.role_name = role_name;
        this.person_name = person_name;
        this.person_lastname = person_lastname;
        this.person_patronymic = person_patronymic;
        this.phone = phone;
        this.email = email;
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

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
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
