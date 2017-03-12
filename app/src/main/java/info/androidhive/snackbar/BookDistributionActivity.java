package info.androidhive.snackbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
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
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookDistributionActivity extends AppCompatActivity {

    private static final String TAG_DEPID = "id";
    private static final String TAG_DEPNAME = "name";
    List<Customlistadding> itemList;
    TableRow tablerow2,tablerow3,tablerow4;
    JSONArray department,classname;
    CartSavingSqlite sqlite_obj;
    Button button;
    List<Customlistadding> listget = new ArrayList<Customlistadding>();
    CartAddinglist cartadding;
    Intent myIntent;
    private static final String TAG_DEPARTMENT = "department";
    int i;
    Spinner spinner1,spinner2,schoolspinner,collegespinner;
    EditText comment;
    private static final String TAG_CLASNAME = "name";
    private static final String TAG_CLASSNAME = "tbl_class";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CLASSID = "id";
    String name,price,code,stock;
    JSONParser jsonParser = new JSONParser();
    private ArrayList<String> deplist,classlist;
    private static String url_institute = "http://dik-pl.com/dikpl/departmentget.php";
    private static String url_classanem = "http://dik-pl.com/dikpl/classget.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_distribution);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        deplist = new ArrayList<String>();
        classlist = new ArrayList<String>();
        new Department().execute();
        new Classname().execute();


        spinner1 = (Spinner) findViewById(R.id.spinner2);
        spinner2 = (Spinner) findViewById(R.id.spinner3);
        schoolspinner = (Spinner) findViewById(R.id.schoolspinner);
        collegespinner = (Spinner) findViewById(R.id.collegespinner);
        comment = (EditText) findViewById(R.id.txtComment);

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

                    listget.add(i,new Customlistadding(null,name,price,code,stock));

                    //Toast.makeText(BookDistributionActivity.this, ""+name+""+stock, Toast.LENGTH_SHORT).show();;
                }

                Intent intent = new Intent(BookDistributionActivity.this, ListCartActivity.class);
                String listSerializedToJson = new Gson().toJson(listget);
                intent.putExtra("listget", listSerializedToJson);
                startActivity(intent);
                finish();

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

       // spinner = (Spinner) findViewById(R.id.spinner2);
        spinner2 = (Spinner) findViewById(R.id.spinner3);
        tablerow2 = (TableRow) findViewById(R.id.tablerow2);
        tablerow3 = (TableRow) findViewById(R.id.tablerow3);
        tablerow4 = (TableRow) findViewById(R.id.tablerow4);

        tablerow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=1;
                TableRow t = (TableRow) v;
                TextView firstTextView = (TextView) t.getChildAt(0);
                TextView secondTextView = (TextView) t.getChildAt(1);
                name = firstTextView.getText().toString();
                stock = secondTextView.getText().toString();
                //sqlite_obj.addToCart(bookname,"price","code",stock);
                itemList.add(new Customlistadding(i,name,"price","code",stock));
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
                int i=2;
                TableRow t = (TableRow) v;
                TextView firstTextView = (TextView) t.getChildAt(0);
                TextView secondTextView = (TextView) t.getChildAt(1);
                name = firstTextView.getText().toString();
                stock = secondTextView.getText().toString();
                //sqlite_obj.addToCart(bookname,"price","code",stock);

                itemList.add(new Customlistadding(i,name,"price","code",stock));
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
                int i=3;
                TableRow t = (TableRow) v;
                TextView firstTextView = (TextView) t.getChildAt(0);
                TextView secondTextView = (TextView) t.getChildAt(1);
                name = firstTextView.getText().toString();
                stock = secondTextView.getText().toString();
                //sqlite_obj.addToCart(bookname,"price","code",stock);

                itemList.add(new Customlistadding(i,name,"price","code",stock));
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

    public class Department extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<NameValuePair> param = new ArrayList<NameValuePair>();
                    // getting JSON string from URL
                    JSONObject json = jsonParser.makeHttpRequest(url_institute, "GET", param);

                    // Check your log cat for JSON reponse
                    Log.d("All Products: ", json.toString());

                    try {
                        // Checking for SUCCESS TAG
                        int success = json.getInt(TAG_SUCCESS);

                        if (success == 1) {
                            // products found
                            // Getting Array of Products
                            department = json.getJSONArray(TAG_DEPARTMENT);

                            // looping through All Products
                            for (int i = 0; i < department.length(); i++) {
                                JSONObject c = department.getJSONObject(i);

                                // Storing each json item in variable
                                String id = c.getString(TAG_DEPID);
                                String name = c.getString(TAG_DEPNAME);
                                //BengaliUnicodeString.getBengaliUTF(getActivity(),head,text);
                                deplist.add(name);
                                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(BookDistributionActivity.this, android.R.layout.simple_spinner_item, deplist);
                                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner1.setAdapter(spinnerAdapter);


                                //Toast.makeText(getApplicationContext(),""+name,Toast.LENGTH_LONG).show();

                            }
                        } else {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            return null;
        }
    }


    public class Classname extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<NameValuePair> param = new ArrayList<NameValuePair>();
                    // getting JSON string from URL
                    JSONObject json = jsonParser.makeHttpRequest(url_classanem, "GET", param);

                    // Check your log cat for JSON reponse
                    Log.d("All Products: ", json.toString());

                    try {
                        // Checking for SUCCESS TAG
                        int success = json.getInt(TAG_SUCCESS);

                        if (success == 1) {
                            // products found
                            // Getting Array of Products
                            classname = json.getJSONArray(TAG_CLASSNAME);

                            // looping through All Products
                            for (int i = 0; i < classname.length(); i++) {
                                JSONObject c = classname.getJSONObject(i);

                                // Storing each json item in variable
                                String classid = c.getString(TAG_CLASSID);
                                String classname = c.getString(TAG_CLASNAME);
                                // Toast.makeText(BookRequistionActivity.this, ""+classname, Toast.LENGTH_SHORT).show();
                                //BengaliUnicodeString.getBengaliUTF(getActivity(),head,text);
                                classlist.add(classname);
                                ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<String>(BookDistributionActivity.this, android.R.layout.simple_spinner_item, classlist);
                                spinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner2.setAdapter(spinnerAdapter1);


                                //Toast.makeText(getApplicationContext(),""+name,Toast.LENGTH_LONG).show();

                            }
                        } else {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            return null;
        }
    }
}
