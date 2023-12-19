package com.example.kichigin.taxsite.controllers;

import com.example.kichigin.taxsite.database.Person_private_inf;
import com.example.kichigin.taxsite.database.Roles;
import com.example.kichigin.taxsite.database.Users;
import com.example.kichigin.taxsite.database.printinfo.UsersInf;
import com.example.kichigin.taxsite.repository.Person_private_infRepository;
import com.example.kichigin.taxsite.repository.RolesRepository;
import com.example.kichigin.taxsite.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

@Controller
public class EditUsersController {

    @Autowired
    Person_private_infRepository person_private_infRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RolesRepository rolesRepository;

    @GetMapping("/users-records")
    public String watch_users (Model model) {

        if(AuthorizationController.authorizedUser.isAuthorized()) {

            ArrayList<UsersInf> usersInfArrayList = new ArrayList<>();
            Iterable<Users> users = usersRepository.findAll();
            System.out.println("-------1------");
            Iterator<Users> iterator = users.iterator();
            while (iterator.hasNext()) {
                Users user = iterator.next();
                UsersInf e = new UsersInf();
                e.setUser_id(user.getUser_id());
                System.out.println("-------2--------");
                e.setLogin(user.getLogin());
                e.setPassword(user.getPassword());
                System.out.println("--------2.5------");
                Person_private_inf person_private_inf1 = person_private_infRepository.findById(user.getUser_id()).orElseThrow();
                System.out.println("--------2.6-------");
                e.setPerson_name(person_private_inf1.getPerson_name());
                e.setPerson_lastname(person_private_inf1.getPerson_lastname());
                e.setPerson_patronymic(person_private_inf1.getPerson_patronymic());
                e.setEmail(person_private_inf1.getEmail());
                System.out.println("-------3--------");
                e.setPhone(person_private_inf1.getPhone());
                Roles roles = rolesRepository.findById(user.getUser_id()).orElseThrow();
                e.setRole_name(roles.getRole_name());
                usersInfArrayList.add(e);
            }
            System.out.println("----------4---------");
            model.addAttribute("users", usersInfArrayList);

            return "users-records";

        }
        else {
        return "login";
        }
    }

    @GetMapping("/users/{id}/remove")
    public String usersRemovePage(@PathVariable(value = "id") Long id, Model model) {
        if (AuthorizationController.authorizedUser.isAuthorized()){

            Optional<Users> user = usersRepository.findById(id);
            Optional<Roles> rol = rolesRepository.findById(id);
            Optional<Person_private_inf> person_private_inf = person_private_infRepository.findById(id);
            UsersInf usersInf = new UsersInf();
            usersInf.setUser_id(id);
            usersInf.setLogin(user.get().getLogin());
            usersInf.setPassword(user.get().getPassword());
            usersInf.setRole_name(rol.get().getRole_name());
            usersInf.setPerson_name(person_private_inf.get().getPerson_name());
            usersInf.setPerson_lastname(person_private_inf.get().getPerson_lastname());
            usersInf.setPerson_patronymic(person_private_inf.get().getPerson_patronymic());
            usersInf.setEmail(person_private_inf.get().getEmail());
            usersInf.setPhone(person_private_inf.get().getPhone());
            model.addAttribute("user", usersInf);

            return "users-edits-remove";
        }
        else {
            return "login";
        }
    }

    @PostMapping("/users/{id}/remove")
    public String user_edit_remove(@PathVariable(value = "id") Long id, Model model) {

        Users users = usersRepository.findById(id).orElseThrow();
        usersRepository.delete(users);
//        Roles roles = rolesRepository.findById(id).orElseThrow();
//        if (Objects.equals(role, "Пользователь")) {
//            roles.setRole_name("user");
//        }
//        else {
//            roles.setRole_name("admin");
//        }
//        rolesRepository.save(roles);
//        Person_private_inf person_private_inf = person_private_infRepository.findById(id).orElseThrow();
//        person_private_inf.setPerson_name(name);
//        person_private_inf.setPerson_lastname(lastname);
//        person_private_inf.setPerson_patronymic(patronymic);
//        person_private_inf.setEmail(email);
//        person_private_inf.setPhone(phone);
//        person_private_infRepository.save(person_private_inf);
        return "redirect:/users-records";
    }

