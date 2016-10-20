package info.androidhive.snackbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.List;

public class BookDistributionActivity extends AppCompatActivity {

    Context context;
    Spinner spinner,spinner2;
    List<Customlistadding> itemList;
    TableRow tablerow2,tablerow3,tablerow4;
    Intent intent;
    String bookname,stock;

    SharedPreferences prefs;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_distribution);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefs = this.getSharedPreferences("yourPrefsKey",Context.MODE_PRIVATE);
        edit = prefs.edit();

        itemList = new ArrayList<Customlistadding>();

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
                bookname = firstTextView.getText().toString();
                stock = secondTextView.getText().toString();

                itemList.add(new Customlistadding(bookname,"price","code",stock));
                saveintoCart(itemList);

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
                bookname = firstTextView.getText().toString();
                stock = secondTextView.getText().toString();

                itemList.add(new Customlistadding(bookname,"price","code",stock));
                saveintoCart(itemList);
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
                bookname = firstTextView.getText().toString();
                stock = secondTextView.getText().toString();
                itemList.add(new Customlistadding(bookname,"price","code",stock));
                saveintoCart(itemList);
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

    private void saveintoCart(List<Customlistadding> itemList) {

        Gson gson = new Gson();
        String json = gson.toJson(itemList);
        edit.putString("list", json);
        edit.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
