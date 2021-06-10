package hcmute.edu.vn.mssv18110290;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import hcmute.edu.vn.mssv18110290.R;

public class ActivityAccuracy extends AppCompatActivity {
    LinearLayout layoutHeader, layoutRobot;
    CheckBox cbRobot;
    Button btnSubmit;
    int a = 1;
    int b=1;
    int c=1;
    int d =1;
    //1 true, 0 false
    int[] rs = {1, 0, 0, 1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accuracy);
        layoutHeader = (LinearLayout) findViewById(R.id.layoutHeader);
        layoutRobot = (LinearLayout) findViewById(R.id.layoutChoseImage);
        cbRobot = (CheckBox) findViewById(R.id.cbRobot);
        btnSubmit = (Button) findViewById(R.id.BtnSubmit);

        ImageButton iBtn_fish = (ImageButton) findViewById(R.id.iBtn_fish);
        ImageButton iBtn_hoa1 = (ImageButton) findViewById(R.id.iBtn_hoa1);
        ImageButton iBtn_hoa2 = (ImageButton) findViewById(R.id.iBtn_hoa2);
        ImageButton iBtn_sanho = (ImageButton) findViewById(R.id.iBtn_sanho);
        iBtn_fish.setBackgroundResource(R.drawable.black_background);
        iBtn_hoa1.setBackgroundResource(R.drawable.black_background);
        iBtn_hoa2.setBackgroundResource(R.drawable.black_background);
        iBtn_sanho.setBackgroundResource(R.drawable.black_background);


        //event click
        iBtn_fish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(c == 1){
                    //chon hinh anh
                    iBtn_fish.setBackgroundResource(R.drawable.ic_baseline_check_circle_outline_24);
                    c = 0;
                    rs[0] = 0;
                }
                else
                {
                    iBtn_fish.setBackgroundResource(R.drawable.black_background);
                    c=1;
                    rs[0] = 1;
                }
            }
        });

        iBtn_hoa1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(a == 1){
                    iBtn_hoa1.setBackgroundResource(R.drawable.ic_baseline_check_circle_outline_24);
                    a = 0;
                    rs[1]=1;
                }
                else
                {
                    iBtn_hoa1.setBackgroundResource(R.drawable.black_background);
                    a=1;
                    rs[1]=0;
                }
            }
        });

        iBtn_hoa2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (b == 1) {
                    iBtn_hoa2.setBackgroundResource(R.drawable.ic_baseline_check_circle_outline_24);
                    b = 0;
                    rs[2]=1;
                } else {
                    iBtn_hoa2.setBackgroundResource(R.drawable.black_background);
                    b = 1;
                    rs[2]=0;
                }
            }
        });

        iBtn_sanho.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                if(d == 1){
                    iBtn_sanho.setBackgroundResource(R.drawable.ic_baseline_check_circle_outline_24);
                    d = 0;
                    rs[3]=0;
                }
                else
                {
                    iBtn_sanho.setBackgroundResource(R.drawable.black_background);
                    d=1;
                    rs[3]=1;
                }
            }
        });

        cbRobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ((CheckBox)v).isChecked() ) {
                    layoutRobot.setVisibility(LinearLayout.VISIBLE);
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<4; i++){
                    if(rs[i]==0) {
                        Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
                        break;
                    }
                    if(i == 3) {
                        Toast.makeText(getApplicationContext(), "You are not robot", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


    }
}