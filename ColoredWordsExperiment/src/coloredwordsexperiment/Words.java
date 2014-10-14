/*
 * Words will print 20 words to a JPanel passed to the constructur. It will 
 * do this upon construction.
 */

package coloredwordsexperiment;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Olof Morra & Jan Heemstra
 */

public class Words{}

class MatchingWords extends Words{
    
    // Add words to the JPanel
    MatchingWords(JPanel panel, boolean match) {
        for(int i = 0; i < 20; i++){
            new Word(panel, match);
        }
    }
}

class NonMatchingWords extends Words{
}

class Word{
    Random random = new Random();
    // Color values
    static Color[] colorChoice = new Color[]{
        Color.BLACK,
        Color.RED,
        Color.GREEN,
        Color.BLUE,
        Color.MAGENTA,
        Color.PINK,
        Color.ORANGE, 
        Color.CYAN
    };
    // Color names
    static String[] colorName = new String[]{
        "Black", 
        "Red", 
        "Green", 
        "Blue", 
        "Magenta", 
        "Pink", 
        "Orange", 
        "Cyan"};
    
    
    // Add a random word to JPanel
    Word(JPanel panel, boolean match) {
        int i = random.nextInt(7); // Pick random i to choose color
        Color color = colorChoice[i]; // Get color from index i
        String word; // Word to have the color
        if (match){
            // Add matching color name
            word = colorName[i];
        } else {
            // Add random color name
            word = colorName[random.nextInt(7)];
        }
        
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 35);
        
        JLabel label = new JLabel();
        label.setFont(font);
        label.setForeground(color);
        label.setText(word);
        
        // Make all words one size
        label.setPreferredSize(new Dimension(160, 43));
        
        // Add the word to the wordPanel
        panel.add(label);
    }
}