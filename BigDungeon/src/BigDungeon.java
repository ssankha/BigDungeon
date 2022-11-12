import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JButton;


/**
 *
 * BigDungeon is the game object. It contains the game loop, loads in the map,
 * creates the map, updates the positions of each entity, creates the camera,
 * has the menu, game over screen, help screen, levels, and winscreen. It also
 * implements Runnable to create a Thread to run the game loop
 *
 * @author Shiv Sankhavaram
 * @version May 25, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class BigDungeon extends Canvas implements Runnable
{
    private boolean isRunning = false;
e
    private Thread thread;

    private int ticks;
    private Handler handler;

    public BufferedImage world = null;

    private Camera camera;

    private BufferedImage loadSS = null;

    private Spritesheet sprites;

    private BufferedImage menuScreen = null;

    private BufferedImage menuTitle = null;

    private BufferedImage playButton = null;

    private BufferedImage helpButton = null;

    private BufferedImage quitButton = null;

    private BufferedImage helpScreen = null;

    private BufferedImage menuButton = null;

    private BufferedImage levelScreen = null;

    private BufferedImage level1Button = null;

    private BufferedImage level2Button = null;

    private BufferedImage level3Button = null;

    private BufferedImage level1 = null;

    private BufferedImage level2 = null;

    private BufferedImage level3 = null;

    private BufferedImage winScreen = null;

    private BufferedImage nextLevelButton = null;

    private BufferedImage gameOver = null;

    private BufferedImage floor = null;

    public int playerHealth = 100;

    public int score = 0;

    private int score1 = 50;

    private int score2 = 88;

    private int score3 = 191;

    public int level = 1;

    public State state = State.MenuState;

    ImageLoader loader = new ImageLoader();


    /**
     * This class's constructor creates a Display object to hold the game and
     * starts the thread. It creates a handler object to update the Entities and
     * to assist with user input. It creates a Camera object that follows the
     * Player as it moves. It adds a KeyManager and Mouse Manager for user input
     * to work and loads in all the images and sprites. It loads in the level1
     * map as well
     */
    public BigDungeon()
    {
        new Display( 1100, 800, "Big Dungeon", this );
        start();

        handler = new Handler();
        camera = new Camera( 0, 0 );
        this.addKeyListener( new KeyManager( handler, this ) );

        menuScreen = loader.loadImage( "/menu_screen.png" );
        menuTitle = loader.loadImage( "/menu_title.png" );
        playButton = loader.loadImage( "/play_button.png" );
        helpButton = loader.loadImage( "/help_button.png" );
        quitButton = loader.loadImage( "/quit_button.png" );

        helpScreen = loader.loadImage( "/instructions.png" );
        menuButton = loader.loadImage( "/menu_button.png" );

        levelScreen = loader.loadImage( "/level_screen.png" );
        level1Button = loader.loadImage( "/level1_button.png" );
        level2Button = loader.loadImage( "/level2_button.png" );
        level3Button = loader.loadImage( "/level3_button.png" );

        level1 = loader.loadImage( "/level1.png" );
        level2 = loader.loadImage( "/level2.png" );
        level3 = loader.loadImage( "/level3.png" );

        gameOver = loader.loadImage( "/game_over_screen.png" );

        winScreen = loader.loadImage( "/game_completed.png" );
        nextLevelButton = loader.loadImage( "/next_level_button.png" );

        floor = loader.loadImage( "/game_floor.png" );

        loadSS = loader.loadImage( "/sprites_ver25.png" );
        sprites = new Spritesheet( loadSS );

        this.addMouseListener(
                new MouseManager( handler, camera, sprites, this ) );

        loadWorld( level1 );
    }


    /**
     *
     * Creates a new Thread to run the game if the game is not already running.
     */
    public synchronized void start()
    {
        if ( isRunning )
        {
            return;
        }

        isRunning = true;
        thread = new Thread( this );
        thread.start();
    }


    /**
     *
     * Stops the thread from running the game if the game has not already been
     * stopped.
     */
    public synchronized void stop()
    {
        if ( !isRunning )
        {
            return;
        }

        isRunning = false;

        try
        {
            thread.join();
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
    }


    /**
     * This method ensures the game runs at around 60 FPS. It calls the render()
     * and tick() 60 times every second.
     */
    public void run()
    {
        double tickTime = 1000000000 / 60;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); // returns how current time in
        // nanoseconds
        long timer = 0;
        ticks = 0;

        while ( isRunning )
        {
            this.requestFocus();
            now = System.nanoTime();
            delta += ( now - lastTime ) / tickTime; // how long until we call
            // the tick and render
            // methods
            timer += now - lastTime;
            lastTime = now;

            if ( delta >= 1 )
            {
                tick();
                render();
                ticks++;
                delta--;
            }

            if ( timer >= 1000000000 )
            {
                //System.out.println( "FPS: " + ticks );
                //System.out.println( state );
                timer = 0;
                ticks = 0;
            }

        }

        stop();
    }


    /**
     *
     * This method updates the camera position on the player. It also triggers
     * the WinStates if the player collects all the Coins in the level.
     */
    public void tick()
    {

        if ( state == State.Level1 || state == State.Level2
                || state == State.Level3 )
        {
            for ( int i = 0; i < handler.entities.size(); i++ )
            {
                if ( handler.entities.get( i )
                        .getCS() == Classification.Player )
                {
                    camera.tick( handler.entities.get( i ) );
                }
            }

            if ( state == State.Level1 )
            {
                if ( score >= score1 )
                {
                    state = State.WinState1;
                    score = 0;
                }
            }
            else if ( state == State.Level2 )
            {
                if ( score >= score2 )
                {
                    state = State.WinState2;
                    score = 0;
                }
            }
            else if ( state == State.Level3 )
            {
                if ( score >= score3 )
                {
                    state = State.WinState3;
                    score = 0;
                }
            }

            handler.tick();
        }

    }


    /**
     * Creates 3 frames in advance using BufferStrategy. Renders a specific
     * screen based on the State which is set by the user inputs. If it's a
     * level, Graphics2D is used to translate the screen so that the camera can
     * properly follow the Player. Without the translate(), the screen becomes
     * all warped and weird. This method also calls handler's tick() which
     * updates all the Entities on the screen.
     *
     */
    public void render()
    {
        BufferStrategy bs = this.getBufferStrategy();

        if ( bs == null )
        {
            this.createBufferStrategy( 3 );
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2 = (Graphics2D)g;

        if ( state == State.Level1 )
        {
            g.drawImage( floor, 0, 0, 1100, 800, this );

            g2.translate( -camera.getX(), -camera.getY() );

            handler.render( g );

            g2.translate( camera.getX(), camera.getY() );

            g.setColor( Color.red );
            g.fillRect( 5, 5, 300, 60 );

            g.setColor( Color.green );
            g.fillRect( 5, 5, playerHealth * 3, 60 );

            Font scoreFont = new Font( "arial", Font.BOLD, 35 );
            g.setFont( scoreFont );
            g.setColor( Color.yellow );
            g.drawString( "Score: " + score, 5, 95 );

            g.dispose();
            bs.show();

        }
        else if ( state == State.MenuState )
        {
            g.drawImage( menuScreen, 0, 0, 1100, 800, this );
            g.drawImage( menuTitle, 200, 20, 700, 200, this );

            g.drawImage( playButton, 425, 300, 250, 100, this );
            g.drawImage( helpButton, 425, 450, 250, 100, this );
            g.drawImage( quitButton, 425, 600, 250, 100, this );

            /*
             * Rectangle playButton = new Rectangle(450, 300, 300, 100);
             * g2.draw( playButton );
             *
             *
             * Rectangle helpButton = new Rectangle(450, 500, 300, 100);
             * g2.draw( helpButton );
             *
             * Rectangle quitButton = new Rectangle(450, 700, 300, 100);
             * g2.draw( quitButton );
             */

            g.dispose();
            bs.show();
        }
        else if ( state == state.HelpState )
        {
            g.drawImage( helpScreen, 0, 0, 1100, 800, this );
            g.drawImage( menuButton, 800, 600, 250, 100, this );

            g.dispose();
            bs.show();
        }
        else if ( state == state.OverState )
        {
            g.drawImage( gameOver, 0, 0, 1100, 800, this );

            g.dispose();
            bs.show();
        }
        else if ( state == State.LevelSelection )
        {
            g.drawImage( levelScreen, 0, 0, 1100, 800, this );
            g.drawImage( level1Button, 75, 350, 250, 100, this );
            g.drawImage( level2Button, 425, 350, 250, 100, this );
            g.drawImage( level3Button, 775, 350, 250, 100, this );

            g.dispose();
            bs.show();

        }
        else if ( state == State.Level2 )
        {
            g.drawImage( floor, 0, 0, 1100, 800, this );

            g2.translate( -camera.getX(), -camera.getY() );

            handler.render( g );

            g2.translate( camera.getX(), camera.getY() );

            g.setColor( Color.red );
            g.fillRect( 5, 5, 400, 60 );

            g.setColor( Color.green );
            g.fillRect( 5, 5, playerHealth * 4, 60 );

            Font scoreFont = new Font( "arial", Font.BOLD, 35 );
            g.setFont( scoreFont );
            g.setColor( Color.yellow );
            g.drawString( "Score: " + score, 5, 95 );

            g.dispose();
            bs.show();

        }
        else if ( state == state.Level3 )
        {
            g.drawImage( floor, 0, 0, 1100, 800, this );

            g2.translate( -camera.getX(), -camera.getY() );

            handler.render( g );

            g2.translate( camera.getX(), camera.getY() );

            g.setColor( Color.red );
            g.fillRect( 5, 5, 400, 60 );

            g.setColor( Color.green );
            g.fillRect( 5, 5, playerHealth * 4, 60 );

            Font scoreFont = new Font( "arial", Font.BOLD, 35 );
            g.setFont( scoreFont );
            g.setColor( Color.yellow );
            g.drawString( "Score: " + score, 5, 95 );

            g.dispose();
            bs.show();
        }
        else if ( state == State.WinState1 || state == State.WinState2 )
        {
            g.drawImage( winScreen, 0, 0, 1100, 800, this );
            g.drawImage( menuButton, 50, 600, 250, 100, this );
            g.drawImage( nextLevelButton, 800, 600, 250, 100, this );

            g.dispose();
            bs.show();
        }
        else if ( state == State.WinState3 )
        {
            g.drawImage( winScreen, 0, 0, 1100, 800, this );
            g.drawImage( menuButton, 50, 600, 250, 100, this );

            g.dispose();
            bs.show();
        }
    }


    /**
     *
     * This method takes in pixel map, with dimensions 64x64, and creates the
     * level based on the color of the pixels. A red pixel creates a Wall, a
     * blue one the Player, a green one a Monster, a yellow one a HealthPotion,
     * and a pink one a HardMonster.
     *
     * @param bi
     *            this is the pixel map to be loaded
     */
    public void loadWorld( BufferedImage bi )
    {
        int width = bi.getWidth();
        int height = bi.getHeight();

        for ( int i = 0; i < width; i++ )
        {
            for ( int j = 0; j < height; j++ )
            {
                int pixel = bi.getRGB( i, j );
                int red = ( pixel >> 16 ) & 0xff;
                int green = ( pixel >> 8 ) & 0xff;
                int blue = ( pixel ) & 0xff;

                if ( red == 255 && blue == 0 && green == 0 )
                {
                    handler.add( new Wall( i * 32,
                            j * 32,
                            Classification.Wall,
                            sprites ) );
                }

                if ( blue == 255 && red == 0 && green == 0 )
                {
                    handler.add( new Player( i * 32,
                            j * 32,
                            Classification.Player,
                            handler,
                            sprites,
                            this ) );
                }

                if ( green == 255 && red == 0 && blue == 0 )
                {
                    handler.add( new Monster( i * 32,
                            j * 32,
                            Classification.Monster,
                            handler,
                            sprites,
                            this ) );
                }

                if ( red == 255 && blue == 255 && green == 0 )
                {
                    handler.add( new HardMonster( i * 32,
                            j * 32,
                            Classification.HardMonster,
                            handler,
                            sprites,
                            this ) );
                }

                if ( red == 255 && green == 255 && blue == 0 )
                {
                    handler.add( new HealthPotion( i * 32,
                            j * 32,
                            Classification.HealthPotion,
                            sprites ) );
                }
            }
        }
    }


    /**
     *
     * Removes all the Entities on screen
     */
    public void clear()
    {
        for ( int i = 0; i < handler.entities.size(); i++ )
        {
            handler.remove( handler.entities.get( i ) );
        }
    }


    /**
     *
     * Clears the screen and does everything in the Constructor except create a
     * new Display. It calls in loadWorld to load in level1.
     */
    public void restartLevel1()
    {
        clear();
        start();

        playerHealth = 100;
        score = 0;

        handler = new Handler();
        camera = new Camera( 0, 0 );
        this.addKeyListener( new KeyManager( handler, this ) );

        ImageLoader loader = new ImageLoader();

        level1 = loader.loadImage( "/level1.png" );
        menuScreen = loader.loadImage( "/menu_screen.png" );
        menuTitle = loader.loadImage( "/menu_title.png" );
        playButton = loader.loadImage( "/play_button.png" );
        helpButton = loader.loadImage( "/help_button.png" );
        quitButton = loader.loadImage( "/quit_button.png" );

        helpScreen = loader.loadImage( "/instructions.png" );
        menuButton = loader.loadImage( "/menu_button.png" );

        gameOver = loader.loadImage( "/game_over_screen.png" );

        loadSS = loader.loadImage( "/sprites_ver25.png" );
        sprites = new Spritesheet( loadSS );

        floor = loader.loadImage( "/game_floor.png" );

        this.addMouseListener(
                new MouseManager( handler, camera, sprites, this ) );

        loadWorld( level1 );
        state = State.MenuState;
    }


    /**
     *
     * Clears the screen and does everything in the Constructor except create a
     * new Display. It calls in loadWorld to load in level2.
     */
    public void restartLevel2()
    {
        clear();
        start();

        playerHealth = 100;
        score = 0;

        handler = new Handler();
        camera = new Camera( 0, 0 );
        this.addKeyListener( new KeyManager( handler, this ) );

        ImageLoader loader = new ImageLoader();

        menuScreen = loader.loadImage( "/menu_screen.png" );
        menuTitle = loader.loadImage( "/menu_title.png" );
        playButton = loader.loadImage( "/play_button.png" );
        helpButton = loader.loadImage( "/help_button.png" );
        quitButton = loader.loadImage( "/quit_button.png" );

        helpScreen = loader.loadImage( "/instructions.png" );
        menuButton = loader.loadImage( "/menu_button.png" );

        gameOver = loader.loadImage( "/game_over_screen.png" );

        level2 = loader.loadImage( "/level2.png" );
        loadSS = loader.loadImage( "/sprites_ver25.png" );
        sprites = new Spritesheet( loadSS );

        floor = loader.loadImage( "/game_floor.png" );

        this.addMouseListener(
                new MouseManager( handler, camera, sprites, this ) );

        loadWorld( level2 );
        state = State.Level2;
    }


    /**
     *
     * Clears the screen and does everything in the Constructor except create a
     * new Display. It calls in loadWorld to load in level3.
     */
    public void restartLevel3()
    {
        clear();
        start();

        playerHealth = 100;
        score = 0;

        handler = new Handler();
        camera = new Camera( 0, 0 );
        this.addKeyListener( new KeyManager( handler, this ) );

        ImageLoader loader = new ImageLoader();

        menuScreen = loader.loadImage( "/menu_screen.png" );
        menuTitle = loader.loadImage( "/menu_title.png" );
        playButton = loader.loadImage( "/play_button.png" );
        helpButton = loader.loadImage( "/help_button.png" );
        quitButton = loader.loadImage( "/quit_button.png" );

        helpScreen = loader.loadImage( "/instructions.png" );
        menuButton = loader.loadImage( "/menu_button.png" );

        gameOver = loader.loadImage( "/game_over_screen.png" );

        level3 = loader.loadImage( "/level3.png" );
        loadSS = loader.loadImage( "/sprites_ver25.png" );
        sprites = new Spritesheet( loadSS );

        floor = loader.loadImage( "/game_floor.png" );

        this.addMouseListener(
                new MouseManager( handler, camera, sprites, this ) );

        loadWorld( level3 );
        state = State.Level3;
    }


    /**
     *
     * Main method which creates a new BigDungeon object to run the game
     *
     * @param args
     *            arguments
     */
    public static void main( String[] args )
    {
        BigDungeon bd = new BigDungeon();
    }
}
