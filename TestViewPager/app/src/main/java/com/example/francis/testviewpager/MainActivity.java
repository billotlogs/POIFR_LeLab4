package com.example.francis.testviewpager;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Callback{
    ViewPagerAdapter viewPagerAdapter;
    Bundle bundle = new Bundle();
    TextView marde, svp;
    EcranPrincipal asd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vpPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        asd = (EcranPrincipal)viewPagerAdapter.getItem(0);

    }

    public void Click(View view){
        marde.setText("Titre test");
        svp.setText("Test Écran 1");
    }

    @Override
    public void setFuck(TextView fuck, TextView svp) {
        marde = fuck;
        this.svp = svp;
    }
}
