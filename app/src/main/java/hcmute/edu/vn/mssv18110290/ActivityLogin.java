package hcmute.edu.vn.mssv18110290;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import hcmute.edu.vn.mssv18110290.database.DatabaseHelper;
import hcmute.edu.vn.mssv18110290.model.Type;

public class ActivityLogin extends AppCompatActivity {

    private Button BtnLogin;
    private EditText Email, Pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        DatabaseHelper db = new DatabaseHelper(this);
//
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nuocgiaikhat);
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] bitmapdata = stream.toByteArray();
//        insert type
//        db.insertType(new Type("nuoc giai khat", bitmapdata ));
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);
        BtnLogin = (Button) findViewById(R.id.cirLoginBtn);
        Email = (EditText) findViewById(R.id.editTextEmail);
        Pass = (EditText) findViewById(R.id.editTextPassword);
    }

    public void openAccuracy(){
        Intent intent = new Intent(this, ActivityAccuracy.class);
        startActivity(intent);
    }


    public void onLoginClick(View view)
    {
        String email = Email.getText().toString();
        String pass = Pass.getText().toString();
        if(email.equals("") || pass.equals("")){
            Toast.makeText(getApplicationContext(), "Email or Password is null", Toast.LENGTH_LONG).show();
            Email.setText("");
            Pass.setText("");
        }
        else{
            final DatabaseHelper db = new DatabaseHelper(getApplicationContext(), "DBminiStop.db", null, 1);
            Cursor user = db.GetData("SELECT * FROM table_users WHERE email = '"+email+"' and password = '"+pass+"'");

            //Kiem tra nen timf thay trong SQLite
            if(user.getCount() == 1){
                startActivity(new Intent(this, ListType.class));
            }
            else if(user.getCount() == 0){
                Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
//                Email.setText("");
//                Pass.setText("");
            }
        }
    }


    public void onAdminClick(View view)
    {
        startActivity(new Intent(this, ActivityAddCatogories.class));
    }

    public void onRegisterClick(View view) {
        startActivity(new Intent(this, ActivityRegister.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
}