/**
 * @author: Jan Heemstra & Olof Morra
 * Course: 2IP90
 */

package coloredwordsexperiment;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


import coloredwordsexperiment.MatchingWords;

public class ColoredWordsExperiment extends JPanel implements ActionListener{
    JFrame frame = new JFrame("Colored Words Experiment"); 
    
    JPanel buttonPane = new JPanel();
    JPanel wordPanel;
    
    // Create buttons for in the frame
    JButton matchingButton = new JButton("Matching");
    JButton nonMatchingButton = new JButton("Non-Matching");
    JButton finishedButton = new JButton("Finished");
     
    JLabel matchingTime = new JLabel("");
    JLabel nonMatchingTime = new JLabel("");
    JLabel averageTime = new JLabel("");
    
    GridLayout gridLayout = new GridLayout(1,6, 20, 20);
    
    ArrayList<MatchingWords> wordList = new ArrayList<MatchingWords>();
    
    long startTimeMatching;
    long startTimeNonMatching;
    boolean isCurrentlyMatching;
    
    long finishTime;
    
    private final int HEIGHT = 600;
    private final int WIDTH = 800;
    
    private final int BUTTONHEIGHT = 40;
    private final int BUTTONWIDTH = 100;
    
    public static void main(String[] args) {
        new ColoredWordsExperiment();
    }
    
    ColoredWordsExperiment() {
        // Create frame with certain height and width
        frame.setSize(WIDTH, HEIGHT);
        
        frame.getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        // Add buttons to frame
        buttonPane.add(matchingButton);
        buttonPane.add(matchingTime);
        buttonPane.add(nonMatchingButton);
        buttonPane.add(nonMatchingTime);
        buttonPane.add(finishedButton);
        buttonPane.add(averageTime);
        
        // Make the buttons align properly
        buttonPane.setLayout(gridLayout);
        
        // Set size of the buttons
        matchingButton.setSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        nonMatchingButton.setSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        finishedButton.setSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        
        matchingButton.addActionListener(this);
        nonMatchingButton.addActionListener(this);
        finishedButton.addActionListener(this);
        
        // Make the frame visible
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Monitor buttons getting pressed
        switch(ae.getActionCommand()){
            case "Matching": matching(ae);
                break;
            case "Non-Matching": nonMatching(ae);
                break;
            case "Finished": finished(ae);
                break;
        }
    }
    
    void matching(ActionEvent ae) {
        wordPanel = new JPanel();
        frame.getContentPane().add(wordPanel, BorderLayout.CENTER);

        MatchingWords words = new MatchingWords(wordPanel, true);
        wordPanel.updateUI();
        
        // Set the starttime
        isCurrentlyMatching = true;
        startTimeMatching = ae.getWhen();
        finishedButton.setEnabled(true);
    }
    
    void nonMatching(ActionEvent ae) {
        wordPanel = new JPanel();
        frame.getContentPane().add(wordPanel, BorderLayout.CENTER);

        MatchingWords words = new MatchingWords(wordPanel, false);
        wordPanel.updateUI();
        
        // Set the starttime
        isCurrentlyMatching = false;
        startTimeNonMatching = ae.getWhen();
        finishedButton.setEnabled(true);
    }
    
    void finished(ActionEvent ae) {
        finishTime = ae.getWhen();
        
        if (isCurrentlyMatching) {
            long deltaTime =  finishTime - startTimeMatching;
            matchingTime.setText("" + deltaTime);
        } else {
            long deltaTime = finishTime - startTimeNonMatching;
            nonMatchingTime.setText("" + deltaTime);
        }
        
        finishedButton.setEnabled(false);
    }
}
