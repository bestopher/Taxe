package com.example.christophe.taxe.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.christophe.taxe.R;

public class VueDensembleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vue_densemble);

        final Intent rcv = this.getIntent();
        final long montantjournalier=300;
        final long montantmensuel = 600;
        final long montantannuel=1000;
        final long montantpatente= 400;
        Button buttonjournalier= (Button ) findViewById(R.id.journalier);

        final String show_user=rcv.getStringExtra("user");
        Intent intentb = new Intent(VueDensembleActivity.this, CollecteActivity.class);
        intentb.putExtra("user1", show_user);
        buttonjournalier.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Selected: ", Toast.LENGTH_LONG).show();
                Intent intentb = new Intent(VueDensembleActivity.this, CollecteActivity.class);
                intentb.putExtra("user1", show_user+"_"+montantjournalier);
                //intentb.putExtra("montant", montantjournalier);
                intentb.putExtra("extension", ".jr");
                //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici SecondActivite
                startActivity(intentb);
                Toast.makeText(getApplicationContext(), "Selected: " + show_user+"/"+ montantjournalier, Toast.LENGTH_LONG).show();


            }
        });


        Button buttonmensuel= (Button ) findViewById(R.id.mensuel);

        buttonmensuel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Selected: ", Toast.LENGTH_LONG).show();
                Intent intentb = new Intent(VueDensembleActivity.this, CollecteActivity.class);
                intentb.putExtra("user1", show_user+"_"+montantmensuel);
                //intentb.putExtra("montant", montantmensuel);
                intentb.putExtra("extension", ".ms");
                //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici SecondActivite
                startActivity(intentb);
                Toast.makeText(getApplicationContext(), "Selected: " + show_user+"/"+montantmensuel, Toast.LENGTH_LONG).show();


            }
        });

        Button buttonannuel= (Button ) findViewById(R.id.annuel);

        buttonannuel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Selected: ", Toast.LENGTH_LONG).show();
                Intent intentb = new Intent(VueDensembleActivity.this, CollecteActivity.class);
                intentb.putExtra("user1", show_user+"_"+montantannuel);
                //intentb.putExtra("montant", montantannuel);
                intentb.putExtra("extension", ".an");
                //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici SecondActivite
                startActivity(intentb);
                Toast.makeText(getApplicationContext(), "Selected: " + show_user+"/"+ montantannuel, Toast.LENGTH_LONG).show();


            }
        });

        Button buttonpatente= (Button ) findViewById(R.id.patente);

        buttonpatente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Selected: ", Toast.LENGTH_LONG).show();
                Intent intentb = new Intent(VueDensembleActivity.this, CollecteActivity.class);
                intentb.putExtra("user1", show_user+"_"+montantpatente);
                //intentb.putExtra("montant", montantpatente);
                intentb.putExtra("extension", ".pt");
                //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici SecondActivite
                startActivity(intentb);
                Toast.makeText(getApplicationContext(), "Selected: " + show_user +"/"+ montantpatente, Toast.LENGTH_LONG).show();


            }
        });
    }
}
