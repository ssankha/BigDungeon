import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 *
 * The MouseManager class is for user input on the keyboard and extends the
 * MouseAdapter class.
 *
 * @author Shiv Sankhavaram
 * @version May 26, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class MouseManager extends MouseAdapter
{
    private Handler handler;

    private Camera camera;

    private Spritesheet ss;

    private BigDungeon bd;


    /**
     * Constructs a new MouseManager with a Handler, Camera, spritesheet, and a
     * BigDungeon. The spritesheet is needed to spawn the Bullets and Bombs.
     *
     * @param handler
     *            the handler
     * @param camera
     *            the camera
     * @param ss
     *            the spritesheet to load in the Bullets and Bombs
     * @param bd
     *            the game
     */
    public MouseManager(
            Handler handler,
            Camera camera,
            Spritesheet ss,
            BigDungeon bd )
    {
        this.handler = handler;
        this.camera = camera;
        this.ss = ss;
        this.bd = bd;
    }


    /**
     * if the game is in a level, pressing the mouse will spawn a Bullet(s) or
     * Bombs depending on the player's score. cX and cY are used to determine
     * where the bullet is shot and how fast it goes. The farther away it is
     * from the Player, the faster it goes. If the game is in a MenuState,
     * pressing in a certain area, either triggers LevelSelection, HelpState, or
     * quits the game. If the game is in a LevelSelection, pressing on a certain
     * area will trigger a certain level. If the game is in a WinState1 or
     * WinState2, pressing a certain area will trigger the MenuState or the next
     * level. If the game is in WinState3, pressing a certain area will trigger
     * the MenuState.
     */
    public void mousePressed( MouseEvent e )
    {
        if ( bd.state == State.Level1 || bd.state == State.Level2
                || bd.state == State.Level3 )
        {
            int cX = (int)( e.getX() + camera.getX() );
            int cY = (int)( e.getY() + camera.getY() );

            for ( int i = 0; i < handler.entities.size(); i++ )
            {
                if ( handler.entities.get( i )
                        .getCS() == Classification.Player )
                {
                    if ( bd.score >= 50 )
                    {
                        handler.add(
                                new Bomb( handler.entities.get( i ).getX() + 25,
                                        handler.entities.get( i ).getY() + 25,
                                        Classification.Bomb,
                                        handler,
                                        ss,
                                        cX,
                                        cY,
                                        bd ) );
                        handler.add(
                                new Bomb( handler.entities.get( i ).getX() - 25,
                                        handler.entities.get( i ).getY() + 25,
                                        Classification.Bomb,
                                        handler,
                                        ss,
                                        cX,
                                        cY,
                                        bd ) );

                    }
                    else if ( bd.score >= 25 )
                    {
                        handler.add(
                                new Bullet( handler.entities.get( i ).getX() + 25,
                                        handler.entities.get( i ).getY() + 25,
                                        Classification.Bullet,
                                        handler,
                                        ss,
                                        cX,
                                        cY,
                                        bd ) );
                        handler.add(
                                new Bullet( handler.entities.get( i ).getX() - 25,
                                        handler.entities.get( i ).getY() - 25,
                                        Classification.Bullet,
                                        handler,
                                        ss,
                                        cX,
                                        cY,
                                        bd ) );
                    }
                    else
                    {
                        handler.add(
                                new Bullet( handler.entities.get( i ).getX() + 25,
                                        handler.entities.get( i ).getY() + 25,
                                        Classification.Bullet,
                                        handler,
                                        ss,
                                        cX,
                                        cY,
                                        bd ) );
                    }

                }
            }
        }
        else if ( bd.state == State.MenuState )
        {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if ( mouseX >= 425 && mouseX <= 675 )
            {
                if ( mouseY >= 300 && mouseY <= 400 )
                {
                    bd.state = State.LevelSelection;
                }
                else if ( mouseY >= 450 && mouseY <= 550 )
                {
                    bd.state = State.HelpState;
                }
                else if ( mouseY >= 600 && mouseY <= 700 )
                {
                    System.exit( 1 );
                }
            }
        }
        else if ( bd.state == State.HelpState )
        {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if ( mouseX >= 800 && mouseX <= 1050 && mouseY >= 600
                    && mouseY <= 700 )
            {
                bd.state = State.MenuState;
            }
        }
        else if ( bd.state == State.LevelSelection )
        {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if ( mouseY >= 350 && mouseY <= 450 )
            {
                if ( mouseX >= 75 && mouseX <= 350 )
                {
                    bd.state = State.Level1;
                }
                else if ( mouseX >= 425 && mouseX <= 675 )
                {
                    bd.restartLevel2();
                }
                else if ( mouseX >= 775 && mouseX <= 1025 )
                {
                    bd.restartLevel3();
                }
            }

        }
        else if ( bd.state == State.WinState1 )
        {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if ( mouseY >= 600 && mouseY <= 700 )
            {
                if ( mouseX >= 50 && mouseX <= 300 )
                {
                    bd.state = State.MenuState;
                }
                else if ( mouseX >= 800 && mouseX <= 1050 )
                {
                    bd.restartLevel2();
                }
            }
        }
        else if ( bd.state == State.WinState2 )
        {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if ( mouseY >= 600 && mouseY <= 700 )
            {
                if ( mouseX >= 50 && mouseX <= 300 )
                {
                    bd.state = State.MenuState;
                }
                else if ( mouseX >= 800 && mouseX <= 1050 )
                {
                    bd.restartLevel3();
                }
            }
        }
        else if ( bd.state == State.WinState3 )
        {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if ( mouseY >= 600 && mouseY <= 700 )
            {
                if ( mouseX >= 50 && mouseX <= 300 )
                {
                    bd.state = State.MenuState;
                }
            }
        }

    }

}