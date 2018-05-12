package com.example.mihai.quarto;

public class Profile {
int id;
String name;
String surname;
String profile;
String pass;

    public Profile(){

    }

    public  Profile(String name, String surname, String profile, String pass){}



    public Profile(int id, String name,String surname ,String profile , String pass ){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profile = profile;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
