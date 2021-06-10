package hcmute.edu.vn.mssv18110290.model;

public class Vouchers {
    int _id;
    String _name;
    float _discount;
    String _description;

    public int getId(){return this._id;}
    public void setId(int id){this._id = id;}

    public String getName(){return this._name;}
    public void setName(String name){this._name = name;}

    public float getDiscount(){return this._discount;}
    public void set_discount(float discount){this._discount = discount;}

    public String getDescription(){return this._description;}
    public void setDescription(String description){this._description = description;}
}
