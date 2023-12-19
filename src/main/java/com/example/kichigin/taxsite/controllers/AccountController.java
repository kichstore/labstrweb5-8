package com.example.kichigin.taxsite.controllers;

import com.example.kichigin.taxsite.database.*;
import com.example.kichigin.taxsite.database.printinfo.Tax_info;
import com.example.kichigin.taxsite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    private Person_private_infRepository person_private_infRepository;

    @Autowired
    private Payers_infRepository payers_infRepository;

    @Autowired
    private Org_infRepository org_infRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/my-account")
    public String accountInfo(Model model) {
        if (AuthorizationController.authorizedUser.isAuthorized()) {
            String usr_login = AuthorizationController.authorizedUser.getLogin();
            model.addAttribute("name", usr_login);
            Optional<Person_private_inf> inf = person_private_infRepository.findById(AuthorizationController.authorizedUser.getUser_id());
            if (inf.isEmpty()) {
                return "/";
            }
            model.addAttribute("login", AuthorizationController.authorizedUser.getLogin());
            model.addAttribute("password", AuthorizationController.authorizedUser.getPassword());
            model.addAttribute("inf", inf.get());

            Iterable<Payers_inf> payers_Infs = payers_infRepository.findAll();
            Iterator<Payers_inf> iterator = payers_Infs.iterator();
            while (iterator.hasNext()) {
                Payers_inf payers_inf = iterator.next();
                if (Objects.equals(payers_inf.getPayers_id(), AuthorizationController.authorizedUser.getPayer_id())) {
                    model.addAttribute("payer_inf", payers_inf);
                    Optional<Org_inf> org_inf = org_infRepository.findById(AuthorizationController.authorizedUser.getPayer_id());
                    model.addAttribute("org_inf", org_inf.get());
                    System.out.println(payers_inf.getTax_type());
//                Tax_info taxInfo = new Tax_info();
                    Optional<Tax> tax_info = taxRepository.findById(payers_inf.getTax_type());
//                ArrayList<Tax> info = new ArrayList<>();
//                tax_info.ifPresent(info::add);
//                model.addAttribute("info", info);
                    model.addAttribute("info", tax_info.get());
                    String typeTax = tax_info.get().getType_tax();
                    if (Objects.equals(typeTax, "forsp_1")) {
                        model.addAttribute("nameTax", "Налогооблажение для ИП первого типа");
                    }
                    if (Objects.equals(typeTax, "forsp_2")) {
                        model.addAttribute("nameTax", "Налогооблажение для ИП второго типа");
                    }
                    if (Objects.equals(typeTax, "fororg_1")) {
                        model.addAttribute("nameTax", "Налогооблажение для ОРГАНИЗАЦИЙ первого типа");
                    }
                    if (Objects.equals(typeTax, "fororg_2")) {
                        model.addAttribute("nameTax", "Налогооблажение для ОРГАНИЗАЦИЙ второго типа");
                    }
                    if (Objects.equals(typeTax, "for_sim")) {
                        model.addAttribute("nameTax", "Упрощенное налогооблажение");
                    }
                }
            }

            Iterable<Users> users = usersRepository.findAll();
            Iterator<Users> iterator1 = users.iterator();
            while (iterator1.hasNext()) {
                Users usr = iterator1.next();
                if (Objects.equals(usr_login, usr.getLogin())) {
                    model.addAttribute("id_for_user_edit", usr.getUser_id());
                    break;
                }
            }
            return "/my-account";
        }
        else {return "/login";}



    }
}
