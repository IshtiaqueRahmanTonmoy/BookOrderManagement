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

import java.util.ArrayList;
import java.util.List;

public class BookRequistionActivity extends AppCompatActivity {

    Spinner spinner,spinner2,spinner11,spinnerbook;
    Button plus,minus,submit,cancel;
    TextView quantity;
    JSONArray department,classname;
    private JSONParser jsonparser = new JSONParser();
    static int num=0;
    private static String url_institute = "http://dik-pl.com/dikpl/departmentget.php";
    private static String url_classanem = "http://dik-pl.com/dikpl/classget.php";
    private static String url_getbook = "http://dik-pl.com/dikpl/getbookname.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_DEPARTMENT = "department";
    private static final String TAG_TH = "th";
    private static final String TAG_CLASSNAME = "tbl_class";
    private static final String TAG_DEPID = "id";
    private static final String TAG_DEPNAME = "name";
    private static final String TAG_BOOKNAME = "book_name";
    private static final String TAG_CLASSID = "id";
    private static final String TAG_CLASNAME = "name";
    private static final String TAG_CLASSIDVAL = "class_id";
    String cid,classvalid,class_id,bookname, bookselect_id,bookselect_type;
    private ArrayList<String> instlist,classlist,booklist;
    String[] SPINNERLIST = {"Guide Book", "Text Book"};
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
        booklist = new ArrayList<String>();

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


        EditText commentEditText = (EditText) findViewById(R.id.commentsEdit);

        Spinner materialDesignSpinner = (Spinner)
                findViewById(R.id.spinner11);
        Spinner materialDesignSpinner1 = (Spinner)
                findViewById(R.id.Spinner);
        Spinner materialDesignSpinner2 = (Spinner)
                findViewById(R.id.stateSpinner);


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
                new InsertData().execute();
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
                                        cid = String.valueOf(position+2);
                                        Toast.makeText(getApplicationContext(),""+cid,Toast.LENGTH_LONG).show();
                                        //new GetTeacheraname().execute();
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
                                        Toast.makeText(getApplicationContext(),""+classvalid,Toast.LENGTH_LONG).show();
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
                    // Check for success tag
                    int success;
                    try {
                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair(TAG_CLASSIDVAL, class_id));

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

                            JSONObject c = bookObj.getJSONObject(0);
                            bookname = c.getString(TAG_BOOKNAME);
                            booklist.add(bookname);
                            ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(BookRequistionActivity.this, android.R.layout.simple_spinner_item, booklist);
                            spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerbook.setAdapter(spinnerAdapter2);

                            spinnerbook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    bookselect_id = String.valueOf(position);
                                    //Toast.makeText(getApplicationContext(),""+classvalid,Toast.LENGTH_LONG).show();

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

                    paramss.add(new BasicNameValuePair("department_id",cid));
                    paramss.add(new  BasicNameValuePair("class_id",class_id));
                    paramss.add(new  BasicNameValuePair("book_type",bookselect_type));
                    paramss.add(new  BasicNameValuePair("quantity",bookselect_id));

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
}
