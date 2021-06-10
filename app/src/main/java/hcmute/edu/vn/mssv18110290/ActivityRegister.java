package hcmute.edu.vn.mssv18110290;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import hcmute.edu.vn.mssv18110290.database.DatabaseHelper;

public class ActivityRegister extends AppCompatActivity {

    private ImageView imageView;
    private EditText etName, etEmail, etPhone, etPass, etRePass;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_register);

        imageView = (ImageView) findViewById(R.id.imageAvatar);
        etName =(EditText) findViewById(R.id.editTextName);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etPhone = (EditText) findViewById(R.id.editTextMobile);
        etPass = (EditText) findViewById(R.id.editTextPassword);
        etRePass = (EditText) findViewById(R.id.editTextRePassword);
        db = new DatabaseHelper(this);

        changeStatusBarColor();
    }

    private void changeStatusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));

        }

    }

    // Quay tro lai login khi co tai khoan
    public void onLoginClick(View view)
    {
        startActivity(new Intent(this, ActivityLogin.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    // Bat camera de tao avatar
    public void onCameraClick(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // start the image capture Intent
        startActivityForResult(intent, 100);
    }

    //Lay hinh anh duoc chup up ImageView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100 && resultCode == RESULT_OK){
            super.onActivityResult(requestCode, resultCode, data);
            Bitmap photo = (Bitmap)data.getExtras().get("data");
            imageView = (ImageView)findViewById(R.id.imageAvatar);
            imageView.setImageBitmap(photo);
        }
    }

    //Chuyen doi ImageView qua byte[] de xu ly
    public byte[] ImageView_To_Byte(ImageView imageCate) {
        BitmapDrawable drawable = (BitmapDrawable) imageCate.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void onRegisterClick(View view){
        try{
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPass.getText().toString();
            String phone = etPhone.getText().toString();
            String rePass = etRePass.getText().toString();
            byte[] image = ImageView_To_Byte(imageView);

            if( nullField(name, email, phone, password, rePass) == true){
                if(password.equals(rePass)){
                    try{
                        db.insertUser(name, email, password, phone, image);
                        Toast.makeText(getApplicationContext(), "Đăng kí thành công. Quay lại đăng nhập", Toast.LENGTH_SHORT).show();
                    }
                    catch(Exception e)
                    {
                        etEmail.setText("");
                        etEmail.setHint("Email đã đăng kí tài khoản");
                        etEmail.setHintTextColor(Color.RED);
                    }

                }
                else if(password != rePass){
                    etRePass.setText("");
                    etRePass.setHint("Xác nhận mật khẩu không đúng...");
                    etRePass.setHintTextColor(Color.RED);
                }
            }

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public boolean nullField(String name, String email, String phone, String password, String rePass){
        int a = 0;
        //Kiem tra chua nhap ten
        if(name.equals("")){
            etName.setHint("Compulsory field");
            etName.setHintTextColor(Color.RED);
            a = 1;
        }
        //Kiem tra chua nhap email
        if(email.equals("")){
            etEmail.setHint("Compulsory field");
            etEmail.setHintTextColor(Color.RED);
            a = 1;
        }
        //Kiem tra chua nhap sdt
        if(phone.equals("")){
            etPhone.setHint("Compulsory field");
            etPhone.setHintTextColor(Color.RED);
            a = 1;
        }
        //Kiem tra chua nhap password
        if(password.equals("")){
            etPass.setHint("Compulsory field");
            etPass.setHintTextColor(Color.RED);
            a = 1;
        }
        //Kiem tra chua nhap confirm pass
        if(rePass.equals("")){
            etRePass.setHint("Compulsory field");
            etRePass.setHintTextColor(Color.RED);
            a = 1;
        }

        if(a == 1){
            return false;
        }
        return true;
    }
}