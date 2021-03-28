package gui;

import javax.swing.*;
import javax.swing.text.DocumentFilter;
import java.awt.*;

/**
 * @author Seth Roper
 * @version 3/24/2020
 * This class represents an Input field where the user can enter text.
 * An InputFeld object is a singleton object and contains a JTextArea of specific dimensions. 
 * this text will be filtered for proper input.
 * 
 * Note: Make a class that handles the flow layout of all GUI components. 
 *
 */
public class InputField
{

  private static boolean exists = false;
  // filters the Jtext area for correct input.
  private DocumentFilter filter;
  // contains the text area the user will type in.
  private JFormattedTextField field;

  private InputField()
  {
    // creates a formattedinput field with one column,
    this.field = new JFormattedTextField();
  }

  /**
   * Method which creates the InputField object.
   * 
   * @return an inputfield object.
   */
  public static InputField createInstance()
  {
    if (!exists)
    {
      exists = true;
      return new InputField();
    }

    else
    {
      throw new IllegalStateException("Input Field already exists");
    }
  }
  
  
  /**
   * @return the text field this input field contains.
   */
  public JTextField getTextField()
  {
    return this.field;
  }

}
