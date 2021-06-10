package hcmute.edu.vn.mssv18110290.model;

import android.media.Image;

import java.sql.Blob;

public class Users {
    int _id;
    String _username;
    String _email;
    String _password;
    String _address;
    String _gender;
    String _phone;
    String _birthday;
    Blob _avatar;

    public int getID(){
        return this._id;
    }
    public void setID(int id){
        this._id = id;
    }

    public String getUsername(){
        return this._username;
    }
    public void setUsername(String username){
        this._username = username;
    }

    public String getEmail(){
        return this._email;
    }
    public void setEmail(String email){
        this._email = email;
    }

    public String getPass(){
        return this._password;
    }
    public void setPass(String pass){
        this._password = pass;
    }

    public String getAddress(){
        return this._address;
    }
    public void setAddress(String addr){ this._password = addr; }

    public String getGender(){
        return this._gender;
    }
    public void setGender(String gender){
        this._gender = gender;
    }

    public String getPhone(){
        return this._phone;
    }
    public void setPhone(String phone){
        this._phone = phone;
    }

    public String getDate(){
        return this._birthday;
    }
    public void setDate(String birthday){
        this._birthday = birthday;
    }

    public Blob getAvatar(){return  this._avatar;}
    public void setAvatar(Blob avatar){this._avatar = avatar;}
}
