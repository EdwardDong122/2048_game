import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MainGame extends JPanel implements KeyListener
{
    MainGrid grid = new MainGrid();

    static MainGame newGame = new MainGame();

    static JFrame frame = new JFrame("2048");

    String gameGrid = grid.toString();


    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawString( "2048", 400, 60 );
        g2.drawString( "Score: " + grid.getScore(), 200 - 4 * String.valueOf( grid.getScore() ).length(), 40 );
        g2.drawString( "Highest Tile: " + grid.getHighestValue(), 280 - 4 * String.valueOf( grid.getHighestValue() ).length(), 40 );
        g2.drawString( "Press Enter to Start", 210, 315 );
        g2.drawString( "Use 'wasd' or Arrow Keys to move", 250, 500 );


        if ( grid.gameOver() )
        {
            g2.setColor( Color.gray );
            g2.fillRect( 140, 50, 250, 250 );
            for ( int i = 0; i < 4; i++ )
            {
                for ( int j = 0; j < 4; j++ )
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


    public static void GUI()
    {
        frame.addKeyListener(newGame);
        frame.getContentPane().add(newGame);
        frame.setSize( 850, 600 );
        frame.setVisible( true );
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed( KeyEvent e )

    {
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

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void drawTiles( Graphics g, Squares square, int x, int y )
    {
        int tileValue = square.getValue();
        int length = String.valueOf( tileValue ).length();
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor( Color.lightGray );
        g2.fillRoundRect( x, y, 50, 50, 5, 5 );
        g2.setColor( Color.black );
        if ( tileValue > 0 )
        {
            g2.setColor(square.getColor());
            g2.fillRoundRect( x, y, 50, 50, 5, 5 );
            g2.setColor( Color.black );
            g.drawString( "" + tileValue, x + 25 - 3 * length, y + 25 );
        }
    }

    public static void main( String[] args )
    {
        GUI();
    }

}