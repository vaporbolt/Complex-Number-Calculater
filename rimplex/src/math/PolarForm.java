package math;

public class PolarForm extends ComplexNumber
{
  private double a;
  private double b;
  
  private double r;
  private double theta;

  public PolarForm(double real, double imaginary)
  {
    super(real, imaginary);
    a = real;
    b = imaginary;
    // calculate polar form
    r = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    theta = Math.atan(b/a);
    if (a < 0)
    {
      theta += Math.PI;
    }
  }
  
  public String toString()
  {
    return String.format("%.2f cis %.2f", r, theta);
  }
  
  public double getR()
  {
    return r;
  }
  
  public double getTheta()
  {
    return theta;
  }
}
