/**
 *
 * Camera zooms in on the screen and makes it so that the player is followed
 * when the user moves it
 *
 * @author Shiv Sankhavaram
 * @version May 25, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class Camera
{
    private float x;

    private float y;


    /**
     * The constructor sets the camera's x and y
     *
     * @param x
     *            the camera's x
     * @param y
     *            the camera's y
     */
    public Camera( float x, float y )
    {
        this.x = x;
        this.y = y;
    }


    /**
     *
     * This method updates the camera by taking the entity's (the player) X and
     * subtracting it by the camera's X and half the width of the screen and
     * then multiplying it by 0.05f. The variables are float because that way we
     * can get more precise and smoother motion following the player. The
     * camera's Y is updated by taking the entity's (the player) Y and
     * subtracting it by the camera's X and subtracting half the height of the
     * screen and then multiplying it by 0.05f. The variables are float because
     * that way we can get more precise and smoother motion following the
     * player.
     *
     * @param e
     *            the player
     */
    public void tick( Entity e )
    {
        x += ( ( e.getX() - x ) - 1100 / 2 ) * 0.05f;
        y += ( ( e.getY() - y ) - 800 / 2 ) * 0.05f;

        if ( x <= 0 )
        {
            x = 0;
        }

        if ( x >= 825 )
        {
            x = 825;
        }

        if ( y <= 0 )
        {
            y = 0;
        }

        if ( y >= 780 )
        {
            y = 780;
        }
    }


    /**
     *
     * returns the camera's X
     *
     * @return the X
     */
    public float getX()
    {
        return x;
    }


    /**
     *
     * sets the camera's X
     *
     * @param x
     *            new X
     */
    public void setX( float x )
    {
        this.x = x;
    }


    /**
     *
     * returns the camera's Y
     *
     * @return the Y
     */
    public float getY()
    {
        return y;
    }


    /**
     *
     * sets the camera's Y
     *
     * @param y
     *            new Y
     */
    public void setY( float y )
    {
        this.y = y;
    }
}