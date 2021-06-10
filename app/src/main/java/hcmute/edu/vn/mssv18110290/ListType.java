package hcmute.edu.vn.mssv18110290;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hcmute.edu.vn.mssv18110290.database.DatabaseHelper;
import hcmute.edu.vn.mssv18110290.model.ListAdapter;
import hcmute.edu.vn.mssv18110290.model.Type;

public class ListType extends AppCompatActivity {

    ListView lv;
    ArrayList<Type> mang;
    ArrayList<Integer> idList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_type);
        lv = (ListView) findViewById(R.id.lvType);

        mang = new ArrayList<Type>();

        //hide ActionBar
        getSupportActionBar().hide();

        //Add phan tu vao mang DATABASE
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        final DatabaseHelper db = new DatabaseHelper(getApplicationContext(), "DBminiStop.db", null, 1);
        Cursor cate = db.GetData("SELECT * FROM table_type");

        while (cate.moveToNext()){
            mang.add(new Type(
                    cate.getInt(0),
                    cate.getString(1),
                    cate.getBlob(2)
            ));
            int idType = cate.getInt(0);
            idList.add(idType);
        }
        ListAdapter adapter = new ListAdapter(getApplicationContext(), R.layout.activity_product, mang);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idType = idList.get(position);

                Intent intent = new Intent(ListType.this, ActivityListProducts.class);
                intent.putExtra("idType", idType);
                startActivity(intent);
            }
        });
    }
}