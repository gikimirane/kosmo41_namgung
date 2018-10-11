package com.study.android.project_exam;

public class UserInfo {
    private String id="";
    private String pw;
    private String phone;
    private int point;
    private String clientno;
    private int usecount;

    public UserInfo(String id, String pw, String phone, String point, String clientno, String usecount) {
        this.id = id;
        this.pw = pw;
        this.phone = phone;
        this.point = Integer.parseInt(point);
        this.clientno = clientno;
        this.usecount = Integer.parseInt(usecount);
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

    public int getUsecount() {
        return usecount;
    }

    public void setUsecount(int usecount) {
        this.usecount = usecount;
    }
}
