package com.example.mine_sweeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button btn= (Button) findViewById(R.id.btnPlayGame);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent play_the_game = PlayGameActivity.makeLaunchIntent(MainMenuActivity.this);
                startActivity((play_the_game));
            }
        });


    }

    public static Intent makeLaunchIntent(Context c) {
        Intent intent = new Intent(c, MainMenuActivity.class);
        return intent;
    }


}
