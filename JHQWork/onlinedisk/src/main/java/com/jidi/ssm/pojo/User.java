package com.jidi.ssm.pojo;

public class User
{
    private int ID;
    private String name;
    private String password;
    private String email;
    private boolean isAdministrator;

    public boolean getisAdministrator() {
        return isAdministrator;
    }
    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
