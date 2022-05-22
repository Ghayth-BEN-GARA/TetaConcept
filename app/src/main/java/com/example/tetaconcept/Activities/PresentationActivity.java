package com.example.tetaconcept.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import com.example.tetaconcept.Adapters.PresentationAdapter;
import com.example.tetaconcept.Models.PresentationItem;
import com.example.tetaconcept.R;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class PresentationActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout dots;
    private AppCompatButton start, next, before, skip;
    private PresentationAdapter presentationAdapter;
    private List<PresentationItem> presentationItems;
    private Animation show, gone;
    private Boolean testEcran = false;
    private int curentItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        viewPager = (ViewPager) findViewById(R.id.view_pager_presentation);
        dots = (TabLayout) findViewById(R.id.dots);
        start = (AppCompatButton) findViewById(R.id.commencer_presentation);
        skip = (AppCompatButton) findViewById(R.id.skip);
        next = (AppCompatButton) findViewById(R.id.next);
        before = (AppCompatButton) findViewById(R.id.before);

        initialisationListePresentation();
        remplirListePresentation();
        attacherAdapterAvecItems();
        configurerIndicateurs();
        indicateurListener();
        gestionClickButton();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),getString(R.string.retour_impossible),Toast.LENGTH_LONG).show();
    }

    public void initialisationListePresentation(){
        presentationItems = new ArrayList<>();
    }

    public void remplirListePresentation(){
        presentationItems.add(new PresentationItem(R.drawable.temps,getString(R.string.temps),getString(R.string.desc_temps)));
        presentationItems.add(new PresentationItem(R.drawable.notification,getString(R.string.notificatuons),getString(R.string.desc_notifications)));
    }

    public void attacherAdapterAvecItems(){
        presentationAdapter = new PresentationAdapter(getApplicationContext(),presentationItems);
        viewPager.setAdapter(presentationAdapter);
    }

    public void configurerIndicateurs(){
        dots.setupWithViewPager(viewPager);
    }

    public void indicateurListener(){
        dots.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(testEcran == false){
                    if(tab.getPosition() == 0) {
                        before.setVisibility(View.INVISIBLE);
                        testEcran = false;
                    }

                    else if(tab.getPosition() == 1) {
                        before.setVisibility(View.VISIBLE);
                        start.setVisibility(View.VISIBLE);
                        skip.setVisibility(View.INVISIBLE);
                        next.setVisibility(View.INVISIBLE);
                        setAnimationShowButton();
                        testEcran = true;
                    }
                }

                else{
                    if(tab.getPosition() == 0) {
                        skip.setVisibility(View.VISIBLE);
                        before.setVisibility(View.INVISIBLE);
                        next.setVisibility(View.VISIBLE);
                        start.setVisibility(View.INVISIBLE);
                        setAnimationgGoneButton();
                        testEcran = true;
                    }

                    else if(tab.getPosition() == 1) {
                        before.setVisibility(View.VISIBLE);
                        start.setVisibility(View.VISIBLE);
                        skip.setVisibility(View.INVISIBLE);
                        next.setVisibility(View.INVISIBLE);
                        setAnimationShowButton();
                        testEcran = true;
                    }
                }
                curentItemPosition = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setAnimationShowButton(){
        show = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.show);
        start.setAnimation(show);
    }

    public void setAnimationgGoneButton(){
        gone = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.gone);
        start.setAnimation(gone);
    }

    public void gestionClickButton(){
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirHomeActivity();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirHomeActivity();
            }
        });

        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToPrevious();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNext();
            }
        });
    }

    public void backToPrevious(){
        viewPager.setCurrentItem(curentItemPosition - 1);
    }

    public void goToNext(){
        viewPager.setCurrentItem(curentItemPosition + 1);
    }

    public void ouvrirHomeActivity(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left,R.anim.stay);
    }
}