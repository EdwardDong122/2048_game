public class MainGrid {
    public Squares[][] grid;

    int points = 0;
    int grids;
    int border = 0;

    public Squares[][] getGrid() {
        return grid;
    }

    public int getScore() {
        return points;
    }
    public MainGrid(){

    }
    public MainGrid(int gridSize) {
        grid = new Squares[gridSize][gridSize];
        for ( int i = 0; i < grid.length; i++ )
        {
            for ( int j = 0; j < grid[i].length; j++ )
            {
                grid[i][j] = new Squares();
            }
        }
    }

    public String toString()
    {
        String str = "";
        for (Squares[] squares : grid) {
            for (Squares square : squares) {
                str += square.toString() + " ";
            }
            str += "\n";
        }
        return str;
    }


    public boolean gameOver() {
        int count = 0;
        for ( int i = 0; i < grid.length; i++ ){
            for ( int j = 0; j < grid[i].length; j++ ){
                if ( grid[i][j].getValue() > 0 ){
                    if (i == 0 && j == 0){
                        if ( grid[i][j].getValue() != grid[i + 1][j].getValue() && grid[i][j].getValue() != grid[i][j + 1].getValue()){
                            count++;
                        }
                    }
                    else if ( i == 0 && j == 3 ) {
                        if ( grid[i][j].getValue() != grid[i + 1][j].getValue() && grid[i][j].getValue() != grid[i][j - 1].getValue()){
                            count++;
                        }
                    }
                    else if ( i == 3 && j == 3 ){
                        if ( grid[i][j].getValue() != grid[i - 1][j].getValue() && grid[i][j].getValue() != grid[i][j - 1].getValue()){
                            count++;
                        }
                    }
                    else if ( i == 3 && j == 0 ){
                        if ( grid[i][j].getValue() != grid[i - 1][j].getValue() && grid[i][j].getValue() != grid[i][j + 1].getValue()){
                            count++;
                        }
                    }
                    else if ( i == 0 && ( j == 1 || j == 2 ) ){
                        if ( grid[i][j].getValue() != grid[i + 1][j].getValue() && grid[i][j].getValue() != grid[i][j + 1].getValue()
                                && grid[i][j].getValue() != grid[i][j - 1].getValue() ){
                            count++;
                        }
                    }
                    else if ( i == 3 && ( j == 1 || j == 2 ) ) {
                        if ( grid[i][j].getValue() != grid[i - 1][j].getValue() && grid[i][j].getValue() != grid[i][j + 1].getValue()
                                && grid[i][j].getValue() != grid[i][j - 1].getValue() ){
                            count++;
                        }
                    }
                    else if ( j == 0 && ( i == 1 || i == 2 ) ) {
                        if ( grid[i][j].getValue() != grid[i][j + 1].getValue() && grid[i][j].getValue() != grid[i - 1][j].getValue()
                                && grid[i][j].getValue() != grid[i + 1][j].getValue() ){
                            count++;
                        }
                    }
                    else if ( j == 3 && ( i == 1 || i == 2 ) ){
                        if ( grid[i][j].getValue() != grid[i][j - 1].getValue() && grid[i][j].getValue() != grid[i - 1][j].getValue()
                                && grid[i][j].getValue() != grid[i + 1][j].getValue() ){
                            count++;
                        }
                    }
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
        return count == 16;
    }
    public void getNumsOnGrid()
    {
        boolean emptySquare = true;
        while (emptySquare)
        {
            int row = (int)( Math.random() * 4 );
            int column = (int)( Math.random() * 4 );
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

    private void getHorizontal( int row, int column, String direction ) {
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

    public int getHighestValue() {
        int highest = grid[0][0].getValue();
        for (Squares[] squares : grid) {
            for (Squares square : squares) {
                if (square.getValue() > highest) {
                    highest = square.getValue();
                }
            }
        }
        return highest;
    }
}
