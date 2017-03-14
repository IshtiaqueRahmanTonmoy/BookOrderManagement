package info.androidhive.snackbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by tonmoy on 10/19/16.
 */

public class DynamicAddCustomlist extends ArrayAdapter<Customlistadding> {

    Context context;
    List<Customlistadding> list;
    double total=0;
    int pos;
    private int[] counters;

    public DynamicAddCustomlist(Context context, int resource, List<Customlistadding> list) {

        super(context, resource, list);
        this.context = context;
        this.list = list;
        //intent = new Intent();

        //this.mCallback = listener;

        //count.setFoods(list.get);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Customlistadding getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewholder;

        final Customlistadding items = list.get(position);
        final int temp = position;
        pos = getItemViewType(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.custom_lista_dding, null);
            viewholder = new ViewHolder();
            viewholder.bookname = (TextView) convertView.findViewById(R.id.bookname);
            viewholder.bookcode = (TextView) convertView.findViewById(R.id.bookcode);
            viewholder.bookstock = (TextView) convertView.findViewById(R.id.bookrate);
            viewholder.quantity = (TextView) convertView.findViewById(R.id.cart_product_quantity_tv);
            //viewholder.edit = (EditText) convertView.findViewById(R.id.price);
            //viewholder.plusbutton = (ImageView) convertView.findViewById(R.id.cart_plus_img);
           // viewholder.minusbutton = (ImageView) convertView.findViewById(R.id.cart_minus_img);
            //viewholder.delete = (Button) convertView.findViewById(R.id.deleteorder);

            convertView.setTag(viewholder);
        }

        else  {

            viewholder = (ViewHolder)convertView.getTag();
        }

        viewholder.bookname.setText(items.getName());
        viewholder.bookcode.setText("code");
        viewholder.bookstock.setText(items.getStock());
        viewholder.quantity.setText(items.getQuantity());

        /*
        viewholder.plusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int mValue = Integer.parseInt(viewholder.quantity.getText().toString());
                //double rate = Double.parseDouble(viewholder.foodrate.getText().toString());
                mValue++;

                //total = Double.parseDouble(list.get(position).getStock()) * mValue;

                viewholder.quantity.setText(String.valueOf(mValue));

                Intent intentplus = new Intent("custom-message");
                //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                intentplus.putExtra("name",viewholder.bookname.getText().toString());
                intentplus.putExtra("id",items.getId());
                intentplus.putExtra("code",items.getCode());
                intentplus.putExtra("stock",items.getStock());
                intentplus.putExtra("total",total);

                //Log.d("ratess",items.getFoodrate());
                intentplus.putExtra("qty",viewholder.quantity.getText().toString());

                notifyDataSetChanged();
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentplus);
            }
        });

        viewholder.minusbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int mValue = Integer.parseInt(viewholder.quantity.getText().toString());
                //double rate = Double.parseDouble(viewholder.foodrate.getText().toString());
                mValue--;

                //total = Double.parseDouble(list.get(position).getStock()) * mValue;
                viewholder.quantity.setText(String.valueOf(mValue));

                Intent intentminus = new Intent("custom-message");
                //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                intentminus.putExtra("name",viewholder.bookname.getText().toString());
                intentminus.putExtra("id",items.getId());
                intentminus.putExtra("code",items.getCode());
                intentminus.putExtra("stock",items.getStock());
                intentminus.putExtra("total",total);

                //Log.d("ratess",items.getFoodrate());
                intentminus.putExtra("qty",viewholder.quantity.getText().toString());

                notifyDataSetChanged();
                LocalBroadcastManager.getInstance(context).sendBroadcast(intentminus);

            }
        });
        */
        return convertView;
    }

    private static class ViewHolder {
        public TextView bookname,bookcode,bookstock,quantity;
        public ImageView plusbutton,minusbutton;
    }
}