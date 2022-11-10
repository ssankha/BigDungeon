import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 *
 * The HealthPotion class allows the Player to pick it up and heal for 20 health
 * if it is not full health already. This class extends to Entity.
 *
 * @author Shiv Sankhavaram
 * @version May 26, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class HealthPotion extends Entity
{
    private BufferedImage healthPotion;


    /**
     * Constructs a new HealthPotion and loads in the sprite from the
     * spritesheet.
     *
     * @param x
     *            the HealthPotion's x
     * @param y
     *            the HealthPotion's y
     * @param cs
     *            the HealthPotion's Classification
     * @param ss
     *            the spritesheet that loads in your sprite
     */
    public HealthPotion( int x, int y, Classification cs, Spritesheet ss )
    {
        super( x, y, cs, ss );
        healthPotion = ss.loadSprite( 3, 6, 50, 50 );
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
     * Paints the HealthPotion
     *
     * @param g
     *            graphics
     */
    public void render( Graphics g )
    {
        g.drawImage( healthPotion, x, y, null );
    }


    /**
     * Creates the hitbox for the HealthPotion, so it can be picked up by the
     * Player
     *
     * @return the hitbox
     */
    public Rectangle getBounds()
    {
        return new Rectangle( x, y, 30, 30 );
    }

}