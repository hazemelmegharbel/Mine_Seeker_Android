package com.example.mine_sweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toast.makeText(HelpActivity.this,"Help", Toast.LENGTH_SHORT).show();

    }

    public static Intent makeHelpIntent(Context c){
        Intent intent = new Intent(c,HelpActivity.class);
        return intent;
    }
}


//creation of hyperlink to CMPT276 homepage was adapted from this video: https://www.youtube.com/watch?v=X0vXAe9UT7I ;