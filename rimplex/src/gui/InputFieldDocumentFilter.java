package gui;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * @author Seth Roper
 * @version 3/29/2020
 * document filter for the input field.
 * prevents characters to be entered after the i has been inputted.
 *
 */
public class InputFieldDocumentFilter extends DocumentFilter
{
  
  
  

  
  @Override
  public void insertString(final DocumentFilter.FilterBypass fb, final int offset,
      final String string, final AttributeSet attr) throws BadLocationException
  {
    
    System.out.println("bruh");
    
    // if the document has an i, don't insert the update.
    if(!fb.getDocument().getText(0, fb.getDocument().getLength() - 1).contains("i"))
    {
      super.insertString(fb, offset, string, attr);
    }
    else
    {
      Toolkit.getDefaultToolkit().beep();
    }
  }
  
  @Override
  public void replace (final DocumentFilter.FilterBypass fb, final int offset, final int length,
      final String string, final AttributeSet attr) throws BadLocationException
  {
    
    
    // if the document has an i, don't insert the update.
    if (fb.getDocument().getText(0, fb.getDocument().getLength()) != null
        && !fb.getDocument().getText(0, fb.getDocument().getLength()).contains("i"))
    {
      super.replace(fb, offset, length, string, attr);
    }
    else
    {
      Toolkit.getDefaultToolkit().beep();
    }
  }
}
