package gui;

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
   * @param input 
   *          the string to be converted
   * @return the new string with converted i characters
   * @throws IllegalArgumentException
   *          if input is null
   */
  public static String applyTypesetting(final String input)
  {
    if (input == null) throw new IllegalArgumentException();
    if (input.isEmpty()) return input;
    return input.replaceAll("i", "<i>i</i>");
  }
}
