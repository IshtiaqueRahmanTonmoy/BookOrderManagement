package info.androidhive.snackbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListCartActivity extends AppCompatActivity {

    List<Customlistadding> itemList = new ArrayList<Customlistadding>();
    ListView listview;
    Context context;
    String booknames,stocks,college_id;
    public static final String PREFS_NAME = "MyPrefsFile";
    DynamicAddCustomlist dynamicadd;
    Button placeorder,total;
    int stock = 0;
    String id,code,stockvalue,totalsvalue,name,quantityplus,quantityminus;
    List<Customlistadding> lists;
    int distribute_id=8;
    int idval;
    String distribute_time,distribute_date,teacher_id,department_id,entryby,comments,status;
    String quantity;
    JSONParser jsonParser = new JSONParser();
    List<Customlistadding> carsList;
    private static String url_institute = "http://dik-pl.com/dikpl/college.php";
    private static String REGISTER_URLVALUE = "http://dik-pl.com/dikpl/newentrydistribute.php";

    String currentDateandTime;
    private static final String TAG_SUCCESS = "success";
    Spinner collegesp,teacher;
    public static final String KEY_DISTRIBUTEID = "distribute_id";
    public static final String KEY_BOOKID = "book_id";
    public static final String KEY_QUANTITY = "quantity";
    public static final String KEY_PRICE = "price";
    public static final String KEY_LINENO = "line_no";
    public static final String KEY_STATUS = "status";


    public static final String KEY_DISTRIBUTETIME = "distribute_time";
    public static final String KEY_DISTRIBUTEDATE = "distribute_date";
    public static final String KEY_COLLEGEID = "college_id";
    public static final String KEY_TEACHERID = "teacher_id";
    public static final String KEY_DEPARTEMENTID = "department_id";
    public static final String KEY_ENTRYBY = "entryby";
    public static final String KEY_COMMENTS = "comments";

    public static final String REGISTER_URL = "http://dik-pl.com/dikpl/distributebooks.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cart);

        listview = (ListView) findViewById(R.id.listView);
        placeorder = (Button) findViewById(R.id.button1);
        total = (Button) findViewById(R.id.button2);
        lists = new ArrayList<Customlistadding>();

        Calendar c = Calendar.getInstance();
        //System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        distribute_time = df.format(c.getTime());

        //Calendar c = Calendar.getInstance();
        //System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
        distribute_date = df1.format(c.getTime());

        String carListAsString = getIntent().getStringExtra("listget");
        //Log.i("list",carListAsString);
        //Toast.makeText(ListCartActivity.this, ""+carListAsString, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        Type type = new TypeToken<List<Customlistadding>>(){}.getType();
        carsList = gson.fromJson(carListAsString, type);

        dynamicadd = new DynamicAddCustomlist(this, R.layout.activity_list_cart,carsList);
        listview.setAdapter(dynamicadd);



        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

        /*
        for (Customlistadding customlist : carsList){
            //Log.i("CarData", customlist.getName()+"-"+customlist.getStock());
            //Toast.makeText(ListCartActivity.this, ""+customlist.getName()+""+customlist.getStock(), Toast.LENGTH_SHORT).show();
            stock = stock + Integer.parseInt(customlist.getStock());
            total.setText(""+stock);
        }
        */

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int book_id = 0;
                int price = 0;
                distribute_id++;
                for(Customlistadding s : carsList){
                    int id = s.getId();
                    String name = s.getName().toString();

                    if(name.equals("!")){
                        book_id = 1;
                        price = 250;
                        Log.d("v", String.valueOf(book_id)+String.valueOf(distribute_id));
                    }
                    else if(name.equals("মধুসুদনের কাব্য পাঠের ভুমিকা | হোসনেয়ারা খাতুন বাংলা অনার্স ১ম বর্ষ")){
                        book_id = 2;
                        price = 750;
                        Log.d("v", String.valueOf(book_id)+String.valueOf(distribute_id));
                    }
                    else if(name.equals("বাংলাদেশ এবং বাঙালির ইতিহাস ও সংস্কৃতি")){
                        book_id = 3;
                        price = 333;
                        Log.d("v", String.valueOf(book_id)+String.valueOf(distribute_id));
                    }
                    else {
                        book_id = 4;
                        price = 450;
                        Log.d("v", String.valueOf(book_id)+String.valueOf(distribute_id));
                    }

                    String code = s.getCode().toString();
                    String stock = s.getStock().toString();
                    //Log.d("pr",price);
                    quantity = s.getQuantity().toString();

                    college_id = s.getCollege_id().toString();
                    teacher_id = s.getTeacher_id().toString();
                    department_id = s.getDepartment_id().toString();

                   // college_id = "313";
                    //teacher_id="18";
                    //department_id = "10";
                    //Log.d("values",s.getCollege_id().toString());
                    entryby = "40";
                    comments = s.getComment().toString();
                    //comments = "test";
                    status = "1";


                    //qtym = s.getQuantityminus().toString();
                    //Log.d(""+id"+i+"name"+name+"code"+code+"stock"+stock+"quantity"+qtys);
                    //String tot = s.getFoodrate().toString();

                    Insertdata insert = new Insertdata(String.valueOf(distribute_id),String.valueOf(book_id),quantity,String.valueOf(price),"1","1");
                    Insertanother another = new Insertanother(distribute_time,distribute_date,college_id,teacher_id,department_id,entryby,comments,status);

                    insert.execute("",null);
                    another.execute("",null);

                    //Log.d("idval",id);
                    //Toast.makeText(ListCartActivity.this, "quantity"+qtys, Toast.LENGTH_SHORT).show();
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
            idval = intent.getIntExtra("id",0);
            name = intent.getStringExtra("name");
            code = intent.getStringExtra("code");
            stockvalue = intent.getStringExtra("stock");
            quantityplus = intent.getStringExtra("qty");
            //quantityminus = intent.getStringExtra("qtyminus");
            totalsvalue = intent.getStringExtra("total");

            Log.d("quant",quantityplus);
            lists.add(new Customlistadding(idval,name,code,stockvalue,quantityplus,totalsvalue));
        }
    };

    private class Insertdata extends AsyncTask<String,String,String > {

        String z = "";
        Boolean isSuccess = false;
        String distribute_id,book_id,quantity,price,line_no,status;

        public Insertdata(String distribute_id, String book_id, String quantity, String price, String line_no, String status) {
            this.distribute_id = distribute_id;
            this.book_id = book_id;
            this.quantity = quantity;
            this.price = price;
            this.line_no = line_no;
            this.status = status;

            Log.d("price",price);
            //Log.d("p",tot);
            //Log.d("debug",id+name+price+qty);
        }

        @Override
        protected String doInBackground(String... params) {

            List<NameValuePair> param = new ArrayList<NameValuePair>();

            param.add(new BasicNameValuePair(KEY_DISTRIBUTEID, distribute_id));
            param.add(new BasicNameValuePair(KEY_BOOKID, book_id));
            param.add(new BasicNameValuePair(KEY_QUANTITY, quantity));
            param.add(new BasicNameValuePair(KEY_PRICE, price));
            param.add(new BasicNameValuePair(KEY_LINENO, line_no));
            param.add(new BasicNameValuePair(KEY_STATUS, status));

            JSONObject json = jsonParser.makeHttpRequest(REGISTER_URL, "POST", param);

            try {
                int success = json.getInt(TAG_SUCCESS);

                //Toast.makeText(DoctorRegistrationActivity.this, "" + success, Toast.LENGTH_SHORT).show();
                if (success == 1) {
                    // successfully created product

                    ListCartActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(ListCartActivity.this.getBaseContext(), "Data insertion completed..", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String success) {

            //pbbar.setVisibility(View.GONE);
            Toast.makeText(ListCartActivity.this, success, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Log.d("Success", z);
                Intent i = new Intent(ListCartActivity.this, ListCartActivity.class);
                startActivity(i);
                finish();
            } else {
                Log.d("Error", z);
            }
        }
    }


    private class Insertanother extends AsyncTask<String,String,String > {

        String z = "";
        Boolean isSuccess = false;
        String distribute_time,distribute_date,college_id,teacher_id,department_id,entryby,comments,status;

        public Insertanother(String distribute_time,String distribute_date,String college_id,String teacher_id,String department_id,String entryby,String comments,String status) {
            this.distribute_time = distribute_time;
            this.distribute_date = distribute_date;
            this.college_id = college_id;
            this.teacher_id = teacher_id;
            this.department_id = department_id;
            this.entryby = entryby;
            this.comments = comments;
            this.status = status;

            //Log.d("price",price);
            //Log.d("p",tot);
            //Log.d("debug",id+name+price+qty);
        }

        @Override
        protected String doInBackground(String... params) {

            List<NameValuePair> param = new ArrayList<NameValuePair>();

            param.add(new BasicNameValuePair(KEY_DISTRIBUTETIME, distribute_time));
            param.add(new BasicNameValuePair(KEY_DISTRIBUTEDATE, distribute_date));
            param.add(new BasicNameValuePair(KEY_COLLEGEID, college_id));
            param.add(new BasicNameValuePair(KEY_TEACHERID, teacher_id));
            param.add(new BasicNameValuePair(KEY_DEPARTEMENTID, department_id));
            param.add(new BasicNameValuePair(KEY_ENTRYBY, entryby));
            param.add(new BasicNameValuePair(KEY_COMMENTS, comments));
            param.add(new BasicNameValuePair(KEY_STATUS, status));

            JSONObject json = jsonParser.makeHttpRequest(REGISTER_URLVALUE, "POST", param);

            try {
                int success = json.getInt(TAG_SUCCESS);

                //Toast.makeText(DoctorRegistrationActivity.this, "" + success, Toast.LENGTH_SHORT).show();
                if (success == 1) {
                    // successfully created product

                    ListCartActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(ListCartActivity.this.getBaseContext(), "Data insertion completed..", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String success) {

            //pbbar.setVisibility(View.GONE);
            Toast.makeText(ListCartActivity.this, success, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Log.d("Success", z);
                Intent i = new Intent(ListCartActivity.this, ListCartActivity.class);
                startActivity(i);
                finish();
            } else {
                Log.d("Error", z);
            }
        }
    }
}
