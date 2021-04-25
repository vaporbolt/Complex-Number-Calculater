package math;

import java.util.ResourceBundle;

/**
 * ComplexNumber class. It can represent either a real number, an imaginary number, or a real number
 * plus an imaginary number
 * 
 * @author Sebastien Roy
 *
 */
public class ComplexNumber
{
  private static final ResourceBundle STRINGS = ResourceBundle.getBundle("languages.Strings");
  private double real;
  private double imaginary;
  private String steps;

  private int sign;

  private final String step = "Step";
  private final String equalNewline = " =\n";
  private final String one = " 1:\n";
  private final String two = " 2:\n";
  private final String plus = " + ";
  private final String equals = "    = ";

  /**
   * ComplexNumber Constructor.
   * 
   * @param real
   *          the value of the real number.
   * @param imaginary
   *          the value of the imaginary value;
   */
  public ComplexNumber(final double real, final double imaginary)
  {
    this.real = real;
    this.imaginary = imaginary;
    this.steps = "";

    this.sign = 1;
  }

  /**
   * Factory method that creates a complex number that represents a real number.
   * 
   * @param real
   *          the value of the real number
   * @return a new Complex Number
   */
  public static ComplexNumber constructReal(final double real)
  {
    return new ComplexNumber(real, 0.0);
  }

  /**
   * Factory method that creates a complex number that represents a imaginary number.
   * 
   * @param imaginary
   *          the value of the imaginary number
   * @return a new Complex Number
   */
  public static ComplexNumber constructImaginary(final double imaginary)
  {
    return new ComplexNumber(0.0, imaginary);
  }

  /**
   * @return the real value
   */
  public double getReal()
  {
    return real * sign;
  }

  /**
   * @param real
   *          value the real to set
   */
  public void setReal(final double real)
  {
    this.real = real;
  }

  /**
   * @return the imaginary value
   */
  public double getImaginary()
  {
    return imaginary * sign;
  }

  /**
   * @param imaginary
   *          the imaginary to set value
   */
  public void setImaginary(final double imaginary)
  {
    this.imaginary = imaginary;
  }

  /**
   * Switches the ComplexNumber from positive to negative or from negative to positive.
   */
  public void negate()
  {
    this.sign *= -1;
  }

  /**
   * Returns the conjugate of this number.
   * 
   * @return a new ComplexNUmber
   */
  public ComplexNumber conjugate()
  {
    final String spacing = "    ";

    String s = "con" + this.toString() + equalNewline;
    s += STRINGS.getString(step) + one;
    s += spacing + this.imaginaryPart().toString() + " * - 1 =\n\n";
    s += STRINGS.getString(step) + two;
    s += spacing + this.realPart() + plus + this.getImaginary() * -1 + "i =\n\n";
    ComplexNumber result = new ComplexNumber(this.real, this.imaginary * -1);
    s += STRINGS.getString(step) + " 3:\n";
    s += spacing + result.toString();
    result.setSteps(s);
    return result;
  }

  /**
   * Returns the real value of this complex number.
   * 
   * @return a new complex number
   */
  public ComplexNumber realPart()
  {
    ComplexNumber result = new ComplexNumber(this.real, 0.0);
    String s = "real" + this.toString() + equalNewline;
    s += STRINGS.getString(step) + one;
    s += equals + result.toString();
    result.setSteps(s);
    return result;
  }

  /**
   * Returns the imaginary value of this complex number.
   * 
   * @return a new complex number
   */
  public ComplexNumber imaginaryPart()
  {
    ComplexNumber result = new ComplexNumber(0.0, this.imaginary);
    String s = "imaginary" + this.toString() + equalNewline;
    s += STRINGS.getString(step) + one;
    s += equals + result.toString();
    result.setSteps(s);
    return result;
  }

  /**
   * Returns the polar form of the ComplexNumber.
   * 
   * @return the polar form of the Complex Number
   */
  public ComplexNumber polarForm()
  {
    return new PolarForm(this.real, this.imaginary);
  }

  /**
   * return a complex number with parentheses around it.
   * 
   * @return String
   */
  public String toString()
  {
    return toString(true);
  }

  /**
   * Converts the complex number into a string. If either the real or imaginary number is an integer
   * it will be displayed without the decimal.
   * 
   * @param parentheses
   *          if true add parentheses around the complex number
   * @return the string form of a complex number.
   */
  public String toString(final boolean parentheses)
  {
    final String openParenthese = "(";
    final String iParenthese = "i)";

    String complete;
    String realNum = "";
    String imaginaryNum = "";

    if (this.real == 0 && this.imaginary == 0)
      return "(0)";
    // check for integer or double
    if (this.real == (int) this.real)
      realNum += (int) this.real;
    else
      realNum += this.real;

    // check for integer or double
    if (this.imaginary == (int) this.imaginary)
      imaginaryNum += (int) this.imaginary;
    else
      imaginaryNum += this.imaginary;

    if (parentheses)
    {
      if (this.imaginary == 0 && this.real != 0)
      {
        complete = openParenthese + realNum + ")";
      }
      else if (this.imaginary != 0 && this.real == 0)
      {
        complete = openParenthese + imaginaryNum + iParenthese;
      }
      else
      {
        complete = openParenthese + realNum + plus + imaginaryNum + iParenthese;
      }
    }
    else
    {
      complete = realNum + plus + imaginaryNum + "i";
    }
    return complete;
  }

  /**
   * Sets the printable steps for this complex number.
   * 
   * @param s
   *          the string to set
   */
  public void setSteps(final String s)
  {
    steps = s;
  }

  /**
   * Access the printable steps for this complex number.
   * 
   * @return the printable steps
   */
  public String getSteps()
  {
    return steps;
  }

}
