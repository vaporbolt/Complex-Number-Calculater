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

  /**
   * Given an equation, return the ComplexNumber that is the evalutation of the equation.
   * 
   * @param input
   *          the equation as a string
   * @return a ComplexNumber, or null if it is not a proper equation
   */
  public static ComplexNumber parseEquation(final String input)
  {
    String number = "()0123456789-.i-";
    String operations = "+-*/^";
    boolean foundFirst = false;
    boolean foundOperation = false;
    char operand = 0;
    ComplexNumber a = null;
    ComplexNumber b = null;
    
    
    int i = 0;
    while (i < input.length())
    {
      char token = input.charAt(i);
      if (token == '(')
      {
        if (!foundFirst)

        {
          a = parseComplexNumber(input.substring(i + 1, input.indexOf(')')));
          i = input.indexOf(')');
          foundFirst = true;
        }
        else
        {
          b = parseComplexNumber(input.substring(i + 1, input.lastIndexOf(')')));
          break;
        }
      }
      else if (foundFirst && !foundOperation && operations.indexOf(token) != -1)
      {
        operand = token;
        foundOperation = true;
      }
      else if (number.indexOf(token) != -1)
      {
        int j = i;

        while (number.indexOf(token) != -1 && j < input.length() - 1)
        {
          j++;
          token = input.charAt(j);
        }

        if (!foundFirst)
        {
          a = parseComplexNumber(input.substring(i, j));
          foundFirst = true;
          i = j - 1;
        }
        else
        {
          b = parseComplexNumber(input.substring(i));
          i = j;
        }
      }
      i++;
    }

    if(input.contains("con"))
    {
      return a.conjugate();
    }
    
    switch (operand)
    {
      case '+':
        return Operation.add(a, b);
      case '*':
        return Operation.multiply(a, b);
      case '/':
        return Operation.divide(a, b);
      case '-':
        return Operation.subtract(a, b);
      case '^':
        return Operation.exponential(a, (int) b.getReal());
      default:
        return null;
    }

  }

}
