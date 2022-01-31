/*
Authors: Edward Dong and Angelina Chen
Class name: MainGame.java
Description: This is our main program. The board game is located in this class, and the user plays the game here.
Score, board, and GUI logistics are all located in this file.
 */

//import necessary packages
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class MainGame extends JPanel implements KeyListener{
    //declare default grid dimensions as 4, the variable will change if user has chosen an option
    int gridSize = 4;
    //make a new grid with the given dimensions
    MainGrid game = new MainGrid(gridSize);
    //allows the board to be displayed
    String gameGrid = game.toString();
    static MainGame newGame = new MainGame();
    //declare title of window
    JFrame frame = new JFrame("2048");
    int score = 0;

    /**
     * this method returns the board
     * @return the board
     */
    public static MainGame instance()
    {
        return newGame;
    }

    //displays the main game when true
    public void showGUI(boolean visible){
        //adds key listener so user can use keys
        frame.addKeyListener(newGame);
        //sets basic operations for GUI to work
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(newGame);
        frame.setSize(575, 575);
        frame.setVisible(visible);
        //makes new grid visible
        game = new MainGrid(gridSize);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                score = game.getScore();
                game.SaveScore();
            }
        });
    }

    /**
     * does nothing
     * @param e the KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * determines what key the user has pressed and calls the method for that key, allowing them to move the tiles and play the game
     * @param e the KeyEvent
     */
    @Override
    public void keyPressed( KeyEvent e )
    {
        //'w' goes up
        if ( e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP )
        {
            game.upKey();
            game.getNumsOnGrid();
            gameGrid = game.toString();
            frame.repaint();
        }
        //'s' goes down
        else if ( e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN )
        {
            game.downKey();
            game.getNumsOnGrid();
            gameGrid = game.toString();
            frame.repaint();
        }
        //'a' goes left
        else if ( e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT )
        {
            game.leftKey();
            game.getNumsOnGrid();
            gameGrid = game.toString();
            frame.repaint();
        }
        //'d' goes right
        else if ( e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT )
        {
            game.rightKey();
            game.getNumsOnGrid();
            gameGrid = game.toString();
            frame.repaint();
        }
        //Enter key starts/restarts the game
        else if ( e.getKeyCode() == KeyEvent.VK_ENTER )
        {
            game = new MainGrid(gridSize);
            game.getNumsOnGrid();
            game.getNumsOnGrid();
            frame.repaint();
        }
        //Escape goes back to main menu
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            score = MainGame.instance().game.points;
            game.SaveScore();
            frame.dispose();
            new MainMenu();
        }
    }

    /**
     * does nothing
     * @param e the KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Updates the board every time tile is moved. It also tells indicates when the game is over for the user.
     * Along with that, displays words to direct user on what to do, including displaying score.
     * @param g the graphics parameter
     */
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        //display info for user to read
        g2.drawString( "Score: " + game.getScore(), 200 - gridSize * String.valueOf( game.getScore() ).length(), 40 );
        g2.drawString( "Highest Tile: " + game.setHighestValue(), 280 - gridSize * String.valueOf( game.setHighestValue() ).length(), 40 );
        g2.drawString( "Press Enter to Start", 150, 400 );
        g2.drawString( "Use 'wasd' or Arrow Keys to move", 150, 350 );
        //prompt the user to restart if board is full
        if ( game.refreshBoard() ) {
            g2.drawString("Press Enter to restart", 150, 450);
            frame.repaint();
        }
        //repaints the board
        g2.setColor( Color.gray );
        g2.fillRect( 140, 50, gridSize*50+(gridSize+1)*10, gridSize*50+(gridSize+1)*10 );
        for ( int i = 0; i < gridSize; i++ )
        {
            for ( int j = 0; j < gridSize; j++ )
            {
                drawTiles( g, game.grid[i][j], j * 60 + 150, i * 60 + 60 );
            }
        }
        //all tiles display game over if user does not have anymore moves
        if ( game.gameOver() )
        {
            g2.setColor( Color.gray );
            g2.fillRect( 140, 50, gridSize*50+(gridSize+1)*10, gridSize*50+(gridSize+1)*10 );
            for ( int i = 0; i < gridSize; i++ )
            {
                for ( int j = 0; j < gridSize; j++ )
                {
                    g2.setColor( Color.RED );
                    g2.fillRoundRect( j * 60 + 150, i * 60 + 60, 50, 50, 5, 5 );
                    g2.setColor( Color.black );
                    g.drawString( "GAME", j * 60 + 160, i * 60 + 75 );
                    g.drawString( "OVER", j * 60 + 160, i * 60 + 95 );
                }
            }
        }
    }

    /**
     * This method draws each individual tile onto the grid
     * @param g the graphics
     * @param square each individual tile
     * @param x the x-coordinate for where the tile is located
     * @param y the y-coordinate for where the tile is located
     */
    public void drawTiles( Graphics g, Squares square, int x, int y ) {
        int tileValue = square.getValue();
        int length = String.valueOf(tileValue).length();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.lightGray);
        g2.fillRoundRect(x, y, 50, 50, 5, 5);
        g2.setColor(Color.black);
        if (tileValue > 0) {
            g2.setColor(square.getColour());
            g2.fillRoundRect(x, y, 50, 50, 5, 5);
            g2.setColor(Color.black);
            g.drawString("" + tileValue, x + 25 - 3 * length, y + 25);
        }
    }

}
