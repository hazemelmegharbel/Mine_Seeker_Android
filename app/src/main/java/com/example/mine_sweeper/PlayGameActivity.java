package com.example.mine_sweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class PlayGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
    }
    public static Intent makeLaunchIntent(Context c) {
        Intent intent = new Intent(c, PlayGameActivity.class);
        return intent;
    }
}
