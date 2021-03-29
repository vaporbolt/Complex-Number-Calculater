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
  
  // displays complex numbers, operators, results
  private JTextPane panel;
  
  // tracks text inside the display
  private String text;

  /**
   * Private constructor.
   */
  private DisplayComponent()
  {
    panel = new JTextPane();
    panel.setBackground(Color.lightGray);
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.setEditable(false);
   
    text = "";
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
    text += complexNumber.toString();
    this.panel.setText(text);
  }
  
  /**
   * adds a text string to the display panel.
   * @param text String
   */
  public void addText(String text)
  {
    this.text += text;
    this.panel.setText(this.text);
  }

  /**
   * gets JPanel.
   * @return JPanel
   */
  public JTextPane getPanel()
  {
    return this.panel;
  }
  
  /**
   * clears display of all text.
   */
  public void reset()
  {
    text = "";
    this.panel.setText(text);
  }
  
}
