package com.example.kichigin.taxsite.database;

import jakarta.persistence.*;

@Entity
public class Q_sum_tax {

    @Id
    @SequenceGenerator(name = "q_sum_seq", sequenceName = "q_sum_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "q_sum_seq")
    private Long id;
    private Long tax_payer_id;
    private String date;
    private Long incom;
    private Long profit;
    private Long payout_pp;
    private Long tax_pay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTax_payer_id() {
        return tax_payer_id;
    }

    public void setTax_payer_id(Long tax_payer_id) {
        this.tax_payer_id = tax_payer_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getIncom() {
        return incom;
    }

    public void setIncom(Long incom) {
        this.incom = incom;
    }

    public Long getProfit() {
        return profit;
    }

    public void setProfit(Long profit) {
        this.profit = profit;
    }

    public Long getPayout_pp() {
        return payout_pp;
    }

    public void setPayout_pp(Long payout_pp) {
        this.payout_pp = payout_pp;
    }

    public Long getTax_pay() {
        return tax_pay;
    }

    public void setTax_pay(Long tax_pay) {
        this.tax_pay = tax_pay;
    }

    public Q_sum_tax() {
    }

    public Q_sum_tax(Long tax_payer_id, String date, Long incom, Long profit, Long payout_pp, Long tax_pay) {
        this.tax_payer_id = tax_payer_id;
        this.date = date;
        this.incom = incom;
        this.profit = profit;
        this.payout_pp = payout_pp;
        this.tax_pay = tax_pay;

    }

    public Q_sum_tax(Long id, Long tax_payer_id, String date, Long incom, Long profit, Long payout_pp, Long tax_pay) {
        this.id = id;
        this.tax_payer_id = tax_payer_id;
        this.date = date;
        this.incom = incom;
        this.profit = profit;
        this.payout_pp = payout_pp;
        this.tax_pay = tax_pay;
    }
}
