import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VoteCounterPanel extends JPanel {

    private int votesForJoe;
    private int votesForSam;
    private int votesForMary;
    private JButton joe;
    private JButton sam;
    private JButton mary;
    private JLabel labelJoe;
    private JLabel labelSam;
    private JLabel labelMary;

    public VoteCounterPanel () {

        joe = new JButton("Vote for Joe");
        joe.addActionListener(new JoeButtonListener());

        sam = new JButton("Vote for Sam");
        sam.addActionListener(new SamButtonListener());

        mary = new JButton("Vote for Mary");
        mary.addActionListener(new MaryButtonListener());

        labelJoe = new JLabel("Votes for Joe: " + votesForJoe);
        labelSam = new JLabel("Votes for Sam: " + votesForSam);
        labelMary = new JLabel("Votes for Mary: " + votesForMary);

        add(joe);
        add(labelJoe);
        add(sam);
        add(labelSam);
        add(mary);
        add(labelMary);

        setPreferredSize(new Dimension(400, 120));
        setBackground(Color.cyan);
    }

    private class JoeButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            votesForJoe++;
            labelJoe.setText("Votes for Joe: " + votesForJoe);
        }
    }

    private class SamButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            votesForSam++;
            labelSam.setText("Votes for Sam: " + votesForSam);
        }
    }

    private class MaryButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            votesForMary++;
            labelMary.setText("Votes for Mary: " + votesForMary);
        }
    }

}
