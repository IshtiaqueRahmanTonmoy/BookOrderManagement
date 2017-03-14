package info.androidhive.snackbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DonationdistributionActivity extends AppCompatActivity {

    CartSavingSqlite sqlite_obj;
    CartAddinglist cartadding;
    String[] SPINNERLIST = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};
    ListView listview;
    String cid;
    List<Customlistadding> itemList;
    Spinner sp1,sp2;
    private static final String TAG_DEPID = "id";
    private static final String TAG_DEPNAME = "name";
    JSONParser jsonParser = new JSONParser();
    private ArrayList<String> deplist,classlist;
    private static final String TAG_DEPARTMENT = "college";
    JSONArray college,classname;
    private static String url_institute = "http://dik-pl.com/dikpl/college.php";
    private static String url_classanem = "http://dik-pl.com/dikpl/teachers.php";
    private static final String TAG_CLASNAME = "name";
    private static final String TAG_CLASSNAME = "th";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CLASS = "college_id";
    String college_id,name;
    String names,price,code,stock;
    TableRow tablerow2,tablerow3,tablerow4;
    Button button;
    int i;
    List<Customlistadding> listget = new ArrayList<Customlistadding>();
    private static final String TAG_CLASSID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationdistribution);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);

        deplist = new ArrayList<String>();
        classlist = new ArrayList<String>();

        new Department().execute();

        sp1 = (Spinner)
                findViewById(R.id.spinner2);
        sp2 = (Spinner)
                findViewById(R.id.spinner3);

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

                    names = cartadding.getCart().get(i).getName();
                    price = cartadding.getCart().get(i).getPrice();
                    code = cartadding.getCart().get(i).getCode();
                    stock = cartadding.getCart().get(i).getStock();

                    listget.add(i,new Customlistadding(name,price,code,stock));

                    //Toast.makeText(BookDistributionActivity.this, ""+name+""+stock, Toast.LENGTH_SHORT).show();;
                }

                Intent intent = new Intent(DonationdistributionActivity.this, ListCartActivity.class);
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

        sqlite_obj = new CartSavingSqlite(DonationdistributionActivity.this);

        cartadding = new CartAddinglist();
        itemList = new ArrayList<Customlistadding>();


        //listview.setAdapter(new DistributeBooktoTeacherCustomList(this, new String[]{""}, new String[]{"মধুসুদনের কাব্য পাঠের ভুমিকা | হোসনেয়ারা খাতুন বাংলা অনার্স ১ম বর্ষ "}));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tablerow2 = (TableRow) findViewById(R.id.tablerow2);
        tablerow3 = (TableRow) findViewById(R.id.tablerow3);
        tablerow4 = (TableRow) findViewById(R.id.tablerow4);

        tablerow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableRow t = (TableRow) v;
                TextView firstTextView = (TextView) t.getChildAt(0);
                TextView secondTextView = (TextView) t.getChildAt(1);
                names = firstTextView.getText().toString();
                stock = secondTextView.getText().toString();
                //sqlite_obj.addToCart(bookname,"price","code",stock);
                itemList.add(new Customlistadding(names,"price","code",stock));
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
                names = firstTextView.getText().toString();
                stock = secondTextView.getText().toString();
                //sqlite_obj.addToCart(bookname,"price","code",stock);

                itemList.add(new Customlistadding(names,"price","code",stock));
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
                names = firstTextView.getText().toString();
                stock = secondTextView.getText().toString();
                //sqlite_obj.addToCart(bookname,"price","code",stock);

                itemList.add(new Customlistadding(names,"price","code",stock));
                cartadding.addCart(itemList);
                //saveintoCart(bookname,stock);
                //Toast.makeText(BookDistributionActivity.this, ""+firstText+""+secondText, Toast.LENGTH_SHORT).show();
            }
        });
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
                            college = json.getJSONArray(TAG_DEPARTMENT);

                            // looping through All Products
                            for (int i = 0; i < college.length(); i++) {
                                JSONObject c = college.getJSONObject(i);

                                // Storing each json item in variable
                                String id = c.getString(TAG_DEPID);
                                String name = c.getString(TAG_DEPNAME);
                                //BengaliUnicodeString.getBengaliUTF(getActivity(),head,text);
                                deplist.add(name);
                                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(DonationdistributionActivity.this, android.R.layout.simple_spinner_item, deplist);
                                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp1.setAdapter(spinnerAdapter);

                                sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        college_id = String.valueOf(position+1);
                                        //Toast.makeText(getApplicationContext(),""+cid,Toast.LENGTH_LONG).show();
                                        new Classname().execute();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

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



    private class  Classname extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    // Check for success tag
                    int success;
                    try {
                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair(TAG_CLASS, college_id));

                        // getting product details by making HTTP request
                        // Note that product details url will use GET request
                        JSONObject json = jsonParser.makeHttpRequest(
                                url_classanem, "GET", params);

                        // check your log for json response
                        Log.d("Single Product Details", json.toString());

                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            // successfully received product details
                            JSONArray bookObj = json
                                    .getJSONArray(TAG_CLASSNAME); // JSON Array

                            JSONObject c = bookObj.getJSONObject(0);
                            name = c.getString(TAG_CLASNAME);
                            Toast.makeText(DonationdistributionActivity.this, ""+name, Toast.LENGTH_SHORT).show();
                            classlist.add(name);
                            ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(DonationdistributionActivity.this, android.R.layout.simple_spinner_item, classlist);
                            spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            sp2.setAdapter(spinnerAdapter2);

                        } else {
                            // product with pid not found
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
