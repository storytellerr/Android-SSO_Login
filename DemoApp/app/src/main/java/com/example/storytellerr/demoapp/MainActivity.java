package com.example.storytellerr.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int PICK_CONTACT_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void Auth(View v) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra("Name","DEMO APP ");
        shareIntent.setType("text/plain");
        startActivityForResult(shareIntent,PICK_CONTACT_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_CONTACT_REQUEST ) {
            if(resultCode == Activity.RESULT_OK) {
                Intent in = new Intent(MainActivity.this,Home.class);
                in.putExtra("name", data.getStringExtra("name"));
                in.putExtra("email", data.getStringExtra("email"));
                in.putExtra("age", data.getStringExtra("age"));
                startActivity(in);
                finish();

            }
        }
    }

}
