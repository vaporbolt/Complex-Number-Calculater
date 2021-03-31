package math;

/**
 * ComplexNumber class. It can represent either a real number, an imaginary number, or a real number
 * plus an imaginary number
 * 
 * @author Sebastien Roy
 *
 */
public class ComplexNumber
{
  private double real;
  private double imaginary;

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
    return real;
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
    return imaginary;
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
   * Returns the conjugate of this number.
   * 
   * @return a new ComplexNUmber
   */
  public ComplexNumber conjugate()
  {
    return new ComplexNumber(this.real, this.imaginary * -1);
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
  public String toString(boolean parentheses)
  {
    String complete;
    String realNum = "";
    String imaginaryNum = "";

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
      complete = "(" + realNum + " + " + imaginaryNum + "i)";
    }
    else
    {
      complete = realNum + " + " + imaginaryNum + "i";
    }
    return complete;
  }

}
