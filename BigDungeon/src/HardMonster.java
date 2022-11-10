import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


/**
 *
 * The HardMonster class is the same as the Monster class except that it has
 * more health and when it dies, it spawns 2 new Monsters. It looks like a green
 * orc.
 *
 * @author Shiv Sankhavaram
 * @version May 25, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class HardMonster extends Monster
{
    private int health;

    private BigDungeon bd;

    private BufferedImage hardMonster;


    /**
     * Constructs a HardMonster and sets it health to 150, 50 more than the
     * Monster. It also loads in the sprite for the HardMonster
     *
     * @param x
     *            the HardMonster's x
     * @param y
     *            the HardMonster's y
     * @param cs
     *            the HardMonster's Classification
     * @param handler
     *            the HardMonster's handler
     * @param ss
     *            the spritesheet to load in the HardMonster's sprite
     * @param bd
     *            the game
     */
    public HardMonster(
            int x,
            int y,
            Classification cs,
            Handler handler,
            Spritesheet ss,
            BigDungeon bd )
    {
        super( x, y, cs, handler, ss, bd );
        health = 150;
        hardMonster = ss.loadSprite( 2, 4, 50, 50 );
    }


    /**
     * Creates the HardMonster on the screen
     *
     * @param g
     *            Graphics
     */
    public void render( Graphics g )
    {
        g.drawImage( hardMonster, x, y, null );
    }


    /**
     * Updates the HardMonster's position by adding the velocity and checks for
     * collisions with Walls, Bullets, Bombs, Players, and drops 3 coins and
     * spawns 2 monsters. If it bumps into a wall, it's velocity gets set to the
     * opposite, and if it gets hit Bullets and Bombs, it loses health. If it
     * clashes into the player, the Player loses health. If its health falls to
     * 0 or below, it is removed.
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
                if ( getBounds().intersects( entity.getBounds() ) )
                {
                    // collision logic
                }
                if ( isMoving )
                {
                    followPlayer( entity );
                }
            }
        }

        if ( health <= 0 )
        {
            handler.remove( this );

            handler.add( new Coin( x + 5, y, Classification.Coin, ss ) );
            handler.add( new Coin( x, y + 5, Classification.Coin, ss ) );
            handler.add( new Coin( x, y, Classification.Coin, ss ) );

            handler.add(
                    new Monster( x, y, Classification.Monster, handler, ss, bd ) );
            handler.add( new Monster( x,
                    y + 30,
                    Classification.Monster,
                    handler,
                    ss,
                    bd ) );
        }

        if ( untilFollow >= 20 )
        {
            isMoving = true;
            untilFollow = 0;
        }
    }
}