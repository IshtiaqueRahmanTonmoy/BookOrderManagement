package info.androidhive.snackbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
    Button placeorder,total;
    int stock = 0;
    String id,code,stockvalue,quantity,totalsvalue,name;
    List<Customlistadding> lists;
    int i=8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cart);

        listview = (ListView) findViewById(R.id.listView);
        placeorder = (Button) findViewById(R.id.button1);
        total = (Button) findViewById(R.id.button2);
        lists = new ArrayList<Customlistadding>();

        String carListAsString = getIntent().getStringExtra("listget");
        //Log.i("list",carListAsString);
        //Toast.makeText(ListCartActivity.this, ""+carListAsString, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Customlistadding>>(){}.getType();
        List<Customlistadding> carsList = gson.fromJson(carListAsString, type);

        dynamicadd = new DynamicAddCustomlist(this, R.layout.activity_list_cart,carsList);
        listview.setAdapter(dynamicadd);


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

        for (Customlistadding customlist : carsList){
            //Log.i("CarData", customlist.getName()+"-"+customlist.getStock());
            //Toast.makeText(ListCartActivity.this, ""+customlist.getName()+""+customlist.getStock(), Toast.LENGTH_SHORT).show();
            stock = stock + Integer.parseInt(customlist.getStock());
            total.setText(""+stock);

        }

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Customlistadding s : lists){
                    String name = s.getName().toString();
                    String code = s.getCode().toString();
                    String stock = s.getStock().toString();
                    //Log.d("pr",price);
                    String qtys = s.getQuantity().toString();

                    Log.d("id"+i+"name",name+"code"+code+"stock"+stock+"quantity"+qtys);
                    //String tot = s.getFoodrate().toString();

                    //Insertdata insert = new Insertdata(id,name,price,qtys,tot);
                    //insert.execute("",null);
                    //Log.d("idval",id);
                    i++;
                }
            }
        });


        //itemList.add(new Customlistadding(name,price,code,stock));
        //dynamicadd = new DynamicAddCustomlist(ListCartActivity.this,itemList);
        //listview.setAdapter(dynamicadd);
        //Toast.makeText(ListCartActivity.this, ""+name+""+stock, Toast.LENGTH_SHORT).show();
    }


    BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            name = intent.getStringExtra("name");
            code = intent.getStringExtra("code");
            stockvalue = intent.getStringExtra("stock");
            quantity = intent.getStringExtra("qty");
            totalsvalue = intent.getStringExtra("total");

            Log.d("quant",quantity);
            lists.add(new Customlistadding(name,code,stockvalue,quantity,totalsvalue));
        }
    };
}
