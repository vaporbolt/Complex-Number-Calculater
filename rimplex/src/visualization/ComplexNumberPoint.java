package visualization;

import java.awt.geom.Point2D;

import math.ComplexNumber;

/**
 * Representation of a complex number on the Cartesian plane.
 * 
 * @author Endre Szakal
 *
 */
public class ComplexNumberPoint
{

  private ComplexNumber complexNumber;
  private Point2D point;

  /**
   * Parameterized Constructor.
   * @param complexNum ComplexNumber
   */
  public ComplexNumberPoint(final ComplexNumber complexNum)
  {
    this.complexNumber = complexNum;
    this.point = new Point2D.Double(complexNum.getReal(), complexNum.getImaginary());
  }
  
  /**
   * Gets ComplexNumber.
   * @return ComplexNumber
   */
  public ComplexNumber getComplexNumber()
  {
    return complexNumber;
  }
  
  /**
   * Gets x coordinate.
   * @return double 
   */
  public double getX()
  {
    return point.getX();
  }

  /**
   * Gets Y coordinate.
   * @return double
   */
  public double getY()
  {
    return point.getY();
  }
}
