/*
    Main Activity. Loads up the Main Menu Activity.
 */
package com.example.mine_sweeper.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.mine_sweeper.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = (Button) findViewById(R.id.btnMain_Menu);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent main_menu = MainMenuActivity.makeLaunchIntent(MainActivity.this);
                startActivity((main_menu));
            }
        });
    }


}
