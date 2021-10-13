package com.kaikeba.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class Express {
    private int id;
    private String number;
    private String username;
    private String userphone;
    private String company;
    private String code;
    private Timestamp inTime;
    private Timestamp outTime;
    private int status;
    private String sysPhone;

    public Express(String number, String username, String userphone, String company, String sysPhone) {
        this.number = number;
        this.username = username;
        this.userphone = userphone;
        this.company = company;
        this.sysPhone = sysPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Express express = (Express) o;
        return id == express.id && status == express.status && Objects.equals(number, express.number) && Objects.equals(username, express.username) && Objects.equals(userphone, express.userphone) && Objects.equals(company, express.company) && Objects.equals(code, express.code) && Objects.equals(inTime, express.inTime) && Objects.equals(outTime, express.outTime) && Objects.equals(sysPhone, express.sysPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, username, userphone, company, code, inTime, outTime, status, sysPhone);
    }

    @Override
    public String toString() {
        return "Express{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", username='" + username + '\'' +
                ", userphone='" + userphone + '\'' +
                ", company='" + company + '\'' +
                ", code='" + code + '\'' +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", status=" + status +
                ", sysPhone='" + sysPhone + '\'' +
                '}';
    }

    public Express() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getInTime() {
        return inTime;
    }

    public void setInTime(Timestamp inTime) {
        this.inTime = inTime;
    }

    public Timestamp getOutTime() {
        return outTime;
    }

    public void setOutTime(Timestamp outTime) {
        this.outTime = outTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSysPhone() {
        return sysPhone;
    }

    public void setSysPhone(String sysPhone) {
        this.sysPhone = sysPhone;
    }

    public Express(String number, String username, String userphone, String company, String sysPhone,String code) {
        this.number = number;
        this.username = username;
        this.userphone = userphone;
        this.company = company;
        this.sysPhone = sysPhone;
        this.code = code;
    }

    public Express(int id, String number, String username, String userphone, String company, String code, Timestamp inTime, Timestamp outTime, int status, String sysPhone) {
        this.id = id;
        this.number = number;
        this.username = username;
        this.userphone = userphone;
        this.company = company;
        this.code = code;
        this.inTime = inTime;
        this.outTime = outTime;
        this.status = status;
        this.sysPhone = sysPhone;
    }
}
