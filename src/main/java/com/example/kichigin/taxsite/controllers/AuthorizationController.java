package com.example.kichigin.taxsite.controllers;

import com.example.kichigin.taxsite.database.Person_payer;
import com.example.kichigin.taxsite.database.Person_private_inf;
import com.example.kichigin.taxsite.database.Roles;
import com.example.kichigin.taxsite.database.Users;
import com.example.kichigin.taxsite.database.printinfo.AuthorizedUser;
import com.example.kichigin.taxsite.repository.Person_payerRepository;
import com.example.kichigin.taxsite.repository.Person_private_infRepository;
import com.example.kichigin.taxsite.repository.RolesRepository;
import com.example.kichigin.taxsite.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.Objects;

@Controller
public class AuthorizationController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private Person_private_infRepository person_private_infRepository;

    @Autowired
    private Person_payerRepository person_payerRepository;

    public static AuthorizedUser authorizedUser = new AuthorizedUser();

    private String errorMessage = "";
    private String errorMessage_reg = "";
    private String regMessage = "";

    private String login = "";
    private String name = "";
    private String lastname = "";
    private String patronymic = "";
    private String phone = "";
    private String email = "";

    private Long count = 0L;

    @GetMapping("/login")
    public String FirstLogin(Model model) {
        authorizedUser.setAuthorized(false);
        authorizedUser.setRole("user");

        errorMessage_reg = "";
        this.login = "";
        this.name = "";
        this.lastname = "";
        this.patronymic = "";
        this.phone = "";
        this.email = "";

        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("regMessage", regMessage);

        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password, Model model) {

        try {
            System.out.println("-----проверка логина------");
            Users users = usersRepository.findByLogin(login).orElseThrow();
            System.out.println("--------проверка пройлена------ " + users.getUser_id());
            if(users.getPassword().equals(password)){
                errorMessage = "";
                regMessage = "";
                System.out.println("-----10------");
                Roles role = rolesRepository.findById(users.getUser_id()).orElseThrow();
                System.out.println("-----10.2----");
                authorizedUser.setAuthorized(true);
                authorizedUser.setUser_id(users.getUser_id());
                if (role.getRole_name().equals("admin")) {
                    authorizedUser.setRole("admin");
                    return "redirect:/users-records";
                }
                System.out.println("------20-----");
                Person_private_inf person_private_inf = person_private_infRepository.findById(users.getUser_id()).orElseThrow();
                System.out.println("Я прошел персон приват 85");
                authorizedUser.setRole("user");
                authorizedUser.setLogin(login);
                authorizedUser.setPassword(password);
                authorizedUser.setName(person_private_inf.getPerson_name());
                authorizedUser.setLastname(person_private_inf.getPerson_lastname());
                authorizedUser.setPatronymic(person_private_inf.getPerson_patronymic());
                authorizedUser.setPhone(person_private_inf.getPhone());
                authorizedUser.setEmail(person_private_inf.getEmail());
                System.out.println("Я в начале персон_паер 95");
                Iterable<Person_payer> personPayers = person_payerRepository.findAll();
                Iterator<Person_payer> iterator = personPayers.iterator();
                while (iterator.hasNext()){
                    Person_payer person = iterator.next();
                    if (Objects.equals(person.getPerson_id(), users.getUser_id())) {
                        authorizedUser.setPayer_id(person.getPayer_id());
                        System.out.println(person.getPerson_id());
                    }
                }

//                Person_payer person_payer = person_payerRepository.findById(users.getUser_id()).orElseThrow();
                System.out.println("Я прошел персогн_паер_97");
//                authorizedUser.setPayer_id(person_payer.getPayer_id());
                model.addAttribute("who_user", login);
                return "redirect:/";

            } else {
                errorMessage = "Неверный пароль";
            }

        } catch (Exception e) {
            errorMessage = "Неверный логин";
            System.out.println("\n" + e );
        }

        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("login", "");
        model.addAttribute("name", "");
        model.addAttribute("lastname", "");
        model.addAttribute("patronymic", "");
        model.addAttribute("phone", "");
        model.addAttribute("email", "");

        errorMessage = "";

        model.addAttribute("errorMessage", errorMessage);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String login, @RequestParam String password, @RequestParam String name, @RequestParam String lastname, @RequestParam String patronymic, @RequestParam String phone, @RequestParam String email, Model model) {
        Long counter = 1L;
        counter++;
        boolean user_is = false;

        Iterable<Users> users_check = usersRepository.findAll();
        Iterator<Users> iterator1 = users_check.iterator();
        while (iterator1.hasNext()) {
            Users user_check = iterator1.next();
            if (Objects.equals(user_check.getLogin(), login)) {
                user_is = true;
                System.out.println("такой логин есть");
                break;

            }
        }

        if (user_is) {
//            Users users_1 = usersRepository.findByLogin(login).orElseThrow();
            errorMessage_reg = "Логин уже занят !!!";
            this.login = login;
            this.name = name;
            this.lastname = lastname;
            this.patronymic = patronymic;
            this.phone = phone;
            this.email = email;
            return "redirect:/registration";
        }
        else {
            Iterable<Users> users = usersRepository.findAll();
            Iterator<Users> iterator = users.iterator();
            while (iterator.hasNext()){
                Users users1 = iterator.next();
                if (Objects.equals(users1.getUser_id(), counter)) {
                    counter++;
                    System.out.println(counter + "-------iterator fo user");
                }
            }

            Users user = new Users(counter, login, password);
            usersRepository.save(user);
            Users new_user = usersRepository.findByLogin(login).orElseThrow();
            Person_private_inf person_private_inf = new Person_private_inf(new_user.getUser_id(), name, lastname, patronymic, phone, email);
            person_private_infRepository.save(person_private_inf);
            Roles roles = new Roles(user.getUser_id(), "user");
            rolesRepository.save(roles);
            regMessage = "Вы зарегистрированы)";
            errorMessage_reg = "";
            errorMessage = "";
            return "redirect:/login";
        }

//        try {
//            Users users = usersRepository.findByLogin(login).orElseThrow();
//            errorMessage_reg = "Логин уже занят !!!";
//            this.login = login;
//            this.name = name;
//            this.lastname = lastname;
//            this.patronymic = patronymic;
//            this.phone = phone;
//            this.email = email;
//        } catch (Exception e) {
//            Iterable<Users> users = usersRepository.findAll();
//            Iterator<Users> iterator = users.iterator();
//            while (iterator.hasNext()){
//                Users users1 = iterator.next();
//                if (Objects.equals(users1.getUser_id(), counter)) {
//                    counter++;
//                }
//            }
//
//            Users user = new Users(counter, login, password);
//            usersRepository.save(user);
//            Person_private_inf person_private_inf = new Person_private_inf(user.getUser_id(), name, lastname, patronymic, phone, email);
//            person_private_infRepository.save(person_private_inf);
//            Roles roles = new Roles(user.getUser_id(), "user");
//            rolesRepository.save(roles);
//            regMessage = "Вы зарегистрированы)";
//            errorMessage_reg = "";
//            errorMessage = "";
//            return "redirect:/login";
//        }
//        return "redirect:/registration";

    }
}
