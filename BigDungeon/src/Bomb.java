import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 *
 * This class is a Bomb that the Player can shoot after picking up 50 Coins. It
 * does 70 damage instead of 40. It extends the Entity class
 *
 * @author Shiv Sankhavaram
 * @version May 25, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class Bomb extends Entity
{
    private Handler handler;

    private BigDungeon bd;

    private BufferedImage bomb;


    /**
     * Constructor creates a Bomb object and loads in the sprite from the
     * spritesheet
     *
     * @param x
     *            the bomb's x coordinate
     * @param y
     *            the bomb's y coordinate
     * @param cs
     *            the bomb's classification
     * @param handler
     *            the bomb's handler
     * @param ss
     *            the spritesheet that has the bomb
     * @param cX
     *            the mouse's and camera's X which determines how fast it
     *            travels
     * @param cY
     *            the mouse's and camera's Y which determines how fast it
     *            travels
     * @param bd
     *            the Big Dungeon game
     */
    public Bomb(
            int x,
            int y,
            Classification cs,
            Handler handler,
            Spritesheet ss,
            int cX,
            int cY,
            BigDungeon bd )
    {
        super( x, y, cs, ss );
        this.handler = handler;
        this.bd = bd;

        velX = ( cX - x ) / 10;
        velY = ( cY - y ) / 10;

        bomb = ss.loadSprite( 6, 1, 50, 50 );
    }


    /**
     * Makes the bomb move by its velocity and if it collides with a Wall, it
     * gets removed.
     */
    public void tick()
    {
        x += velX;
        y += velY;

        for ( int i = 0; i < handler.entities.size(); i++ )
        {
            if ( handler.entities.get( i ).getCS() == Classification.Wall )
            {
                if ( handler.entities.get( i )
                        .getBounds()
                        .intersects( getBounds() ) )
                {
                    handler.remove( this );
                }
            }

        }
    }


    /**
     * Draws the bomb using the spritesheet
     *
     * @param g
     *            graphics
     */
    public void render( Graphics g )
    {
        g.drawImage( bomb, x, y, null );
    }


    /**
     * Returns the hitbox of the bomb, so it can be used for collisions
     *
     * @return the hitbox
     */
    public Rectangle getBounds()
    {
        return new Rectangle( x, y, 40, 40 );
    }

}