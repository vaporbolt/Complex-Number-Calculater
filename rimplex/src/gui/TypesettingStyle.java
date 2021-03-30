package gui;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 * Helper method to change "i" in strings to italics.
 * 
 * @author Jackson Brantley
 *
 */
public final class TypesettingStyle
{

  /**
   * Constructor, set to private as there is no need to create an instance of this class.
   */
  private TypesettingStyle()
  {
    
  }
  
  /**
   * Given a string, convert every "i" character to italics. If input is empty, return the empty string.
   * 
   * @return the new string with converted i characters
   */
  public static Style applyTypesetting()
  {
    StyleContext sc = new StyleContext();
    Style italic = sc.addStyle("BLACK", null);
    italic.addAttribute(StyleConstants.Italic, true);
    return italic;
  }
  
  /**
   * Replaces all i characters in display with italicized i characters.
   * 
   * @param pane the display
   */
  public static void displayTypesetting(JTextPane pane)
  {
    try
    { 
      // copy the text from display into placeholder string
      String s = pane.getDocument().getText(0, pane.getDocument().getLength());
      
      // length of the text that has been removed from string s, for indexing purposes
      int length = 0;
      
      // continue looping over string s while there are still i characters
      while (s.contains("i"))
      {
        // sets the current index to the next i character in display
        int index = s.indexOf("i") + length;
        
        // removes the un-italic i from display
        pane.getDocument().remove(index, 1);
        
        // adds the now italicized i to the location the un-italic i was in
        pane.getDocument().insertString(index, "i", TypesettingStyle.applyTypesetting());
        
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
