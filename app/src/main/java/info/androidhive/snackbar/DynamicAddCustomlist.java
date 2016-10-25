package info.androidhive.snackbar;

import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tonmoy on 10/19/16.
 */

public class DynamicAddCustomlist extends BaseAdapter {

    Context context;
    List<Customlistadding> list;

    public DynamicAddCustomlist(Context context, List<Customlistadding> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Customlistadding getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewholder;
        if (convertView == null) {

            viewholder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.custom_lista_dding, parent, false);

            convertView.setClickable(true);
            convertView.setFocusable(true);

            //convertView = inflater.inflate(R.layout.bookdistributioncustomlayout, null);

            viewholder.name = (TextView) convertView.findViewById(R.id.bookname);
            viewholder.price = (TextView) convertView.findViewById(R.id.bookprice);
            viewholder.code = (TextView) convertView.findViewById(R.id.bookcode);
            viewholder.stock = (TextView) convertView.findViewById(R.id.currentstock);
            viewholder.number = (TextView) convertView.findViewById(R.id.quantity_text_view);
            viewholder.buttonplus = (ImageView) convertView.findViewById(R.id.cart_plus_img);
            viewholder.buttonminus = (ImageView) convertView.findViewById(R.id.cart_minus_img);

            convertView.setTag(viewholder);
        }

        else  {
            viewholder = (ViewHolder)convertView.getTag();
        }

        Customlistadding items = list.get(position);
        viewholder.name.setText(items.getName());
        viewholder.price.setText(items.getPrice());
        viewholder.code.setText(items.getCode());
        viewholder.stock.setText(items.getStock());

        return convertView;
    }

    private static class ViewHolder {
        public TextView name,price,code,stock,number;
        public ImageView buttonplus,buttonminus;
        public TextView stockcount,placeorder;
    }
}
