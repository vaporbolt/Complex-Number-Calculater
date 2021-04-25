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

  private static final String STEP = "Step";
  private static final String EQUALSNEWLINE = " =\n";
  private static final String PLUS = " + ";
  private static final String ONE = " 1: \n";
  private static final String TWO = " 2: \n";
  private static final String THREE = " 3: \n";
  private static final String FOUR = " 4: \n";
  private static final String SPACING = "    ";
  private static final String EQUALS = " = ";
  private static final String TWONEWLINES = "\n\n";
  private static final String IEQUALS = "i = ";
  private static final String ITWONEWLINES = "i\n\n";
  private static final String TIMES = " * ";
  private static final String OPENEQUALS = ") = ";
  private static final String DIVIDE = " / ";

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
    String s = a.toString() + PLUS + b.toString() + EQUALSNEWLINE;
    s += STRINGS.getString(STEP) + ONE;
    double real = a.getReal() + b.getReal();
    s += SPACING + a.getReal() + PLUS + b.getReal() + EQUALS + real + TWONEWLINES;
    s += STRINGS.getString(STEP) + TWO;
    double imaginary = a.getImaginary() + b.getImaginary();
    s += SPACING + a.getImaginary() + "i + " + b.getImaginary() + IEQUALS + imaginary
        + ITWONEWLINES;
    s += STRINGS.getString(STEP) + THREE;
    ComplexNumber result = new ComplexNumber(a.getReal() + b.getReal(),
        a.getImaginary() + b.getImaginary());
    s += SPACING + real + PLUS + imaginary + IEQUALS + result.toString();
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
    final String minus = " - ";

    String s = a.toString() + minus + b.toString() + EQUALSNEWLINE;
    s += STRINGS.getString(STEP) + ONE;
    double real = a.getReal() - b.getReal();
    s += SPACING + a.getReal() + minus + b.getReal() + EQUALS + real + TWONEWLINES;
    s += STRINGS.getString(STEP) + TWO;
    double imaginary = a.getImaginary() - b.getImaginary();
    s += SPACING + a.getImaginary() + "i - " + b.getImaginary() + IEQUALS + imaginary
        + ITWONEWLINES;
    s += STRINGS.getString(STEP) + THREE;
    ComplexNumber result = new ComplexNumber(real, imaginary);
    s += SPACING + real + PLUS + imaginary + IEQUALS + result.toString();
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
    final String spacingParenthese = "    (";
    final String iTimes = "i * ";

    String s = a.toString() + TIMES + b.toString() + EQUALSNEWLINE;
    s += STRINGS.getString(STEP) + ONE;
    double real = a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary();
    s += spacingParenthese + a.getReal() + TIMES + b.getReal() + ") - (" + a.getImaginary() + iTimes
        + b.getImaginary() + OPENEQUALS + real + TWONEWLINES;

    s += STRINGS.getString(STEP) + TWO;
    double imaginary = a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal();
    s += spacingParenthese + a.getReal() + TIMES + b.getImaginary() + "i) + (" + a.getImaginary()
        + iTimes + b.getReal() + OPENEQUALS + imaginary + ITWONEWLINES;

    s += STRINGS.getString(STEP) + THREE;
    ComplexNumber result = new ComplexNumber(real, imaginary);
    s += SPACING + real + PLUS + imaginary + IEQUALS + result.toString();

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
    String s = a.toString() + DIVIDE + b.toString() + EQUALSNEWLINE;
    s += STRINGS.getString(STEP) + ONE;
    s += "    Con" + b.toString() + EQUALS + b.conjugate().toString() + TWONEWLINES;
    s += STRINGS.getString(STEP) + TWO;
    ComplexNumber top = multiply(a, b.conjugate());
    s += SPACING + a.toString() + TIMES + b.conjugate().toString() + EQUALS + top.toString()
        + TWONEWLINES;
    s += STRINGS.getString(STEP) + THREE;
    double bottom = multiply(b, b.conjugate()).getReal();
    if (bottom == 0)
    {
      throw new IllegalArgumentException();
    }
    s += SPACING + b.toString() + TIMES + b.conjugate().toString() + EQUALS + bottom + TWONEWLINES;

    s += STRINGS.getString(STEP) + FOUR;
    ComplexNumber result = new ComplexNumber(top.getReal() / bottom, top.getImaginary() / bottom);
    s += SPACING + top.toString() + " / (" + bottom + OPENEQUALS + result.toString();
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
    s += STRINGS.getString(STEP) + ONE;
    s += "    1 / " + a.toString() + TWONEWLINES;
    s += STRINGS.getString(STEP) + TWO;
    s += "    (1 / " + a.toString() + ") * (" + a.conjugate().toString() + DIVIDE
        + a.conjugate().toString() + ") =\n\n";
    s += STRINGS.getString(STEP) + THREE;
    s += SPACING + a.conjugate().toString() + DIVIDE + multiply(a, a.conjugate()).toString()
        + " =\n\n";
    s += STRINGS.getString(STEP) + FOUR;
    if (a.getImaginary() == 0)
      return new ComplexNumber(1 / a.getReal(), 0);
    ComplexNumber numerator = a.conjugate();
    ComplexNumber denominator = multiply(a, numerator);
    ComplexNumber result = divide(numerator, denominator);
    s += SPACING + result.toString();
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
    final String space = " ";
    final String newLine = ":\n";

    ComplexNumber result = new ComplexNumber(1, 0);
    String s = a.toString() + "^" + n + EQUALSNEWLINE;
    int i = 1;
    while (i < Math.abs(n) + 1)
    {
      s += STRINGS.getString(STEP) + space + i + newLine;
      s += SPACING + a.toString() + TIMES + result.toString() + EQUALS
          + multiply(result, a).toString() + TWONEWLINES;
      result = multiply(result, a);
      i++;
    }
    if (n < 0)
    {
      s += STRINGS.getString(STEP) + space + i + newLine;
      s += "    inv" + a.toString() + EQUALS + inverse(result);
      result = inverse(result);
    }
    result.setSteps(s);
    return result;
  }

  /**
   * Takes the squareroot of the complexNumber.
   * 
   * @param a
   *          ComplexNumber
   * @return ComplexNumber
   */
  public static ComplexNumber squareRoot(final ComplexNumber a)
  {
    final String sqrtParenthese = "    sqrt(";

    String s = "sqrt" + a.toString() + EQUALSNEWLINE;
    s += STRINGS.getString(STEP) + ONE;
    PolarForm polar = new PolarForm(a.getReal(), a.getImaginary());
    s += "    pol" + a.toString() + EQUALS + polar.toString() + TWONEWLINES;
    s += STRINGS.getString(STEP) + TWO;
    ComplexNumber result = new ComplexNumber(
        Math.sqrt(polar.getR()) * Math.cos(polar.getTheta() / 2),
        Math.sqrt(polar.getR()) * Math.sin(polar.getTheta() / 2));
    s += sqrtParenthese + polar.getR() + ") * cos(" + polar.getTheta() + " / 2) = "
        + result.getReal() + TWONEWLINES;
    s += STRINGS.getString(STEP) + THREE;
    s += sqrtParenthese + polar.getR() + ") * sin(" + polar.getTheta() + " / 2)i = "
        + result.getImaginary() + ITWONEWLINES;
    s += STRINGS.getString(STEP) + FOUR;
    s += SPACING + result.getReal() + PLUS + result.getImaginary() + IEQUALS + result.toString();
    result.setSteps(s);
    return result;
  }

  /**
   * Takes the log of the Complex Number.
   * 
   * @param a
   *          ComplexNumber
   * 
   * @return ComplexNumber
   */
  public static ComplexNumber log(final ComplexNumber a)
  {
    return null;
  }
}
