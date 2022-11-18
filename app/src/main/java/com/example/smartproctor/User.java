package com.example.smartproctor;

public class User {
    public String fullname,registerno,email,phn,role;
    public User()
    {

    }
    public User(String fullname,String registerno,String phn,String email,String role){
        this.fullname=fullname;
        this.registerno=registerno;
        this.email=email;
        this.phn=phn;
        this.role=role;
    }

    public String getEmail() {
        return email;
    }

    public String getPhn() {
        return phn;
    }

    public String getFullname() {
        return fullname;
    }

    public String getRegisterno() {
        return registerno;
    }
    public String getRole() {
        return role;
    }

}
