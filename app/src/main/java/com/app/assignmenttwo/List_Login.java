package com.app.assignmenttwo;

public class List_Login {

    String stringName,stringEmail,stringNumber,stringPassword,stringId;

    public List_Login(){
        this.stringEmail = "";
        this.stringName = "";
        this.stringNumber = "";
        this.stringPassword = "";
        this.stringId = "";
    }

    public List_Login(String stringName,String stringNumber,String stringEmail,String stringPassword){
        this.stringEmail = stringEmail;
        this.stringName = stringName;
        this.stringNumber = stringNumber;
        this.stringPassword = stringPassword;
    }

    public void setStringName(String stringName) {
        this.stringName = stringName;
    }

    public String getStringName() {
        return stringName;
    }

    public void setStringEmail(String stringEmail) {
        this.stringEmail = stringEmail;
    }

    public String getStringEmail() {
        return stringEmail;
    }

    public void setStringNumber(String stringNumber) {
        this.stringNumber = stringNumber;
    }

    public String getStringNumber() {
        return stringNumber;
    }

    public void setStringPassword(String stringPassword) {
        this.stringPassword = stringPassword;
    }

    public String getStringPassword() {
        return stringPassword;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getStringId() {
        return stringId;
    }
}
