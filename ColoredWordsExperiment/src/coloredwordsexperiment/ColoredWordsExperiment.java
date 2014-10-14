/**
 * Author: Jan Heemstra & Olof Morra
 * Course: 2IP90
 */

package coloredwordsexperiment;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author s149740 
 */
public class ColoredWordsExperiment extends JPanel {
    JFrame frame = new JFrame("Colored Words Experiment"); 
    
    JPanel buttonPane = new JPanel();
    
    // Create buttons for in the frame
     JButton matchingButton = new JButton("Matching");
     JButton nonMatchingButton = new JButton("Non-Matching");
     JButton finishedButton = new JButton("Finished");
     
     JLabel matchingTime = new JLabel("                             ");
     JLabel nonMatchingTime = new JLabel("                             ");
     JLabel averageTime = new JLabel("");
    
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
        
        // Set size of the buttons
        matchingButton.setSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        nonMatchingButton.setSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        finishedButton.setSize(new Dimension(BUTTONWIDTH, BUTTONHEIGHT));
        
        // Make the frame visible
        frame.setVisible(true);
    }
    
}