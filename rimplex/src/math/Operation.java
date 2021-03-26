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
  public static ComplexNumber devide(final ComplexNumber a, final ComplexNumber b)
  {
    ComplexNumber top = Operation.multiply(a, b.conjugate());
    double bottom = b.getReal() * b.getReal() + b.getImaginary() * b.getImaginary();
    return new ComplexNumber(top.getReal() / bottom, top.getImaginary() / bottom);
  }

}
