package visualization;

import javax.swing.JFrame;
import math.ComplexNumber;

/**
 * Run this class to test the Cartesian Plane class
 * @author aszak
 *
 */
public class CartesianContainer extends JFrame
{ 
  /**
   * 
   */
  private static final long serialVersionUID = -7416241047940405149L;
  private CartesianPlane plane;
  
  public CartesianContainer()
  {
    plane = new CartesianPlane();
    this.add(plane);
  }
  
  public void showUI() 
  {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 600);
    setVisible(true);
   }
  
  public void addPoint(ComplexNumber number)
  {
    plane.addPoint(number);
  }
  
  public static void main(String[] args)
  {
    CartesianContainer c = new CartesianContainer();
    c.showUI();
    
    c.addPoint(new ComplexNumber(4, 5));
    c.addPoint(new ComplexNumber(-8, -4));
    c.addPoint(new ComplexNumber(10.3, 7.0));
    
  }
}
