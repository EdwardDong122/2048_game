import java.awt.event.*;
import javax.swing.*;


public class TitlePage implements ActionListener {
    private JFrame frame;
    private JLabel titleLabel;
    private JButton nextButton;
    public static void main(String [] args){
        new TitlePage();
    }
    public TitlePage(){
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our 2048 Game");
        frame.pack();
        frame.setVisible(true);
        nextButton = new JButton("Click anywhere to begin");
        nextButton.addActionListener(this);
        titleLabel = new JLabel("2048");
        titleLabel.setBounds(575,50,100,100);
        nextButton.setBounds(550, 200, 100,100);
        frame.setSize(1200,1200);
        frame.add(titleLabel);
        frame.add(nextButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        new Game2048().setVisible(true);
    }
}
