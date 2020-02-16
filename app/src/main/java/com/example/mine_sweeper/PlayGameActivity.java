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
    private GameLogic game;
    private Button buttons [][];
    private int found_mines = 0;
    private int numOfMoves = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        Toast.makeText(PlayGameActivity.this,"Begin Playing !", Toast.LENGTH_SHORT).show();
        int rows= opt.getRows();
        int cols =opt.getCols();
        int mines=opt.getMines();

        if(rows==0 || cols==0 || rows == 0)
        {
            if(rows == 0){
                opt.setRows(4);
            }
            if(cols == 0){
                opt.setCols(6);
            }
            if(mines == 0){
                opt.setMines(6);
            }
            Toast.makeText(PlayGameActivity.this, "you have selected "+ opt.getRows()+ " x "+ opt.getCols()+ " Board size and "+ opt.getMines()+" mines", Toast.LENGTH_SHORT)
                    .show();

        }



        buttons= new Button[opt.getRows()][opt.getCols()];

        game = new GameLogic(opt.getRows(), opt.getCols(), opt.getMines());

        game.setUp();
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
                final int finalR = r;
                final int finalC = c;
                final Button btn=new Button(this);

                btn.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                btn.setPadding(0,0,0,0);

                btn.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                       // Toast.makeText(PlayGameActivity.this,"Button clicked is row " + finalR + " column " + finalC, Toast.LENGTH_SHORT).show();
                        gridButtonClicked(finalR,finalC);
                        }
                });

                tablerow.addView(btn);
                buttons[r][c] = btn;
            }
        }
    }

    private void gridButtonClicked(int row, int col) {
        Button btn = buttons[row][col];

        // TODO Check to see if corresponding row and column is a mine

        //TODO Check to see if the place has already been filled in yet

        boolean already_played = game.check_if_already_played(row,col);
        boolean mine_presence = game.check_for_mine(row,col);


        if(already_played == false) {
            if(mine_presence){
                btn.setText("Mine");
                found_mines++;
                checkGame();
            }
            else{
                int surrounding_mines = game.scan(row,col);
                btn.setText("" + surrounding_mines);
                numOfMoves++;
            }

            game.place_item(row,col);

        }
        else{

            if(mine_presence == true){
                int surrounding_mines = game.scan(row,col);
                Toast.makeText(PlayGameActivity.this,"Surrounding mines are " + surrounding_mines, Toast.LENGTH_SHORT).show();
                numOfMoves++;
            }
        }




    }

    private void checkGame(){
        if(found_mines == opt.getMines()){
            Toast.makeText(PlayGameActivity.this,"Congratulations, game is over !", Toast.LENGTH_SHORT).show();
            Toast.makeText(PlayGameActivity.this,"You took " + numOfMoves + " moves", Toast.LENGTH_SHORT).show();
        }

    }

    public static Intent makeLaunchIntent(Context c) {
        Intent intent = new Intent(c, PlayGameActivity.class);
        return intent;
    }
}
