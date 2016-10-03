package info.androidhive.snackbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by tonmoy on 10/3/16.
 */

public class DistributeBooktoTeacherCustomList extends BaseAdapter{

    Context context;
    String[] data;
    String[] value;
    private static LayoutInflater inflater = null;

    public DistributeBooktoTeacherCustomList(Context context, String[] data, String[] value) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        this.value = value;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.bookdistributioncustomlayout, null);
        TextView booktext = (TextView) vi.findViewById(R.id.textView1);
        TextView currentstock = (TextView) vi.findViewById(R.id.textView2);
        booktext.setText(data[position]);
        currentstock.setText(value[position]);
        return vi;
    }
}
