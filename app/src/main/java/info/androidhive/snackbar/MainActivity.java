package info.androidhive.snackbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CoordinatorLayout coordinatorLayout;
    private Button btnBookRequistion, btnBookDistribution, btnDonationRequistion,btnDontaionDistribution,btnExpense;
    private FloatingActionButton fab;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null) {
            email =(String) b.get("mobile");
            Toast.makeText(MainActivity.this, ""+email, Toast.LENGTH_SHORT).show();

        }

        btnBookRequistion = (Button) findViewById(R.id.btnRequistion);
        btnBookDistribution = (Button) findViewById(R.id.btnDistribute);
        btnDonationRequistion = (Button) findViewById(R.id.btnDonation);
        btnDontaionDistribution = (Button) findViewById(R.id.btnDonationdistribution);
        btnExpense = (Button) findViewById(R.id.btnExpense);

        btnBookRequistion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar snackbar = Snackbar
                 //       .make(coordinatorLayout, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);

                //snackbar.show();
                Intent intent = new Intent(MainActivity.this,BookRequistionActivity.class);
                intent.putExtra("mobile",email);
                startActivity(intent);

            }
        });

        btnBookDistribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,BookDistributionActivity.class);
                startActivity(intent);

            }
        });

        btnDonationRequistion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Donationrequistion.class);
                intent.putExtra("mobile",email);
                startActivity(intent);
            }
        });

        btnDontaionDistribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DonationdistributionActivity.class);
                startActivity(intent);
            }
        });

        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddnewexpenseActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.quit)
                    .setMessage(R.string.really_quit)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //Stop the activity
                            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    })
                    .setNegativeButton(R.string.no, null)
                    .show();

            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }

    }
}



