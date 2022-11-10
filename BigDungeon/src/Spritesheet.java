import java.awt.image.BufferedImage;


/**
 *
 * The Spritesheet represents my spritesheet that I can load my sprites from.
 *
 * @author Shiv Sankhavaram
 * @version May 26, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class Spritesheet
{
    private BufferedImage bi;


    /**
     * Constructs a Spritesheet based on the spritesheet in the project folder
     *
     * @param bi
     *            the spritesheet
     */
    public Spritesheet( BufferedImage bi )
    {
        this.bi = bi;
    }


    /**
     *
     * My spritesheet has been made so each sprite is in a 50x50 grid and every
     * sprite is fitted on to a 300X300 grid. This creates 6 rows and 6 columns
     * of sprites. Using the getSubImage(), I can call a specific column and row
     * to load in sprite at that location on my spritesheet.
     *
     * @param col
     *            the column of the sprite
     * @param row
     *            the row of the sprite
     * @param width
     *            the width of each sprite
     * @param height
     *            the heigh of each sprite
     * @return
     */
    public BufferedImage loadSprite( int col, int row, int width, int height )
    {
        return bi.getSubimage( ( col * 50 ) - 50,
                ( row * 50 ) - 50,
                width,
                height );
    }
}