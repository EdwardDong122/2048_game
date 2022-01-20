/*
* Authors: Edward Dong and Angelina Chen
* Class Name: MainGame.java
* Description of class: Sets up the game board, score, and keys that are needed to play the game
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* calls and adds other classes to the main game
*/
public class MainGame extends JPanel implements KeyListener
{
    // declare variables
    MainGrid grid = new MainGrid();

    static MainGame newGame = new MainGame();

    static JFrame frame = new JFrame("2048");

    String gameGrid = grid.toString();
    
/* prints out score, basic instructions and clears the board when the game is over
*/

    public void paint(Graphics a)
    {
        super.paint(a);
        Graphics2D a2 = (Graphics2D)a;
        a2.drawString( "2048", 400, 60 );
        a2.drawString( "Score: " + grid.getScore(), 200 - 4 * String.valueOf( grid.getScore() ).length(), 40 );
        a2.drawString( "Highest Tile: " + grid.getHighestValue(), 280 - 4 * String.valueOf( grid.getHighestValue() ).length(), 40 );
        a2.drawString( "Press Enter to Start", 210, 315 );
        a2.drawString( "Use 'wasd' or Arrow Keys to move", 250, 500 );


        if ( grid.gameOver() )
        {
            a2.setColor( Color.gray );
            a2.fillRect( 140, 50, 250, 250 );
            for ( int i = 0; i < 4; i++ )
            {
                for ( int j = 0; j < 4; j++ )
                {
                    a2.setColor( Color.RED );
                    a2.fillRoundRect( j * 60 + 150, i * 60 + 60, 50, 50, 5, 5 );
                    a2.setColor( Color.black );
                    a.drawString( "GAME", j * 60 + 160, i * 60 + 75 );
                    a.drawString( "OVER", j * 60 + 160, i * 60 + 95 );
                }
            }
        }
    }

/* sets the size of the GUI
*/
    public static void GUI()
    {
        // KeyListener receives keyboard events (keystrokes)
        frame.addKeyListener(newGame);
        frame.getContentPane().add(newGame);
        frame.setSize( 850, 600 );
        frame.setVisible( true );
    }

/* performs an action when a key is clicked
*/
    @Override
    public void keyPressed( KeyEvent e )

    {
        // when it gets a keystorke, an action happens and changes the grid
        if ( e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP )
        {
            grid.upKey();
            grid.getNumsOnGrid();
            gameGrid = grid.toString();
            frame.repaint();
        }
        else if ( e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN )
        {
            grid.downKey();
            grid.getNumsOnGrid();
            gameGrid = grid.toString();
            frame.repaint();
        }
        else if ( e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT )
        {
            grid.leftKey();
            grid.getNumsOnGrid();
            gameGrid = grid.toString();
            frame.repaint();
        }
        else if ( e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT )
        {
            grid.rightKey();
            grid.getNumsOnGrid();
            gameGrid = grid.toString();
            frame.repaint();
        }
        else if ( e.getKeyCode() == KeyEvent.VK_ENTER )
        {
            grid = new MainGrid();
            grid.getNumsOnGrid();
            grid.getNumsOnGrid();
            frame.repaint();
        }
    }
/* draws the tiles, board and colours it
*/
    public void drawTiles( Graphics a, Squares square, int x, int y )
    {
        int tileValue = square.getValue();
        int length = String.valueOf( tileValue ).length();
        Graphics2D a2 = (Graphics2D)a;
        a2.setColor( Color.lightGray );
        a2.fillRoundRect( x, y, 50, 50, 5, 5 );
        a2.setColor( Color.black );
        if ( tileValue > 0 )
        {
            a2.setColor(square.getColor());
            a2.fillRoundRect( x, y, 50, 50, 5, 5 );
            a2.setColor( Color.black );
            a.drawString( "" + tileValue, x + 25 - 3 * length, y + 25 );
        }
    }
    
// main method
    public static void main( String[] args )
    {
        GUI();
    }

}
