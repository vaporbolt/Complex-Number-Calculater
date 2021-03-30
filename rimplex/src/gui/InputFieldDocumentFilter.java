package gui;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


/**
 * @author Seth Roper
 * @version 3/29/2020
 * document filter for the input field.
 * prevents characters to be entered after the i has been inputted.
 * 
 *
 */
public class InputFieldDocumentFilter extends DocumentFilter
{
  private String i = "i";
  private ArrayList<String> validChars;
  // dumb checkstyle thing.
  
  /**
   * Constructs an InputFiledDocumentFIlter.
   */
  public InputFieldDocumentFilter()
  {
    super();
    validChars = new ArrayList<String>();
    validChars.add("+");
    validChars.add("-");
    validChars.add(" ");
    validChars.add(i);
  }
  
  @Override
  public void insertString(final DocumentFilter.FilterBypass fb, final int offset,
      final String string, final AttributeSet attr) throws BadLocationException
  {

    // if the document has an i, don't insert the update.
    if (!fb.getDocument().getText(0, fb.getDocument().getLength()).contains(i))
    {
      if (string.equals(i))
        super.insertString(fb, offset, string, TypesettingStyle.applyTypesetting());
      else
        super.insertString(fb, offset, string, null);
    }
    else
    {
      Toolkit.getDefaultToolkit().beep();
    }
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

  
    // if the document has an i, don't insert the update.
    if (fb.getDocument().getText(0, fb.getDocument().getLength()) != null
        && !fb.getDocument().getText(0, fb.getDocument().getLength()).contains(i))
    {
      if (string.equals(i))
        super.replace(fb, offset, length, string, TypesettingStyle.applyTypesetting());
      else
        super.replace(fb, offset, length, string, null);
    }
    else
    {
      Toolkit.getDefaultToolkit().beep();
    }
  }
  
  /**
   * @return whether the character entered is a valid character of an imaginary number.
   * @param string the string whose valid input will be determined.
   * 
   */
  private boolean isValidInput(final String string) 
  {
    Iterator<String> iterator = this.validChars.iterator();
    boolean isValid = false;
    while(iterator.hasNext())
    {
      if(string.equals(iterator.next()))
      {
        isValid = true;
      }
    }
    
    return isValid;
  }
}
