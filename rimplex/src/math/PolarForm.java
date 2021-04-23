package math;

/**
 * Polar Form Representation of a ComplexNumber.
 * 
 * @author Endre Szakal
 * @version 4/23/2021
 *
 */
public class PolarForm extends ComplexNumber
{
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
    a = real;
    b = imaginary;
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
