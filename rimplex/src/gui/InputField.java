package gui;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import util.EnteringComplexNumbers;
import math.ComplexNumber;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
    document = this.field.getDocument();
    this.filter = new  InputFieldDocumentFilter();
    this.abstractDocument = (AbstractDocument) this.document;
    this.abstractDocument.setDocumentFilter(filter);
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
  
  /**
   * When the user hits enter, the equation from inputField is calculated,
   * and the result is transfered to display.
   * 
   * @param display the display
   * @param nums the list of complex numbers
   */
  public void enterText(DisplayComponent display, ArrayList<ComplexNumber> nums)
  {
    // to get the correct InputMap
    int condition = JComponent.WHEN_FOCUSED;  
    
    // get maps for binding from the inputField JTextPane
    InputMap inputMap = field.getInputMap(condition);
    ActionMap actionMap = field.getActionMap();

    // the key stroke we want to capture
    KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);

    // tell input map that we are handling the enter key
    inputMap.put(enterStroke, enterStroke.toString());
    
    // tell action map just how we want to handle the enter key
    actionMap.put(enterStroke.toString(), new AbstractAction() {

      private static final long serialVersionUID = 1L;
      
      @Override
       public void actionPerformed(ActionEvent arg0) {
          try
          {
            ComplexNumber result;
            
            // gets the result of the equation in the input field
            result = EnteringComplexNumbers.parseEquation(field.getText());
            
            // adds the result to the display
            display.addText(field.getText() + " = " + result.toString() + "\n");
            
            // sets the input field to the result to use for the next calculation
            field.setText(result.toString());
            
            // apply typestting to display
            display.displayTypesetting(0, display.getText().length());
          }
          catch (Exception e)
          {
            // beep if there are already two operands
            Toolkit.getDefaultToolkit().beep();
          }
       }
    });
  }
}
