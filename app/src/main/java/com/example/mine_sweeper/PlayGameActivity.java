package com.example.mine_sweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
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

        populateButtons();
    }

    private void populateButtons() {
        TableLayout table= findViewById(R.id.tableForButtons);
        for(int r=0;r<opt.getRows();r++)
        {
            TableRow tablerow=new TableRow(this);

            table.addView(tablerow);

            tablerow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));

            for(int c=0;c<opt.getCols();c++)
            {
                Button btn=new Button(this);

                btn.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                btn.setPadding(0,0,0,0);

                btn.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        gridButtonClicked();
                    }
                });

                tablerow.addView(btn);
            }
        }
    }

    private void gridButtonClicked() {
    }

    public static Intent makeLaunchIntent(Context c) {
        Intent intent = new Intent(c, PlayGameActivity.class);
        return intent;
    }
}
