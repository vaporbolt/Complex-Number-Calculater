package gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
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
    
    // if the document has an i, don't insert the update.
    if(fb.getDocument().getText(0, fb.getDocument().getLength() - 1).contains("i"))
    {
      return;
    }
    super.insertString(fb, offset, string, attr);
  }
  
}
