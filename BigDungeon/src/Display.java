import java.awt.Dimension;

import javax.swing.JFrame;


/**
 *
 * This is the Display class, which creates the screen for the game to be hosted
 * on.
 *
 * @author Shiv Sankhavaram
 * @version May 25, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class Display {
    /**
     * The constructor creates a new JFrame that cannot be resized and is set in
     * the center of the screen. It adds the BigDungeon object to it and sets it
     * to visible so we can see the game.
     *
     * @param width
     * @param height
     * @param title
     * @param bd
     */
    public Display(int width, int height, String title, BigDungeon bd) {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.add(bd);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}