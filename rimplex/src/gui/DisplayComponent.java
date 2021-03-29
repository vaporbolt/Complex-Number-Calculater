package gui;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.*;

import math.ComplexNumber;

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
  
  private JPanel panel;

  /**
   * Private constructor.
   */
  private DisplayComponent()
  {
    panel = new JPanel();
    panel.setBackground(Color.lightGray);
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
   * adds the complex number to display in correct format.
   * @param complexNumber ComplexNumber
   */
  public void addComplexNumber(ComplexNumber complexNumber)
  {
    String result = "(" + complexNumber.getReal() + " + ";
    result += "" + complexNumber.getImaginary() + "i)";
    panel.add(new JLabel(result));
  }
  
  /**
   * adds text to the display panel.
   * @param text String
   */
  public void addText(String text)
  {
    panel.add(new JLabel(" " + text + " "));
  }

  /**
   * gets JPanel.
   * @return JPanel
   */
  public JPanel getPanel()
  {
    return this.panel;
  }
  
}
