package info.androidhive.snackbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
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
    DynamicAddCustomlist dynamicadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cart);

        listview = (ListView) findViewById(R.id.listview);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        String price = extras.getString("price");
        String code = extras.getString("code");
        String stock = extras.getString("stock");

        itemList.add(new Customlistadding(name,price,code,stock));
        dynamicadd = new DynamicAddCustomlist(ListCartActivity.this,itemList);
        listview.setAdapter(dynamicadd);
        //Toast.makeText(ListCartActivity.this, ""+name+""+stock, Toast.LENGTH_SHORT).show();
    }
}
