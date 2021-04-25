package pictures;

import java.awt.Image;
import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * A Logo object is a Singleton Object which stores and sets the logo.
 * 
 * @author Seth Roper
 * @version 4/22/2021
 *
 */
public class RimplexIcon
{
  private static String defaultPath = "/pictures/logoRimplex.png";
  private static boolean exists = false;
  // stores the ImageIcon
  private ImageIcon icon;
  // the frame who's logo will be added.
  private JFrame frame;

  /**
   * @param icon
   *          the imageIcon.
   * @param frame
   *          the Jframe.
   */
  private RimplexIcon(final ImageIcon icon, final JFrame frame)
  {
    this.icon = icon;
    this.frame = frame;
    // scale it so it fits
    Image newimg = this.icon.getImage().getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);
    this.frame.setIconImage(newimg);
  }

  /**
   * @param path
   *          the path to the icon to be used
   * @param framee
   *          the Jframe where the icon will be added.
   * @throws IllegalStateException
   *           If a RimplexIcon already exists.
   * @return a new RimplexIcon object if one does not exists
   */
  public static RimplexIcon createInstance(final String path, final JFrame framee)
      throws IllegalStateException
  {
    // if an icon already exists, throw an exception.
    if (exists)
    {
      throw new IllegalStateException("Rimplex icon already exists");
    }

    exists = true;

    ImageIcon icon = null;

    // if args is empty, use default logo.
    if (path == null || path.length() == 0)
    {
      icon = new ImageIcon(RimplexIcon.class.getResource(defaultPath));
    }

    else
    {
      try
      {
        // check if it's a valid path
        ImageIO.read(new File(path));
        icon = new ImageIcon(path);
      }

      catch (IOException e)
      {
        System.out.println("that file could not be found. default icon will be used.");
        icon = new ImageIcon(RimplexIcon.class.getResource(defaultPath));
      }
    }

    return new RimplexIcon(icon, framee);
  }

}
