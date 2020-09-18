package com.example.drawdot;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.EditText;;
import android.view.ViewGroup;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

public class AndroidDemo extends AppCompatActivity {
    private LinearLayoutCompat root;
    private final Random rand = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = (LinearLayoutCompat) findViewById(R.id.root);

        LinearLayoutCompat.LayoutParams containerParams = new LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0.0F);

        LinearLayoutCompat.LayoutParams widgetParams = new LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                1.0F);

        root = new LinearLayoutCompat(this);
        root.setOrientation(LinearLayoutCompat.VERTICAL);
        root.setBackgroundColor(Color.LTGRAY);
        root.setLayoutParams(containerParams);

        LinearLayoutCompat ll = new LinearLayoutCompat(this);
        ll.setOrientation(LinearLayoutCompat.HORIZONTAL);
        ll.setBackgroundColor(Color.GRAY);
        ll.setLayoutParams(containerParams);

        root.addView(ll);

        final EditText tb1 = new EditText(this);
        tb1.setText("Default");
        tb1.setFocusable(false);
        tb1.setLayoutParams(widgetParams);
        ll.addView(tb1);

        final EditText tb2 = new EditText(this);
        tb2.setText("Default");
        tb2.setFocusable(false);
        tb2.setLayoutParams(widgetParams);
        ll.addView(tb2);
    }
}
