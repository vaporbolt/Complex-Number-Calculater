package gui;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import util.EnteringComplexNumbers;
import visualization.CartesianPlane;
import math.ComplexNumber;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.util.Locale;

/**
 * @author Seth Roper
 * @version 3/24/2020 This class represents an Input field where the user can enter text. An
 *          InputFeld object is a singleton object and contains a JTextArea of specific dimensions.
 *          this text will be filtered for proper input.
 * 
 *          Note: Make a class that handles the flow layout of all GUI components.
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
  private Document document;

  private AbstractDocument abstractDocument;

  private final String i = "i";

  private InputField()
  {
    // creates a formattedinput field with one column,
    this.field = new JTextPane();
    document = this.field.getDocument();
    this.filter = new InputFieldDocumentFilter();
    this.abstractDocument = (AbstractDocument) this.document;
    this.abstractDocument.setDocumentFilter(filter);
    this.field.setBackground(new Color(199, 238, 255));
    
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
   * helper method that prevents input after the "i" is typed.
   */
  public void preventInput()
  {
    try
    {
      if (document.getText(0, document.getLength()).contains(i))
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
   * When the user hits enter, the equation from inputField is calculated, and the result is
   * transfered to display and graphed.
   * 
   * @param display
   *          DisplayComponent
   * @param plane
   *          CartesianPlane
   * @param stepWindow
   *          StepDisplay
   */
  public void enterText(final DisplayComponent display, final CartesianPlane plane,
      final StepDisplay stepWindow)
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
    actionMap.put(enterStroke.toString(), new AbstractAction()
    {

      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(final ActionEvent arg0)
      {
        try
        {
          ComplexNumber result;

          // gets the result of the equation in the input field
          result = EnteringComplexNumbers.parseEquation(field.getText());

          // adds the result to the display
          Locale locale = Locale.getDefault();
          String answer = result.toString() + "\n";
          String format = String.format(locale, field.getText() + " = %s", answer);
          display.addText(format);
          stepWindow.getPane().setText(result.getSteps());
          stepWindow.applyLanguage();
          stepWindow.displayTypesetting(0, stepWindow.getPane().getText().length());

          // sets the input field to the result to use for the next calculation
          field.setText(String.format(locale, "%s", result.toString()));
          plane.addPoint(result);

          // apply typestting to display
          display.displayTypesetting(0, display.getText().length());
          inputTypesetting(0, field.getText().length());
        }
        catch (IllegalStateException e)
        {
          // beep if there are already two operands
          Toolkit.getDefaultToolkit().beep();
        }
      }
    });
  }

  /**
   * Replaces all i characters in display with italicized i characters.
   * 
   * @param start
   *          the offset of the string to typeset
   * @param end
   *          the end of the string to typeset
   */
  public void inputTypesetting(final int start, final int end)
  {
    try
    {
      // copy the text from display into placeholder string
      String s = field.getDocument().getText(start, end);

      // length of the text that has been removed from string s, for indexing purposes
      int length = 0;

      // continue looping over string s while there are still i characters
      while (s.contains(i))
      {
        // sets the current index to the next i character in display
        int index = s.indexOf(i) + length;

        // removes the un-italic i from display
        field.getDocument().remove(index, 1);

        // adds the now italicized i to the location the un-italic i was in
        field.getDocument().insertString(index, i, TypesettingStyle.applyTypesetting(false));

        // sets length to the length of text that has been removed from string s
        length += s.substring(0, s.indexOf(i) + 1).length();

        // removes everything in the string before and including the current i character
        s = s.substring(s.indexOf(i) + 1);
      }
    }
    catch (BadLocationException e)
    {

    }
  }
}
