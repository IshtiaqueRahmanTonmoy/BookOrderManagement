package info.androidhive.snackbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.MovementMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddnewexpenseActivity extends AppCompatActivity {

    Spinner spinner;
    private ArrayList<String> expenselist;
    String distancefr,distancet,nameofvehic,distancekm,type;
    EditText distancefrom,distanceto,nameofvehicle,cost,distanceinkm,mobilerent,Transportcost,Compensations,packetwithdraw,othercost,total;
    Button submit;
    int costs=0,mobrent=0,compensations=0,transportcost=0;
    String costsstring,mobrentstring,compensationsstring,transportcoststring,packetwithdrawstring,othercoststring,totalstring,startjournstring,endjourneystring,totaljourneystring,personalusestring,officestring,kmrentstring;
    int totals=0;
    int byvalue=0;
    int packetwidthdraw=0;
    int othercosts=0;
    int packetdrawal=0;
    private static final String TAG_SUCCESS = "success";
    int calculateusedkm = 0,calculatesumkm2 = 0,distotal = 0,personaluse = 0,officeuse = 0,kmrent = 0;
    JSONParser jsonParser;
    EditText input,input1,input2,input3,input4,input5;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addnewexpense);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //jsonParser = new JSONParser();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        distancefrom = (EditText) findViewById(R.id.Destinationfrom);
        distanceto = (EditText) findViewById(R.id.Destinationto);
        nameofvehicle = (EditText) findViewById(R.id.Vehiclename);
        cost = (EditText) findViewById(R.id.cost);
        distanceinkm = (EditText) findViewById(R.id.Distanceinkm);
        mobilerent = (EditText) findViewById(R.id.Mobilerent);
        Compensations = (EditText) findViewById(R.id.Compensation);
        Transportcost = (EditText) findViewById(R.id.Transportcost);
        packetwithdraw = (EditText) findViewById(R.id.Packetwithdraw);
        othercost = (EditText) findViewById(R.id.Othercost);
        total = (EditText) findViewById(R.id.Totalamount);
        spinner = (Spinner) findViewById(R.id.expensetype);
        submit = (Button) findViewById(R.id.button1);
        spinner = (Spinner) findViewById(R.id.expensetype);

        expenselist = new ArrayList<String>();
        expenselist.add("মোটর সাইকেল ছাড়া");
        expenselist.add("কোম্পানির মোটর সাইকেল");
        expenselist.add("নিজের মোটর সাইকেল");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(AddnewexpenseActivity.this, android.R.layout.simple_spinner_item, expenselist);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);


        spinner.setSelection(Adapter.NO_SELECTION, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                type = parent.getSelectedItem().toString();

                AlertDialog.Builder alert = new AlertDialog.Builder(AddnewexpenseActivity.this);

                LinearLayout layout = new LinearLayout(AddnewexpenseActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                alert.setTitle("Title");
                alert.setMessage("Message");

// Set an EditText view to get user input
                input = new EditText(AddnewexpenseActivity.this);
                input.setHint("Start Journey km..");
                layout.addView(input);

                //alert.setView(input);

                input1 = new EditText(AddnewexpenseActivity.this);
                input1.setHint("finishing journey km..");
                layout.addView(input1);
                //alert.setView(input1);

                input2 = new EditText(AddnewexpenseActivity.this);
                input2.setHint("Total km..");
                layout.addView(input2);

                input3 = new EditText(AddnewexpenseActivity.this);
                input3.setHint("Personal use..");
                layout.addView(input3);

                input4 = new EditText(AddnewexpenseActivity.this);
                input4.setHint("Office use..");
                layout.addView(input4);

                input5 = new EditText(AddnewexpenseActivity.this);
                input5.setHint("Km rate..");
                layout.addView(input5);
                //alert.setView(input2);

                input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        //int value = Integer.parseInt(input.getText().toString());
                        //calculateusedkm = 0 - value;
                        calculateusedkm = 0 - Integer.parseInt(input.getText().toString());
                        input2.setText(""+calculateusedkm);
                        //Toast.makeText(getApplicationContext(),"chanded text"+calculateusedkm,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                input1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        calculatesumkm2 = Integer.parseInt(input1.getText().toString()) - Integer.parseInt(input.getText().toString());
                        input2.setText(""+calculatesumkm2);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                input3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        personaluse = Integer.parseInt(input3.getText().toString()) - Integer.parseInt(input2.getText().toString());
                        input4.setText(""+personaluse);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                cost.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        costs = Integer.parseInt(cost.getText().toString());
                        Transportcost.setText(""+costs);
                        giveMeSum(costs);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        //Toast.makeText(getApplicationContext(),""+costs+""+mobrent,Toast.LENGTH_LONG).show();
                    }
                });

                mobilerent.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        mobrent = Integer.parseInt(mobilerent.getText().toString());
                        giveMeSum(mobrent);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });



                Compensations.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        compensations = Integer.parseInt(Compensations.getText().toString());
                        giveMeSum(compensations);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                packetwithdraw.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        packetwidthdraw = Integer.parseInt(packetwithdraw.getText().toString());
                        giveMeSum(packetwidthdraw);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                othercost.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        othercosts = Integer.parseInt(othercost.getText().toString());
                        giveMeSum(othercosts);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });


                alert.setView(layout);

                alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Do something with value!
                        //passTheValues(calculateusedkm,calculatesumkm2,distotal,personaluse,officeuse,kmrent);
                        //Toast.makeText(getApplicationContext(),"transport"+transportcost+"costs"+costs,Toast.LENGTH_LONG).show();

                        distancefr = distancefrom.getText().toString();
                        distancet = distanceto.getText().toString();
                        nameofvehic = nameofvehicle.getText().toString();
                        transportcoststring = Transportcost.getText().toString();
                        distancekm = distanceinkm.getText().toString();
                        costsstring = cost.getText().toString();
                        mobrentstring = mobilerent.getText().toString();
                        compensationsstring = Compensations.getText().toString();
                        packetwithdrawstring = packetwithdraw.getText().toString();
                        othercoststring = othercost.getText().toString();
                        totalstring = total.getText().toString();

                        Log.d("v", String.valueOf(costs+mobrent+compensations+packetwidthdraw+othercosts));
                        Log.d("ouputs","from"+distancefr+"to"+distancet+"nameofvehic"+nameofvehic+"transportcost"+transportcoststring+"distancekm"+distancekm+"cost"+costsstring+"mobile"+mobrentstring+"compnesation"+compensations+"packet"+packetdrawal+"othercost"+othercosts+"total"+totalstring);
                        Log.d("values","startjourfr"+String.valueOf(calculateusedkm)+"endjourneykm"+String.valueOf(calculatesumkm2)+"totaljourkm"+String.valueOf(distotal)+"personalusekm"+String.valueOf(personaluse)+"officeuse"+String.valueOf(officeuse)+"kmerent"+String.valueOf(kmrent));
            /*
               startjournstring = input.getText().toString();
               endjourneystring = input1.getText().toString();
               totaljourneystring = input2.getText().toString();
               personalusestring = input3.getText().toString();
               officestring = input4.getText().toString();
               kmrentstring = input5.getText().toString();
             */

                        //Toast.makeText(getApplicationContext(),"costs"+distancefr+"mobrent"+distancet+"total"+totals, Toast.LENGTH_LONG).show();

                        new InsertintoDB().execute();



                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(AddnewexpenseActivity.this, ""+date, Toast.LENGTH_SHORT).show();
               //Log.d("date string", String.valueOf(date));


                //Toast.makeText(getApplicationContext(),"called", Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),"called", Toast.LENGTH_LONG).show();
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

    /*
    private void passTheValues(final int calculateusedkm, final int calculatesumkm2, final int distotal, final int personaluse, final int officeuse, final int kmrent) {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"sum"+String.valueOf(calculateusedkm)+"sum2",Toast.LENGTH_LONG).show();
                new InsertintoDB().execute();
                //Log.d("distance from",String.valueOf(calculateusedkm)+"distance to"+String.valueOf(calculatesumkm2));
            }
        });
    }
   */


    private void giveMeSum(int value) {
        total.setText(""+value);
    }

    private class InsertintoDB extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Date today = new Date();
                    date = new java.sql.Date(today.getTime());

                    jsonParser = new JSONParser();
                    String urlsubmidata = "http://dik-pl.com/dikpl/expense.php";
                    List<NameValuePair> paramss = new ArrayList<NameValuePair>();

                    paramss.add(new BasicNameValuePair("destination_from",distancefr));
                    paramss.add(new  BasicNameValuePair("destination_to",distancet));
                    paramss.add(new  BasicNameValuePair("vicle_name",nameofvehic));
                    paramss.add(new  BasicNameValuePair("transport_fee",transportcoststring));
                    paramss.add(new  BasicNameValuePair("approximate_kilometer",distancekm));
                    paramss.add(new  BasicNameValuePair("journey_cost",costsstring));
                    paramss.add(new  BasicNameValuePair("mobile_cost",mobrentstring));
                    paramss.add(new  BasicNameValuePair("entertainment_cost",costsstring));
                    paramss.add(new BasicNameValuePair("packet_lift",packetwithdrawstring));
                    paramss.add(new  BasicNameValuePair("others_cost",othercoststring));
                    paramss.add(new  BasicNameValuePair("total_cost",totalstring));
                    paramss.add(new  BasicNameValuePair("expense_type",type));

                    paramss.add(new  BasicNameValuePair("start_journey_km",input.getText().toString()));
                    paramss.add(new  BasicNameValuePair("end_journey_km",input1.getText().toString()));
                    paramss.add(new  BasicNameValuePair("total_journey_km",input2.getText().toString()));
                    paramss.add(new  BasicNameValuePair("personal_use_km",input3.getText().toString()));
                    paramss.add(new  BasicNameValuePair("office_use_km",input4.getText().toString()));
                    paramss.add(new  BasicNameValuePair("kilomitter_rate",input5.getText().toString()));
                    paramss.add(new  BasicNameValuePair("date",String.valueOf(date)));

                    JSONObject json = jsonParser.makeHttpRequest(urlsubmidata, "POST", paramss);
                    //Log.d("Create Response", json.toString());
                    try {

                        int success = json.getInt(TAG_SUCCESS);

                        // Toast.makeText(RegistrationActivity.this, "" + success, Toast.LENGTH_SHORT).show();
                        if (success == 1) {
                            // successfully created product

                            AddnewexpenseActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(AddnewexpenseActivity.this.getBaseContext(), "Insert into expense completed..", Toast.LENGTH_LONG).show();
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