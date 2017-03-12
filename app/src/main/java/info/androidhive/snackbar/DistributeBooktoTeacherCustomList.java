package info.androidhive.snackbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tonmoy on 10/3/16.
 */

public class DistributeBooktoTeacherCustomList extends BaseAdapter{

    Context context;
    List<Items> list;
    String name,stock;
    List<Customlistadding> addList;

    public DistributeBooktoTeacherCustomList(Context context, List<Items> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Items getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    private static class ViewHolder {
        public TextView booktext, currentstock;
        public Button button;
        public ListView listview;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        final ViewHolder viewholder;
        if (convertView == null) {

            viewholder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.bookdistributioncustomlayout, parent, false);

            convertView.setClickable(true);
            convertView.setFocusable(true);

            //convertView = inflater.inflate(R.layout.bookdistributioncustomlayout, null);

            viewholder.booktext = (TextView) convertView.findViewById(R.id.textView1);
            viewholder.currentstock = (TextView) convertView.findViewById(R.id.textView2);
            viewholder.button = (Button) convertView.findViewById(R.id.submitButton);
            viewholder.listview = (ListView) convertView.findViewById(R.id.listviews);

            convertView.setTag(viewholder);
        }

        else  {
            viewholder = (ViewHolder)convertView.getTag();
        }

            Items items = list.get(position);
            viewholder.booktext.setText(items.getName());
            viewholder.currentstock.setText(items.getStock());

            viewholder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = viewholder.booktext.getText().toString();
                stock = viewholder.currentstock.getText().toString();

                addList = new ArrayList<Customlistadding>();
                addList.add(new Customlistadding(null,name,"rateTk","code",stock));

                //viewholder.listview.setAdapter(new DynamicAddCustomlist(context ,addList));
                //Toast.makeText(context,"Bookname"+bookname+"Currentstock"+stock,Toast.LENGTH_LONG).show();
                //String title = ((TextView) v.findViewById(R.id.textView1)).getText().toString();

                //String stock = (String) ((TextView) v.findViewById(R.id.textView2)).getText();

            }
        });

            return convertView;
       }
  }
