package com.example.prhyt.laundryownernew;

public class user_info {
    public String username;
    public String useremail;
    public String userpassword;
    public int clothes;
    public double balance;
    public double cost;
    public String datechange;

    public String getDate() {
        return datechange;
    }

    public void setDate(String date) {
        this.datechange = date;
    }

    public user_info(String username, String useremail, String userpassword , int userclothes, double userbalance, double usercost, String date) {
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;

        this.balance=userbalance;
        this.clothes=userclothes;
        this.cost=usercost;
        this.datechange=date;

    }


    public user_info(){ }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public int getClothes() {
        return clothes;
    }

    public void setClothes(int clothes) {
        this.clothes = clothes;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


}