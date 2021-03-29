package gui;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
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
  private InputFieldDocumentFilter filter;
  // contains the text area the user will type in.
  private JTextPane field;
  // the document this input field contains
  private  Document document;
  
  private AbstractDocument abstractDocument;

  private InputField()
  {
    // creates a formattedinput field with one column,
    this.field = new JTextPane();
    document = this.getTextField().getDocument();
    this.filter = new  InputFieldDocumentFilter();
    abstractDocument = (AbstractDocument) this.getTextField().getDocument();
    this.abstractDocument.setDocumentFilter(new InputFieldDocumentFilter());
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
  public JTextPane getTextField()
  {
    return this.field;
  }
  
  
  /**
   * helper method that prevents input after the "I" is typed.
   */
  public void preventInput()
  {
    try
    {
      if(document.getText(0, document.getLength()).contains("i"))
      {
        document.remove(document.getLength() - 2, document.getLength() - 1);
      }
    }
    catch (BadLocationException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  
  /**
   * clears the input field.
   */
  public void clear()
  {
    try
    {
      document.remove(0, document.getLength());
    }
    catch (BadLocationException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
