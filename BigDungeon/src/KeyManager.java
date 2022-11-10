import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 *
 * The KeyManager class is for user input on the keyboard and extends the
 * KeyAdapter class.
 *
 * @author Shiv Sankhavaram
 * @version May 26, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class KeyManager extends KeyAdapter
{
    private Handler handler;

    private BigDungeon bd;


    /**
     * Constructs a KeyManager object that takes in a Handler and BigDungeon as
     * parameters
     *
     * @param handler
     *            the handler
     * @param bd
     *            the game
     */
    public KeyManager( Handler handler, BigDungeon bd )
    {
        this.handler = handler;
        this.bd = bd;
    }


    /**
     * If W is pressed, it sets handler's up field to true. If A is pressed, it
     * sets handler's left field to true. If S is pressed, it sets handler's
     * down field to true. If D is pressed, it sets handler's right field to
     * true. If the player dies, and it's an overState, pressing ENTER will take
     * the Player back to the MenuState.
     *
     * @param e
     *            the key that is pressed
     */
    public void keyPressed( KeyEvent e )
    {
        int key = e.getKeyCode();

        if ( bd.state == State.Level1 || bd.state == State.Level2
                || bd.state == State.Level3 )
        {
            for ( Entity entity : handler.entities )
            {
                if ( entity.getCS() == Classification.Player )
                {
                    if ( key == KeyEvent.VK_W )
                    {
                        handler.setUp( true );
                    }

                    if ( key == KeyEvent.VK_A )
                    {
                        handler.setLeft( true );
                    }

                    if ( key == KeyEvent.VK_S )
                    {
                        handler.setDown( true );
                    }

                    if ( key == KeyEvent.VK_D )
                    {
                        handler.setRight( true );
                    }
                }
            }
        }
        else if ( bd.state == State.OverState )
        {
            if ( key == KeyEvent.VK_ENTER )
            {
                bd.restartLevel1();
            }
        }

    }


    /**
     * If W is released, it sets handler's up field to false. If A is released,
     * it sets handler's left field to false. If S is released, it sets
     * handler's down field to false. If D is released, it sets handler's right
     * field to false.
     *
     * @param e
     *            the key that is pressed
     */
    public void keyReleased( KeyEvent e )
    {
        int key = e.getKeyCode();

        for ( Entity entity : handler.entities )
        {
            if ( entity.getCS() == Classification.Player )
            {
                if ( key == KeyEvent.VK_W )
                {
                    handler.setUp( false );
                }

                if ( key == KeyEvent.VK_A )
                {
                    handler.setLeft( false );
                }

                if ( key == KeyEvent.VK_S )
                {
                    handler.setDown( false );
                }

                if ( key == KeyEvent.VK_D )
                {
                    handler.setRight( false );
                }
            }
        }
    }
}