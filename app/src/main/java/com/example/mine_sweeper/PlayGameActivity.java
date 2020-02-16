package com.example.mine_sweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class PlayGameActivity extends AppCompatActivity {
    private final Options opt= Options.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        Toast.makeText(PlayGameActivity.this,"Begin Playing !", Toast.LENGTH_SHORT).show();
        int rows=opt.getRows();
        int cols =opt.getCols();
        int mines=opt.getMines();
        if(rows==0||cols==0)
        {
            opt.setRows(4);
            opt.setCols(6);
            opt.setMines(6);
        }
        Toast.makeText(PlayGameActivity.this, "you have selected "+ opt.getRows()+ " x "+ opt.getCols()+ " Board size and "+ opt.getMines()+" mines", Toast.LENGTH_SHORT)
            .show();
        Toast.makeText(PlayGameActivity.this,"This is a dumb toast",Toast.LENGTH_SHORT).show();


    }
    public static Intent makeLaunchIntent(Context c) {
        Intent intent = new Intent(c, PlayGameActivity.class);
        return intent;
    }
}
