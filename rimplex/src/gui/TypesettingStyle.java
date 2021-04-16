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
    italic.addAttribute(StyleConstants.Family, "TimesRoman");
    italic.addAttribute(StyleConstants.FontSize, 20);
    return italic;
  }
  
}
