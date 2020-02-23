/*
    Main Menu Activity. Sets the whole project in motion.
 */
package com.example.mine_sweeper.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mine_sweeper.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toast.makeText(MainMenuActivity.this,"Welcome to the Main Menu", Toast.LENGTH_SHORT).show();

        Button btn_game= (Button) findViewById(R.id.btnPlayGame);
        btn_game.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent play_the_game = PlayGameActivity.makeLaunchIntent(MainMenuActivity.this);
                startActivity((play_the_game));
            }
        });

        Button btn_options = (Button) findViewById(R.id.btnOptionsMenu);
        btn_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent options_menu_intent = OptionsActivity.makeOptionsIntent(MainMenuActivity.this);
                startActivity(options_menu_intent);
            }
        });

        Button btn_help = (Button) findViewById(R.id.btnHelpScreen);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help_menu_intent = HelpActivity.makeHelpIntent(MainMenuActivity.this);
                startActivity(help_menu_intent);

            }

        });


    }

    public static Intent makeLaunchIntent(Context c) {
        Intent intent = new Intent(c, MainMenuActivity.class);
        return intent;
    }


}
