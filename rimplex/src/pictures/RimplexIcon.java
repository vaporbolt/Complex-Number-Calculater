package pictures;

import java.awt.Image;

import java.io.IOException;
import java.io.InputStream;

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

  private ImageIcon icon;

  /**
   * Gets logo resource in package. file needs to be named logoRimplex.png.
   * 
   * @throws IOException
   *           If file can't be read
   */
  public RimplexIcon() throws IOException
  {
    try
    {
      InputStream s = this.getClass().getResourceAsStream("logoRimplex.png");
      Image image = ImageIO.read(s);
      icon = new ImageIcon(image);

    }
    catch (IOException e)
    {
      throw new IOException("that file could not be found!");
    }
  }

  /**
   * Adds icon to the Jframe.
   * 
   * @param frame
   *          JFrame
   */
  public void addIcon(final JFrame frame)
  {
    // scale it so it fits
    Image newimg = this.icon.getImage().getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);
    frame.setIconImage(newimg);
  }

}
