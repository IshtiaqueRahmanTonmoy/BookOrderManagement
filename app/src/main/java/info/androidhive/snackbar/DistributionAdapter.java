package info.androidhive.snackbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by TONMOYPC on 10/25/2017.
 */
public class DistributionAdapter extends BaseAdapter {

    ArrayList<BookDistribution> booklist;
    Activity activity;

    public DistributionAdapter(ArrayList<BookDistribution> booklist, Activity activity) {
        super();
        this.booklist = new ArrayList<>(booklist);
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return booklist.size();
    }

    @Override
    public Object getItem(int position) {
        return booklist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder {
        TextView mBookname;
        TextView mWritername;
        TextView mSubject;
        TextView mClass;
        TextView mType;
        TextView mQuantity;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_bookdistribution, null);
            holder = new ViewHolder();
            //holder.mName = (TextView) convertView.findViewById(R.id.name);
            holder.mBookname = (TextView) convertView.findViewById(R.id.book_name);
            holder.mWritername = (TextView) convertView.findViewById(R.id.writer_name);
            holder.mSubject = (TextView) convertView.findViewById(R.id.subject);
            holder.mClass = (TextView) convertView.findViewById(R.id.classname);
            holder.mType = (TextView) convertView.findViewById(R.id.type);
            holder.mQuantity = (TextView) convertView.findViewById(R.id.quantity);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BookDistribution item = booklist.get(position);

        //holder.mName.setText(item.getName().toString());
        holder.mBookname.setText(item.getBookname().toString());
        holder.mWritername.setText(item.getWritername().toString());
        holder.mSubject.setText(item.getSubject().toString());
        holder.mClass.setText(item.getType().toString());
        holder.mType.setText(item.getType().toString());
        holder.mQuantity.setText(item.getQuantity().toString());


        return convertView;
    }

}