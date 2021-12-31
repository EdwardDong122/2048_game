import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Game2048 extends JFrame implements ActionListener {

    static Game2048 game2048 = null;

    public class StartPanel extends JPanel {

        private JButton buttonStart = new JButton("Start");
        private JButton buttonExit = new JButton("Exit");
        private JLabel label = new JLabel("2048");

        StartPanel() {
            this.setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = new Insets(10, 10, 10, 10);
            GridBagConstraints constraints1 = new GridBagConstraints();
            constraints1.anchor = GridBagConstraints.WEST;
            constraints1.insets = new Insets(10, 10, 10, 10);
            GridBagConstraints constraints2 = new GridBagConstraints();
            constraints2.anchor = GridBagConstraints.NORTH;

            constraints.gridx = 4;
            constraints.gridy = 6;
            constraints1.gridx = 4;
            constraints1.gridy = 8;
            constraints2.gridx = 4;
            constraints2.gridy = 0;
            this.add(buttonStart, constraints);
            this.add(buttonExit, constraints1);
            this.add(label, constraints2);

            buttonStart.addActionListener(game2048);

        }
    }

    private StartPanel startPanel = new StartPanel();

    @Override
    public void actionPerformed(ActionEvent evt) {
        // do something here...
        startPanel.setVisible(false);
    }

    public Game2048() {
        super("2048 Game");

        // add the panel to this frame
        add(startPanel);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                game2048 = new Game2048();
                game2048.setSize(1200, 1200);
                game2048.setVisible(true);
            }
        });
    }
}