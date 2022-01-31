/*
Authors: Edward Dong and Angelina Chen
Class name: Squares.java
Description: This class determines the logistics of each individual square in the grid. For example, it determines
the colour of the square depending on its value, and sets the value as well.
 */
import java.awt.*;

public class Squares {
    // state variables
    int value;
    Color colour;

    /**
     * A setter method that returns the colour when called
     * @return void
     */
    public void setColour()
    {
        if ( this.getValue() == 2 )
        {
            colour = new Color(56, 192, 222);
        }
        else if ( this.getValue() == 4 )
        {
            colour = new Color(231, 184, 193);
        }
        else if ( this.getValue() == 8 )
        {
            colour = new Color(241, 137, 66);
        }
        else if ( this.getValue() == 16 )
        {
            colour = new Color(236, 206, 87);
        }
        else if ( this.getValue() == 32 )
        {
            colour = new Color(51, 155, 23);
        }
        else if ( this.getValue() == 64 )
        {
            colour = new Color(231, 68, 68);
        }
        else if ( this.getValue() == 128 )
        {
            colour = new Color(143, 192, 222);
        }
        else if ( this.getValue() == 256 )
        {
            colour = new Color(111, 97, 237);
        }
        else if ( this.getValue() == 512 )
        {
            colour = new Color(164, 84, 43);
        }
        else if ( this.getValue() == 1024 )
        {
            colour = new Color(185, 231, 172);
        }
        else
        {
            colour = new Color(255, 255, 255);
        }
    }
    /**
     * A getter method that returns the colour when called
     * @return colour
     */
    public Color getColour() {
        this.setColour();
        return colour;
    }

    /**
     * method that sets the value of the variable
     */
    public Squares()
    {
        value = 0;
    }

    /**
     * resets the value of the variable
     * @param n
     */
    public Squares(int n)
    {
        value = n;
    }

    /**
     * A getter method that returns the value when called
     * @return value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Another method that sets the value
     * @param value
     */
    public void setValue(int value)
    {
        this.value = value;
    }

    /**
     * A getter method that returns the value when called
     * @return " " + value
     */
    public String toString()
    {
        return "" + value;
    }
}
