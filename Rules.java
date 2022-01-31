/*
Authors: Edward Dong and Angelina Chen
Class name: Rules.java
Description: This class contains the rules of the game which will help the user learn how to play.
 */

//import necessary packages
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

//implement ActionListener
public class Rules implements ActionListener{
    //declare JLabel to store the text and display it
    public JLabel ruleLabelTitle = new JLabel();
    //declare to store the text read from the file
    public String ruleLine;
    //declare the frame
    private JFrame frame;
    public Rules() {
        //declare GUI components
        frame = new JFrame();
        JButton backButton = new JButton("Back");

        //Set location and dimensions of components
        backButton.setBounds(650,20,100,100);
        ruleLabelTitle.setBounds(525,20,500,100);

        //add action listener to the button, so it has a function
        backButton.addActionListener(this);

        //Tell program to exit when window closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set title of window
        frame.setTitle("Rules");

        //set size of window
        frame.setSize(800,325);

        //make window visible
        frame.setVisible(true);

        //reset the layout of the frame to put the components into
        frame.setLayout(null);

        //add all the components onto the frame to make them visible, and also call the method to read from the text file
        frame.add(ruleLabelTitle);
        frame.add(backButton);
        readText();
    }

    /**
     * this method declares a buffered reader which reads input from a file to write it in the main program
     * this will allow it to be displayed for the user to read
     */
    public void readText(){
        try {
            //declare reader
            BufferedReader reader = new BufferedReader(new FileReader("Rules.txt"));
            //reads through every line of the text file
            for (int i = 0; i < 10; i++) {
                //allows each line to be correctly formatted
                for (int k = -40; k <= 400; k += 40) {
                    //reads the line in the text file
                    ruleLine = reader.readLine();
                    if (k != 0) {
                        //store the text into the JLabel for it to be displayed
                        ruleLabelTitle = new JLabel(ruleLine);
                    }
                    //set the font type and font size of the JLabel
                    ruleLabelTitle.setFont(new Font("Arial", Font.PLAIN, 24));
                    //declare the dimensions of the JLabel
                    ruleLabelTitle.setBounds(20, k, 800, 100);
                    //add the JLabel onto the frame, so it is visible to the user
                    frame.add(ruleLabelTitle);

                }

            }
        }
        //handle any errors in case reader cannot read input
        catch (IOException iox) {
            System.out.println("File could not be read");
        }
    }

    /**
     * if the button is clicked, the main menu is displayed and the current window is disposed
     * @param e the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        new MainMenu();
    }
}
