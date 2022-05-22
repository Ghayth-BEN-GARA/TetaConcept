package com.example.tetaconcept.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tetaconcept.R;
import com.google.android.material.textfield.TextInputEditText;

public class SaisirTempsActivity extends AppCompatActivity {
    private ImageView retour;
    private TextView tempsErreur;
    private TextInputEditText time;
    private AppCompatButton confirmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisir_temps);

        retour = (ImageView) findViewById(R.id.back);
        tempsErreur = (TextView) findViewById(R.id.temps_erreur);
        time = (TextInputEditText) findViewById(R.id.time_input);
        confirmer = (AppCompatButton) findViewById(R.id.demarrer_temps);

        setInputChanged(time);
        gererClick();
    }

    @Override
    public void onBackPressed() {
        ouvrirHomeActivity();
    }

    public void ouvrirHomeActivity(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_to_right,R.anim.stay);
    }

    public void gererClick(){
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validerFormulaireSaisieTemps();
            }
        });
    }

    public boolean tempsIsEmpty(String temps){
        return temps.matches("");
    }

    public void validerFormulaireSaisieTemps(){
        if(tempsIsEmpty(time.getText().toString())){
            tempsErreur.setText("Les secondes sont obligatoires..");
            time.requestFocus();
        }

        else{
            tempsErreur.setText(null);
            clearFocus();
        }
    }

    public void setInputChanged(TextInputEditText time){
        time.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tempsErreur.setText(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void clearFocus(){
        time.clearFocus();
    }
}