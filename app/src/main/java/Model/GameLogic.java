/*
    Game logic class. Maintains arrays to maintain game logic.
 */
package Model;

public class GameLogic {

    private int rows;
    private int cols;
    private int mines;

    private int[][] randomMines;

    private int[][] currentStatus;

    public GameLogic(int rows, int cols, int mines) {

        this.rows = rows;
        this.cols = cols;

        if(mines > rows * cols){
            this.mines = rows * cols;
        }
        else{
            this.mines = mines;
        }

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

    public void setUp(){

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


            if(index_x >= cols){
                index_x = 0;
            }
            if(index_y >= rows){
                index_y = 0;
            }

            int valueChecker = randomMines[index_y][index_x];
            if(valueChecker == 0){
                randomMines[index_y][index_x] = 1;;
                currentPlacedMines++;
            }
        }
    }

    public void place_item(int row, int col){
        currentStatus[row][col] = 1;
    }

    public boolean check_if_already_played(int row, int col)
    {
        boolean to_be_returned = false;
        if(currentStatus[row][col] == 1){
            to_be_returned = true;
        }
        return to_be_returned;
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
        for(int col_x = 0; col_x < cols; col_x++){
            if(col_x == col)
            {
                continue;
            }
            int value = randomMines[row][col_x];
            if(value == 1 && (currentStatus[row][col_x] == 0 ))
            {
                to_be_returned++;
            }
        }


        for(int row_y = 0; row_y < rows; row_y++){
            if(row_y == row){
                continue;
            }
            int value = randomMines[row_y][col];
            if(value == 1 && (currentStatus[row_y][col] == 0)){
                to_be_returned++;
            }
        }


        return to_be_returned;
    }

    public void print_random(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print(" " + randomMines[i][j]);
            }
            System.out.println();
        }
    }

    public void print_current_status(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print(" " + currentStatus[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("Testing");
        GameLogic game = new GameLogic(5,5,25);
        game.setUp();
//        System.out.println("Printing locations of random mines");
       game.print_random();
        System.out.println("Printing our current status");
        game.print_current_status();
        game.place_item(3,3);

        boolean is_mine = game.check_for_mine(2,2);
        if(is_mine){
            System.out.println("THERE IS A MINE HERE");
        }
        else
        {
            System.out.println("THERE IS NO MINE HERE");
        }
//        System.out.println();
//        game.print_current_status();
//        int num_of_mines = game.scan(3,3);
//        System.out.println("There are " + num_of_mines+ " mines around");

    }

}



