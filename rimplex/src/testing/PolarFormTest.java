package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import math.ComplexNumber;
import math.PolarForm;

/**
 * Tests PolarForm class.
 * 
 * @author Endre Szakal
 * @version 4/23/2021
 *
 */
public class PolarFormTest
{

  /**
   * Tests multiple inputs to PolarForm.
   */
  @Test
  public void testPolarForm()
  {
    ComplexNumber c1 = new ComplexNumber(5, 2);
    // a > 0
    PolarForm p1 = new PolarForm(c1.getReal(), c1.getImaginary());
    assertEquals(5.39, p1.getR(), .01);
    assertEquals(0.38, p1.getTheta(), .01);

    // a < 0
    PolarForm p2 = new PolarForm(-10, 12);
    assertEquals(15.62, p2.getR(), .01);
    assertEquals(2.2655, p2.getTheta(), .01);

    // a = 0 and b = 0
    PolarForm p3 = new PolarForm(0, 0);
    assertEquals(0, p3.getR(), .01);
    assertEquals(0, p3.getTheta(), .01);

    // a = 0 and b = 2
    PolarForm p4 = new PolarForm(0, 2);
    assertEquals(2, p4.getR(), .01);
    assertEquals(1.57, p4.getTheta(), .01);

    // a = 15 and b = 0
    PolarForm p5 = new PolarForm(15, 0);
    assertEquals(15, p5.getR(), .01);
    assertEquals(0, p5.getTheta(), .01);
  }

  /**
   * Tests toString method.
   */
  @Test
  public void testString()
  {
    PolarForm p = new PolarForm(4, 5);
    assertEquals("6.40 cis 0.90", p.toString());
  }
}
