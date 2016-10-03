package info.androidhive.snackbar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
//import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

public class BookRequistionActivity extends AppCompatActivity {

    Spinner spinner,spinner2;
    Button plus,minus;
    TextView quantity;

    static int num=0;

    String[] SPINNERLIST = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_requistion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //spinner = (Spinner) findViewById(R.id.Spinner);
        //spinner2 = (Spinner) findViewById(R.id.stateSpinner);
        plus = (Button) findViewById(R.id.buttonplus);
        minus = (Button) findViewById(R.id.buttonminus);
        quantity = (TextView) findViewById(R.id.quantity_text_view);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);

        MaterialEditText commentEditText = (MaterialEditText) findViewById(R.id.commentsEdit);

        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.spinner11);
        MaterialBetterSpinner materialDesignSpinner1 = (MaterialBetterSpinner)
                findViewById(R.id.Spinner);
        MaterialBetterSpinner materialDesignSpinner2 = (MaterialBetterSpinner)
                findViewById(R.id.stateSpinner);

        materialDesignSpinner.setAdapter(arrayAdapter);
        materialDesignSpinner1.setAdapter(arrayAdapter);
        materialDesignSpinner2.setAdapter(arrayAdapter);

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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
