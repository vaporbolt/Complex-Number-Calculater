package color;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Holds the colors included for rimplex to implement. Colors read from file.
 * 
 * @author Endre Szakal
 * @version 4/21/2021
 *
 */
public class ColorScheme
{
  private static boolean exists = false;

  private Color fontColor;
  private Color backgroundColor;

  private Color buttonBackgroundColor;
  private Color buttonBorderColor;
  private Color buttonDefault;
  private Color buttonSecondary;
  private Color buttonTertiary;

  private Color historyBackgroundColor;
  private Color planeBackgroundColor;
  private Color stepBackgroundColor;
  private Color settingsBackgroundColor;

  private Color fieldColor;

  private String regex = " ";

  /**
   * Private Constructor.
   * 
   * @param in
   *          BufferedReader
   * @throws IOException
   *           If file reading fails
   */
  private ColorScheme(final BufferedReader in) throws IOException
  {
    readColorFile(in);
  }

  /**
   * Factory method for ColorScheme.
   * 
   * @param in
   *          BufferedReader
   * @return ColorScheme
   * @throws IOException,
   *           IllegalStateException
   */
  public static ColorScheme createInstance(final BufferedReader in)
      throws IOException, IllegalStateException
  {
    if (!exists)
      return new ColorScheme(in);
    exists = true;

    throw new IllegalStateException("A Color Scheme already exists for Rimplex!");
  }

  /**
   * Reads passed color file and parses color information.
   * 
   * @param in
   *          color file
   * @throws IOException
   *           If file reading fails
   */
  public void readColorFile(final BufferedReader in) throws IOException
  {
    // Product Wide
    String[] font = in.readLine().split(regex);
    this.fontColor = new Color(Integer.parseInt(font[0]), Integer.parseInt(font[1]),
        Integer.parseInt(font[2]));

    String[] back = in.readLine().split(regex);
    this.backgroundColor = new Color(Integer.parseInt(back[0]), Integer.parseInt(back[1]),
        Integer.parseInt(back[2]));

    // Buttons
    String[] bBack = in.readLine().split(regex);
    buttonBackgroundColor = new Color(Integer.parseInt(bBack[0]), Integer.parseInt(bBack[1]),
        Integer.parseInt(bBack[2]));

    String[] bBorder = in.readLine().split(regex);
    this.buttonBorderColor = new Color(Integer.parseInt(bBorder[0]), Integer.parseInt(bBorder[1]),
        Integer.parseInt(bBorder[2]));

    String[] bDefault = in.readLine().split(regex);
    this.buttonDefault = new Color(Integer.parseInt(bDefault[0]), Integer.parseInt(bDefault[1]),
        Integer.parseInt(bDefault[2]));

    String[] bSecondary = in.readLine().split(regex);
    this.buttonSecondary = new Color(Integer.parseInt(bSecondary[0]),
        Integer.parseInt(bSecondary[1]), Integer.parseInt(bSecondary[2]));

    String[] bTertiary = in.readLine().split(regex);
    this.buttonTertiary = new Color(Integer.parseInt(bTertiary[0]), Integer.parseInt(bTertiary[1]),
        Integer.parseInt(bTertiary[2]));

    // Section Specific
    String[] hBack = in.readLine().split(regex);
    this.historyBackgroundColor = new Color(Integer.parseInt(hBack[0]), Integer.parseInt(hBack[1]),
        Integer.parseInt(hBack[2]));

    String[] pBack = in.readLine().split(regex);
    this.planeBackgroundColor = new Color(Integer.parseInt(pBack[0]), Integer.parseInt(pBack[1]),
        Integer.parseInt(pBack[2]));

    String[] stepBack = in.readLine().split(regex);
    this.stepBackgroundColor = new Color(Integer.parseInt(stepBack[0]),
        Integer.parseInt(stepBack[1]), Integer.parseInt(stepBack[2]));

    String[] setBack = in.readLine().split(regex);
    this.settingsBackgroundColor = new Color(Integer.parseInt(setBack[0]),
        Integer.parseInt(setBack[1]), Integer.parseInt(setBack[2]));

    String[] field = in.readLine().split(regex);
    this.fieldColor = new Color(Integer.parseInt(field[0]), Integer.parseInt(field[1]),
        Integer.parseInt(field[2]));
  }

  /**
   * @return the buttonDefault
   */
  public Color getButtonDefault()
  {
    return buttonDefault;
  }

  /**
   * @return the buttonSecondary
   */
  public Color getButtonSecondary()
  {
    return buttonSecondary;
  }

  /**
   * @return the buttonTertiary
   */
  public Color getButtonTertiary()
  {
    return buttonTertiary;
  }

  /**
   * @return the exists
   */
  public static boolean isExists()
  {
    return exists;
  }

  /**
   * @return the fontColor
   */
  public Color getFontColor()
  {
    return fontColor;
  }

  /**
   * @return the backgroundColor
   */
  public Color getBackgroundColor()
  {
    return backgroundColor;
  }

  /**
   * @return the buttonBackgroundColor
   */
  public Color getButtonBackgroundColor()
  {
    return buttonBackgroundColor;
  }

  /**
   * @return the buttonBorderColor
   */
  public Color getButtonBorderColor()
  {
    return buttonBorderColor;
  }

  /**
   * @return the historyBackgroundColor
   */
  public Color getHistoryBackgroundColor()
  {
    return historyBackgroundColor;
  }

  /**
   * @return the planeBackgroundColor
   */
  public Color getPlaneBackgroundColor()
  {
    return planeBackgroundColor;
  }

  /**
   * @return the stepBackgroundColor
   */
  public Color getStepBackgroundColor()
  {
    return stepBackgroundColor;
  }

  /**
   * @return the settingsBackgroundColor
   */
  public Color getSettingsBackgroundColor()
  {
    return settingsBackgroundColor;
  }

  /**
   * @return the fieldColor
   */
  public Color getFieldColor()
  {
    return fieldColor;
  }

  /**
   * @return the regex
   */
  public String getRegex()
  {
    return regex;
  }
}
