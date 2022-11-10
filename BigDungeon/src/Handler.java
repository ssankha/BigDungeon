import java.awt.Graphics;
import java.util.LinkedList;


/**
 *
 * The Handler class has a LinkedList that holds every Entity in our game. This
 * class is used to tick() and render(), as well as help with user input
 *
 * @author Shiv Sankhavaram
 * @version May 25, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class Handler
{
    LinkedList<Entity> entities = new LinkedList<Entity>();

    private boolean up = false;

    private boolean down = false;

    private boolean left = false;

    private boolean right = false;


    /**
     *
     * returns if the user pressed W, prompting the player to move up or not
     *
     * @return if the player pressed W
     */
    public boolean isUp()
    {
        return up;
    }


    /**
     *
     * sets the up field
     *
     * @param up
     *            the new result
     */
    public void setUp( boolean up )
    {
        this.up = up;
    }


    /**
     *
     * returns if the user pressed S, prompting the player to move down or not
     *
     * @return if the player pressed S
     */
    public boolean isDown()
    {
        return down;
    }


    /**
     * sets the field down
     *
     * @param down
     *            the new result
     */
    public void setDown( boolean down )
    {
        this.down = down;
    }


    /**
     *
     * returns if the user pressed A, prompting the player to move left or not
     *
     * @return if the player pressed A
     */
    public boolean isLeft()
    {
        return left;
    }


    /**
     *
     * sets the field left
     *
     * @param left
     *            the new result
     */
    public void setLeft( boolean left )
    {
        this.left = left;
    }


    /**
     *
     * returns if the user pressed D, prompting the player to move right or not
     *
     * @return if the player pressed D
     */
    public boolean isRight()
    {
        return right;
    }


    /**
     *
     * sets the field right
     *
     * @param right
     *            the new result
     */
    public void setRight( boolean right )
    {
        this.right = right;
    }


    /**
     *
     * Iterates through the list of entities and calls their tick() to update
     * them
     */
    public void tick()
    {
        for ( int i = 0; i < entities.size(); i++ )
        {
            Entity e = entities.get( i );
            e.tick();
        }

    }


    /**
     *
     * Iterates through the list of entities and calls their render() to update
     * them
     */
    public void render( Graphics g )
    {
        for ( int i = 0; i < entities.size(); i++ )
        {
            Entity e = entities.get( i );
            e.render( g );
        }
    }


    /**
     *
     * Adds an entity to the linked list
     *
     * @param e
     *            the new entity
     */
    public void add( Entity e )
    {
        entities.add( e );
    }


    /**
     *
     * removes an entity from the linked list
     *
     * @param e
     *            the entity
     */
    public void remove( Entity e )
    {
        entities.remove( e );
    }

}