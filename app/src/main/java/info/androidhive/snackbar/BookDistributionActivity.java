package info.androidhive.snackbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookDistributionActivity extends AppCompatActivity {

    Context context;
    Spinner spinner,spinner2;
    List<Customlistadding> itemList;
    TableRow tablerow2,tablerow3,tablerow4;
    Intent intent;
    CartSavingSqlite sqlite_obj;
    Button button;
    List<Customlistadding> listget = new ArrayList<Customlistadding>();
    CartAddinglist cartadding;
    Intent myIntent;
    int i;
    String name,price,code,stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_distribution);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sqlite_obj = new CartSavingSqlite(BookDistributionActivity.this);

        cartadding = new CartAddinglist();
        itemList = new ArrayList<Customlistadding>();

        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int size = cartadding.getCart().size();

               /*
                String id[] = new String[size];
                String name[] = new String[size];
                String rate[] = new String[size];
               */

                for(i=0;i<size;i++){

                    name = cartadding.getCart().get(i).getName();
                    price = cartadding.getCart().get(i).getPrice();
                    code = cartadding.getCart().get(i).getCode();
                    stock = cartadding.getCart().get(i).getStock();

                    listget.add(i,new Customlistadding(name,price,code,stock));

                    //Toast.makeText(BookDistributionActivity.this, ""+name+""+stock, Toast.LENGTH_SHORT).show();;
                }

                Intent intent = new Intent(BookDistributionActivity.this, ListCartActivity.class);
                String listSerializedToJson = new Gson().toJson(listget);
                intent.putExtra("listget", listSerializedToJson);
                startActivity(intent);

                //Log.d("values",listget.get(2).getCode());

                /*
                for(Customlistadding custom : cartadding.getCart()){
                   String name = custom.getName();
                   String price = custom.getPrice();
                   String code = custom.getCode();
                   String stock = custom.getStock();


                   Toast.makeText(BookDistributionActivity.this, ""+name, Toast.LENGTH_SHORT).show();
               }
               */
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner2);
        spinner2 = (Spinner) findViewById(R.id.spinner3);
        tablerow2 = (TableRow) findViewById(R.id.tablerow2);
        tablerow3 = (TableRow) findViewById(R.id.tablerow3);
        tablerow4 = (TableRow) findViewById(R.id.tablerow4);

        tablerow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableRow t = (TableRow) v;
                TextView firstTextView = (TextView) t.getChildAt(0);
                TextView secondTextView = (TextView) t.getChildAt(1);
                name = firstTextView.getText().toString();
                stock = secondTextView.getText().toString();
                //sqlite_obj.addToCart(bookname,"price","code",stock);
                itemList.add(new Customlistadding(name,"price","code",stock));
                cartadding.addCart(itemList);
                //saveintoCart(bookname,stock);

             /*
                intent = new Intent(BookDistributionActivity.this, ListCartActivity.class);
                Bundle extras = new Bundle();
                extras.putString("BOOKNAME",bookname);
                extras.putString("STOCK",stock);
                intent.putExtras(extras);
                startActivity(intent);
              */
                //listview.setAdapter(new DynamicAddCustomlist(context,itemList));
                //Toast.makeText(BookDistributionActivity.this, ""+firstText+""+secondText, Toast.LENGTH_SHORT).show();
            }
        });

        tablerow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableRow t = (TableRow) v;
                TextView firstTextView = (TextView) t.getChildAt(0);
                TextView secondTextView = (TextView) t.getChildAt(1);
                name = firstTextView.getText().toString();
                stock = secondTextView.getText().toString();
                //sqlite_obj.addToCart(bookname,"price","code",stock);

                itemList.add(new Customlistadding(name,"price","code",stock));
                cartadding.addCart(itemList);
                //saveintoCart(bookname,stock);
             /*
                intent = new Intent(BookDistributionActivity.this, ListCartActivity.class);
                Bundle extras = new Bundle();
                extras.putString("BOOKNAME",bookname);
                extras.putString("STOCK",stock);
                intent.putExtras(extras);
                startActivity(intent);
               */
                //listview.setAdapter(new DynamicAddCustomlist(context,itemList));

                //Toast.makeText(BookDistributionActivity.this, ""+firstText+""+secondText, Toast.LENGTH_SHORT).show();
            }
        });

        tablerow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableRow t = (TableRow) v;
                TextView firstTextView = (TextView) t.getChildAt(0);
                TextView secondTextView = (TextView) t.getChildAt(1);
                name = firstTextView.getText().toString();
                stock = secondTextView.getText().toString();
                //sqlite_obj.addToCart(bookname,"price","code",stock);

                itemList.add(new Customlistadding(name,"price","code",stock));
                cartadding.addCart(itemList);
                //saveintoCart(bookname,stock);
                //Toast.makeText(BookDistributionActivity.this, ""+firstText+""+secondText, Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
