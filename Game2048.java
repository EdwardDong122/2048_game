import java.awt.event.*;
import javax.swing.*;

public class Game2048 implements ActionListener {
    private JButton buttonStart;
    private JButton buttonExit;
    private JFrame frame;


    public static void main(String[] args) {
        new Game2048();
    }
    public Game2048(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our 2048 Game");
        frame.setSize(1200,1200);
        frame.setVisible(true);
        frame.setLayout(null);
        buttonStart = new JButton("Start");
        buttonStart.addActionListener(this);
        buttonExit = new JButton("Exit");
        buttonExit.addActionListener(this);
        buttonExit.setBounds(550, 550, 50,50);
        buttonStart.setBounds(550, 250, 50,50);
        frame.add(buttonExit);
        frame.add(buttonStart);

        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonExit){
            frame.dispose();
        }
        else if (e.getSource()==buttonStart){
            frame.dispose();
            new MainGame().setVisible(true);
        }
}

    public static void setVisible(boolean b) {
        Game2048.setVisible(true);
    }
}