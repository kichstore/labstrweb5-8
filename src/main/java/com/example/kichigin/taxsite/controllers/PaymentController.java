package com.example.kichigin.taxsite.controllers;

import com.example.kichigin.taxsite.database.*;
import com.example.kichigin.taxsite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

@Controller
public class PaymentController {

    @Autowired
    PayersRepository payersRepository;

    @Autowired
    Payers_infRepository payers_infRepository;

    @Autowired
    TaxRepository taxRepository;

    @Autowired
    Person_private_infRepository person_private_infRepository;

    @Autowired
    Org_infRepository org_infRepository;

    @Autowired
    Q_sum_taxRepository q_sum_taxRepository;

    @GetMapping("/add-payment")
    public String add_payment(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized())
        {

            Iterable<Payers_inf> payers_Infs = payers_infRepository.findAll();
            Iterator<Payers_inf> iterator = payers_Infs.iterator();
            while (iterator.hasNext()) {
                Payers_inf payers_inf = iterator.next();
                if (Objects.equals(payers_inf.getPayers_id(), AuthorizationController.authorizedUser.getPayer_id())) {
                    Optional<Tax> tax = taxRepository.findById(payers_inf.getTax_type());
                    model.addAttribute("tax_inf", tax.get());
                }

            }

            String usr_login = AuthorizationController.authorizedUser.getLogin();
            model.addAttribute("name", usr_login);

            Optional<Person_private_inf> person_private_inf = person_private_infRepository.findById(AuthorizationController.authorizedUser.getUser_id());
            model.addAttribute("person_inf", person_private_inf.get());
            System.out.println(person_private_inf.get().getPerson_name());

            Optional<Org_inf> org_inf = org_infRepository.findById(AuthorizationController.authorizedUser.getPayer_id());
            model.addAttribute("org_inf", org_inf.get());

            return "add-payment";
        }
        return "/login";
    }

    @PostMapping("/add-payment")
    public String add_payment_new(@RequestParam String payment_data, @RequestParam String incom, @RequestParam String profit, @RequestParam String payout_pp, @RequestParam String sum, Model model) {
        System.out.println(payment_data + " " + incom + " " + profit + " " + payout_pp + " " + sum + " ");
        Long id_for_table = 0L;
        Long income_new = Long.parseLong(incom);
        Long profit_new = Long.parseLong(profit);
        Long payout_pp_new = Long.parseLong(payout_pp);
        Long tax = Long.parseLong(sum);

        Iterable<Q_sum_tax> q_sum_taxes = q_sum_taxRepository.findAll();
        Iterator<Q_sum_tax> iterator = q_sum_taxes.iterator();
        while (iterator.hasNext()) {
            Q_sum_tax qSumTaxElement = iterator.next();
            if (qSumTaxElement.getId() > id_for_table) {
                id_for_table = qSumTaxElement.getId();
            }
        }
        id_for_table++;
        System.out.println(id_for_table + " " + AuthorizationController.authorizedUser.getPayer_id() + " " + income_new + " " + profit_new + " " + payout_pp_new + " " + tax);

        Q_sum_tax q_sum_tax = new Q_sum_tax(id_for_table, AuthorizationController.authorizedUser.getPayer_id(), payment_data, income_new, profit_new, payout_pp_new, tax);
        q_sum_taxRepository.save(q_sum_tax);

        Iterable<Payers_inf> payersInfs = payers_infRepository.findAll();
        Iterator<Payers_inf> iterator_2 = payersInfs.iterator();
        while (iterator_2.hasNext()) {
            Payers_inf payers_inf_element = iterator_2.next();
            System.out.println("Я перед if ---------" + payers_inf_element.getPayers_id());
            if (Objects.equals(payers_inf_element.getPayers_id(), AuthorizationController.authorizedUser.getPayer_id())) {
                System.out.println("Я внутри иф------");
                try {

                    Payers_inf payers_inf = payers_infRepository.findById(payers_inf_element.getId()).orElseThrow();
                    payers_inf.setSum_incom(payers_inf.getSum_incom() + income_new);
                    payers_inf.setSum_profit(payers_inf.getSum_profit() + profit_new);
                    payers_inf.setSum_payout_pp(payers_inf.getSum_payout_pp() + payout_pp_new);
                    payers_inf.setSum_tax_pay(payers_inf.getSum_tax_pay() + tax);
                    payers_infRepository.save(payers_inf);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }

        }


        return "redirect:/tax";
    }

}
