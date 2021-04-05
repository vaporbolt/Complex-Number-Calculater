package gui;

import java.awt.Color;

import javax.swing.*;
import javax.swing.text.BadLocationException;

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
   * gets text from display.
   * @return String
   */
  public String getText()
  {
    return this.text;
  }
  
  /**
   * replaces current display text with the given String
   * @param replacement String
   */
  public void replaceText(String replacement)
  {
    this.text = replacement;
    this.panel.setText(text);
  }
  
  /**
   * clears display of all text.
   */
  public void reset()
  {
    text = "";
    this.panel.setText(text);
  }

  /**
   * Replaces all i characters in display with italicized i characters.
   * 
   * @param start the offset of the string to typeset
   * @param end the end of the string to typeset
   */
  public void displayTypesetting(int start, int end)
  {
    try
    { 
      // copy the text from display into placeholder string
      String s = panel.getDocument().getText(start, end);
      
      // length of the text that has been removed from string s, for indexing purposes
      int length = 0;
      
      // continue looping over string s while there are still i characters
      while (s.contains("i"))
      {
        // sets the current index to the next i character in display
        int index = s.indexOf("i") + length;
        
        // removes the un-italic i from display
        panel.getDocument().remove(index, 1);
        
        // adds the now italicized i to the location the un-italic i was in
        panel.getDocument().insertString(index, "i", TypesettingStyle.applyTypesetting());
        
        // sets length to the length of text that has been removed from string s
        length += s.substring(0, s.indexOf("i") + 1).length();
        
        // removes everything in the string before and including the current i character
        s = s.substring(s.indexOf("i") + 1);
      }
    }
    catch (BadLocationException e)
    {
      
    }
  }
}
