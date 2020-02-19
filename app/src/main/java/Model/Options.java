package Model;

public class Options {
    private int rows;
    private int cols;
    private int mines;
    private int chosen_board_size;
    private int chosen_mine_size;
    private static Options instance;

    public static Options getInstance(){
        if(instance == null){
            instance = new Options();
        }

        return  instance;
    }


    private Options() {
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

    public void setChosen_board_size(int board_size){this.chosen_board_size = board_size;}

    public void setChosen_mine_size(int mine_size){this.chosen_mine_size = mine_size;}

    public int getChosen_board_size() {
        return chosen_board_size;
    }

    public int getChosen_mine_size() {
        return chosen_mine_size;
    }
}
