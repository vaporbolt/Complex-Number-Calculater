package math;

import java.util.ResourceBundle;

/**
 * Polar Form Representation of a ComplexNumber.
 * 
 * @author Endre Szakal
 * @version 4/23/2021
 *
 */
public class PolarForm extends ComplexNumber
{
  private static final ResourceBundle STRINGS = ResourceBundle.getBundle("languages.Strings");
  private double a;
  private double b;

  private double r;
  private double theta;

  /**
   * Parameterized Constructor.
   * 
   * @param real
   *          Double
   * @param imaginary
   *          Double
   */
  public PolarForm(final double real, final double imaginary)
  {
    super(real, imaginary);
    final String step = "Step";
    
    a = real;
    b = imaginary;
    String s = "pol" + super.toString() + " =\n";
    s += STRINGS.getString(step) + " 1:\n";
    // calculate polar form
    if (real == 0 && imaginary == 0)
    {
      r = 0;
      theta = 0;
    }
    else 
    {
      r = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
      theta = Math.atan(b / a);
      if (a < 0)
      {
        theta += Math.PI;
      }
    }
    s += String.format("    (" + a + "^2 + " + b + "^2) ^ 0.5 = " + "%.2f" + "\n\n", r);
    s += STRINGS.getString(step) + " 2:\n";
    s += String.format("    tan^-1(" + a + " / " + b + ") = %.2f\n\n", theta);
    s += STRINGS.getString(step) + " 3:\n";
    s += "    = " + this.toString();
    super.setSteps(s);
  }

  /**
   * Returns String representation.
   * 
   * @return String Polar Form
   */
  public String toString()
  {
    return String.format("%.2f cis %.2f", r, theta);
  }

  /**
   * @return r double
   */
  public double getR()
  {
    return r;
  }

  /**
   * @return theta
   */
  public double getTheta()
  {
    return theta;
  }
}
