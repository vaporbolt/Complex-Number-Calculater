package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gui.DisplayComponent;
import math.ComplexNumber;

/**
 * tests implementation of the DisplayComponent class.
 * 
 * @author Endre Szakal
 *
 */
public class DisplayComponentTest
{


  /**
   * idk what this test is doing yet.
   */
  @Test
  public void testAddComplexNumber()
  {
    DisplayComponent d = DisplayComponent.createInstance();
    ComplexNumber c = new ComplexNumber(5, 10);
    d.addComplexNumber(c);
  }
  
}
