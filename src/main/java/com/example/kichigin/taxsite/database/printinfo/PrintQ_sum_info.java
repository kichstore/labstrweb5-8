package com.example.kichigin.taxsite.database.printinfo;

public class PrintQ_sum_info {
    private int count;
    private String data;
    private Long incom;
    private Long profit;
    private Long payout_pp;
    private Long tax_pay;

    public PrintQ_sum_info() {
    }

    public PrintQ_sum_info(int count, String data, Long incom, Long profit, Long payout_pp, Long tax_pay) {
        this.count = count;
        this.data = data;
        this.incom = incom;
        this.profit = profit;
        this.payout_pp = payout_pp;
        this.tax_pay = tax_pay;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
