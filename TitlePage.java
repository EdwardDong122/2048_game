/*
Authors: Edward Dong and Angelina Chen
Class Name: TitlePage.java
Description of class: This class is the first page of the program; the title page.
It will contain one button ("click anywhere to start"), and a title.
 */

//import necessary packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//implement ActionListener for when the user clicks buttons
public class TitlePage implements ActionListener {
    //declare GUI to make popup window for user
    public JFrame frame;
    public JButton nextButton;
    public JLabel titleImage;
    public JLabel titleLabel;
    public JLabel titleLabel2;
    public JLabel titleLabel3;
    //set image variable as image in folder
    ImageIcon image = new ImageIcon("images/2048.png");

    public TitlePage(){
        //declare GUI components, including the image, the button, and the window frame
        frame = new JFrame();
        nextButton = new JButton("Click to begin");
        titleImage = new JLabel(image);
        titleLabel = new JLabel("Authors: Edward D. and Angelina C.");
        titleLabel2 = new JLabel("Course: ICS3U7-02");
        titleLabel3 = new JLabel("Date Completed: Jan 30, 2021");
        //Tell program to exit when window closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        //set window title, pack frame
        frame.setTitle("Our 2048 Game");
        frame.pack();
        //make frame visible
        frame.setVisible(true);
        //add action listener to button
        nextButton.addActionListener(this);
        //adjust the positioning and font size of the components on the window
        titleLabel.setBounds(200,50,700,50);
        titleLabel.setFont(new Font("Arial",Font.BOLD, 20));
        titleLabel2.setBounds(200,110,500,50);
        titleLabel2.setFont(new Font("Arial",Font.BOLD, 20));
        titleLabel3.setBounds(200,170,500,50);
        titleLabel3.setFont(new Font("Arial",Font.BOLD, 20));
        nextButton.setBounds(200, 500, 400,150);
        nextButton.setFont(new Font("Arial", Font.PLAIN, 50));
        //set size of window
        frame.setSize(800,800);
        //add image, labels, and button onto the window
        frame.setContentPane(titleImage);
        frame.add(nextButton);
        frame.add(titleLabel);
        frame.add(titleLabel2);
        frame.add(titleLabel3);
    }

    //declare ActionListener for TitlePage
    @Override
    public void actionPerformed(ActionEvent e) {
        //if button is clicked, new window is visible and current window is automatically closed
        frame.dispose();
        new MainMenu();

    }

    public static void main(String [] args){
        //direct program to the TitlePage() method
        new TitlePage();
    }
}
