package com.example.tetaconcept.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tetaconcept.Models.Session;
import com.example.tetaconcept.R;
import java.util.Calendar;

public class SplashScreenActivity extends AppCompatActivity {
    private TextView copiright, slogan;
    private ImageView logoApplication;
    private Animation animationTop,animationBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        copiright = (TextView) findViewById(R.id.copyright_app);
        slogan = (TextView) findViewById(R.id.slogan_app);
        logoApplication = (ImageView) findViewById(R.id.logo_app);

        setCopyrightText();
        setAnimationComposant();
        setActionAfterSplash();
    }

    public int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public void setCopyrightText(){
        copiright.setText(getString(R.string.copiright1) + " " + getCurrentYear() + getString(R.string.copiright2));
    }

    public void setAnimationComposant(){
        animationBottom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.top_to_bottom);
        animationTop = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bottom_to_top);
        logoApplication.setAnimation(animationBottom);
        slogan.setAnimation(animationTop);
    }

    public void setActionAfterSplash(){
        Session session = new Session(getApplicationContext());
        session.initialiserSharedPreferences();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(session.getEtatApplication() == true){
                    ouvrirHomeActivity();
                }

                else{
                    session.saveEtatApplication(true);
                    ouvrirPresentationActivity();
                }
            }
        },3000);
    }

    public void ouvrirPresentationActivity(){
        Intent intent = new Intent(getApplicationContext(), PresentationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left,R.anim.stay);
    }

    public void ouvrirHomeActivity(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left,R.anim.stay);
    }
}