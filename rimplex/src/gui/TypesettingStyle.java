package gui;

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
   * Given a string, convert every "i" character to italics. If input is empty, return the empty
   * string.
   * 
   * @param isDisplay
   *          boolean
   * @return the new string with converted i characters
   */
  public static Style applyTypesetting(final boolean isDisplay)
  {
    StyleContext sc = new StyleContext();
    Style italic = sc.addStyle("BLACK", null);
    italic.addAttribute(StyleConstants.Italic, true);
    italic.addAttribute(StyleConstants.Family, "TimesRoman");
    if (isDisplay)
      italic.addAttribute(StyleConstants.FontSize, 16);
    else
      italic.addAttribute(StyleConstants.FontSize, 20);
    return italic;
  }

}
