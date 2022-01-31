/*
Authors: Edward Dong and Angelina Chen
Class name: MainGrid.java
Description: This is class covers most of the logic, setting the numbers on the board,
declaring key functions, getting the score, and more.
 */

//declare necessary package
import java.io.*;

public class MainGrid {
    //declare variables
    int points = 0;
    int grids;
    int border = 0;

    //declare the board
    public Squares[][]grid;

    /**
     * A getter method that returns the score when called
     * @return the score
     */
    public int getScore() {
        return points;

    }

    /**
     * Declares the size of the board using user input, and makes the board
     * @param gridSize the dimension of the board
     */
    public MainGrid(int gridSize){
        //declare size of grid
        grids = gridSize;
        grid = new Squares[gridSize][gridSize];
        //form random values in each tile (0,2,4)
        for ( int i = 0; i < grid.length; i++ )
        {
            for ( int j = 0; j < grid[i].length; j++ )
            {
                grid[i][j] = new Squares();
            }
        }
    }

    /**
     * Turns the value into a string, making it able to be shown in the GUI
     * @return str, the value of each square in the board as string to be displayed onto the user's window
     */
    public String toString()
    {
        //declare string
        String str = "";
        for (Squares[] squares : grid) {
            for (Squares square : squares) {
                str += square.toString() + " ";
            }
            str += "\n";
        }
        return str;
    }

    /**
     * This method determines whether the user is still able to move, if they cannot move the game is over, returning true.
     * @return boolean true or false depending on if game is over
     */
    public boolean gameOver() {
        int count = 0;
        //checks each tile using a nested for loop
        for ( int i = 0; i < grids; i++ ){
            for ( int j = 0; j < grid[i].length; j++ ){
                //only checks if the tile has a value other than 0
                if ( grid[i][j].getValue() > 0 ){
                    //checks if the tile is a corner tile
                    if (i == 0 && j == 0){
                        if ( grid[i][j].getValue() != grid[i + 1][j].getValue() && grid[i][j].getValue() != grid[i][j + 1].getValue()){
                            count++;
                        }
                    }
                    else if ( i == 0 && j == grids-1 ) {
                        if ( grid[i][j].getValue() != grid[i + 1][j].getValue() && grid[i][j].getValue() != grid[i][j - 1].getValue()){
                            count++;
                        }
                    }
                    else if ( i == grids-1 && j == grids-1 ){
                        if ( grid[i][j].getValue() != grid[i - 1][j].getValue() && grid[i][j].getValue() != grid[i][j - 1].getValue()){
                            count++;
                        }
                    }
                    else if ( i == grids-1 && j == 0 ){
                        if ( grid[i][j].getValue() != grid[i - 1][j].getValue() && grid[i][j].getValue() != grid[i][j + 1].getValue()){
                            count++;
                        }
                    }
                    //checks if the tile is an edge tile
                    else if ( i == 0 ){
                        if ( grid[i][j].getValue() != grid[i + 1][j].getValue() && grid[i][j].getValue() != grid[i][j + 1].getValue()
                                && grid[i][j].getValue() != grid[i][j - 1].getValue() ){
                            count++;
                        }
                    } else if ( i == grids-1 ) {
                        if ( grid[i][j].getValue() != grid[i - 1][j].getValue() && grid[i][j].getValue() != grid[i][j + 1].getValue()
                                && grid[i][j].getValue() != grid[i][j - 1].getValue() ){
                            count++;
                        }
                    } else if ( j == 0  ) {
                        if ( grid[i][j].getValue() != grid[i][j + 1].getValue() && grid[i][j].getValue() != grid[i - 1][j].getValue()
                                && grid[i][j].getValue() != grid[i + 1][j].getValue() ){
                            count++;
                        }
                    } else if ( j == grids-1 ){
                        if ( grid[i][j].getValue() != grid[i][j - 1].getValue() && grid[i][j].getValue() != grid[i - 1][j].getValue()
                                && grid[i][j].getValue() != grid[i + 1][j].getValue() ){
                            count++;
                        }
                    }
                    //checks if the tile is a tile located in the middle of the grid
                    else {
                        if ( grid[i][j].getValue() != grid[i][j - 1].getValue() && grid[i][j].getValue() != grid[i][j + 1].getValue()
                                && grid[i][j].getValue() != grid[i - 1][j].getValue()
                                && grid[i][j].getValue() != grid[i + 1][j].getValue() ){
                            count++;
                        }
                    }
                }
            }
        }
        //if all tiles are unable to move, game over is true
        if(count == grids*grids) {
            //save points
            SaveScore();
        }
        return count == grids*grids;
    }

    public void SaveScore()
    {
        try {
            FileWriter file2 =  new FileWriter("UsernamesScore.txt", true); // Sets filewriter to true so it doesnt overwrite
            BufferedWriter writer2 = new BufferedWriter(file2); // Declares reader for Scores.txt

            writer2.write(points + "\n"); // Writes score
            writer2.close();
        } catch (IOException iox) { //  Catches exception
            System.out.println("ERROR!");
        }
    }

