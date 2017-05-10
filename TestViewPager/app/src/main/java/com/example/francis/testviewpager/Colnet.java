package com.example.francis.testviewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Francis on 2017-05-07.
 */

public class Colnet extends android.support.v4.app.Fragment {
    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static Colnet newInstance(int page, String title) {
        Colnet ecranPrincipal = new Colnet();
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
        View view = inflater.inflate(R.layout.test, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.fuck);
        return view;
    }
}
