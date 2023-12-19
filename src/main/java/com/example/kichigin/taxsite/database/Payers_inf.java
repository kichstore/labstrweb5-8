package com.example.kichigin.taxsite.database;

import jakarta.persistence.*;

@Entity
public class Payers_inf {

    @Id
    @SequenceGenerator(name = "pay_inf_seq", sequenceName = "pay_inf_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "pay_inf_seq")
    private Long id;
    private Long payers_id;
    private Long tax_type;
    private Long sum_incom;
    private Long sum_profit;
    private Long sum_payout_pp;
    private Long sum_tax_pay;

    public Payers_inf() {
    }

    public Payers_inf(Long id, Long payers_id, Long tax_type, Long sum_incom, Long sum_profit, Long sum_payout_pp, Long sum_tax_pay) {
        this.id = id;
        this.payers_id = payers_id;
        this.tax_type = tax_type;
        this.sum_incom = sum_incom;
        this.sum_profit = sum_profit;
        this.sum_payout_pp = sum_payout_pp;
        this.sum_tax_pay = sum_tax_pay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayers_id() {
        return payers_id;
    }

    public void setPayers_id(Long payers_id) {
        this.payers_id = payers_id;
    }

    public Long getTax_type() {
        return tax_type;
    }

    public void setTax_type(Long tax_type) {
        this.tax_type = tax_type;
    }

    public Long getSum_incom() {
        return sum_incom;
    }

    public void setSum_incom(Long sum_incom) {
        this.sum_incom = sum_incom;
    }

    public Long getSum_profit() {
        return sum_profit;
    }

    public void setSum_profit(Long sum_profit) {
        this.sum_profit = sum_profit;
    }

    public Long getSum_payout_pp() {
        return sum_payout_pp;
    }

    public void setSum_payout_pp(Long sum_payout_pp) {
        this.sum_payout_pp = sum_payout_pp;
    }

    public Long getSum_tax_pay() {
        return sum_tax_pay;
    }

    public void setSum_tax_pay(Long sum_tax_pay) {
        this.sum_tax_pay = sum_tax_pay;
    }
}
