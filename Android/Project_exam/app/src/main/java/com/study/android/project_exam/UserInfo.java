package com.study.android.project_exam;

public class UserInfo {
    private String id;
    private String pw;
    private String phone;
    private int point;
    private String clientno;

    public UserInfo(String id, String pw, String phone, String point, String clientno) {
        this.id = id;
        this.pw = pw;
        this.phone = phone;
        this.point = Integer.parseInt(point);
        this.clientno = clientno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getClientno() {
        return clientno;
    }

    public void setClientno(String clientno) {
        this.clientno = clientno;
    }
}
