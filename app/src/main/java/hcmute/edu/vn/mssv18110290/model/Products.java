package hcmute.edu.vn.mssv18110290.model;

import android.media.Image;

import java.sql.Blob;

public class Products {
    int _id;
    String _name;
    float _price;
    int _amount;
    int _type;
    String _description;
    int _brand;
    int _status;
    byte[] _avatar;
    float _discount;
    float _rate;

    public Products(String name, float price, byte[] avatar) {
        this._name = name;
        this._price = price;
        this._avatar = avatar;
    }

    public int getID(){
        return this._id;
    }
    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }
    public void setName(String name){
        this._name = name;
    }

    public float getPrice(){
        return this._price;
    }
    public void setPrice(float price){
        this._price = price;
    }

    public int getAmount(){
        return this._amount;
    }
    public void setAmount(int amount){
        this._amount = amount;
    }

    public int getType(){return  this._type;}
    public void setType(int type){this._type = type;}

    public String getDescription(){return this._description;}
    public void setDescription(String description){this._description = description;}

    public int getBrand(){return  this._brand;}
    public void setBrand(int brand){this._brand = brand;}

    public int getStatus(){return this._status;}
    public void setStatus(int status){this._status = status;}

    public byte[] getAvatar(){return this._avatar;}
    public void setAvatar(byte[] avatar){this._avatar = avatar;}

    public float getDiscount(){return this._discount;}
    public void setDiscount(float discount){this._discount = discount;}

    public float getRate(){return this._rate;}
    public void setRate(float rate){this._rate = rate;}
}
