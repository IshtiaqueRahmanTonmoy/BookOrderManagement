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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookRequistionActivity extends AppCompatActivity {

    String id,finalgetid,book_name,rate;
    Spinner spinner,spinner2,spinner11,spinnerbook;
    Button plus,minus,submit,cancel;
    TextView quantity;
    JSONArray department,classname;
    private JSONParser jsonparser = new JSONParser();
    static int num=0;
    private static String url_institute = "http://dik-pl.com/dikpl/departmentget.php";
    private static String url_classanem = "http://dik-pl.com/dikpl/classget.php";
    private static String url_getbook = "http://dik-pl.com/dikpl/getbookname.php";
    private static String url_getbookname = "http://dik-pl.com/dikpl/getbookidbyname.php";
    private static String url_getrate = "http://dik-pl.com/dikpl/getrate.php";
    private static String url_getiuserid = "http://dik-pl.com/dikpl/getuserid.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_DEPARTMENT = "department";
    private static final String TAG_TH = "th";
    private static final String TAG_BOOKID = "id";
    private static final String TAG_CLASSNAME = "tbl_class";
    private static final String TAG_DEPID = "id";
    private static final String TAG_DEPNAME = "name";
    private static final String TAG_BOOKNAME = "book_name";
    private static final String TAG_CLASSID = "id";
    private static final String TAG_CLASNAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_ID = "id";
    private static final String TAG_CLASSIDVAL = "class_id";
    private static final String TAG_BOOKRate = "sell_price";
    private static final String TAG_BOOKN = "book_name";
    private static final String TAG_DEPTID = "department_id";

    private static final String TAG_DIVISIONID = "division_id";
    private static final String TAG_JONALID = "jonal_id";
    private static final String TAG_DISTRICTID = "district_id";
    private static final String TAG_THANAID = "thana_id";



    String cid,classvalid,class_id,bookname, bookselect_id,bookselect_type,vid,department_id;
    private ArrayList<String> instlist,classlist,booklist;
    String[] SPINNERLIST = {"Guide Book", "Text Book"};
    String requisition_by,email;
    double r,total;
    EditText commentEditText;
    int q;
    DateFormat dateFormat;
    String invoice_no;
    Date dates;
    String total_amount,total_quantity,comment,date,date2,status,division_id,jonal_id,district_id,thana_id;
    int i=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_requistion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dates = new Date();

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null) {
            email =(String) b.get("mobile");
            //Toast.makeText(BookRequistionActivity.this, ""+email, Toast.LENGTH_SHORT).show();

        }
        new getuser().execute();


        new Department().execute();
        new Classname().execute();

        spinner = (Spinner) findViewById(R.id.Spinner);
        spinner2 = (Spinner) findViewById(R.id.stateSpinner);
        spinner11 = (Spinner) findViewById(R.id.spinner11);
        spinnerbook = (Spinner) findViewById(R.id.citySpinner);
        plus = (Button) findViewById(R.id.buttonplus);
        minus = (Button) findViewById(R.id.buttonminus);
        quantity = (TextView) findViewById(R.id.quantity_text_view);
        submit = (Button) findViewById(R.id.button1);
        cancel = (Button) findViewById(R.id.cancel);

        instlist = new ArrayList<String>();
        classlist = new ArrayList<String>();


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        spinner11.setAdapter(arrayAdapter);
        spinner11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bookselect_type = String.valueOf(position);
                //Toast.makeText(getApplicationContext(),""+classvalid,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        commentEditText = (EditText) findViewById(R.id.commentsEdit);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=num+1;
                quantity.setText(""+num);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=num-1;
                quantity.setText(""+num);
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i = i + 1;
                invoice_no = "2017-02-0"+i;
                //Toast.makeText(BookRequistionActivity.this, ""+invoice_no, Toast.LENGTH_SHORT).show();
                total_amount = String.valueOf(total);
                total_quantity = quantity.getText().toString();
                comment = commentEditText.getText().toString();
                date = dateFormat.format(dates);
                date2 = dateFormat.format(dates);
                status = ""+1;

                r = Double.parseDouble(rate);
                q = Integer.parseInt(quantity.getText().toString());
                //Log.d("total", r,q);
               // Toast.makeText(BookRequistionActivity.this, "rate"+r+"quantity"+q, Toast.LENGTH_SHORT).show();
                total = r*q;
                total_amount = String.valueOf(total);
                //Toast.makeText(BookRequistionActivity.this, "total"+total, Toast.LENGTH_SHORT).show();
                //Log.d("total", String.valueOf(total));

               new InsertData().execute();
               new InsertintoReq().execute();
            }
        });
    }

    private class getuser extends AsyncTask<String, String, String> {

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
                        paramsss.add(new BasicNameValuePair(TAG_EMAIL,'"'+email+'"'));
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
                                    .getJSONArray(TAG_TH); // JSON Array
                            JSONObject c = bookObj.getJSONObject(0);
                            requisition_by = c.getString(TAG_ID);
                            division_id = c.getString(TAG_DIVISIONID);
                            jonal_id = c.getString(TAG_JONALID);
                            district_id = c.getString(TAG_DISTRICTID);
                            thana_id = c.getString(TAG_THANAID);


                            Toast.makeText(BookRequistionActivity.this, "requistion by"+requisition_by, Toast.LENGTH_SHORT).show();

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

    public class Department extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<NameValuePair> param = new ArrayList<NameValuePair>();
                    // getting JSON string from URL
                    JSONObject json = jsonparser.makeHttpRequest(url_institute, "GET", param);

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
                                instlist.add(name);
                                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(BookRequistionActivity.this, android.R.layout.simple_spinner_item, instlist);
                                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(spinnerAdapter);

                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        department_id = String.valueOf(position+1);
                                        //Toast.makeText(getApplicationContext(),""+vid,Toast.LENGTH_LONG).show();
                                        //department_id = String.valueOf(position - 1);
                                        //new GetBookname().execute();
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

    public class Classname extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<NameValuePair> param = new ArrayList<NameValuePair>();
                    // getting JSON string from URL
                    JSONObject json = jsonparser.makeHttpRequest(url_classanem, "GET", param);

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
                                ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<String>(BookRequistionActivity.this, android.R.layout.simple_spinner_item, classlist);
                                spinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner2.setAdapter(spinnerAdapter1);

                                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        class_id = String.valueOf(position+2);
                                        //Toast.makeText(getApplicationContext(),""+classvalid,Toast.LENGTH_LONG).show();
                                        new GetBookname().execute();
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


    private class GetBookname extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    booklist = new ArrayList<String>();
                    // Check for success tag
                    int success;
                    try {
                        // Building Parameters
                        //Toast.makeText(BookRequistionActivity.this, ""+department_id, Toast.LENGTH_SHORT).show();
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair(TAG_DEPTID,department_id));

                        // getting product details by making HTTP request
                        // Note that product details url will use GET request
                        JSONObject json = jsonparser.makeHttpRequest(
                                url_getbook, "GET", params);

                        // check your log for json response
                        Log.d("Single Product Details", json.toString());

                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            // successfully received product details
                            JSONArray bookObj = json
                                    .getJSONArray(TAG_TH); // JSON Array
                            for (int x = 0; x < bookObj.length(); x++) {


                                JSONObject c = bookObj.getJSONObject(x);
                                id = c.getString(TAG_BOOKID);
                                bookname = c.getString(TAG_BOOKNAME);
                                //Toast.makeText(BookRequistionActivity.this, ""+id, Toast.LENGTH_SHORT).show();
                                booklist.add(bookname);
                            }

                            ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(BookRequistionActivity.this, android.R.layout.simple_spinner_item, booklist);
                            spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerbook.setAdapter(spinnerAdapter2);

                            spinnerbook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    book_name = spinnerbook.getSelectedItem().toString();
                                    bookselect_id = String.valueOf(position) + 1;

                                    //new getrate().execute();
                                    new getBookid().execute(book_name);

                                    //Toast.makeText(getApplicationContext(),""+book_name,Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                }
                            });

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

    private class InsertData extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    String urlsubmidata = "http://dik-pl.com/dikpl/requistion.php";
                    List<NameValuePair> paramss = new ArrayList<NameValuePair>();

                    paramss.add(new BasicNameValuePair("department_id",department_id));
                    paramss.add(new  BasicNameValuePair("class_id",class_id));
                    paramss.add(new  BasicNameValuePair("book_type",bookselect_type));
                    paramss.add(new  BasicNameValuePair("quantity",quantity.getText().toString()));

                    JSONObject json = jsonparser.makeHttpRequest(urlsubmidata, "POST", paramss);
                    //Log.d("Create Response", json.toString());
                    try {

                        int success = json.getInt(TAG_SUCCESS);

                        // Toast.makeText(RegistrationActivity.this, "" + success, Toast.LENGTH_SHORT).show();
                        if (success == 1) {
                            // successfully created product

                            BookRequistionActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(BookRequistionActivity.this.getBaseContext(), "Successfully inserted..", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            });
                        } else {
                            // failed to create product
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });


            return null;

        }
    }

    private class getBookid extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            // updating UI from Background Thread
            final String s = params[0];


            final JSONParser jp = new JSONParser();

            runOnUiThread(new Runnable() {
                public void run() {
                    // Check for success tag
                    int success;
                    try {
                        // Building Parameters
                        //Toast.makeText(BookRequistionActivity.this, ""+department_id, Toast.LENGTH_SHORT).show();
                        List<NameValuePair> paramsss = new ArrayList<NameValuePair>();
                        paramsss.add(new BasicNameValuePair(TAG_BOOKNAME, '"'+s+'"'));
                        //Log.d("params",finalQuery);

                        // getting product details by making HTTP request
                        // Note that product details url will use GET request
                        JSONObject json = jp.makeHttpRequest(
                                url_getbookname, "GET", paramsss);

                        // check your log for json response
                        Log.d("Single Book Details", paramsss.toString());

                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            // successfully received product details
                            JSONArray bookObj = json
                                    .getJSONArray(TAG_TH); // JSON Array


                                JSONObject c = bookObj.getJSONObject(0);
                                finalgetid = c.getString(TAG_BOOKID);
                               // Log.d("valuesfd",finalgetid);
                                //new getrate().execute();
                                //Toast.makeText(BookRequistionActivity.this, ""+finalgetid, Toast.LENGTH_SHORT).show();
                                new getrate().execute();

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



    private class getrate extends AsyncTask<String, String, String> {

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
                        paramsss.add(new BasicNameValuePair(TAG_BOOKID,finalgetid));
                        //Log.d("params",book_name);

                        // getting product details by making HTTP request
                        // Note that product details url will use GET request
                        JSONObject json = jp.makeHttpRequest(
                                url_getrate, "GET", paramsss);

                        // check your log for json response
                        Log.d("Single Product Details", json.toString());

                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            // successfully received product details
                            JSONArray bookObj = json
                                    .getJSONArray(TAG_TH); // JSON Array
                            JSONObject c = bookObj.getJSONObject(0);
                            rate = c.getString(TAG_BOOKRate);

                            Log.d("total", String.valueOf(total));
                            //Toast.makeText(BookRequistionActivity.this, ""+rate, Toast.LENGTH_SHORT).show();

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

    private class InsertintoReq extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {

            final JSONParser jp = new JSONParser();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    String urlsubmidata1 = "http://dik-pl.com/dikpl/inserttablerequistion.php";
                    List<NameValuePair> paramss = new ArrayList<NameValuePair>();


                    Log.d("allvalues",requisition_by+invoice_no+total_amount+total_quantity+comment+date+date2+status);
                    paramss.add(new BasicNameValuePair("requisition_by",requisition_by));

                    paramss.add(new BasicNameValuePair("invoice_no",invoice_no));
                    paramss.add(new BasicNameValuePair("division_id",division_id));
                    paramss.add(new BasicNameValuePair("jonal_id",jonal_id));
                    paramss.add(new BasicNameValuePair("district_id",district_id));
                    paramss.add(new BasicNameValuePair("thana_id",thana_id));
                    paramss.add(new  BasicNameValuePair("total_amount",total_amount));
                    paramss.add(new  BasicNameValuePair("total_quantity",total_quantity));
                    paramss.add(new  BasicNameValuePair("comment",comment));
                    paramss.add(new  BasicNameValuePair("date",date));
                    paramss.add(new  BasicNameValuePair("date2",date2));
                    paramss.add(new  BasicNameValuePair("status",status));

                    JSONObject json = jp.makeHttpRequest(urlsubmidata1, "POST", paramss);
                    //Log.d("Create Response", json.toString());
                    try {

                        int success = json.getInt(TAG_SUCCESS);

                        // Toast.makeText(RegistrationActivity.this, "" + success, Toast.LENGTH_SHORT).show();
                        if (success == 1) {
                            // successfully created product

                            BookRequistionActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(BookRequistionActivity.this.getBaseContext(), "Successfully inserted..", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            });
                        } else {
                            // failed to create product
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