    @GetMapping("/users/{id}/edit")
    public String usersEditPage(@PathVariable(value = "id") Long id, Model model) {
        if (AuthorizationController.authorizedUser.isAuthorized()){

            Optional<Users> user = usersRepository.findById(id);
            Optional<Roles> rol = rolesRepository.findById(id);
            Optional<Person_private_inf> person_private_inf = person_private_infRepository.findById(id);
            UsersInf usersInf = new UsersInf();
            usersInf.setUser_id(id);
            usersInf.setLogin(user.get().getLogin());
            usersInf.setPassword(user.get().getPassword());
            usersInf.setRole_name(rol.get().getRole_name());
            usersInf.setPerson_name(person_private_inf.get().getPerson_name());
            usersInf.setPerson_lastname(person_private_inf.get().getPerson_lastname());
            usersInf.setPerson_patronymic(person_private_inf.get().getPerson_patronymic());
            usersInf.setEmail(person_private_inf.get().getEmail());
            usersInf.setPhone(person_private_inf.get().getPhone());
            model.addAttribute("user", usersInf);

            return "users-edits";
        }
        else {
            return "login";
        }
    }

    @PostMapping("/users/{id}/edit")
    public String user_edit(@PathVariable(value = "id") Long id, @RequestParam String login, @RequestParam String password, @RequestParam String role, @RequestParam String name, @RequestParam String lastname, @RequestParam String patronymic, @RequestParam String phone, @RequestParam String email) {

        Users users = usersRepository.findById(id).orElseThrow();
        users.setLogin(login);
        users.setPassword(password);
        usersRepository.save(users);
        Roles roles = rolesRepository.findById(id).orElseThrow();
        if (Objects.equals(role, "Пользователь")) {
            roles.setRole_name("user");
        }
        else {
            roles.setRole_name("admin");
        }
        rolesRepository.save(roles);
        Person_private_inf person_private_inf = person_private_infRepository.findById(id).orElseThrow();
        person_private_inf.setPerson_name(name);
        person_private_inf.setPerson_lastname(lastname);
        person_private_inf.setPerson_patronymic(patronymic);
        person_private_inf.setEmail(email);
        person_private_inf.setPhone(phone);
        person_private_infRepository.save(person_private_inf);
        return "redirect:/users-records";
    }

    @GetMapping("/users/{id}/edit-user")
    public String usersEditPage_for_user(@PathVariable(value = "id") Long id, Model model) {
        if (AuthorizationController.authorizedUser.isAuthorized()){

            Optional<Users> user = usersRepository.findById(id);
            Optional<Person_private_inf> person_private_inf = person_private_infRepository.findById(id);
            UsersInf usersInf = new UsersInf();
            usersInf.setUser_id(id);
            usersInf.setLogin(user.get().getLogin());
            usersInf.setPassword(user.get().getPassword());
            usersInf.setPerson_name(person_private_inf.get().getPerson_name());
            usersInf.setPerson_lastname(person_private_inf.get().getPerson_lastname());
            usersInf.setPerson_patronymic(person_private_inf.get().getPerson_patronymic());
            usersInf.setEmail(person_private_inf.get().getEmail());
            usersInf.setPhone(person_private_inf.get().getPhone());
            model.addAttribute("user", usersInf);

            return "users-edits-user";
        }
        else {
            return "login";
        }
    }

    @PostMapping("/users/{id}/edit-user")
    public String user_edit_for_user(@PathVariable(value = "id") Long id, @RequestParam String login, @RequestParam String password, @RequestParam String name, @RequestParam String lastname, @RequestParam String patronymic, @RequestParam String phone, @RequestParam String email) {

        Users users = usersRepository.findById(id).orElseThrow();
        users.setLogin(login);
        users.setPassword(password);
        AuthorizationController.authorizedUser.setLogin(login);
        AuthorizationController.authorizedUser.setPassword(password);
        usersRepository.save(users);
        Person_private_inf person_private_inf = person_private_infRepository.findById(id).orElseThrow();
        person_private_inf.setPerson_name(name);
        person_private_inf.setPerson_lastname(lastname);
        person_private_inf.setPerson_patronymic(patronymic);
        person_private_inf.setEmail(email);
        person_private_inf.setPhone(phone);
        person_private_infRepository.save(person_private_inf);
        return "redirect:/my-account";
    }
}
