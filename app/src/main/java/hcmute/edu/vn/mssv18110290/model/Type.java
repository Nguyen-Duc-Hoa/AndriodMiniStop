package hcmute.edu.vn.mssv18110290.model;

public class Type {
    public int _id;
    public String _name;
    public byte[] _avatar;

    public Type(String name, byte[] avatar) {
        this._name = name;
        this._avatar = avatar;
    }

    public Type(int id, String name, byte[] avatar) {
        this._id = id;
        this._name = name;
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

    public byte[] getAvatar(){
        return this._avatar;
    }
    public void setAvatar(byte[] avatar){
        this._avatar = avatar;
    }
}
