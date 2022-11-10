import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 *
 * ImageLoader loads in each image by taking in the file path.
 *
 * @author Shiv Sankhavaram
 * @version May 26, 2020
 * @author Period: 5
 * @author Assignment: BigDungeon
 *
 * @author Sources: None
 */
public class ImageLoader
{
    private BufferedImage bi;


    /**
     *
     * Loads in an image from the project folder using the file path, so it can
     * be shown on the screen
     *
     * @param path
     *            the image's file path
     * @return the image
     */
    public BufferedImage loadImage( String path )
    {
        try
        {
            bi = ImageIO.read( getClass().getResource( path ) );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }

        return bi;
    }
}