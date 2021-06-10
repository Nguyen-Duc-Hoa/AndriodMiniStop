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

public class ListAdapter extends ArrayAdapter<Type> {

    public ListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ListAdapter(Context context, int resource, List<Type> types){
        super(context, resource, types);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.activity_product, null);
        }

        Type t = getItem(position);

        if(t != null){
            //Anh xa, gan gia tri
            TextView tv1 = (TextView) v.findViewById(R.id.tvCategory);
            tv1.setText(t._name);

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
