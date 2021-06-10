package hcmute.edu.vn.mssv18110290.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import hcmute.edu.vn.mssv18110290.R;

public class ProductAdapter extends ArrayAdapter<Products> {

    public ProductAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ProductAdapter(Context context, int resource, List<Products> types){
        super(context, resource, types);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.activity_itemsproduct, null);

        }

        Products t = getItem(position);

        if(t != null){
            //Anh xa, gan gia tri
            TextView tv1 = (TextView) v.findViewById(R.id.tvNameProd);
            tv1.setText(t._name);

            TextView tv2= (TextView) v.findViewById((R.id.tvPrice));
            String price = String.valueOf((int) t._price) + " VNĐ";
            tv2.setText(price);

            ImageView image = (ImageView) v.findViewById(R.id.imageAvatar);
            byte[] avatar = (byte[]) t._avatar;
            if(avatar != null){
                Bitmap btm = BitmapFactory.decodeByteArray(avatar, 0, avatar.length);
                if(btm != null){
                    image.setImageBitmap(btm);
                }
            }
        }
        return v;
    }
}
