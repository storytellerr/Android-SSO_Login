package com.example.storytellerr.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);

        TextView name=findViewById(R.id.name);
        TextView email=findViewById(R.id.email);
        TextView age=findViewById(R.id.age);

        Intent intent= getIntent();

        name.setText("Name:"+intent.getStringExtra("name"));
        email.setText("Email:"+intent.getStringExtra("email"));
        age.setText("Age:"+intent.getStringExtra("age"));
    }

}
