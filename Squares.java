import java.awt.*;

public class Squares {
    int value;
    Color colour;
    public void setColor()
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
    public Color getColor() {
        this.setColor();
        return colour;
    }
    public Squares()
    {
        value = 0;
    }

    public Squares(int n)
    {
        value = n;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public String toString()
    {
        return "" + value;
    }
}