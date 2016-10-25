package info.androidhive.snackbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
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

        String carListAsString = getIntent().getStringExtra("listget");
        //Log.i("list",carListAsString);
        //Toast.makeText(ListCartActivity.this, ""+carListAsString, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Customlistadding>>(){}.getType();
        List<Customlistadding> carsList = gson.fromJson(carListAsString, type);

        DynamicAddCustomlist customAdapter = new DynamicAddCustomlist(context,carsList);
        listview.setAdapter(customAdapter);
        /*
        for (Customlistadding customlist : carsList){
            //Log.i("CarData", customlist.getName()+"-"+customlist.getStock());
            Toast.makeText(ListCartActivity.this, ""+customlist.getName()+""+customlist.getStock(), Toast.LENGTH_SHORT).show();
        }
        */

        //itemList.add(new Customlistadding(name,price,code,stock));
        //dynamicadd = new DynamicAddCustomlist(ListCartActivity.this,itemList);
        //listview.setAdapter(dynamicadd);
        //Toast.makeText(ListCartActivity.this, ""+name+""+stock, Toast.LENGTH_SHORT).show();
    }
}
