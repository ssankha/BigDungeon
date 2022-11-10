import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 *
 * This class makes Bullet which the player can shoot. It extends the Entity
 * class. The bullet is a pixelated Cowboys logo.
 *
 * @author Shiv Sankhavaram
 * @version May 25, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class Bullet extends Entity
{
    private Handler handler;

    private BufferedImage bullet;

    private BigDungeon bd;


    /**
     * This constructs a new Bullet and loads it in from the spritesheet.
     *
     * @param x
     *            the Bullet's X
     * @param y
     *            the Bullet's Y
     * @param cs
     *            the Bullet's classification
     * @param handler
     *            the Bullet's handler
     * @param ss
     *            the spritesheet it gets loaded from
     * @param cX
     *            the mouse's and camera's X which determines how fast it
     *            travels
     * @param cY
     *            the mouse's and camera's Y which determines how fast it
     *            travels
     * @param bd
     *            the game
     */
    public Bullet(
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

        bullet = ss.loadSprite( 5, 5, 50, 50 );

    }


    /**
     * the Bullet's X and Y is changed by their velocity. If it collides with a
     * wall, it gets removed.
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
     * This paints the Bullet
     *
     * @param g
     *            graphics
     */
    public void render( Graphics g )
    {
        g.drawImage( bullet, x, y, null );

    }


    /**
     * this creates the bullet's hitbox for collisions
     *
     * @return the hitbox
     */
    public Rectangle getBounds()
    {
        return new Rectangle( x, y, 15, 15 );
    }

}