package com.study.android.project_exam;

import java.util.StringTokenizer;

public class myorderlistItem {

    private String menu;
    private String price;
    private String code;


    public myorderlistItem(String menu1, String price, String code) {
        this.menu = menu1;
        StringTokenizer strtoken = new StringTokenizer(menu,"|");

        String result="";
        int i=0;
        while(strtoken.hasMoreTokens()) {
            if(i%2==0) {
                result=result+strtoken.nextToken();
            }else {
                result =result+strtoken.nextToken()+"잔 ";
            }
            i++;
        }
        this.menu = result;
        this.price = price + "원";
        this.code = code;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
