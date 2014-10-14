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
    // An array of all times so far, in order to be able to compute the average
    ArrayList<Float> allTimes = new ArrayList<Float>(); 
    
    JFrame frame = new JFrame("Colored Words Experiment"); 
    
    JPanel buttonPane = new JPanel(); // Panel for the IO
    JPanel wordPanel; // Panel on which the words are drawn
    
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
    
    private final int BUTTONHEIGHT = 8000;
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
        
        
        // TODO: This seems to do nothing. I set BUTTONHEIGHT to 8000, but it
        // is still as small as it was.
        // Set size of the buttons
        matchingButton.setSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        nonMatchingButton.setSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        finishedButton.setSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        
        // Add actionlisteners
        matchingButton.addActionListener(this);
        nonMatchingButton.addActionListener(this);
        finishedButton.addActionListener(this);
        
        // We can't finish if we haven't started!
        finishedButton.setEnabled(false);
        
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

        // Make words
        new MatchingWords(wordPanel, false);
        
        // Draw words on the screen
        wordPanel.updateUI();
        
        // Set the starttime
        isCurrentlyMatching = false;
        startTimeNonMatching = ae.getWhen();
        finishedButton.setEnabled(true);
    }
    
    void finished(ActionEvent ae) {
        // Get time at end
        finishTime = ae.getWhen();
        
        if (isCurrentlyMatching) {
            // Compute time
            float deltaTime =  finishTime - startTimeMatching;
            deltaTime /= 1000;
            
            // Print time to label next to matching
            matchingTime.setText("" + deltaTime);
            
            // Contribute to global average
            allTimes.add(deltaTime);
        } else {
            // Compute time
            float deltaTime = finishTime - startTimeNonMatching;
            deltaTime /= 1000;
            
            // Print time to label next to non-matching
            nonMatchingTime.setText("" + deltaTime);
            
            // Contribute to global average
            allTimes.add(deltaTime);
        }
        
        // Compute new average
        float averageTimeFloat = 0;
        for (float i : allTimes) {
            averageTimeFloat += i;
        }
        averageTimeFloat /= allTimes.size();
        
        // Print new average
        averageTime.setText("" + averageTimeFloat);
        
        // We can't finish twice without starting inbetween
        finishedButton.setEnabled(false);
    }
}
