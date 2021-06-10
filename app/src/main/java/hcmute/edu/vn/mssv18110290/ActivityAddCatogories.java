package hcmute.edu.vn.mssv18110290;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import hcmute.edu.vn.mssv18110290.database.DatabaseHelper;

public class ActivityAddCatogories extends AppCompatActivity {

    private DatabaseHelper db;
    private ImageView imageCate;
    private EditText category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_catogories);

        imageCate = (ImageView) findViewById(R.id.imageCategory);
        category = (EditText) findViewById(R.id.etCategory);
        db = new DatabaseHelper(this);

    }

    public void onCameraClick(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // start the image capture Intent
        startActivityForResult(intent, 8888);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 8888 && resultCode == RESULT_OK){
            super.onActivityResult(requestCode, resultCode, data);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageCate = (ImageView) findViewById(R.id.imageCategory);
            imageCate.setImageBitmap(photo);
        }
    }

    public void onAddClick(View v){
        try{
            String cate = category.getText().toString();
            byte[] image = ImageView_To_Byte(imageCate);
            db.insertType(cate, image);
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

    public void onCancelClick(View v){
        startActivity(new Intent(this, ListType.class));
    }

    public void onAddProClick(View v){
        startActivity(new Intent(this, ActivityAddPro.class));
    }
}