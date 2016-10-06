package info.androidhive.snackbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.MovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddnewexpenseActivity extends AppCompatActivity {

    Spinner spinner;
    private ArrayList<String> expenselist;
    String distancefr,distancet,nameofvehic,distancekm,type;
    EditText distancefrom,distanceto,nameofvehicle,cost,distanceinkm,mobilerent,Transportcost,Compensations,packetwithdraw,othercost,total;
    Button submit;
    int costs =0,mobrent=0,totals=0,byvalue=0,compensations=0,packetwidthdraw=0,othercosts=0;
    JSONParser jsonParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addnewexpense);

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


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                  Toast.makeText(getApplicationContext(),""+costs+""+mobrent,Toast.LENGTH_LONG).show();
                  giveMeSum(costs);
            }

            @Override
            public void afterTextChanged(Editable s) {
                cost.removeTextChangedListener(this);
                if (costs!=Integer.parseInt(cost.getText().toString())) {

                }
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
                mobilerent.removeTextChangedListener(this);
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
                Compensations.removeTextChangedListener(this);
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
                packetwithdraw.removeTextChangedListener(this);
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
                 othercost.removeTextChangedListener(this);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distancefr = distancefrom.getText().toString();
                distancet = distanceto.getText().toString();
                nameofvehic = nameofvehicle.getText().toString();
                //costs = cost.getText().toString();
                distancekm = distanceinkm.getText().toString();
                //mobrent = mobilerent.getText().toString();
                //packetdrawal = packetwithdraw.getText().toString();
                //othercosts = othercost.getText().toString();
                //totals = total.getText().toString();

                new InsertintoDB().execute();
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

    private void giveMeSum(int value) {
        totals+=value;
        total.setText(""+totals);
    }

    private class InsertintoDB extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {


            //Toast.makeText(getApplicationContext(),"possible book"+possbook,Toast.LENGTH_LONG).show();
            //insert(cid,teacherid,subjectid,classid,nostudent,possbook,onudanamount,bookid,amountk);

            jsonParser = new JSONParser();

            String urlsubmidata = "http://192.168.0.106/dikpl/android/donation/add/";
            //String urlsubmidata = ""+url+"/";
            List<NameValuePair> paramss = new ArrayList<NameValuePair>();
          /*
            paramss.add(new BasicNameValuePair("",distancefr));
            paramss.add(new  BasicNameValuePair("",distancet));
            paramss.add(new  BasicNameValuePair("",nameofvehic));
            paramss.add(new  BasicNameValuePair("",costs));
            paramss.add(new  BasicNameValuePair("",distancekm));
            paramss.add(new  BasicNameValuePair("",mobrent));
            paramss.add(new BasicNameValuePair("", packetdrawal));
            paramss.add(new  BasicNameValuePair("",othercosts));
            paramss.add(new  BasicNameValuePair("",totals));
            paramss.add(new  BasicNameValuePair("",totals));
           */

            JSONObject json = jsonParser.makeHttpRequest(urlsubmidata,
                    "POST", paramss);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            //Toast.makeText(getApplicationContext(),"Successfully inserted..",Toast.LENGTH_LONG).show();

            // closing this screen

            return null;

        }
    }
}
