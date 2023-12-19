package com.example.kichigin.taxsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String Home(Model model) {
        model.addAttribute("title", "Хай евриван!!!");
        String usr_login = AuthorizationController.authorizedUser.getLogin();
        model.addAttribute("name", usr_login);
        return "home";
    }

    @GetMapping("/about")
    public String About(Model model) {
        model.addAttribute("title", "Страница про нас");
        String usr_login = AuthorizationController.authorizedUser.getLogin();
        model.addAttribute("name", usr_login);
        return "about";
    }

    @GetMapping("/faqs")
    public String Faqs(Model model) {
        model.addAttribute("title", "Страница про нас");
        String usr_login = AuthorizationController.authorizedUser.getLogin();
        model.addAttribute("name", usr_login);
        return "faqs";
    }


}