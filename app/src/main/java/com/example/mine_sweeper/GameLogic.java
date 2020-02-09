package com.example.mine_sweeper;

public class GameLogic {

    private int rows;
    private int cols;
    private int mines;

    private int[][] randomMines;

    private int[][] currentStatus;

    public GameLogic(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    private void setUp(){

        currentStatus = new int[rows][cols];
        randomMines =  new int[rows][cols];

        int currentPlacedMines = 0;

        while(currentPlacedMines < mines){
            int min = 0;
            int max = rows*cols;

            int indexRandomMine = (int) (Math.random() * ((max - min) + 1)) + min;

            int index_x = ((int) indexRandomMine) % cols;
            double second_index_div = Math.floor(indexRandomMine/cols);

            int index_y = (int) second_index_div;

            int valueChecker = randomMines[index_y][index_x];
            if(valueChecker == 0){
                valueChecker = 1;
                currentPlacedMines++;
            }
        }
    }

    public void place_item(int row, int col){
        currentStatus[row][col] = 1;
    }

    public boolean check_for_mine(int row, int col){
        boolean to_be_returned = false;
        if(randomMines[row][col] == 1)
        {
            to_be_returned = true;
        }
        return to_be_returned;
    }

    public int scan(int row, int col){
        int to_be_returned = 0;
        for(int col_x = 0; col_x < cols; col++){
            int value = randomMines[row][col_x];
            if(value == 1)
            {
                to_be_returned++;
            }
        }

        for(int row_y = 0; row_y < rows; row++){
            int value = randomMines[row_y][col];
            if(value == 1){
                to_be_returned++;
            }
        }

        return to_be_returned;
    }

}
