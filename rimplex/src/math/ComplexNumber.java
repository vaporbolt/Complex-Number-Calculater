package math;

/**
 * ComplexNumber class.
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
   * Converts the complex number into a string. 
   * If either the real or imaginary number is an integer it will be displayed without the decimal.
   * 
   * @return the string form of a complex number.
   */
  public String toString()
  {
    String complete;
    String realNum = "";
    String imaginaryNum = "";
    if (this.real == (int)this.real) realNum += (int)this.real;
    else realNum += this.real;
    if (this.imaginary == (int)this.imaginary) imaginaryNum += (int)this.imaginary;
    else imaginaryNum += this.imaginary;
    complete = realNum + " + " + imaginaryNum + "i";
    return complete;
  }

}
