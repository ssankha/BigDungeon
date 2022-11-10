import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 *
 * This is the Coin class, which is an item the Player can collect which
 * increases the score by 1. A Monster drops 1 Coin, while a HardMonster drops 3
 * Coins. This object extends the Entity class.
 *
 * @author Shiv Sankhavaram
 * @version May 25, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class Coin extends Entity
{
    private BufferedImage coin;


    /**
     * Constructs a new Coin and loads in its sprite from the spritesheet
     *
     * @param x
     *            the Coin's X
     * @param y
     *            the Coin's Y
     * @param cs
     *            the Coin's Classification
     * @param ss
     *            the spritesheet it gets loaded in from
     */
    public Coin( int x, int y, Classification cs, Spritesheet ss )
    {
        super( x, y, cs, ss );
        coin = ss.loadSprite( 4, 6, 50, 50 );
    }


    /**
     * Since the position of the coin does not change we don't actually need to
     * implement anything for the tick(), but we need it in the class because it
     * extends the abstract class Entity.
     */
    public void tick()
    {

    }


    /**
     * Draws the coin on the screen
     *
     * @param g
     *            graphics
     */
    public void render( Graphics g )
    {
        g.drawImage( coin, x, y, null );

    }


    /**
     * Creates a hitbox for the Coin, so it can be collected by the player
     *
     * @return the hitbox
     */
    public Rectangle getBounds()
    {
        return new Rectangle( x, y, 25, 25 );
    }

}