package info.androidhive.snackbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ListCartActivity extends AppCompatActivity {

    List<Customlistadding> itemList = new ArrayList<Customlistadding>();
    ListView listview;
    Context context;
    String booknames,stocks;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cart);

        listview = (ListView) findViewById(R.id.listview);

        Bundle extras = getIntent().getExtras();
        booknames = extras.getString("BOOKNAME");
        stocks = extras.getString("STOCK");

        itemList.add(new Customlistadding(booknames,"price","code",stocks));
        saveArray(itemList);
    }

    public void saveArray(List<Customlistadding> itemlist){

        this.itemList = itemlist;

        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        int size = itemlist.size();
        //Toast.makeText(ListCartActivity.this, ""+size, Toast.LENGTH_SHORT).show();
        editor.putInt("list_size", size);

        for (int i = 0; i < size; i++) {
            editor.remove("list_"+i);
        }
        /*
        for (int i = 0; i < size; i++) {
            editor.putBoolean("list_"+i, itemlist.get(i));
        }
        */

        editor.commit();
    }
}
