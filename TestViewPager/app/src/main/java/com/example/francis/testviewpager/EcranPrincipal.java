package com.example.francis.testviewpager;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Francis on 2017-05-07.
 */

public class EcranPrincipal extends android.support.v4.app.Fragment {
    // Store instance variables
    private String title;
    private int page;
    TextView asd;
    Callback test;

    // newInstance constructor for creating fragment with arguments
    public static EcranPrincipal newInstance(int page, String title) {
        EcranPrincipal ecranPrincipal = new EcranPrincipal();
        Bundle args = new Bundle();
        ecranPrincipal.setArguments(args);
        return ecranPrincipal;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        test.setFuck((TextView) view.findViewById(R.id.fuck),
                     (TextView) view.findViewById(R.id.svp));

        asd = (TextView) view.findViewById(R.id.fuck);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        test = (Callback)context;
        super.onAttach(context);
    }

    public TextView svp(){
        return asd;
    }
}
