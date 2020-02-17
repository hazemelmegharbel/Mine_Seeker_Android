package Model;

public class Options {
    private int rows;
    private int cols;
    private int mines;
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
}
