import java.awt.Graphics;
import java.awt.Rectangle;


/**
 *
 * This is the abstract class Entity. Many classes extend to it and it is used
 * as a blueprint for the other classes by providing the methods each Entity
 * should have.
 *
 * @author Shiv Sankhavaram
 * @version May 25, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public abstract class Entity
{
    protected int x;

    protected int y;

    protected float velX = 0;

    protected float velY = 0;

    protected Classification cs;

    protected Spritesheet ss;


    /**
     * Creates a new Entity
     *
     * @param x
     *            the Entity's x
     * @param y
     *            the Entity's y
     * @param cs
     *            the Entity's classification
     * @param ss
     *            the spritesheet to load the Entity from
     */
    public Entity( int x, int y, Classification cs, Spritesheet ss )
    {
        this.x = x;
        this.y = y;
        this.cs = cs;
        this.ss = ss;
    }


    /**
     *
     * updates the Entity's position and checks for collisions
     */
    public abstract void tick();


    /**
     *
     * paints the Entity on the screen
     *
     * @param g
     *            graphics
     */
    public abstract void render( Graphics g );


    /**
     *
     * returns a hitbox for the Entity for collisions
     *
     * @return the hitbox
     */
    public abstract Rectangle getBounds();


    /**
     *
     * returns the classification
     *
     * @return the classification
     */
    public Classification getCS()
    {
        return cs;
    }


    /**
     *
     * sets the classification
     *
     * @param cs
     *            the new classification
     */
    public void setCS( Classification cs )
    {
        this.cs = cs;
    }


    /**
     *
     * returns x
     *
     * @return the x
     */
    public int getX()
    {
        return x;
    }


    /**
     *
     * sets the x
     *
     * @param x
     *            the new x
     */
    public void setX( int x )
    {
        this.x = x;
    }


    /**
     *
     * returns the y
     *
     * @return the y
     */
    public int getY()
    {
        return y;
    }


    /**
     *
     * sets the y
     *
     * @param y
     *            the new y
     */
    public void setY( int y )
    {
        this.y = y;
    }


    /**
     *
     * returns the x velocity
     *
     * @return the the x velocity
     */
    public float getVelX()
    {
        return velX;
    }


    /**
     *
     * sets the x velocity
     *
     * @param velX
     *            the new x velocity
     */
    public void setVelX( float velX )
    {
        this.velX = velX;
    }


    /**
     *
     * returns the y velocity
     *
     * @return the the y velocity
     */
    public float getVelY()
    {
        return velY;
    }


    /**
     *
     * sets the y velocity
     *
     * @param velY
     *            the new y velocity
     */
    public void setVelY( float velY )
    {
        this.velY = velY;
    }
}