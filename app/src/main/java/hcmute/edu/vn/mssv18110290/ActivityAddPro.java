package hcmute.edu.vn.mssv18110290;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import hcmute.edu.vn.mssv18110290.database.DatabaseHelper;

public class ActivityAddPro extends AppCompatActivity {

    private ImageView avatar;
    private EditText name, price, amount, type, descript;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pro);
        avatar = (ImageView) findViewById(R.id.imagePro);
        name = (EditText) findViewById(R.id.etName);
        amount = (EditText) findViewById(R.id.etAmount);
        type = (EditText) findViewById(R.id.etCategory);
        descript = (EditText) findViewById(R.id.etDecript);
        price = (EditText) findViewById(R.id.etPrice);
        db = new DatabaseHelper(this);
    }

    public void onCameraClick(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // start the image capture Intent
        startActivityForResult(intent, 8889);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 8889 && resultCode == RESULT_OK){
            super.onActivityResult(requestCode, resultCode, data);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            avatar = (ImageView) findViewById(R.id.imagePro);
            avatar.setImageBitmap(photo);
        }
    }

    public void onAddClick(View v){
        try{
            String nam = name.getText().toString();
            float gia = Float.parseFloat(price.getText().toString());
            int sl = Integer.parseInt(amount.getText().toString());
            int loai = Integer.parseInt(type.getText().toString());
            String mota = descript.getText().toString();
            byte[] image = ImageView_To_Byte(avatar);
            db.insertProduct(nam, gia, sl, loai, mota, image);
            Toast.makeText(getApplicationContext(), "thanh cong", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public byte[] ImageView_To_Byte(ImageView imageCate) {
        BitmapDrawable drawable = (BitmapDrawable) imageCate.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}