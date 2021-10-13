package com.kaikeba.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
    private int id;
    private String userName;
    private String userPhone;
    private String userIdNumber;
    private String userPassword;
    private Timestamp enrollTime;
    private Timestamp loginTime;

    public User() {
    }

    public User(int id, String userName, String userPhone, String userIdNumber, String userPassword, Timestamp enrollTime, Timestamp loginTime) {
        this.id = id;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userIdNumber = userIdNumber;
        this.userPassword = userPassword;
        this.enrollTime = enrollTime;
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userIdNumber='" + userIdNumber + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", enrollTime=" + enrollTime +
                ", loginTime=" + loginTime +
                '}';
    }

    public User(String userName, String userPhone, String userIdNumber, String userPassword) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userIdNumber = userIdNumber;
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(userName, user.userName) && Objects.equals(userPhone, user.userPhone) && Objects.equals(userIdNumber, user.userIdNumber) && Objects.equals(userPassword, user.userPassword) && Objects.equals(enrollTime, user.enrollTime) && Objects.equals(loginTime, user.loginTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userPhone, userIdNumber, userPassword, enrollTime, loginTime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserIdNumber() {
        return userIdNumber;
    }

    public void setUserIdNumber(String userIdNumber) {
        this.userIdNumber = userIdNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
