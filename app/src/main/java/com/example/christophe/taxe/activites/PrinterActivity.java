package com.example.christophe.taxe.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.christophe.taxe.R;

public class PrinterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer);



        Button buttontest= (Button ) findViewById(R.id.button2);

        buttontest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Selected: " , Toast.LENGTH_LONG).show();


            }
        });

        final Intent rcv = this.getIntent();
        Button buttondemarrer= (Button ) findViewById(R.id.button3);

        buttondemarrer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Selected: ", Toast.LENGTH_LONG).show();
                Intent intentb = new Intent(PrinterActivity.this, VueDensembleActivity.class);
                intentb.putExtra("user", rcv.getStringExtra("username"));

                startActivity(intentb);


            }
        });

    }
}
