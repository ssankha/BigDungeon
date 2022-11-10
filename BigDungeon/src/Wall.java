import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 *
 * The Wall class is the border of each level and blocks the Player from
 * traversing through it. It is also used to create sections in the levels. It
 * extends the Entity class.
 *
 * @author Shiv Sankhavaram
 * @version May 26, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class Wall extends Entity
{
    private BufferedImage wallSprite;


    /**
     * Constructs a new Wall and loads in the sprite for it from the
     * spritesheet.
     *
     * @param x
     *            the Wall's x
     * @param y
     *            the Wall's y
     * @param cs
     *            the Wall's Classification
     * @param ss
     *            the spritesheet to load the Wall from
     */
    public Wall( int x, int y, Classification cs, Spritesheet ss )
    {
        super( x, y, cs, ss );

        wallSprite = ss.loadSprite( 2, 6, 50, 50 );
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
     * Draws the Wall on the screen
     *
     * @param g
     *            graphics
     */
    public void render( Graphics g )
    {
        g.drawImage( wallSprite, x, y, null );
    }


    /**
     * Creates a hitbox for the Wall for collisions with the Player, Bullets,
     * Bombs, Monsters, and HardMonster.s
     *
     * @return the hitbox
     */
    public Rectangle getBounds()
    {
        return new Rectangle( x, y, 50, 50 );
    }

}