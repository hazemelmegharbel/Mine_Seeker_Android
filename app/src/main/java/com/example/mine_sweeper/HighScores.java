package com.example.mine_sweeper;

public class HighScores {
    private final int NUM_OF_MINE_OPTIONS = 3;
    private final int NUM_OF_BOARD_CONFIGURATIONS = 2;
    private static HighScores highscore;
    public int[][] highscores;

    public static HighScores getInstance(){
        if(highscore == null){
            highscore = new HighScores();
        }
        return highscore;
    }
    private HighScores(){
        highscores = new int[NUM_OF_BOARD_CONFIGURATIONS][NUM_OF_MINE_OPTIONS];
    }

    public int GetHighScore(int board_size, int mine_size){
        return highscores[board_size][mine_size];
    }

    public void SetNewHighScore(int board_size, int mine_size, int new_score){
        highscores[board_size][mine_size] = new_score;
    }

    public void resetHighScores(){
        for(int i = 0; i < NUM_OF_BOARD_CONFIGURATIONS; i++){
            for(int j = 0; j< NUM_OF_MINE_OPTIONS; j++){
                highscores[i][j] = 0;
            }
        }
    }
}
