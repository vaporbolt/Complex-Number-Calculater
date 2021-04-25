package math;

import java.util.ResourceBundle;

/**
 * Operation utility class.
 * 
 * @author Sebastien Roy.
 *
 */
public class Operation
{
  private static final ResourceBundle STRINGS = ResourceBundle.getBundle("languages.Strings");

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
    String s = a.toString() + " + " + b.toString() + " =\n";
    s += STRINGS.getString("Step") + " 1: \n";
    double real = a.getReal() + b.getReal();
    s += "    " + a.getReal() + " + " + b.getReal() + " = " + real + "\n\n";
    s += STRINGS.getString("Step") + " 2: \n";
    double imaginary = a.getImaginary() + b.getImaginary();
    s += "    " + a.getImaginary() + "i + " + b.getImaginary() + "i = " + imaginary + "i\n\n";
    s += STRINGS.getString("Step") + " 3: \n";
    ComplexNumber result = new ComplexNumber(a.getReal() + b.getReal(),
        a.getImaginary() + b.getImaginary());
    s += "    " + real + " + " + imaginary + "i = " + result.toString();
    result.setSteps(s);
    return result;
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

    String s = a.toString() + " - " + b.toString() + " =\n";
    s += STRINGS.getString("Step") + " 1: \n";
    double real = a.getReal() - b.getReal();
    s += "    " + a.getReal() + " - " + b.getReal() + " = " + real + "\n\n";
    s += STRINGS.getString("Step") + " 2: \n";
    double imaginary = a.getImaginary() - b.getImaginary();
    s += "    " + a.getImaginary() + "i - " + b.getImaginary() + "i = " + imaginary + "i\n\n";
    s += STRINGS.getString("Step") + " 3: \n";
    ComplexNumber result = new ComplexNumber(real, imaginary);
    s += "    " + real + " + " + imaginary + "i = " + result.toString();
    result.setSteps(s);
    return result;

    // return new ComplexNumber(a.getReal() - b.getReal(), a.getImaginary() - b.getImaginary());
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
    String s = a.toString() + " * " + b.toString() + " =\n";
    s += STRINGS.getString("Step") + " 1: \n";
    double real = a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary();
    s += "    (" + a.getReal() + " * " + b.getReal() + ") - (" + a.getImaginary() + "i * "
        + b.getImaginary() + ") = " + real + "\n\n";
    s += STRINGS.getString("Step") + " 2: \n";
    double imaginary = a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal();
    s += "    (" + a.getReal() + " * " + b.getImaginary() + "i) + (" + a.getImaginary() + "i * "
        + b.getReal() + ") = " + imaginary + "i\n\n";
    s += STRINGS.getString("Step") + " 3: \n";
    ComplexNumber result = new ComplexNumber(real, imaginary);
    s += "    " + real + " + " + imaginary + "i = " + result.toString();
    result.setSteps(s);
    return result;

    // return new ComplexNumber(a.getReal() * b.getReal() - a.getImaginary() *
    // b.getImaginary(),a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal());
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
    String s = a.toString() + " / " + b.toString() + " =\n";
    s += STRINGS.getString("Step") + " 1: \n";
    s += "    Con" + b.toString() + " = " + b.conjugate().toString() + "\n\n";
    s += STRINGS.getString("Step") + " 2: \n";
    ComplexNumber top = multiply(a, b.conjugate());
    s += "    " + a.toString() + " * " + b.conjugate().toString() + " = " + top.toString() + "\n\n";
    s += STRINGS.getString("Step") + " 3: \n";
    double bottom = multiply(b, b.conjugate()).getReal();
    if (bottom == 0)
    {
      throw new IllegalArgumentException();
    }
    s += "    " + b.toString() + " * " + b.conjugate().toString() + " = " + bottom + "\n\n";

    s += STRINGS.getString("Step") + " 4: \n";
    ComplexNumber result = new ComplexNumber(top.getReal() / bottom, top.getImaginary() / bottom);
    s += "    " + top.toString() + " / (" + bottom + ") = " + result.toString();
    result.setSteps(s);
    return result;

    /*
     * ComplexNumber top = Operation.multiply(a, b.conjugate()); double bottom = b.getReal() *
     * b.getReal() + b.getImaginary() * b.getImaginary(); if (bottom == 0) { throw new
     * IllegalArgumentException(); } return new ComplexNumber(top.getReal() / bottom,
     * top.getImaginary() / bottom);
     */
  }

  /**
   * Returns a new ComplexNumber that is the inverse of the original.
   * 
   * @param a
   *          the complex number to invert
   * @return the inverse of a
   */
  public static ComplexNumber inverse(final ComplexNumber a)
  {
    String s = a.toString() + " ^ -1 =\n";
    s += STRINGS.getString("Step") + " 1:\n";
    s += "    1 / " + a.toString() + "\n\n";
    s += STRINGS.getString("Step") + " 2:\n";
    s += "    (1 / " + a.toString() + ") * (" + a.conjugate().toString() + " / "
        + a.conjugate().toString() + ") =\n\n";
    s += STRINGS.getString("Step") + " 3:\n";
    s += "    " + a.conjugate().toString() + " / " + multiply(a, a.conjugate()).toString()
        + " =\n\n";
    s += STRINGS.getString("Step") + " 4:\n";
    if (a.getImaginary() == 0)
      return new ComplexNumber(1 / a.getReal(), 0);
    ComplexNumber numerator = a.conjugate();
    ComplexNumber denominator = multiply(a, numerator);
    ComplexNumber result = divide(numerator, denominator);
    s += "    " + result.toString();
    result.setSteps(s);
    return result;
  }

  /**
   * Returns a new ComplexNumber that is a^n.
   * 
   * @param a
   *          the complex number.
   * @param n
   *          the exponent.
   * @return the a^n
   */
  public static ComplexNumber exponential(final ComplexNumber a, final int n)
  {
    ComplexNumber result = new ComplexNumber(1, 0);
    for (int i = 0; i < Math.abs(n); i++)
    {
      result = multiply(result, a);
    }
    if (n < 0)
    {
      result = inverse(result);
    }
    return result;
  }

  public static ComplexNumber squareRoot(final ComplexNumber a)
  {
    return null;
  }

  public static ComplexNumber log(final ComplexNumber a)
  {
    return null;
  }
}
