package hcmute.edu.vn.mssv18110290;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import hcmute.edu.vn.mssv18110290.database.DatabaseHelper;
import hcmute.edu.vn.mssv18110290.model.ListAdapter;
import hcmute.edu.vn.mssv18110290.model.ProductAdapter;
import hcmute.edu.vn.mssv18110290.model.Products;
import hcmute.edu.vn.mssv18110290.model.Type;

public class ActivityListProducts extends AppCompatActivity {

    ListView lv;
    ArrayList<Products> products;
    ArrayList<Integer> idList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);

        lv = (ListView) findViewById(R.id.lvType);

        int idtype = getIntent().getIntExtra("idType", 0);
        products = new ArrayList<Products>();

        final DatabaseHelper db = new DatabaseHelper(getApplicationContext(), "DBminiStop.db", null, 1);
        Cursor prod = db.GetData("SELECT * FROM table_products WHERE type = "+idtype);

        while (prod.moveToNext()){
            products.add(new Products(
                    prod.getString(1),
                    (float) prod.getDouble(2),
                    prod.getBlob(8)
            ));
            int id = prod.getInt(0);
            idList.add(id);
        }
        ProductAdapter adapter = new ProductAdapter(getApplicationContext(), R.layout.activity_itemsproduct, products);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idProd = idList.get(position);

                Intent intent = new Intent(ActivityListProducts.this, ActivityDetail.class);
                intent.putExtra("idProd", idProd);
                startActivity(intent);
            }
        });
    }


}