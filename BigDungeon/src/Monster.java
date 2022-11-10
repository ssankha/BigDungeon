import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 *
 * The Monster class follows the Player if it gets with a certain radius. It
 * looks like a Skeleton and extends the Entity class.
 *
 * @author Shiv Sankhavaram
 * @version May 26, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class Monster extends Entity
{
    protected Handler handler;

    protected int untilFollow;

    private int health;

    protected boolean isMoving;

    private BufferedImage monster;

    private BigDungeon bd;


    /**
     * Constructs a Monster with 100 health and loads in its sprite from the
     * spritesheet.
     *
     * @param x
     *            the Monster's x
     * @param y
     *            the Monster's y
     * @param cs
     *            the Monster's Classification
     * @param handler
     *            the handler
     * @param ss
     *            the spritesheet the Monster is loaded from
     * @param bd
     *            the game
     */
    public Monster(
            int x,
            int y,
            Classification cs,
            Handler handler,
            Spritesheet ss,
            BigDungeon bd )
    {
        super( x, y, cs, ss );
        this.handler = handler;
        this.bd = bd;

        health = 100;
        untilFollow = 0;
        isMoving = false;
        monster = ss.loadSprite( 4, 2, 50, 50 );
    }


    /**
     * The Monster's position is changed by its velocity. While the monster is
     * not moving, the isMoving field is incremented until it's 20 and if it is
     * within 400 pixels of the player, start to follow it. If it bumps into a
     * wall, it's velocity gets set to the opposite, and if it gets hit Bullets
     * and Bombs, it loses health. If it clashes into the player, the Player
     * loses health. If its health falls to 0 or below, it is removed.
     */
    public void tick()
    {
        x += velX;
        y += velY;

        if ( !isMoving )
        {
            untilFollow++;
        }
        for ( int i = 0; i < handler.entities.size(); i++ )
        {
            Entity entity = handler.entities.get( i );

            if ( entity.getCS() == Classification.Wall )
            {

                if ( getBounds().intersects( entity.getBounds() ) )
                {
                    x -= velX;
                    y -= velY;

                    velX *= -1;
                    velY *= -1;

                    isMoving = false;
                }

            }

            if ( entity.getCS() == Classification.Bullet )
            {
                if ( getBounds().intersects( entity.getBounds() ) )
                {
                    health -= 40;
                    handler.remove( entity );
                }
            }

            if ( entity.getCS() == Classification.Bomb )
            {
                if ( getBounds().intersects( entity.getBounds() ) )
                {
                    health -= 70;
                    handler.remove( entity );
                }
            }

            if ( entity.getCS() == Classification.Player )
            {
                if ( isMoving )
                {
                    followPlayer( entity );
                }
            }
        }

        if ( health <= 0 )
        {
            handler.remove( this );
            handler.add( new Coin( x, y, Classification.Coin, ss ) );
        }

        if ( untilFollow >= 20 )
        {
            isMoving = true;
            untilFollow = 0;
        }
    }


    /**
     *
     * If the Monster is to the left or above the player, its
     * x-velocity/y-velocity is set to 2. But, if the Monster is to the right or
     * below the player, its x-velocity/y-velocity is set to -2.
     *
     * @param player
     *            the player
     */
    public void followPlayer( Entity player )
    {
        if ( x - player.x < 400 && y - player.y < 400 )
        {
            if ( x > player.getX() )
            {
                velX = -2;

            }

            if ( x < player.getX() )
            {
                velX = 2;
            }

            if ( y > player.getY() )
            {
                velY = -2;
            }

            if ( y < player.getY() )
            {
                velY = 2;
            }
        }

        else
        {
            velX = 0;
            velY = 0;
        }

    }


    /**
     * this method paints the Monster
     *
     * @param g
     *            graphics
     */
    public void render( Graphics g )
    {
        g.drawImage( monster, x, y, null );
    }


    /**
     * creates the hitbox for the Monster when it collides with Bullets, Bombs,
     * or the Player
     *
     * @return the hitbox
     */
    public Rectangle getBounds()
    {
        return new Rectangle( x, y, 40, 40 );
    }


    /**
     * creates the hitbox for the Monster when it collides with Walls. I had to
     * make it bigger because the getBounds() caused the Monster to glitch into
     * the Wall when it collided with it.
     *
     * @return the hitbox
     */
    public Rectangle getBoundsBig()
    {
        return new Rectangle( x - 25, y - 25, 100, 100 );
    }
}