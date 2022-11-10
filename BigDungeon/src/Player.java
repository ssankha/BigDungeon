import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 *
 * The Player class can be controlled by the user's mouse and keyboard input.
 * The Player extends the Entity class.
 *
 * @author Shiv Sankhavaram
 * @version May 26, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class Player extends Entity
{
    private Handler handler;

    private BufferedImage player;

    private BigDungeon bd;

    private int spawn = 0;


    /**
     * Constructs a new Player and loads in its sprite from the spritesheet
     *
     * @param x
     *            the Player's x
     * @param y
     *            the Player's Y
     * @param cs
     *            the Player's Classification
     * @param handler
     *            the Player's handler
     * @param ss
     *            the spritesheet to load from
     * @param bd
     *            the game
     */
    public Player(
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
        player = ss.loadSprite( 1, 1, 50, 50 );
    }


    /**
     * Adjusts its position by adding its velocities. By using handlers
     * is(Direction)() methods, which is set by KeyManager, the velocity is
     * adjusted accordingly. But, to make sure that there are smooth transitions
     * between left and right movement, as well as top and down movement, it
     * checks if the opposite direction is not true to determine the proper
     * velocity.
     */
    public void tick()
    {
        x += velX;
        y += velY;

        checkCollision();

        if ( handler.isUp() )
        {
            velY = -5;
        }
        else if ( !handler.isDown() )
        {
            velY = 0;
        }

        if ( handler.isDown() )
        {
            velY = 5;
        }
        else if ( !handler.isUp() )
        {
            velY = 0;
        }

        if ( handler.isLeft() )
        {
            velX = -5;
        }
        else if ( !handler.isRight() )
        {
            velX = 0;
        }

        if ( handler.isRight() )
        {
            velX = 5;
        }
        else if ( !handler.isLeft() )
        {
            velX = 0;
        }
    }


    /**
     * paints the Player on the screen
     *
     * @param g
     *            graphics
     */
    public void render( Graphics g )
    {
        g.drawImage( player, x, y, null );
    }


    /**
     *
     * This method checks if it collides with Wall, Monster, HardMonster,
     * HealthPotion, or Coin. if it collides with a Wall, the Player's position
     * is subtracted by its velocities. If the Player collides with a Monster or
     * HardMonster, it loses health and if it falls below 0, the state switches
     * to OverState (game over). If the Player is not at full health but still
     * has more than 0, it can collide with a HealthPotion and gain 20 health.
     * If the Player collides with a Coin, the score increments by 1.
     */
    private void checkCollision()
    {

        for ( int i = 0; i < handler.entities.size(); i++ )
        {
            if ( handler.entities.get( i ).getCS() == Classification.Wall )
            {
                if ( getBounds()
                        .intersects( handler.entities.get( i ).getBounds() ) )
                {
                    x -= velX;
                    y -= velY;
                }
            }

            if ( handler.entities.get( i ).getCS() == Classification.Monster
                    || handler.entities.get( i )
                    .getCS() == Classification.HardMonster )
            {
                if ( getBounds()
                        .intersects( handler.entities.get( i ).getBounds() ) )
                {
                    if ( bd.playerHealth > 0 )
                    {
                        bd.playerHealth--;
                    }
                    else
                    {
                        bd.state = State.OverState;
                    }
                }
            }

            if ( handler.entities.get( i )
                    .getCS() == Classification.HealthPotion )
            {
                if ( getBounds()
                        .intersects( handler.entities.get( i ).getBounds() ) )
                {
                    if ( bd.playerHealth < 100 )
                    {
                        bd.playerHealth += 20;
                        handler.remove( handler.entities.get( i ) );
                    }

                    if ( bd.playerHealth > 100 )
                    {
                        bd.playerHealth = 100;
                    }

                }
            }

            if ( handler.entities.get( i ).getCS() == Classification.Coin )
            {
                if ( getBounds()
                        .intersects( handler.entities.get( i ).getBounds() ) )
                {
                    bd.score++;
                    handler.remove( handler.entities.get( i ) );
                }
            }

        }
    }


    /**
     * Creates a hitbox to be used for collisions with Walls, Monsters,
     * HardMonsters, HealthPotions, and Coins
     *
     * @return the hitbox
     */
    public Rectangle getBounds()
    {
        return new Rectangle( x + 10, y, 40, 40 );
    }

}