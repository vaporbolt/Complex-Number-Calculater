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
  public ComplexNumberPoint(ComplexNumber complexNum)
  {
    this.complexNumber = complexNum;
    this.point = new Point2D.Double(complexNum.getReal(), complexNum.getImaginary());
  }
  
  public ComplexNumber getComplexNumber()
  {
    return complexNumber;
  }
  
  public double getX()
  {
    return point.getX();
  }

  public double getY()
  {
    return point.getY();
  }
}
