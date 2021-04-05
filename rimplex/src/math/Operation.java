package math;

/**
 * Operation utility class.
 * 
 * @author Sebastien Roy.
 *
 */
public class Operation
{
  /**
   * Returns a new ComplexNumber that is the sum of the two given numbers.
   * 
   * @param a
   *          one ComplexNumber
   * @param b
   *          one ComplexNumber
   * @return a new ComplexNumber
   */
  public static ComplexNumber add(final ComplexNumber a, final ComplexNumber b)
  {
    return new ComplexNumber(a.getReal() + b.getReal(), a.getImaginary() + b.getImaginary());
  }

  /**
   * Returns a new ComplexNumber that is the difference of the two given numbers, a - b.
   * 
   * @param a
   *          one ComplexNumber
   * @param b
   *          one ComplexNumber
   * @return a new ComplexNumber
   */
  public static ComplexNumber subtract(final ComplexNumber a, final ComplexNumber b)
  {
    return new ComplexNumber(a.getReal() - b.getReal(), a.getImaginary() - b.getImaginary());
  }

  /**
   * Returns a new ComplexNumber that is the product of the two given numbers.
   * 
   * @param a
   *          one ComplexNumber
   * @param b
   *          one ComplexNumber
   * @return a new ComplexNumber
   */
  public static ComplexNumber multiply(final ComplexNumber a, final ComplexNumber b)
  {
    return new ComplexNumber(a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary(),
        a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal());
  }

  /**
   * Returns a new ComplexNumber that is the quotient of the two given numbers.
   * 
   * @param a
   *          one ComplexNumber
   * @param b
   *          one ComplexNumber
   * @return a new ComplexNumber
   */
  public static ComplexNumber divide(final ComplexNumber a, final ComplexNumber b)
  {
    ComplexNumber top = Operation.multiply(a, b.conjugate());
    double bottom = b.getReal() * b.getReal() + b.getImaginary() * b.getImaginary();
    if (bottom == 0)
    {
      throw new IllegalArgumentException();
    }
    return new ComplexNumber(top.getReal() / bottom, top.getImaginary() / bottom);
  }
  
  /**
   * Returns a new ComplexNumber that is the inverse of the original.
   * 
   * @param a the complex number to invert
   * @return the inverse of a
   */
  public static ComplexNumber inverse(final ComplexNumber a)
  {
    if (a.getImaginary() == 0) return new ComplexNumber(1 / a.getReal(), 0);
    ComplexNumber numerator = a.conjugate();
    ComplexNumber denominator = multiply(a, numerator);
    return divide(numerator, denominator);
  }
}
