package com.example.mine_sweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toast.makeText(OptionsActivity.this,"Select Options", Toast.LENGTH_SHORT).show();

        createRadioButtons();

    }

    private void createRadioButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup_board_size);

       int[] rows =  getResources().getIntArray(R.array.num_board_rows);
       int[] cols = getResources().getIntArray(R.array.num_board_cols);

        for(int i = 0; i < rows.length; i++){
            int row_value = rows[i];
            int col_value = cols[i];

            RadioButton button = new RadioButton(this);
            button.setText("" + row_value +" rows x" + col_value+ " colunms" );

            group.addView(button);

        }
    }

    public static Intent makeOptionsIntent(Context c){
        Intent intent = new Intent(c, OptionsActivity.class);
        return intent;
    }
}
