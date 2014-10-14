/*
 * Words and it's children will print words to the JPanel provided to it's
 * contructor.
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
    
    static Color[] colorChoice = new Color[]{
        Color.BLACK,
        Color.RED,
        Color.GREEN,
        Color.BLUE,
        Color.MAGENTA,
        Color.PINK
    };
    
    static String[] colorName = new String[]{
        "Black", 
        "Red", 
        "Green", 
        "Blue", 
        "Magenta", 
        "Pink"};
    
    String text;
    Color color;
    
    
    // Add a random word to JPanel
    Word(JPanel panel, boolean match) {
        int i = random.nextInt(5);
        Color color = colorChoice[i];
        String word;
        if (match){
            // Add matching color name
            word = colorName[i];
        } else {
            // Add random color name
            word = colorName[random.nextInt(5)];
        }
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 35);
        
        JLabel label = new JLabel();
        label.setFont(font);
        label.setForeground(color);
        label.setText(word);
        
        label.setPreferredSize(new Dimension(160, 43));
        
        panel.add(label);
        
        
    }
}