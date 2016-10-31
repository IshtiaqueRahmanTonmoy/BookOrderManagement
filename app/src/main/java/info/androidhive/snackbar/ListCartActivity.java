package info.androidhive.snackbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListCartActivity extends AppCompatActivity implements CustomButtonListener{

    List<Customlistadding> itemList = new ArrayList<Customlistadding>();
    ListView listview;
    Context context;
    String booknames,stocks;
    public static final String PREFS_NAME = "MyPrefsFile";
    DynamicAddCustomlist dynamicadd;
    Button placeorder,total;
    int stock = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cart);

        listview = (ListView) findViewById(R.id.listView);
        placeorder = (Button) findViewById(R.id.button1);
        total = (Button) findViewById(R.id.button2);

        String carListAsString = getIntent().getStringExtra("listget");
        //Log.i("list",carListAsString);
        //Toast.makeText(ListCartActivity.this, ""+carListAsString, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Customlistadding>>(){}.getType();
        List<Customlistadding> carsList = gson.fromJson(carListAsString, type);

        dynamicadd = new DynamicAddCustomlist(context,carsList);
        listview.setAdapter(dynamicadd);


        for (Customlistadding customlist : carsList){
            //Log.i("CarData", customlist.getName()+"-"+customlist.getStock());
            //Toast.makeText(ListCartActivity.this, ""+customlist.getName()+""+customlist.getStock(), Toast.LENGTH_SHORT).show();
            stock = stock + Integer.parseInt(customlist.getStock());
            total.setText(""+stock);

        }

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //itemList.add(new Customlistadding(name,price,code,stock));
        //dynamicadd = new DynamicAddCustomlist(ListCartActivity.this,itemList);
        //listview.setAdapter(dynamicadd);
        //Toast.makeText(ListCartActivity.this, ""+name+""+stock, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClickListener(int position, EditText editText, int value) {
        int quantity = Integer.parseInt(editText.getText().toString());
        quantity = quantity+1*value;
        if(quantity<0)
            quantity=0;
        editText.setText(quantity+"");
    }
}
