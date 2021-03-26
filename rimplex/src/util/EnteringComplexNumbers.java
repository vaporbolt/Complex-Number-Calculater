package util;

import math.ComplexNumber;

/**
 * Utility class for parsing complex numbers.
 * 
 * @author Jackson Brantley
 *
 */
public class EnteringComplexNumbers
{

  /**
   * Given a string, convert the string into a complex number. If the string is missing either the
   * real number or the imaginary number, return a complex number with the missing number as 0.0.
   * 
   * @param input
   *          the string to convert to a complex number
   * @return the complex number
   * @throws NullPointerException
   *           if input is null
   * @throws IllegalArgumentException
   *           if input is empty
   * @throws NumberFormatException
   *           if input is not a valid complex number
   */
  public static ComplexNumber parseComplexNumber(String input)
  {
    if (input == null)
      throw new NullPointerException();
    if (input.isEmpty())
      throw new IllegalArgumentException();

    double real = 0.0;
    double imaginary = 0.0;
    String operator = "";

    if (input.contains("+"))
      operator = "+";
    else if (input.contains("-"))
      operator = "-";
    else if (input.contains("*"))
      operator = "*";
    else if (input.contains("/"))
      operator = "/";

    if (operator.isEmpty())
    {
      try
      {
        if (input.contains("i"))
          imaginary = Double.parseDouble(input.substring(0, input.indexOf("i")));
        else
          real = Double.parseDouble(input);
      }
      catch (NumberFormatException e)
      {
        throw e;
      }
    }
    else
    {
      try
      {
        real = Double.parseDouble(input.substring(0, input.indexOf(operator)));
        imaginary = Double
            .parseDouble(input.substring(input.indexOf(operator) + 1, input.indexOf("i")));
      }
      catch (NumberFormatException e)
      {
        throw e;
      }
    }

    return new ComplexNumber(real, imaginary);
  }
}
