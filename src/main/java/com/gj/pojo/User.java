package com.gj.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String ulogincode;

    private String upassword;

    private String email;

    private String phone;

    private Integer ustatus;

    private Integer urole;

    private String question;

    private String answer;

    private String mlastloginip;

    private Date mlastlogindate;

    public User(Integer id, String ulogincode, String upassword, String email, String phone, Integer ustatus, Integer urole, String question, String answer, String mlastloginip, Date mlastlogindate) {
        this.id = id;
        this.ulogincode = ulogincode;
        this.upassword = upassword;
        this.email = email;
        this.phone = phone;
        this.ustatus = ustatus;
        this.urole = urole;
        this.question = question;
        this.answer = answer;
        this.mlastloginip = mlastloginip;
        this.mlastlogindate = mlastlogindate;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUlogincode() {
        return ulogincode;
    }

    public void setUlogincode(String ulogincode) {
        this.ulogincode = ulogincode == null ? null : ulogincode.trim();
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword == null ? null : upassword.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getUstatus() {
        return ustatus;
    }

    public void setUstatus(Integer ustatus) {
        this.ustatus = ustatus;
    }

    public Integer getUrole() {
        return urole;
    }

    public void setUrole(Integer urole) {
        this.urole = urole;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getMlastloginip() {
        return mlastloginip;
    }

    public void setMlastloginip(String mlastloginip) {
        this.mlastloginip = mlastloginip == null ? null : mlastloginip.trim();
    }

    public Date getMlastlogindate() {
        return mlastlogindate;
    }

    public void setMlastlogindate(Date mlastlogindate) {
        this.mlastlogindate = mlastlogindate;
    }
}