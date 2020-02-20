
/*
    Play game activity. Allows for saving and loading highscores. Utitlizes the game logic class.
    http://www.orangefreesounds.com/hmm-sound-effect/
    Tutorials used : https://www.youtube.com/watch?v=jcliHGR3CHo&t=256s
    https://www.youtube.com/watch?v=C_Ka7cKwXW0&t=23s
 */
package com.example.mine_sweeper;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import Model.GameLogic;
import Model.HighScores;
import Model.Options;

public class PlayGameActivity extends AppCompatActivity {
    public static final String SHARED_PREFERENCES = "shared preferences";
    public static final String TASK_LIST = "task list";
    private final Options opt= Options.getInstance();
    private GameLogic game;
    private Button buttons [][];
    private int found_mines = 0;
    private int numOfScans = 0;
    private SoundPool soundPool;
    private int hmmSound, successSound;
    private int chosen_board_size;
    private int chosen_mine_size;
    private HighScores highScores;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);


        loadData();

        Toast.makeText(PlayGameActivity.this,"Begin Playing !", Toast.LENGTH_SHORT).show();
        int rows = opt.getRows();
        int cols = opt.getCols();
        int mines = opt.getMines();
        TextView RemainingMines= (TextView) findViewById(R.id.NumMinesLeft);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder().
                    setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION).
                    setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();

            soundPool = new SoundPool.Builder().
                    setMaxStreams(2).
                    setAudioAttributes(audioAttributes).build();
        }
        else{
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        }

        hmmSound = soundPool.load(this,R.raw.hmm,1);
        successSound = soundPool.load(this,R.raw.success,1);

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
                RemainingMines.setText("Remaining Mines: "+ 6);
            }

        }


        chosen_board_size = opt.getChosen_board_size();
        chosen_mine_size = opt.getChosen_mine_size();

        TextView HighScores= (TextView) findViewById(R.id.highScores);
        int highscoreValue = highScores.GetHighScore(chosen_board_size ,chosen_mine_size );
        if(highscoreValue == 1000){
            HighScores.setText("High Score : ");
        }
        else{
            HighScores.setText("High Score : " + highscoreValue);

        }

        RemainingMines.setText("Remaining Mines: " + opt.getMines());
        buttons= new Button[opt.getRows()][opt.getCols()];

        game = new GameLogic(opt.getRows(), opt.getCols(), opt.getMines());

        game.setUp();
        populateButtons();
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

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(highScores.highscores);
        editor.putString(TASK_LIST, json);
        editor.apply();
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


        boolean already_played = game.check_if_already_played(row,col);
        boolean mine_presence = game.check_for_mine(row,col);

        TextView FoundMines= (TextView) findViewById(R.id.NumMinesLeft);
        TextView ScansUsed= (TextView) findViewById(R.id.NumScans);
        lockButtonSize();


        if(already_played == false) {
            if(mine_presence){

                int newWidth=btn.getWidth();
                int newHeight= btn.getHeight();
                Bitmap originalBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.sea_mine);
                Bitmap scaledBitmap=Bitmap.createScaledBitmap(originalBitmap,newWidth,newHeight,true);
                Resources resource=getResources();

                btn.setBackground(new BitmapDrawable(resource, scaledBitmap));

                found_mines++;
                FoundMines.setText("Remaining Mines: "+(opt.getMines()-found_mines));
                checkGame();
                updateBoard(row,col);
                soundPool.play(successSound,1,1,0,0,1);
            }
            else{
                int surrounding_mines = game.scan(row,col);
                btn.setText("" + surrounding_mines);
                numOfScans++;
                soundPool.play(hmmSound,1,1,0,0,1);
                ScansUsed.setText("Scans Used: "+numOfScans);
            }

            game.place_item(row,col);

        }
        else{

            if(mine_presence == true){
                int surrounding_mines = game.scan(row,col);
                btn.setText(""+surrounding_mines);
                numOfScans++;
                soundPool.play(hmmSound,1,1,0,0,1);
                ScansUsed.setText("Scans Used: "+numOfScans);
            }
        }

    }

    private void lockButtonSize() {
        for(int i=0;i<opt.getRows();i++)
        {
            for(int j=0;j<opt.getCols();j++)
            {
                Button btnTmp= buttons[i][j];

                int width=btnTmp.getWidth();
                btnTmp.setMinWidth(width);
                btnTmp.setMaxHeight(width);

                int height=btnTmp.getHeight();
                btnTmp.setMinHeight(height);
                btnTmp.setMaxHeight(height);

            }
        }
    }

    private void updateBoard(int row, int col) {
        for (int row_y = 0; row_y < opt.getRows(); row_y++) {
            if (row_y == row) {
                continue;
            }
            Button btn = buttons[row_y][col];
            boolean already_played = game.check_if_already_played(row_y, col);
            if (already_played == true) {
                String buttontext = btn.getText().toString();

                boolean mine_presence = game.check_for_mine(row_y, col);
                {
                    if (buttontext.length() > 0) {
                        int num_of_current_hidden_mines = Integer.parseInt(buttontext);
                        int new_num = num_of_current_hidden_mines - 1;
                        btn.setText("" + new_num);
                    }
                }
            }
        }
            for (int col_x = 0; col_x < opt.getCols(); col_x++) {
                if (col_x == col) {
                    continue;
                }
                Button btn = buttons[row][col_x];
                boolean already_played = game.check_if_already_played(row, col_x);
                if (already_played == true) {
                    String buttontext = btn.getText().toString();

                    // Check to see if the position is a mine
                    boolean mine_presence = game.check_for_mine(row, col_x);
                    if (buttontext.length() > 0) {
                        int num_of_current_hidden_mines = Integer.parseInt(buttontext);
                        int new_num = num_of_current_hidden_mines - 1;
                        btn.setText("" + new_num);
                    }

                }
            }
    }


    private void checkGame(){
        if(found_mines == opt.getMines()){
            FragmentManager manager = getSupportFragmentManager();
            CongratsFragment dialog = new CongratsFragment();

            if(numOfScans < highScores.GetHighScore(chosen_board_size ,chosen_mine_size )){
                highScores.SetNewHighScore(chosen_board_size,chosen_mine_size,numOfScans);
            }
            saveData();
            Toast.makeText(this, "New High Score " + highScores.GetHighScore(chosen_board_size, chosen_mine_size), Toast.LENGTH_LONG).show();
            highScores.incrementGamesPlayed();
            dialog.show(manager,"MessageDialog");


        }

    }

    public static Intent makeLaunchIntent(Context c) {
        Intent intent = new Intent(c, PlayGameActivity.class);
        return intent;
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }
}
