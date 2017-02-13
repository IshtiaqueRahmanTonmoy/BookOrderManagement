package info.androidhive.snackbar;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Donationrequistion extends AppCompatActivity {

    //String[] SPINNERLIST = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};
    Button button,noofstudentplus,noofstudentminus,possiblebookplus,possiblebookminus,amountoftkplus,amountoftkminus;
    EditText onudan,amountoktk;
    JSONArray college;
    TextView Noofstudent,possiblebooks,amountoftk;
    static int numofstudent,numofpossible,noofamount=0;
    Spinner instspinner,teacherspinner,subjectnamespinner,classnamespinner,booknamespinner;
    private ArrayList<String> instlist,teacherlist,subjectlist,classlist,booklist;
    private static String url_institute = "http://dik-pl.com/dikpl/college.php";
    private static String baseurl="http://dik-pl.com/dikpl/teachers.php";
    private static String getdepartment="http://dik-pl.com/dikpl/department.php";
    private static String url_teacher="http://dik-pl.com/dikpl/tblclass.php";
    private static String url_book="http://dik-pl.com/dikpl/books.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_INSID = "id";
    private static final String TAG_INSNAME = "name";
    private static final String TAG_COLLEGEID = "college_id";
    private static final String TAG_TEACHERNAME = "name";
    private static final String TAG_TABLETEACHER = "teachers";
    private static final String TAG_COLLEGEIDARRAY = "college";
    private static final String TAG_TEACHERID = "id";
    private static final String TAG_CID = "class_id";
    private static final String TAG_SUBJECTID = "id";
    private static final String TAG_CLASSID = "id";
    private static final String TAG_CLASSNAME = "name";
    private static final String TAG_SUBJECTNAME = "name";
    private static final String TAG_BOOKID = "id";
    private static final String TAG_BOOKNAME = "book_name";
    String insid,insname,teachername,cid,sids,subjid,classid,teacherid,subjectid,subjectname,classname,bookid,bookname,onudanamount,nostudent,possbook,amountk;
    private ProgressDialog pDialog;
    private JSONParser jsonparser;
    String college_id;
    private int CalendarHour, CalendarMinute;
    Calendar calendar;
    String format;
    TimePickerDialog timepickerdialog;
    private JSONArray jsonarray;
    private static final String TAG_PHONE = "phone";
    private static final String TAG_DOCTORLIST = "patientdetail";
    private static final String TAG_NAME = "name";
    String namevalue;
    private static final String DOCTORDETAILGET_URL = "http://darumadhaka.com/patientmanagement/searchallpatientinfo.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donationrequistion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        new Instituionname().execute();

        //new Teachername().execute();
        //new Subjectname().execute();
        //new Classname().execute();
        //new Booknames).execute();

       // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
        //        android.R.layout.simple_dropdown_item_1line, SPINNERLIST);

        instlist = new ArrayList<String>();
        instspinner = (Spinner) findViewById(R.id.countrySpinner);
        teacherspinner = (Spinner) findViewById(R.id.stateSpinner);
        subjectnamespinner = (Spinner) findViewById(R.id.citySpinner);
        classnamespinner = (Spinner) findViewById(R.id.citySpinner1);
        booknamespinner = (Spinner) findViewById(R.id.citySpinner2);

        button = (Button) findViewById(R.id.submitbutton);
        noofstudentplus = (Button) findViewById(R.id.plusbtn);
        noofstudentminus = (Button) findViewById(R.id.minusbtn);
        possiblebookplus = (Button) findViewById(R.id.plusbutton);
        possiblebookminus = (Button) findViewById(R.id.minusbutton);
        Noofstudent = (TextView) findViewById(R.id.noofstudents);
        possiblebooks = (TextView) findViewById(R.id.possiblebooks);
        amountoftk = (TextView) findViewById(R.id.amountoftk);
        onudan = (EditText) findViewById(R.id.onudanEdit);
        amountoktk = (EditText) findViewById(R.id.amountoftk);

        //materialDesignSpinner.setAdapter(arrayAdapter);
        //materialDesignSpinner1.setAdapter(arrayAdapter);
        //materialDesignSpinner2.setAdapter(arrayAdapter);
        //materialDesignSpinner3.setAdapter(arrayAdapter);
        //materialDesignSpinner4.setAdapter(arrayAdapter);


        onudan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                CalendarHour = calendar.get(Calendar.HOUR_OF_DAY);
                CalendarMinute = calendar.get(Calendar.MINUTE);
                timepickerdialog = new TimePickerDialog(Donationrequistion.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                if (hourOfDay == 0) {

                                    hourOfDay += 12;

                                    format = "AM";
                                }
                                else if (hourOfDay == 12) {

                                    format = "PM";

                                }
                                else if (hourOfDay > 12) {

                                    hourOfDay -= 12;

                                    format = "PM";

                                }
                                else {

                                    format = "AM";
                                }


                                onudan.setText(hourOfDay + ":" + minute + format);
                            }
                        }, CalendarHour, CalendarMinute, false);
                timepickerdialog.show();

            }
        });
        noofstudentplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numofstudent=numofstudent+1;
                Noofstudent.setText(""+numofstudent);
            }
        });

        noofstudentminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numofstudent=numofstudent-1;
                Noofstudent.setText(""+numofstudent);
            }
        });

        possiblebookplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numofpossible=numofpossible+1;
                possiblebooks.setText(""+numofpossible);
            }
        });

        possiblebookminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numofpossible=numofpossible-1;
                possiblebooks.setText(""+numofpossible);
            }
        });





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nostudent = Noofstudent.getText().toString();
                possbook = possiblebooks.getText().toString();
                amountk = amountoktk.getText().toString();
                onudanamount = onudan.getText().toString();


                //Toast.makeText(getApplicationContext(),"no of student"+onudanamount,Toast.LENGTH_LONG).show();
                //insert(cid,teacherid,subjectid,classid,nostudent,possbook,onudanamount,bookid,amountk);
                //String cid,teacherid,subjectid,classid,noofstudent,possiblebooks,onudan,bookid,amountoftk;

                new InsertintoDatabase().execute();
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


    //Institute name

    public class Instituionname extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    jsonparser = new JSONParser();
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
                            college = json.getJSONArray(TAG_COLLEGEIDARRAY);

                            // looping through All Products
                            for (int i = 0; i < college.length(); i++) {
                                JSONObject c = college.getJSONObject(i);

                                // Storing each json item in variable
                                String id = c.getString(TAG_INSID);
                                String name = c.getString(TAG_INSNAME);
                                //BengaliUnicodeString.getBengaliUTF(getActivity(),head,text);
                                instlist.add(name);
                                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(Donationrequistion.this, android.R.layout.simple_spinner_item, instlist);
                                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                instspinner.setAdapter(spinnerAdapter);

                                instspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        college_id = String.valueOf(position+2);
                                        Toast.makeText(getApplicationContext(),""+cid,Toast.LENGTH_LONG).show();
                                        new GetTeacheraname().execute();
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

    public class GetTeacheraname extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    teacherlist = new ArrayList<String>();
                    jsonparser = new JSONParser();
                    List<NameValuePair> param = new ArrayList<NameValuePair>();

                    //String val = insid;
                    //Toast.makeText(getApplicationContext(),""+val,Toast.LENGTH_LONG).show();
                    param.add(new BasicNameValuePair(TAG_COLLEGEID,  college_id));
                    JSONObject json = jsonparser.makeHttpRequest(baseurl, "GET", param);

                    Log.e("Response: ", "> " + json);

                    JSONArray th = null;
                    try {
                        th = json.getJSONArray("th");
                        for (int x = 0; x < th.length(); x++) {
                            JSONObject catObj11 = th.getJSONObject(x);
                            teacherid = catObj11.getString(TAG_TEACHERID);
                            teachername = catObj11.getString(TAG_TEACHERNAME);
                            Log.d("output",teachername);
                            teacherlist.add(teachername);
                        }

                        //Log.e("Thana: ", "> " + than);
                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(Donationrequistion.this, android.R.layout.simple_list_item_1, teacherlist);
                        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        teacherspinner.setAdapter(spinnerAdapter);


                        teacherspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                sids = String.valueOf(position+1);
                                new SubjectName().execute();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            return null;
        }
    }

    public class SubjectName extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    subjectlist = new ArrayList<String>();
                    jsonparser = new JSONParser();
                    List<NameValuePair> param = new ArrayList<NameValuePair>();

                    //String val = insid;
                    //Toast.makeText(getApplicationContext(),""+val,Toast.LENGTH_LONG).show();
                    param.add(new BasicNameValuePair(TAG_SUBJECTID,sids));
                    JSONObject json = jsonparser.makeHttpRequest(getdepartment, "GET", param);

                    Log.e("Response: ", "> " + json);

                    JSONArray th = null;
                    try {
                        th = json.getJSONArray("th");
                        for (int x = 0; x < th.length(); x++) {
                            JSONObject catObj11 = th.getJSONObject(x);
                            subjectid = catObj11.getString(TAG_SUBJECTID);
                            subjectname = catObj11.getString(TAG_SUBJECTNAME);
                            Log.d("output",teachername);
                            subjectlist.add(subjectname);
                        }

                        //Log.e("Thana: ", "> " + than);
                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(Donationrequistion.this, android.R.layout.simple_list_item_1, subjectlist);
                        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        subjectnamespinner.setAdapter(spinnerAdapter);


                        subjectnamespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                subjid = String.valueOf(position+1);
                                new ClassName().execute();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            return null;
        }
    }

    public class ClassName extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    classlist = new ArrayList<String>();
                    jsonparser = new JSONParser();
                    List<NameValuePair> param = new ArrayList<NameValuePair>();

                    //String val = insid;
                    //Toast.makeText(getApplicationContext(),""+val,Toast.LENGTH_LONG).show();
                    param.add(new BasicNameValuePair(TAG_CLASSID , subjid));
                    JSONObject json = jsonparser.makeHttpRequest(url_teacher, "GET", param);

                    Log.e("Response: ", "> " + json);

                    JSONArray th = null;
                    try {
                        th = json.getJSONArray("th");
                        for (int x = 0; x < th.length(); x++) {
                            JSONObject catObj11 = th.getJSONObject(x);
                            classid = catObj11.getString(TAG_CLASSID);
                            classname = catObj11.getString(TAG_CLASSNAME);
                            Log.d("output",teachername);
                            classlist.add(classname);
                        }

                        //Log.e("Thana: ", "> " + than);
                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(Donationrequistion.this, android.R.layout.simple_list_item_1, classlist);
                        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        classnamespinner.setAdapter(spinnerAdapter);


                        classnamespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                classid = String.valueOf(position+1);
                                new BookName().execute();

                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            return null;
        }
    }

    public class BookName extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    booklist = new ArrayList<String>();
                    jsonparser = new JSONParser();
                    List<NameValuePair> param = new ArrayList<NameValuePair>();

                    //String val = insid;
                    //Toast.makeText(getApplicationContext(),""+val,Toast.LENGTH_LONG).show();
                    param.add(new BasicNameValuePair(TAG_CID,classid));
                    JSONObject json = jsonparser.makeHttpRequest(url_book, "GET", param);

                    Log.e("Response: ", "> " + json);

                    JSONArray th = null;
                    try {
                        th = json.getJSONArray("th");
                        for (int x = 0; x < th.length(); x++) {
                            JSONObject catObj11 = th.getJSONObject(x);
                            bookid = catObj11.getString(TAG_BOOKID);
                            bookname = catObj11.getString(TAG_BOOKNAME);
                            Log.d("output",teachername);
                            booklist.add(bookname);
                        }

                        //Log.e("Thana: ", "> " + than);
                        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(Donationrequistion.this, android.R.layout.simple_list_item_1, booklist);
                        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        booknamespinner.setAdapter(spinnerAdapter);


                        booknamespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                subjid = String.valueOf(position+1);
                                //new BookName().execute();

                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            return null;
        }
    }

    private class InsertintoDatabase extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {


            //Toast.makeText(getApplicationContext(),"possible book"+possbook,Toast.LENGTH_LONG).show();
            //insert(cid,teacherid,subjectid,classid,nostudent,possbook,onudanamount,bookid,amountk);
         runOnUiThread(new Runnable() {
             @Override
             public void run() {
                 String urlsubmidata = "http://dik-pl.com/dikpl/donation.php";
                 //String urlsubmidata = ""+url+"/";
                 List<NameValuePair> paramss = new ArrayList<NameValuePair>();
                 paramss.add(new  BasicNameValuePair("college_id",cid));
                 paramss.add(new  BasicNameValuePair("teacher_id",teacherid));
                 paramss.add(new  BasicNameValuePair("department_id",subjectid));
                 paramss.add(new  BasicNameValuePair("class_id",classid));
                 paramss.add(new  BasicNameValuePair("student_quantity",nostudent));
                 paramss.add(new  BasicNameValuePair("possible_book",possbook));
                 paramss.add(new BasicNameValuePair("duration", onudanamount));
                 paramss.add(new  BasicNameValuePair("book_id",bookid));
                 paramss.add(new  BasicNameValuePair("money_amount",amountk));

                 JSONObject json = jsonparser.makeHttpRequest(urlsubmidata,
                         "POST", paramss);

                 Toast.makeText(Donationrequistion.this, "Successfully inserted..", Toast.LENGTH_SHORT).show();
                 // check log cat fro response
                 Log.d("Create Response", json.toString());

                 // check for success tag
                 //Toast.makeText(getApplicationContext(),"Successfully inserted..",Toast.LENGTH_LONG).show();

                 // closing this screen


             }
         });

            return null;
        }
    }
}
