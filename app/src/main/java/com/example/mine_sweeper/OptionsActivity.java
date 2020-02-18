package com.example.mine_sweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import Model.GameLogic;
import  Model.Options;

import Model.Options;

public class OptionsActivity extends AppCompatActivity {
   private final Options opt = Options.getInstance();
   private final HighScores highScores = HighScores.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toast.makeText(OptionsActivity.this,"Select Options", Toast.LENGTH_SHORT).show();
        Toast.makeText(OptionsActivity.this,"Select Options", Toast.LENGTH_SHORT).show();
        createRadioButtons();
        Button btnReset = findViewById(R.id.resetBtn);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highScores.resetHighScores();
            }
        });
        
        saveData();

    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(highScores.highscores);
        editor.putString("task list", json);
        editor.apply();
    }



    private void createRadioButtons() {

        RadioGroup group_board_size = (RadioGroup) findViewById(R.id.radioGroup_board_size);

        RadioGroup group_mines = (RadioGroup) findViewById(R.id.radioGroup_num_mines);

       int[] rows =  getResources().getIntArray(R.array.num_board_rows);
       int[] cols = getResources().getIntArray(R.array.num_board_cols);

       int[] mines = getResources().getIntArray(R.array.num_of_mines);

        for(int i = 0; i < rows.length; i++){

            final int row_value = rows[i];
            final int col_value = cols[i];

            opt.setChosen_board_size(i);

            RadioButton button = new RadioButton(this);
            button.setText("" + row_value +" rows x" + col_value+ " colunms" );
            button.setTextColor(getApplication().getResources().getColor(R.color.colorAccent)); //TAKE DEFAULT COLOR
            group_board_size.addView(button);
            if( i == 0)
            {
                button.setChecked(true);
            }
            button.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v)
                {

                    opt.setRows(row_value);
                    opt.setCols(col_value);

                }
            });

        }

        for(int i = 0; i < mines.length; i++)
        {
            final int mine_value = mines[i];
            opt.setChosen_mine_size(i);
            RadioButton button = new RadioButton(this);
            button.setText("" + mine_value + " mines");
            button.setTextColor(getApplication().getResources().getColor(R.color.colorAccent)); //TAKE DEFAULT COLOR
            group_mines.addView(button);

            if( i == 0)
            {
                button.setChecked(true);
            }
            button.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v)
                {

                    opt.setMines(mine_value);

                }
            });
        }
    }

    public static Intent makeOptionsIntent(Context c){
        Intent intent = new Intent(c, OptionsActivity.class);
        return intent;
    }
}
