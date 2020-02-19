/*
    Highscores class. Uses singleton and keeps an array to store highscores.
 */
package Model;

public class HighScores {
    private final int NUM_OF_MINE_OPTIONS = 4;
    private final int NUM_OF_BOARD_CONFIGURATIONS = 3;
    private static HighScores highscore;
    public int numberOfGamesPlayed;
    public int[][] highscores;

    public static HighScores getInstance(){
        if(highscore == null){
            highscore = new HighScores();
        }
        return highscore;
    }
    private HighScores(){
        numberOfGamesPlayed = 0;
        highscores = new int[NUM_OF_BOARD_CONFIGURATIONS][NUM_OF_MINE_OPTIONS];
        for(int i =0; i < NUM_OF_BOARD_CONFIGURATIONS; i++){
            for(int j = 0; j < NUM_OF_MINE_OPTIONS; j++){
                highscores[i][j] = 1000;
            }
        }
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
                highscores[i][j] = 1000;
            }
        }
        numberOfGamesPlayed = 0;
    }

    public void incrementGamesPlayed(){
        numberOfGamesPlayed++;
    }
}
