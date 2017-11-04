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
import org.apache.http.message.BasicNameValuePair;
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
    TableRow tablerow2,tablerow3,tablerow4,tablerow5;
    JSONArray department,classname;
    CartSavingSqlite sqlite_obj;
    Button button;
    List<Customlistadding> listget = new ArrayList<Customlistadding>();
    CartAddinglist cartadding;
    Intent myIntent;
    private static final String TAG_DEPARTMENT = "department";
    int i;
    Spinner spinner1,spinner2,schoolspinner,collegespinner,teacher;
    EditText comment;
    private static final String TAG_TEACHERID = "id";
    private static final String TAG_TEACHERNAME = "name";
    private static final String TAG_CLASNAME = "name";
    private static final String TAG_CLASSNAME = "tbl_class";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MPO = "mpo_id";
    private static final String TAG_CLASSID = "id";
    private static final String TAG_COLLEGEID = "college_id";
    private static final String TAG_COLLEGEIDARRAY = "college";


    private static final String TAG_ARRAY = "th";
    private static final String TAG_BOOKNAME = "book_name";
    private static final String TAG_WRITERNAME = "writter_name";
    private static final String TAG_QUANTITY = "quantity";
    private static final String TAG_DEPTNAME = "deptname";
    private static final String TAG_CLASSSNAME = "class_name";

    JSONArray college;
    private ArrayList<String> instlist;
    private static final String TAG_INSID = "id";
    private static final String TAG_INSNAME = "name";
    String name,price,code,stock,quantity;
    JSONParser jsonParser = new JSONParser();
    private ArrayList<String> deplist,classlist,teacherlist;
    private static String url_getiuserid = "http://dik-pl.com/dikpl/gettablebookdistribution.php";
    private static String url_institute = "http://dik-pl.com/dikpl/departmentget.php";
    private static String url_instituteg = "http://dik-pl.com/dikpl/college.php";
    private static String url_classanem = "http://dik-pl.com/dikpl/classget.php";
    private static String baseurl="http://dik-pl.com/dikpl/teachers.php";
    String college_id,teacher_id,teachername,email,department_id;
    private ListView listview;
    String bookname,writtername,quantitys,deptname,class_name;
    private ArrayList<BookDistribution> usersList;
    DistributionAdapter mAdapter;

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

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null) {
            email =(String) b.get("mobile");
            //Toast.makeText(BookRequistionActivity.this, ""+email, Toast.LENGTH_SHORT).show();

        }

        new getList().execute();
        new getInstituionname().execute();
        //new getuser().execute();
        //new Department().execute();

        schoolspinner = (Spinner) findViewById(R.id.schoolspinner);
        listview = (ListView) findViewById(R.id.listview);

        sqlite_obj = new CartSavingSqlite(BookDistributionActivity.this);

        cartadding = new CartAddinglist();
        itemList = new ArrayList<Customlistadding>();

        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int size = cartadding.getCart().size();
                for(i=0;i<size;i++){

                    name = cartadding.getCart().get(i).getName();
                    price = cartadding.getCart().get(i).getPrice();
                    code = cartadding.getCart().get(i).getCode();
                    stock = cartadding.getCart().get(i).getStock();
                    quantity = cartadding.getCart().get(i).getQuantity();

                    listget.add(i,new Customlistadding(name,price,code,stock,quantity,college_id,teacher_id,department_id,comment.getText().toString()));

                    //Toast.makeText(BookDistributionActivity.this, ""+name+""+stock, Toast.LENGTH_SHORT).show();;
                }

                Intent intent = new Intent(BookDistributionActivity.this, ListCartActivity.class);
                String listSerializedToJson = new Gson().toJson(listget);
                intent.putExtra("listget", listSerializedToJson);
                startActivity(intent);
                finish();

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

    public class getInstituionname extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    instlist = new ArrayList<String>();
                    List<NameValuePair> param = new ArrayList<NameValuePair>();
                    // getting JSON string from URL
                    JSONObject json = jsonParser.makeHttpRequest(url_instituteg, "GET", param);

                    // Check your log cat for JSON reponse
                    Log.d("All Products: ", json.toString());

                    try {
                        // Checking for SUCCESS TAG
                        int success = json.getInt(TAG_SUCCESS);

                        if (success == 1) {
                            // products found
                            // Getting Array of Products
                            college = json.getJSONArray(TAG_COLLEGEIDARRAY);

                            // looping through All Products
                            for (int i = 0; i < college.length(); i++) {
                                JSONObject c = college.getJSONObject(i);

                                // Storing each json item in variable
                                String id = c.getString(TAG_INSID);
                                String name = c.getString(TAG_INSNAME);
                                //BengaliUnicodeString.getBengaliUTF(getActivity(),head,text);
                                instlist.add(name);
                                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(BookDistributionActivity.this, android.R.layout.simple_spinner_item, instlist);
                                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                schoolspinner.setAdapter(spinnerAdapter);

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
    private class getList extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            // updating UI from Background Thread
            final JSONParser jp = new JSONParser();
            runOnUiThread(new Runnable() {
                public void run() {
                    // Check for success tag
                    int success;

                    try {
                        // Building Parameters
                        //Toast.makeText(BookRequistionActivity.this, ""+department_id, Toast.LENGTH_SHORT).show();
                        List<NameValuePair> paramsss = new ArrayList<NameValuePair>();
                        paramsss.add(new BasicNameValuePair(TAG_MPO,"2"));
                        Log.d("params",paramsss.toString());

                        // getting product details by making HTTP request
                        // Note that product details url will use GET request
                        JSONObject json = jp.makeHttpRequest(
                                url_getiuserid, "GET", paramsss);

                        // check your log for json response
                        Log.d("Single id Details", json.toString());

                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            // successfully received product details
                            JSONArray bookObj = json
                                    .getJSONArray(TAG_ARRAY); // JSON Array
                            JSONObject c = bookObj.getJSONObject(0);
                            bookname = c.getString(TAG_BOOKNAME);
                            writtername = c.getString(TAG_WRITERNAME);
                            quantitys = c.getString(TAG_QUANTITY);
                            deptname = c.getString(TAG_DEPTNAME);
                            class_name = c.getString(TAG_CLASSNAME);
                            itemList.add(new Customlistadding(bookname,writtername,deptname,class_name,quantitys));


                            Toast.makeText(BookDistributionActivity.this, "bookname"+bookname, Toast.LENGTH_SHORT).show();

                          /*
                            double r = Double.parseDouble(rate);
                            int q = Integer.parseInt(quantity.getText().toString());
                            double v = r * q;
                            Toast.makeText(BookRequistionActivity.this, ""+v, Toast.LENGTH_SHORT).show();
                           */

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
