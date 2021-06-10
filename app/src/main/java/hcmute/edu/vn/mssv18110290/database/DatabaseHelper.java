package hcmute.edu.vn.mssv18110290.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110290.model.Products;
import hcmute.edu.vn.mssv18110290.model.Type;
import hcmute.edu.vn.mssv18110290.model.Vouchers;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="DBminiStop.db";

    public static final String USERS ="table_users";
    public static final String PRODUCTS ="table_products";
    public static final String TYPES ="table_type";
    public static final String BRAND ="table_brand";
    public static final String CARTITEMS ="table_cartItems";
    public static final String ORDERS ="table_orders";
    public static final String ORDERITEMS ="table_orderItems";
    public static final String VOUCHERS ="table_vouchers";
    public static final String USER_VOUCHER ="table_userVoucher";


    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    //select
    public Cursor GetData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbl_Brand = "create table "+ BRAND +" (idBrand integer primary key autoincrement, brandName text, avatar blob)";
        String tbl_Type = "create table "+ TYPES +" (idType integer primary key autoincrement, typeName text, avatar blob)";
        String tbl_User ="create table " + USERS +" (id integer primary key autoincrement, userName text, email text unique, password text, address text, gender text, phone text, birthday datetime, avatar byte, role integer, status integer)";
        String tbl_Product = "create table " + PRODUCTS +" (id integer primary key autoincrement, name text, price real, amount integer, type integer, description text, brand integer, status integer, avatar blob," +
                " discount real, rate real, foreign key(type) references "+TYPES+"(idType), foreign key(brand) references "+BRAND+"(idBrand))";
        String tbl_cartItems ="create table "+CARTITEMS+" (idItem integer, idUser integer, primary key(idItem, idUser), foreign key(idItem) references "+ PRODUCTS +"(id), foreign key(idUser) references "+USERS+"(id))";
        String tbl_orders = "create table "+ ORDERS +" (idOrder integer primary key autoincrement, idUser integer, delivery real, createdDate text, status integer, description text, address text, totalOrder real, " +
                "foreign key(idUser) references "+USERS+"(id))";
        String tbl_orderItems = "create table "+ ORDERITEMS +" (idOrder integer, idItem integer, price real, quantity integer, total real, status integer, " +
                "foreign key(idOrder) references "+ORDERS+"(idOrder), foreign key(idItem) references "+PRODUCTS+"(id))";
        String tbl_voucher ="create table " + VOUCHERS +" (idVoucher integer primary key autoincrement, discount real, description text, price real)";
        String tbl_voucherUser = "create table "+USER_VOUCHER+" (idVoucher integer, idUser integer, primary key(idVoucher, idUser), foreign key(idVoucher) references "+VOUCHERS+"(idVoucher), foreign key(idUser) references "+USERS+"(id))";

        db.execSQL(tbl_Brand);
        db.execSQL(tbl_Type);
        db.execSQL(tbl_User);
        db.execSQL(tbl_Product);
        db.execSQL(tbl_cartItems);
        db.execSQL(tbl_orders);
        db.execSQL(tbl_orderItems);
        db.execSQL(tbl_voucher);
        db.execSQL(tbl_voucherUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ BRAND);
        db.execSQL("DROP TABLE IF EXISTS "+ TYPES);
        db.execSQL("DROP TABLE IF EXISTS "+ USERS);
        db.execSQL("DROP TABLE IF EXISTS "+ PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS "+ CARTITEMS);
        db.execSQL("DROP TABLE IF EXISTS "+ ORDERS);
        db.execSQL("DROP TABLE IF EXISTS "+ ORDERITEMS);
        db.execSQL("DROP TABLE IF EXISTS "+ VOUCHERS);
        db.execSQL("DROP TABLE IF EXISTS "+ USER_VOUCHER);
        onCreate(db);

    }

    // code to add the new Type
    public void insertType(String name, byte[] avatar) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO "+TYPES+" VALUES (null, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindBlob(2, avatar);
        ContentValues values = new ContentValues();

        statement.executeInsert();
    }

    // code to add the new Type
    public void insertProduct(String name, float price, int amount, int type, String description, byte[] avatar) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO "+PRODUCTS+" VALUES (null, ?, ?, ?, ?, ?, 1, null, ?, null, null)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindDouble(2, price);
        statement.bindLong(3, amount);
        statement.bindLong(4, type);
        statement.bindString(5, description);
        statement.bindBlob(6, avatar);

        ContentValues values = new ContentValues();

        statement.executeInsert();
    }

    public void insertUser(String name, String email, String password, String phone, byte[] avatar){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO "+USERS+" VALUES (null, ?, ?, ?, null, null, ?, null, ?, null, 1)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, email);
        statement.bindString(3, password);
        statement.bindString(4, phone);
        statement.bindBlob(5, avatar);

        ContentValues values = new ContentValues();
        statement.executeInsert();
    }


    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TYPES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    //add Vouchers
    void addVoucher(Vouchers vouchers){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", vouchers.getName());
        values.put("discount", vouchers.getDiscount());
        values.put("description", vouchers.getDescription());

        // Inserting Row
        db.insert(VOUCHERS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

    }
    // code to get all type in a list view
    public List<Vouchers> getAllVouchers() {
        List<Vouchers> VoucherList = new ArrayList<Vouchers>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + VOUCHERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Vouchers vouchers = new Vouchers();
                vouchers.setId(Integer.parseInt(cursor.getString(0)));
                vouchers.setName(cursor.getString(1));
                vouchers.set_discount(cursor.getFloat(2));
                vouchers.setDescription(cursor.getString(3));
                // Adding contact to list
                VoucherList.add(vouchers);
            } while (cursor.moveToNext());
        }

        // return type list
        return VoucherList;
    }
    // Deleting Vouchers
    public void deleteContact(Vouchers vouchers) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VOUCHERS, "id" + " = ?",
                new String[] { String.valueOf(vouchers.getId()) });
        db.close();
    }
    // code to update the single voucher
    public int updateVoucher(Vouchers vouchers) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", vouchers.getName());
        values.put("discount", vouchers.getDiscount());
        values.put("description", vouchers.getDescription());

        // updating row
        return db.update(VOUCHERS, values, "id" + " = ?",
                new String[] { String.valueOf(vouchers.getId()) });
    }


    
}
