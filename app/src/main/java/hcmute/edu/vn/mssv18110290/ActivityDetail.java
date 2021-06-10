package hcmute.edu.vn.mssv18110290;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hcmute.edu.vn.mssv18110290.database.DatabaseHelper;
import hcmute.edu.vn.mssv18110290.model.Products;

public class ActivityDetail extends AppCompatActivity {

    //Thong tin san pham
    ImageView ivAddCart, ivAvatar;
    TextView tvRate, tvAmount, tvPrice, tvDescript, tvCate, tvName;
    float price;
    int idProd;


    //So luong va gia khi add to Cart
    TextView tvNum, tvTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivAddCart = (ImageView) findViewById(R.id.ivAddCart);
        tvRate = (TextView) findViewById((R.id.tvRating));
        tvAmount = (TextView) findViewById((R.id.tvAmount));
        tvPrice =(TextView) findViewById((R.id.tvPrice));
        tvDescript = (TextView) findViewById(R.id.tvDescript);
        tvCate = (TextView) findViewById(R.id.tvCategory);
        tvName = (TextView) findViewById(R.id.tvNameProd);
        ivAvatar =(ImageView) findViewById(R.id.imageAvatar);

        //Thong tin khi add to Cart
        tvNum = (TextView) findViewById(R.id.tvNum);
        tvTotal = (TextView) findViewById(R.id.tvTotal);

        //loadDetail();
        //Get id product
        idProd = getIntent().getIntExtra("idProd", -1);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        final DatabaseHelper db = new DatabaseHelper(getApplicationContext(), "DBminiStop.db", null, 1);
        Cursor prod = db.GetData("SELECT * FROM table_products p, table_type t WHERE p.type = t.idType and p.id = "+idProd);

        if (prod.getCount() == 1)
        {
            while (prod.moveToNext()){
                String name = prod.getString(1);
                price = (float) prod.getDouble(2);
                int amount = prod.getInt(3);
                String description = prod.getString(5);
                byte[] image = prod.getBlob(8);
                float rate = (float) prod.getDouble(10);
                String type = prod.getString(12);
                //Thong tin san pham


                tvName.setText(name);

                String amount1 = String.valueOf((int) amount);
                tvAmount.setText(amount1);

                String price1 = String.valueOf((int) price) + " VNĐ";
                tvPrice.setText(price1);

                tvDescript.setText(description);

                if(image != null){
                    Bitmap btm = BitmapFactory.decodeByteArray(image, 0, image.length);
                    if(btm != null){
                        ivAvatar.setImageBitmap(btm);
                    }
                }

                String rate1 = String.valueOf((int) rate);
                tvRate.setText(rate1);

                tvCate.setText(type);

                //Thong tin khi add to cart
                tvTotal.setText(price1);
                tvNum.setText("01");
            }



        }
    }

    public  void loadDetail(int idProd)
    {

        final DatabaseHelper db = new DatabaseHelper(getApplicationContext(), "DBminiStop.db", null, 1);
        Cursor prod = db.GetData("SELECT * FROM table_products p, table_type t WHERE p.type = t.idType and p.id = "+idProd);

        if (prod.getCount() == 1)
        {
            String name = prod.getString(1);
            float price = (float) prod.getDouble(2);
            int amount = prod.getInt(3);
            String description = prod.getString(5);
            byte[] image = (byte[]) prod.getBlob(8);
            float rate = (float) prod.getDouble(10);
            String type = prod.getString(12);

            //Thong tin san pham
            tvName.setText(name);
            tvAmount.setText(amount);
            tvPrice.setText((int) price);
            tvDescript.setText(description);
            if(image != null){
                Bitmap btm = BitmapFactory.decodeByteArray(image, 0, image.length);
                if(btm == null){
                    ivAvatar.setImageBitmap(btm);
                }
            }
            tvRate.setText((int) rate);
            tvCate.setText(type);

            //Thong tin khi add to cart
            tvTotal.setText((int) price);
            tvNum.setText((int) 1);
        }

    }

    public void onIncreaseClick(View v)
    {
        int amount = Integer.valueOf((String) tvAmount.getText());
        int num = Integer.valueOf((String) tvNum.getText());
        float total;
        if(num < amount)
        {
            num = num + 1;
            total = price*(float)num;
            if(num<10){
                String number = "0"+String.valueOf((int) num);
                tvNum.setText(number);
                tvTotal.setText(String.valueOf((int) total)+"VND");
            }
            else if(num>9){
                tvNum.setText(String.valueOf((int) num));
                tvTotal.setText(String.valueOf((int) total)+"VND");
            }

        }
    }

    public void onDecreaseClick(View v)
    {
        int num = Integer.valueOf((String) tvNum.getText());
        float total;
        if(num > 1)
        {
            num = num - 1;
            total = price*(float)num;
            if(num<10){
                String number = "0"+String.valueOf((int) num);
                tvNum.setText(number);
                tvTotal.setText(String.valueOf((int) total) +"VND");
            }
            else if(num>9){
                tvNum.setText(String.valueOf((int) num));
                tvTotal.setText(String.valueOf((int) total) +"VND");
            }

        }
    }
    public void onFinishClick(View v)
    {
        finish();
    }
}