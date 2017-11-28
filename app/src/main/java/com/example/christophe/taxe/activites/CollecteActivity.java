package com.example.christophe.taxe.activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.christophe.taxe.R;
import com.example.christophe.taxe.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CollecteActivity extends AppCompatActivity {

    private Button marche;
    private EditText id_client;
    private EditText marchertext;

    private String Show_msg1;
    private String Show_montant;
    private  String ex;

    public CollecteActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collecte);

        final Intent rcv = this.getIntent();

        final Utils utils=new Utils();
        id_client=(EditText)findViewById(R.id.client_id) ;
        marchertext=(EditText)findViewById(R.id.marcher_id);

        Show_msg1=rcv.getStringExtra("user1");
        Show_montant=rcv.getStringExtra("montant1");
        ex=rcv.getStringExtra("extension");
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        marche=(Button) findViewById(R.id.id_collecte);

        marche.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                String nom_marche=marchertext.getText().toString();
                String num_client=id_client.getText().toString();
                String devId=utils.getAndroidId(getApplication().getApplicationContext());
                String extras=Show_msg1 +"_"+nom_marche+"_"+num_client+ex;//+"_"+Show_montant;
                //utils.createFlyFile(devId,ex, extras);

                Toast.makeText(getApplicationContext(), ""+ dateFormat.format(new Date())+"_"+ extras, Toast.LENGTH_LONG).show();

            }
        });
    }
}
