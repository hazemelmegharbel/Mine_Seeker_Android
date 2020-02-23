/*
    Options activity. Also sets the radio buttons and allows user to reset highscores.
 */
package com.example.mine_sweeper.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mine_sweeper.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import com.example.mine_sweeper.Model.HighScores;
import  com.example.mine_sweeper.Model.Options;

public class OptionsActivity extends AppCompatActivity {
    public static final String SHARED_PREFERENCES = "shared preferences";
    public static final String TASK_LIST = "task list";
    private final Options opt = Options.getInstance();
   private HighScores highScores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        loadData();

        Toast.makeText(OptionsActivity.this,"Select Options", Toast.LENGTH_SHORT).show();
        createRadioButtons();
        Button btnReset = findViewById(R.id.resetBtn);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highScores.resetHighScores();
                saveData();
                TextView tv = findViewById(R.id.numberOfGames);
                tv.setText("Number of Games : " + highScores.numberOfGamesPlayed);
            }
        });

        TextView v = findViewById(R.id.numberOfGames);
        v.setText("Number of Games : " + highScores.numberOfGamesPlayed);

    }

    public static Intent makeOptionsIntent(Context c){
        Intent intent = new Intent(c, OptionsActivity.class);
        return intent;
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(TASK_LIST, null);
        Type type = new TypeToken<int[][]>(){}.getType();
        if(highScores == null){
            highScores = HighScores.getInstance();
        }

    }


    private void createRadioButtons() {

        RadioGroup group_board_size = (RadioGroup) findViewById(R.id.radioGroup_board_size);

        RadioGroup group_mines = (RadioGroup) findViewById(R.id.radioGroup_num_mines);

        int[] rows =  getResources().getIntArray(R.array.num_board_rows);
        int[] cols = getResources().getIntArray(R.array.num_board_cols);

        int[] mines = getResources().getIntArray(R.array.num_of_mines);

        for(int i = 0; i < rows.length; i++){

            final int chosen = i;
            final int row_value = rows[i];
            final int col_value = cols[i];


            RadioButton button = new RadioButton(this);
            button.setText("" + row_value +" rows x" + col_value+ " colunms" );
            button.setTextColor(getApplication().getResources().getColor(R.color.colorAccent)); //TAKE DEFAULT COLOR
            group_board_size.addView(button);

            button.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v)
                {
                    opt.setChosen_board_size(chosen);
                    opt.setRows(row_value);
                    opt.setCols(col_value);

                }
            });

        }

        for(int i = 0; i < mines.length; i++)
        {
            final int chosen = i;
            final int mine_value = mines[i];

            RadioButton button = new RadioButton(this);
            button.setText("" + mine_value + " mines");
            button.setTextColor(getApplication().getResources().getColor(R.color.colorAccent)); //TAKE DEFAULT COLOR
            group_mines.addView(button);

            button.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v)
                {
                    opt.setChosen_mine_size(chosen);
                    opt.setMines(mine_value);

                }
            });
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(highScores.highscores);
        editor.putString(TASK_LIST, json);
        editor.apply();
    }

}
