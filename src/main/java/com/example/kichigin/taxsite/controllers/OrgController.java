package com.example.kichigin.taxsite.controllers;

import com.example.kichigin.taxsite.database.Org_inf;
import com.example.kichigin.taxsite.database.Person_payer;
import com.example.kichigin.taxsite.database.Person_private_inf;
import com.example.kichigin.taxsite.database.printinfo.Orginf;
import com.example.kichigin.taxsite.repository.Org_infRepository;
import com.example.kichigin.taxsite.repository.PayersRepository;
import com.example.kichigin.taxsite.repository.Person_payerRepository;
import com.example.kichigin.taxsite.repository.Person_private_infRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Controller
public class OrgController {

    @Autowired
    PayersRepository payersRepository;

    @Autowired
    Person_payerRepository person_payerRepository;

    @Autowired
    Person_private_infRepository person_private_infRepository;

    @Autowired
    Org_infRepository org_infRepository;

    @GetMapping("/inf-org-records")
    public String inf_org(Model model) {
        if(AuthorizationController.authorizedUser.isAuthorized()) {
            Long counter = 0L;
            counter++;
            ArrayList<Orginf> org_inf_list = new ArrayList<>();
            Iterable<Org_inf> orgInfs = org_infRepository.findAll();
            Iterator<Org_inf> iterator = orgInfs.iterator();
            while (iterator.hasNext()) {
                Org_inf element = iterator.next();
                Orginf org_inf = new Orginf();
                org_inf.setId(element.getOrg_id());
                org_inf.setFull_name_org(element.getFull_name_org());
                org_inf.setCeo(element.getCeo());
                org_inf.setType(element.getType());
                org_inf.setE_signature(element.getE_signature());
                org_inf.setPayer_reg_number(element.getPayer_reg_number());
                Optional<Person_payer> person_payer = person_payerRepository.findById(counter);
                Optional<Person_private_inf> person_private_inf = person_private_infRepository.findById(person_payer.get().getPerson_id());
                org_inf.setLastname(person_private_inf.get().getPerson_lastname());
                org_inf_list.add(org_inf);
                counter++;
            }
            model.addAttribute("orgs_inf", org_inf_list);

            return "inf-org-records";
        }
        else {return "login";}

    }

    @GetMapping("/org/{id}/edit")
    public String edit_org_what(@PathVariable(value = "id") Long id, Model model){
        if (AuthorizationController.authorizedUser.isAuthorized()){

            Optional<Org_inf> org_inf = org_infRepository.findById(id);
            Orginf orginf = new Orginf();
            orginf.setId(id);
            orginf.setFull_name_org(org_inf.get().getFull_name_org());
            orginf.setCeo(org_inf.get().getCeo());
            orginf.setType(org_inf.get().getType());
            orginf.setE_signature(org_inf.get().getE_signature());
            orginf.setPayer_reg_number(org_inf.get().getPayer_reg_number());
            Optional<Person_payer> person_payer = person_payerRepository.findById(id);
            Optional<Person_private_inf> person_private_inf = person_private_infRepository.findById(person_payer.get().getPerson_id());
            orginf.setLastname(person_private_inf.get().getPerson_lastname());

            model.addAttribute("org", orginf);

            return "org-edit";
        }
        else {return "login";}
    }

    @PostMapping("/org/{id}/edit")
    public String edit_org(@PathVariable(value = "id") Long id, @RequestParam String full_name_org, @RequestParam String ceo, @RequestParam String type, Model model) {

        Org_inf org_inf = org_infRepository.findById(id).orElseThrow();
        org_inf.setFull_name_org(full_name_org);
        org_inf.setCeo(ceo);
        org_inf.setType(type);
        org_infRepository.save(org_inf);

        return "redirect:/inf-org-records";
    }
}
