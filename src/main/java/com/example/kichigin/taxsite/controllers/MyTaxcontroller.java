package com.example.kichigin.taxsite.controllers;

import com.example.kichigin.taxsite.database.Org_inf;
import com.example.kichigin.taxsite.database.Q_sum_tax;
import com.example.kichigin.taxsite.database.printinfo.PrintQ_sum_info;
import com.example.kichigin.taxsite.repository.Org_infRepository;
import com.example.kichigin.taxsite.repository.Q_sum_taxRepository;
import com.example.kichigin.taxsite.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

@Controller
public class MyTaxcontroller {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private Q_sum_taxRepository q_sum_taxRepository;

    @Autowired
    private Org_infRepository org_infRepository;

    @GetMapping("/tax")
    public String tax(Model model) {
        String usr_login = AuthorizationController.authorizedUser.getLogin();
        model.addAttribute("name", usr_login);
        if(AuthorizationController.authorizedUser.isAuthorized()) {
            System.out.println("Зашел в if");
            String user = AuthorizationController.authorizedUser.getLogin();
//            Iterable<Q_sum_tax> q_sum_tax = q_sum_taxRepository.findAll();
            model.addAttribute("user", user);

            ArrayList<PrintQ_sum_info> print_Org_infArrayList = new ArrayList<>();
            Iterable<Q_sum_tax> q_sum_inf = q_sum_taxRepository.findAll();
            Iterator<Q_sum_tax> iterator = q_sum_inf.iterator();
            int count = 1;
            System.out.println("Начинаю while");
            while (iterator.hasNext()) {
                Q_sum_tax element = iterator.next();
                PrintQ_sum_info e = new PrintQ_sum_info();
//                System.out.println(element.getTax_payer_id());
//                System.out.println(AuthorizationController.authorizedUser.getPayer_id());
                if (Objects.equals(element.getTax_payer_id(), AuthorizationController.authorizedUser.getPayer_id())) {
//                    System.out.println(element.getDate());
//                    System.out.println(element.getIncom());
                    e.setCount(count);
                    e.setData(element.getDate());
                    e.setIncom(element.getIncom());
                    e.setProfit(element.getProfit());
                    e.setPayout_pp(element.getPayout_pp());
                    e.setTax_pay(element.getTax_pay());
                    print_Org_infArrayList.add(e);
                    count += 1;

                }
            }
            if(!print_Org_infArrayList.isEmpty()) {
                System.out.println("Зашел в передачу data-tax");
//                System.out.println(print_Org_infArrayList);

                model.addAttribute("data_tax", print_Org_infArrayList);
                return "tax";
            }
            else {
                System.out.println("Колбек логин 60");
                return "login";
            }



//            model.addAttribute("list_tax", q_sum_tax);
//            return "tax";
        }
        else {
            System.out.println("Колбек в 70");
            return "login";
        }



    }

}
