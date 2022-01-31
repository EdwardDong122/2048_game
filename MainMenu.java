/*
Authors: Edward Dong and Angelina Chen
Class Name: MainMenu.java
Description: This class codes the main menu of the program. It contains 2 buttons called "start" and "exit".
If exit is clicked, the window closes and the program is stopped. If start is clicked, the button will go to the main program
where the board/game is located.
 */

//import necessary packages
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;


//implement ActionListener to for when the user clicks buttons
public class MainMenu implements ActionListener {
    //declare GUI components to make popup window for user
    private JButton buttonStart;
    private JButton buttonExit;
    private JButton chooseSize;
    private JButton buttonRules;
    private JFrame frame;
    private JLabel titleLabel;
    private String users;

    public MainMenu(){
        //declare GUI components
        frame = new JFrame();
        titleLabel = new JLabel("MAIN MENU");
        buttonStart = new JButton("Start");
        buttonExit = new JButton("Exit");
        chooseSize = new JButton("Choose Grid Size");
        buttonRules = new JButton("Rules");
        //Tell program to exit when window closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set title of window
        frame.setTitle("Main Menu");
        //set size of window
        frame.setSize(800,550);
        //make window visible
        frame.setVisible(true);
        //reset the layout of the frame to put the components into
        frame.setLayout(null);
        //implement ActionListener into the buttons for them to function
        buttonStart.addActionListener(this);
        buttonExit.addActionListener(this);
        chooseSize.addActionListener(this);
        buttonRules.addActionListener(this);
        //position the location of the buttons on the frame
        titleLabel.setBounds(250,50,500,100);
        buttonStart.setBounds(190, 200, 200,100);
        chooseSize.setBounds(410, 200, 200,100);
        buttonRules.setBounds(190,350,200,100);
        buttonExit.setBounds(410, 350, 200,100);
        //set font of title
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        //add the buttons onto the window
        frame.add(buttonExit);
        frame.add(buttonStart);
        frame.add(chooseSize);
        frame.add(buttonRules);
        frame.add(titleLabel);
    }


    //declare ActionListener for MainMenu
    @Override
    public void actionPerformed(ActionEvent e) {
        //If "exit" button is clicked, dispose the frame
        if (e.getSource() == buttonExit) {
            frame.dispose();
        }
        //If "start" button is clicked, dispose the current frame and go to the game
        else if (e.getSource() == buttonStart) {
            frame.dispose();
            users = getUser();
            try {

                FileWriter file = new FileWriter("UsernamesScore.txt", true);
                BufferedWriter writer = new BufferedWriter(file);

                writer.write(users + "-"); // Writes username
                writer.close();
            }
            catch (IOException iox){ //  Catches exception
                System.out.println("ERROR!");
            }
            MainGame.instance().showGUI(true);
        }
        else if (e.getSource()== chooseSize){
            frame.dispose();
            new OptionMenu();
            String size = OptionMenu.Options();
            if(size.isEmpty()) {
            }
            else
            {
                MainGame.instance().gridSize = Integer.parseInt(size);
            }
            frame.setVisible(true);

        }
        else if (e.getSource()==buttonRules){
            frame.dispose();
            new Rules();
        }
    }

    /**
     * This method prompts for the username of the user, so it can be printed onto a text file
     *  @return String username
     */

    public String getUser() {
        //Declare the window for prompting user
        JFrame frame = new JFrame();
        String users = JOptionPane.showInputDialog(frame,"Enter your Username");  // Prompts user for username

        // Determines if the user exited the prompt window
        if (users == null) {
            JOptionPane.showMessageDialog(frame,"You need to enter a username.","Alert",JOptionPane.WARNING_MESSAGE);
            getUser();
        }

        // Determines if the user did not enter anything
        else if(users.equals("")) {
            JOptionPane.showMessageDialog(frame,"Enter a valid username.","Alert",JOptionPane.WARNING_MESSAGE);
            getUser();

        }
        return users;
    }
}
