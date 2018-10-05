package com.study.android.project_exam;

public class SingerItem {

    private String drink;
    private String amount;
    private String count="0";

    public SingerItem(String drink, String amount) {
        this.drink = drink;
        this.amount = amount;
        this.count=count;
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

    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
}
