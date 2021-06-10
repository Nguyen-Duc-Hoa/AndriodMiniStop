package hcmute.edu.vn.mssv18110290;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hcmute.edu.vn.mssv18110290.R;
import hcmute.edu.vn.mssv18110290.ActivityAccuracy;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button BtnLogin = (Button) findViewById(R.id.cirLoginBtn);
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        setContentView(R.layout.activity_main);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccuracy(v);
            }
        });
    }

    public void openAccuracy(View view){
        Intent intent = new Intent(this, ActivityAccuracy.class);
        startActivity(intent);
    }

}