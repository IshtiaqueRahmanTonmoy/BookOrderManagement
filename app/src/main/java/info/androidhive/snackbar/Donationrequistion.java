package info.androidhive.snackbar;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private static final String TAG_DIVISIONID = "division_id";
    private static final String TAG_JONALID = "jonal_id";
    private static final String TAG_DISTRICTID = "district_id";
    private static final String TAG_THANAID = "thana_id";
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
    String insid,insname,teachername,cid,sids,subjid,class_id,teacherid,subjectid,subjectname,classname,book_id,bookname,onudanamount,nostudent,possbook,amountk;
    private ProgressDialog pDialog;
    private JSONParser jsonparser;
    String college_id,bookid,transfer_money_amount;
    private int CalendarHour, CalendarMinute;
    Calendar calendar;
    String format,transfer_student_quantity;
    private static final String TAG_EMAIL = "email";
    private static final String TAG_TH = "th";
    private static final String TAG_ID = "id";
    TimePickerDialog timepickerdialog;
    private JSONArray jsonarray;
    private static final String TAG_PHONE = "phone";
    private static final String TAG_DOCTORLIST = "patientdetail";
    private static final String TAG_NAME = "name";
    String email,namevalue,student_quantity,possible_book,duration,money_amount,teacher_id,transfer_possible_book;
    private static String url_getiuserid = "http://dik-pl.com/dikpl/getuserid.php";
    private static final String DOCTORDETAILGET_URL = "http://darumadhaka.com/patientmanagement/searchallpatientinfo.php";
    String requisition_by,division_id,jonal_id,district_id,thana_id,classid,department_id,date;
    DateFormat dateFormat;
    int mYear,mMonth,mDay;
    Calendar myCalendar;
    String approved_date;
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


        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null) {
            email =(String) b.get("mobile");
            new getuser().execute();
            //Toast.makeText(BookRequistionActivity.this, ""+email, Toast.LENGTH_SHORT).show();

        }

        new Instituionname().execute();

        //new Teachername().execute();
        //new Subjectname().execute();
        //new Classname().execute();
        //new Booknames).execute();

        // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
        //        android.R.layout.simple_dropdown_item_1line, SPINNERLIST);


        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Toast.makeText(Donationrequistion.this, ""+date, Toast.LENGTH_SHORT).show();