    /**
     * This method declares the values of each square in the grid, either 0,2, or 4
     */
    public void getNumsOnGrid()
    {
        boolean emptySquare = true;
        while (emptySquare)
        {
            int row = (int)( Math.random() * grids );
            int column = (int)( Math.random() * grids    );
            double x = Math.random();
            if (grid[row][column].getValue() == 0 ){
                if ( x < 0.2 )
                {
                    grid[row][column] = new Squares(4);
                }
                else
                {
                    grid[row][column] = new Squares(2);
                }
                emptySquare = false;
            }
        }
    }

    /**
     * Determines if 2 tiles are of the same value, and will combine them, also moving them in the direction that was requested (vertical)
     * Additionally, updates the score
     * @param row the row the tile is on
     * @param column the column the tile is on
     * @param direction the direction the tile is moving
     */
    private void getVertical( int row, int column, String direction )
    {
        Squares startingLocation = grid[border][column];
        Squares compare = grid[row][column];
        if ( startingLocation.getValue() == 0 || startingLocation.getValue() == compare.getValue() ) {
            if ( row > border || ( direction.equals( "downKey" ) && ( row < border ) ) ) {
                int addScore = startingLocation.getValue() + compare.getValue();
                if ( startingLocation.getValue() != 0 ) {
                    points += addScore;
                }
                startingLocation.setValue( addScore );
                compare.setValue( 0 );
            }
        }
        else
        {
            if ( direction.equals( "downKey" ) ) {
                border--;
            }
            else {
                border++;
            }
            getVertical( row, column, direction );
        }
    }

    /**
     * Determines if 2 tiles are of the same value, and will combine them, also moving them in the direction that was requested (horizontal)
     * Additionally, updates the score
     * @param row the row the tile is on
     * @param column the column the tile is on
     * @param direction the direction the tile is moving
     */
    private void getHorizontal( int row, int column, String direction ) {
        //declare the variables
        Squares startingLocation = grid[row][border];
        Squares compare = grid[row][column];
        if ( startingLocation.getValue() == 0 || startingLocation.getValue() == compare.getValue() ) {
            if ( column > border || ( direction.equals("rightKey") && ( column < border ) ) ) {
                int addScore = startingLocation.getValue() + compare.getValue();
                if ( startingLocation.getValue() != 0 ) {
                    points += addScore;
                }
                startingLocation.setValue( addScore );
                compare.setValue(0);
            }
        }
        else {
            if (direction.equals("rightKey")) {
                border--;
            }
            else {
                border++;
            }
            getHorizontal( row, column, direction );
        }
    }

    /**
     * Method for the movement of the left key
     */
    public void leftKey() {
        for ( int i = 0; i < grids; i++ ) {
            border = 0;
            for ( int j = 0; j < grids; j++ ) {
                if ( grid[i][j].getValue() != 0 ) {
                    if ( border <= j ) {
                        getHorizontal( i, j, "leftKey" );
                    }
                }
            }
        }
    }

    /**
     * Method for the movement of the right key
     */
    public void rightKey() {
        for ( int i = 0; i < grids; i++ ) {
            border = ( grids - 1 );
            for ( int j = ( grids - 1 ); j >= 0; j-- ) {
                if ( grid[i][j].getValue() != 0 ) {
                    if ( border >= j ) {
                        getHorizontal( i, j, "rightKey" );
                    }
                }
            }
        }
    }

    /**
     * Method for the movement of the down key
     */
    public void downKey() {
        for ( int i = 0; i < grids; i++ ) {
            border = ( grids - 1 );
            for ( int j = grids - 1; j >= 0; j-- ) {
                if ( grid[j][i].getValue() != 0 ) {
                    if ( border >= j ) {
                        getVertical( j, i, "downKey" );
                    }
                }
            }
        }
    }

    /**
     * Method for the movement of the up key
     */
    public void upKey() {
        for ( int i = 0; i < grids; i++ ) {
            border = 0;
            for ( int j = 0; j < grids; j++ ) {
                if ( grid[j][i].getValue() != 0 ) {
                    if ( border <= j ) {
                        getVertical( j, i, "upKey" );
                    }
                }
            }
        }
    }

    /**
     * This method determines the highest value of all the squares on the board and returns it
     * @return highest, the highest value of all the squares on the board
     */
    public int setHighestValue() {
        int highest = grid[0][0].getValue();
        //checks each square and replaces the "highest" variable each time there is a higher value
        for (Squares[] squares : grid) {
            for (Squares square : squares) {
                if (square.getValue() > highest) {
                    highest = square.getValue();
                }
            }
        }
        return highest;
    }

    /**
     * This method determines if the board is full of tiles greater than 0, and if so,
     * may prompt the user to restart
     * @return boolean value
     */
    public boolean refreshBoard() {
        int count = 0;
        for (Squares[] squares : grid) {
            for (Squares square : squares) {
                if (square.getValue() > 0) {
                    count++;
                }
            }
        }
        return count==grids*grids;
    }
}
