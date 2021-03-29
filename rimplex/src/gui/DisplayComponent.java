package gui;

import java.awt.GridBagLayout;

import javax.swing.*;

/**
 * Singleton that creates the display for the Rimplex expressions.
 * 
 * @author Endre Szakal
 * @version 3/28/2021
 *
 */
public class DisplayComponent
{

  // tracks if an instance exists of the Display.
  private static boolean exists = false;

  private JTextArea text;
  // provides scroll for the textarea
  private JScrollPane scrollPane;

  /**
   * Private constructor.
   */
  private DisplayComponent()
  {
    text = new JTextArea();
    text.setEditable(false);
    text.setLineWrap(true);
    text.setWrapStyleWord(true);
    scrollPane = new JScrollPane(text);
  }

  /**
   * Checks if a DisplayComponent already exists before creating a new instance of it.
   * 
   * @return DisplayComponent
   */
  public static DisplayComponent createInstance()
  {
    if (!exists)
    {
      exists = true;
      return new DisplayComponent();
    }
    else
    {
      throw new IllegalStateException("A DisplayComponent already exists!");
    }
  }

  /**
   * Text setter.
   * @param text String
   */
  public void setText(String text)
  {
    this.text.setText(text);
  }

  /**
   * TextArea getter.
   * @return JTextArea
   */
  public JTextArea getText()
  {
    return this.text;
  }
  
  /**
   * gets the scrollPane.
   * @return JScrollPane
   */
  public JScrollPane getScrollPane()
  {
    return this.scrollPane;
  }

}