// Method 2


        instlist = new ArrayList<String>();
        instspinner = (Spinner) findViewById(R.id.countrySpinner);
        teacherspinner = (Spinner) findViewById(R.id.stateSpinner);
        subjectnamespinner = (Spinner) findViewById(R.id.citySpinner);
        classnamespinner = (Spinner) findViewById(R.id.citySpinner1);
        booknamespinner = (Spinner) findViewById(R.id.citySpinner2);

        button = (Button) findViewById(R.id.submitbutton);

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

                myCalendar = Calendar.getInstance();
                mYear= myCalendar.get(Calendar.YEAR);
                mMonth= myCalendar.get(Calendar.MONTH);
                mDay= myCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(Donationrequistion.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

                        myCalendar.set(Calendar.YEAR, mYear);
                        myCalendar.set(Calendar.MONTH, mMonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, mDay);
                        updateLabel();
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                date = dateFormat.format(new Date());
                student_quantity = Noofstudent.getText().toString();
                transfer_student_quantity = Noofstudent.getText().toString();
                possible_book = possiblebooks.getText().toString();
                transfer_possible_book = possiblebooks.getText().toString();
                duration = onudan.getText().toString();
                money_amount = amountoktk.getText().toString();
                transfer_money_amount = amountoktk.getText().toString();


                Toast.makeText(Donationrequistion.this, "student"+student_quantity+"possiblebook"+possible_book+"amountoftk"+money_amount+"onudan"+duration, Toast.LENGTH_SHORT).show();
                Log.d("ouput","class"+cid+"teacher"+teacherid+"subject"+subjectid+"class"+classid+"studentno"+nostudent+"possiblebook"+possbook+"onudan"+onudanamount+"id"+bookid+"amount"+amountk);
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


        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int m = mMonth + 1;
        //Toast.makeText(ListCartActivity.this, ""+m, Toast.LENGTH_SHORT).show();
        approved_date = ""+(mDay<10?("0"+mDay):(mDay))+"-"+(m<10?("0"+m):(m))+"-"+mYear;

    }

    private void updateLabel() {
        String myFormat = "MM-dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        onudan.setText(sdf.format(myCalendar.getTime()));
    }


    //Institute name

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


                            Toast.makeText(Donationrequistion.this, "requistion by"+requisition_by, Toast.LENGTH_SHORT).show();

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
                                        Toast.makeText(getApplicationContext(),""+college_id,Toast.LENGTH_LONG).show();
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
                    param.add(new BasicNameValuePair(TAG_COLLEGEID, college_id));
                    JSONObject json = jsonparser.makeHttpRequest(baseurl, "GET", param);

                    Log.e("Response: ", "> " + json);

                    JSONArray th = null;
                    try {
                        th = json.getJSONArray("th");
                        for (int x = 0; x < th.length(); x++) {
                            JSONObject catObj11 = th.getJSONObject(x);
                            teacherid = catObj11.getString(TAG_TEACHERID);
                            Toast.makeText(Donationrequistion.this, "teacheid"+teacherid, Toast.LENGTH_SHORT).show();
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
                                teacher_id = String.valueOf(position+1);
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
                    param.add(new BasicNameValuePair(TAG_SUBJECTID,teacher_id));
                    JSONObject json = jsonparser.makeHttpRequest(getdepartment, "GET", param);

                    Log.e("Response: ", "> " + json);

                    JSONArray th = null;
                    try {
                        th = json.getJSONArray("th");
                        for (int x = 0; x < th.length(); x++) {
                            JSONObject catObj11 = th.getJSONObject(x);
                            subjectid = catObj11.getString(TAG_SUBJECTID);
                            Toast.makeText(Donationrequistion.this, "subjectid"+subjectid, Toast.LENGTH_SHORT).show();

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
                                department_id = String.valueOf(position+1);
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
                    param.add(new BasicNameValuePair(TAG_CLASSID , department_id));
                    JSONObject json = jsonparser.makeHttpRequest(url_teacher, "GET", param);

                    Log.e("Response: ", "> " + json);

                    JSONArray th = null;
                    try {
                        th = json.getJSONArray("th");
                        for (int x = 0; x < th.length(); x++) {
                            JSONObject catObj11 = th.getJSONObject(x);
                            classid = catObj11.getString(TAG_CLASSID);
                            Toast.makeText(Donationrequistion.this, "classid"+classid, Toast.LENGTH_SHORT).show();
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
                                class_id = String.valueOf(position+1);
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
                            //Toast.makeText(Donationrequistion.this, "bookid"+bookid, Toast.LENGTH_SHORT).show();
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
                                book_id = String.valueOf(position+1);
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

                    paramss.add(new  BasicNameValuePair("requisition_by",requisition_by));
                    paramss.add(new  BasicNameValuePair("approved_by","1"));
                    paramss.add(new  BasicNameValuePair("approved_date",approved_date));
                    paramss.add(new  BasicNameValuePair("invoice_no","0"));
                    paramss.add(new  BasicNameValuePair("type","0"));
                    paramss.add(new  BasicNameValuePair("requisition_status","0"));
                    paramss.add(new BasicNameValuePair("date",date));
                    paramss.add(new BasicNameValuePair("division_id",division_id));
                    paramss.add(new BasicNameValuePair("jonal_id",jonal_id));
                    paramss.add(new BasicNameValuePair("district_id",district_id));
                    paramss.add(new BasicNameValuePair("thana_id",thana_id));
                    paramss.add(new  BasicNameValuePair("college_id",college_id));
                    paramss.add(new  BasicNameValuePair("teacher_id",teacher_id));
                    paramss.add(new  BasicNameValuePair("department_id",department_id));
                    paramss.add(new  BasicNameValuePair("class_id",class_id));
                    paramss.add(new  BasicNameValuePair("student_quantity",student_quantity));
                    paramss.add(new  BasicNameValuePair("transfer_student_quantity",transfer_student_quantity));
                    paramss.add(new  BasicNameValuePair("possible_book",possible_book));
                    paramss.add(new BasicNameValuePair("duration", duration));
                    paramss.add(new  BasicNameValuePair("transfer_possible_book",transfer_possible_book));
                    paramss.add(new  BasicNameValuePair("book_id",book_id));
                    paramss.add(new  BasicNameValuePair("money_amount",money_amount));
                    paramss.add(new  BasicNameValuePair("transfer_money_amount",transfer_money_amount));
                    paramss.add(new  BasicNameValuePair("distribution_id","0"));
                    paramss.add(new  BasicNameValuePair("status","1"));

                    Log.d("ouput","class"+cid+"teacher"+teacherid+"subject"+subjectid+"class"+classid+"studentno"+nostudent+"possiblebook"+possbook+"onudan"+onudanamount+"id"+bookid+"amount"+amountk);
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