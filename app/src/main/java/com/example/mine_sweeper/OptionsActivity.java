package com.example.mine_sweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {
   private final Options opt = Options.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toast.makeText(OptionsActivity.this,"Select Options", Toast.LENGTH_SHORT).show();
        Toast.makeText(OptionsActivity.this,"Select Options", Toast.LENGTH_SHORT).show();
        createRadioButtons();

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
                    Toast.makeText(OptionsActivity.this, "You clicked "+ row_value +" x "+col_value +" Board size", Toast.LENGTH_SHORT)
                            .show();
                    opt.setRows(row_value);
                    opt.setCols(col_value);

                }
            });

        }

        for(int i = 0; i < mines.length; i++)
        {
            final int mine_value = mines[i];
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
                    Toast.makeText(OptionsActivity.this, "You clicked "+ mine_value+" mines", Toast.LENGTH_SHORT)
                            .show();
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
