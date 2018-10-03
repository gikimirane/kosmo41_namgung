package com.study.android.project_exam;

public class SingerItem {

    private String drink;
    private String amount;

    public SingerItem(String drink, String amount) {
        this.drink = drink;
        this.amount = amount;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
