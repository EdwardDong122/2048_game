/*
Authors: Edward Dong and Angelina Chen
Class name: OptionMenu.java
Description: This class allows for the user to choose a grid size, from 3x3 to 8x8.
Also has a button to return to the main menu
 */

//import necessary packages
import javax.swing.*;

public class OptionMenu {
    public static String Options() {
        //give choices for the user to choose
        String[] optionsToChoose = {"3", "4", "5", "6", "7", "8"};
        //set the variable gridSize to whichever choice the user chooses
        String gridSize = (String) JOptionPane.showInputDialog(
                null,
                "Pick a number:",
                "Choose Grid Size",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsToChoose,
                optionsToChoose[1]);

        //returns the gridSize as a string
        return gridSize;
    }
}
