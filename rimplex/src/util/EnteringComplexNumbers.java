package util;

import math.ComplexNumber;
import math.Operation;

/**
 * Utility class for parsing complex numbers.
 * 
 * @author Jackson Brantley
 *
 */
public final class EnteringComplexNumbers
{

  /**
   * Constructor, set to private as there is no need to create an instance of this class.
   */
  private EnteringComplexNumbers()
  {

  }

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
  public static ComplexNumber parseComplexNumber(final String input)
  {
    if (input == null)
      throw new NullPointerException();
    if (input.isEmpty())
      throw new IllegalArgumentException();

    double real = 0.0;
    double imaginary = 0.0;
    String operator = "";
    int index = 0;

    if (input.contains("+"))
    {
      operator = "+";
      index = input.indexOf(operator);
    }
    else if (input.contains("-"))
    {
      if (input.indexOf("-") == 0)
      {
        if (input.indexOf("-", input.indexOf("-") + 1) == -1)
          operator = "";
        else
        {
          operator = "-";
          index = input.indexOf("-", input.indexOf("-") + 1);
        }
      }
      else if (input.indexOf("-") != 0)
      {
        operator = "-";
        index = input.indexOf(operator);
      }
    }

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
        real = Double.parseDouble(input.substring(0, index));
        imaginary = Double.parseDouble(input.substring(index + 1, input.indexOf("i")));
        if (operator.equals("-"))
          imaginary *= -1;
      }
      catch (NumberFormatException e)
      {
        throw e;
      }
    }

    return new ComplexNumber(real, imaginary);
  }

  /**
   * Given a string, check to see if the string is a complex number.
   * 
   * @param input
   *          the string to check
   * @return true if a complex number, false otherwise
   */
  public static boolean isComplexNumber(String input)
  {
    try
    {
      parseComplexNumber(input);
      return true;
    }
    catch (Exception e)
    {
      return false;
    }
  }

  public static ComplexNumber parseEquation(final String input)
  {
    ComplexNumber a = parseComplexNumber(
        input.substring(input.indexOf('(') + 1, input.indexOf(')')));
    ComplexNumber b = parseComplexNumber(
        input.substring(input.lastIndexOf('(') + 1, input.lastIndexOf(')')));

    String operand = input.substring(input.indexOf(')') + 1, input.lastIndexOf('('));
    if (operand.contains("+"))
    {
      return Operation.add(a, b);
    }
    else if (operand.contains("*"))
    {
      return Operation.multiply(a, b);
    }
    if (operand.contains("/"))
    {
      return Operation.divide(a, b);
    }
    else
    {
      return Operation.subtract(a, b);
    }

  }

}
