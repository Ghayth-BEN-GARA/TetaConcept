package com.example.tetaconcept.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tetaconcept.R;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    private TextView copiright;
    private AppCompatButton temps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        copiright = (TextView) findViewById(R.id.copyright_app);
        temps = (AppCompatButton) findViewById(R.id.btn_temps);

        setCopyrightText();
        gererClick();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),getString(R.string.retour_impossible),Toast.LENGTH_LONG).show();
    }

    public int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public void setCopyrightText(){
        copiright.setText(getString(R.string.copiright1) + " " + getCurrentYear() + getString(R.string.copiright2));
    }

    public void gererClick(){
        temps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirSaisirTempsClickActivity();
            }
        });
    }

    public void ouvrirSaisirTempsClickActivity(){
        Intent intent = new Intent(getApplicationContext(), SaisirTempsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left,R.anim.stay);
    }
}