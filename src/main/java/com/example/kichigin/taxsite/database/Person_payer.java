package com.example.kichigin.taxsite.database;

import jakarta.persistence.*;

@Entity
public class Person_payer {

    @Id
    @SequenceGenerator(name = "ppr_seq", sequenceName = "ppr_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ppr_seq")
    private Long id;
    private Long person_id;
    private Long payer_id;

    public Person_payer() {

    }

    public Person_payer(Long id, Long person_id, Long payer_id) {
        this.id = id;
        this.person_id = person_id;
        this.payer_id = payer_id;
    }

    public Person_payer(Long person_id, Long payer_id) {
        this.person_id = person_id;
        this.payer_id = payer_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(Long payer_id) {
        this.payer_id = payer_id;
    }

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }
}
