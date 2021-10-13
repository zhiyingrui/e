package com.kaikeba.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class Courier {
    private int id;
    private String courierName;
    private String courierPhone;
    private String courierIdNumber;
    private String courierPassword;
    private String courierSendNumber;
    private Timestamp enrollTime;
    private Timestamp loginTime;

   //值得表扬
    public Courier(int id, String courierName, String courierPhone, String courierIdNumber, String courierPassword, int courierSendNumber, Timestamp enrollTime, Timestamp loginTime) {

    }

    public Courier(int id, String courierName, String courierPhone, String courierIdNumber, String courierPassword, String courierSendNumber, Timestamp enrollTime, Timestamp loginTime) {
        this.id = id;
        this.courierName = courierName;
        this.courierPhone = courierPhone;
        this.courierIdNumber = courierIdNumber;
        this.courierPassword = courierPassword;
        this.courierSendNumber = courierSendNumber;
        this.enrollTime = enrollTime;
        this.loginTime = loginTime;
    }

    public Courier(String courierName, String courierPhone, String courierIdNumber, String courierPassword) {
        this.courierName = courierName;
        this.courierPhone = courierPhone;
        this.courierIdNumber = courierIdNumber;
        this.courierPassword = courierPassword;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", courierName='" + courierName + '\'' +
                ", courierPhone='" + courierPhone + '\'' +
                ", courierIdNumber='" + courierIdNumber + '\'' +
                ", courierPassword='" + courierPassword + '\'' +
                ", courierSendNumber='" + courierSendNumber + '\'' +
                ", enrollTime=" + enrollTime +
                ", loginTime=" + loginTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return id == courier.id && Objects.equals(courierName, courier.courierName) && Objects.equals(courierPhone, courier.courierPhone) && Objects.equals(courierIdNumber, courier.courierIdNumber) && Objects.equals(courierPassword, courier.courierPassword) && Objects.equals(courierSendNumber, courier.courierSendNumber) && Objects.equals(enrollTime, courier.enrollTime) && Objects.equals(loginTime, courier.loginTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courierName, courierPhone, courierIdNumber, courierPassword, courierSendNumber, enrollTime, loginTime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public String getCourierPhone() {
        return courierPhone;
    }

    public void setCourierPhone(String courierPhone) {
        this.courierPhone = courierPhone;
    }

    public String getCourierIdNumber() {
        return courierIdNumber;
    }

    public void setCourierIdNumber(String courierIdNumber) {
        this.courierIdNumber = courierIdNumber;
    }

    public String getCourierPassword() {
        return courierPassword;
    }

    public void setCourierPassword(String courierPassword) {
        this.courierPassword = courierPassword;
    }

    public String getCourierSendNumber() {
        return courierSendNumber;
    }

    public void setCourierSendNumber(String courierSendNumber) {
        this.courierSendNumber = courierSendNumber;
    }

    public Timestamp getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(Timestamp enrollTime) {
        this.enrollTime = enrollTime;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
}
