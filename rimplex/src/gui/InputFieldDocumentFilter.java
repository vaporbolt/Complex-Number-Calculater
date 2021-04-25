package gui;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * document filter for the input field. prevents characters to be entered after the i has been
 * inputted.
 * 
 * @author Seth Roper
 * @version 3/29/2020
 *
 */
public class InputFieldDocumentFilter extends DocumentFilter
{
  private String i = "i";
  private ArrayList<String> validChars;

  /**
   * Constructs an InputFiledDocumentFIlter.
   */
  public InputFieldDocumentFilter()
  {
    super();
    validChars = new ArrayList<String>();
    validChars.add("+");
    validChars.add("-");
    validChars.add("*");
    validChars.add("/");
    validChars.add(".");
    validChars.add(" ");
    validChars.add("(");
    validChars.add(")");
    validChars.add("c");
    validChars.add("o");
    validChars.add("n");
    validChars.add("^");
    validChars.add(i);
  }

  @Override
  public void insertString(final DocumentFilter.FilterBypass fb, final int offset,
      final String string, final AttributeSet attr) throws BadLocationException
  {
    if (string.equals(i))
      super.insertString(fb, offset, string, TypesettingStyle.applyTypesetting(false));
    else
      super.insertString(fb, offset, string, null);
  }

  @Override
  public void replace(final DocumentFilter.FilterBypass fb, final int offset, final int length,
      final String string, final AttributeSet attr) throws BadLocationException
  {

    try
    {
      Integer.parseInt(string);
    }

    catch (NumberFormatException e)
    {
      if (!this.isValidInput(string))
      {
        Toolkit.getDefaultToolkit().beep();
        return;
      }
    }

    if (string.equals(i))
      super.replace(fb, offset, length, string, TypesettingStyle.applyTypesetting(false));
    else
      super.replace(fb, offset, length, string, null);
  }

  /**
   * @param string
   *          the string whose valid input will be determined.
   * @return whether the character entered is a valid character of an imaginary number.
   * 
   */
  private boolean isValidInput(final String string)
  {
    Iterator<String> iterator = this.validChars.iterator();
    boolean isValid = false;
    while (iterator.hasNext())
    {
      if (string.equals(iterator.next()))
      {
        isValid = true;
      }
    }

    return isValid;
  }
}
