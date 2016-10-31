package info.androidhive.snackbar;

import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by tonmoy on 10/19/16.
 */

public class DynamicAddCustomlist extends BaseAdapter {

    Context context;
    List<Customlistadding> list;
    public ArrayList<HashMap<String, String>> listQuantity;
    public ArrayList<Integer> quantity = new ArrayList<Integer>();
    CustomButtonListener customButtonListener;
    static String get_price, get_quntity;
    int g_quntity, g_price, g_minus;
    private String[] listViewItems, prices, static_price;
    static HashMap<String, String> map = new HashMap<>();

    ViewHolder viewholder;

    public DynamicAddCustomlist(Context context, List<Customlistadding> list) {
        this.context = context;
        this.list = list;

        for (int i = 0; i < list.size(); i++) {
            quantity.add(0);
            //quantity[i]=0;
        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {

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
            //viewholder.number = (TextView) convertView.findViewById(R.id.quantity_text_view);
            viewholder.quantity = (EditText) convertView.findViewById(R.id.editext);
            viewholder.buttonplus = (Button) convertView.findViewById(R.id.cart_plus_img);
            viewholder.buttonminus = (Button) convertView.findViewById(R.id.cart_minus_img);

            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        Customlistadding items = list.get(position);
        viewholder.name.setText(items.getName());
        viewholder.price.setText(items.getPrice());
        viewholder.code.setText(items.getCode());
        viewholder.stock.setText(items.getStock());

        try {

            viewholder.quantity.setText(quantity.get(position) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewholder.buttonplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customButtonListener != null) {
                    customButtonListener.onButtonClickListener(position, viewholder.quantity, 1);
                    quantity.set(position, quantity.get(position) + 1);

                    get_price = viewholder.price.getText().toString();

                    g_price = Integer.valueOf(static_price[position]);

                    get_quntity = viewholder.quantity.getText().toString();
                    g_quntity = Integer.valueOf(get_quntity);

                    map.put("" + viewholder.name.getText().toString(), " " + viewholder.quantity.getText().toString());
//                    Log.d("A ", "" + a);
//                    Toast.makeText(context, "A" + a, Toast.LENGTH_LONG).show();
//                    Log.d("Position ", "" + position);
//                    System.out.println(+position + " Values " + map.values());
                    viewholder.price.getTag();
                    viewholder.price.setText("" + g_price * g_quntity);
                    ShowHashMapValue();
                }

            }
        });
        //listViewHolder.edTextQuantity.setText("0");
        viewholder.buttonminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customButtonListener != null) {
                    customButtonListener.onButtonClickListener(position, viewholder.quantity, -1);
                    if (quantity.get(position) > 0)
                        quantity.set(position, quantity.get(position) - 1);
                }
            }
        });

        return convertView;
    }

    private void ShowHashMapValue() {
        Set setOfKeys = map.keySet();

/**
 * get the Iterator instance from Set
 */
        Iterator iterator = setOfKeys.iterator();

/**
 * Loop the iterator until we reach the last element of the HashMap
 */
        while (iterator.hasNext()) {
/**
 * next() method returns the next key from Iterator instance.
 * return type of next() method is Object so we need to do DownCasting to String
 */
            String key = (String) iterator.next();

/**
 * once we know the 'key', we can get the value from the HashMap
 * by calling get() method
 */
            String value = map.get(key);

            System.out.println("Key: " + key + ", Value: " + value);
        }

    }
}
