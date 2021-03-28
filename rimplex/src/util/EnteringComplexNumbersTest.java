package util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.ComplexNumber;
import math.Operation;

class EnteringComplexNumbersTest
{

  /*
   * 
   * UNIT TESTS
   * 
   */

  @Test
  void testMultipleFormats()
  {
    ComplexNumber c = EnteringComplexNumbers.parseComplexNumber("1 + 2i");
    assertEquals(1.0, c.getReal());
    assertEquals(2.0, c.getImaginary());

    ComplexNumber c2 = EnteringComplexNumbers.parseComplexNumber("1.0 + 2.0i");
    assertEquals(1.0, c2.getReal());
    assertEquals(2.0, c2.getImaginary());

    ComplexNumber c3 = EnteringComplexNumbers.parseComplexNumber("1.0 + 2i");
    assertEquals(1.0, c3.getReal());
    assertEquals(2.0, c3.getImaginary());

    ComplexNumber c4 = EnteringComplexNumbers.parseComplexNumber("1 + 2.0i");
    assertEquals(1.0, c4.getReal());
    assertEquals(2.0, c4.getImaginary());

    ComplexNumber c5 = EnteringComplexNumbers.parseComplexNumber("1");
    assertEquals(1.0, c5.getReal());
    assertEquals(0.0, c5.getImaginary());

    ComplexNumber c6 = EnteringComplexNumbers.parseComplexNumber("1i");
    assertEquals(0.0, c6.getReal());
    assertEquals(1.0, c6.getImaginary());

    ComplexNumber c7 = EnteringComplexNumbers.parseComplexNumber("1+2i");
    assertEquals(1.0, c7.getReal());
    assertEquals(2.0, c7.getImaginary());

    ComplexNumber c8 = EnteringComplexNumbers.parseComplexNumber("1+ 2i");
    assertEquals(1.0, c8.getReal());
    assertEquals(2.0, c8.getImaginary());

    ComplexNumber c9 = EnteringComplexNumbers.parseComplexNumber("1 +2i");
    assertEquals(1.0, c9.getReal());
    assertEquals(2.0, c9.getImaginary());
  }
  
  @Test
  void testMultipleFormatsNegative()
  {
    ComplexNumber c = EnteringComplexNumbers.parseComplexNumber("-1 + 2i");
    assertEquals(-1.0, c.getReal());
    assertEquals(2.0, c.getImaginary());

    ComplexNumber c2 = EnteringComplexNumbers.parseComplexNumber("1.0 + -2.0i");
    assertEquals(1.0, c2.getReal());
    assertEquals(-2.0, c2.getImaginary());

    ComplexNumber c3 = EnteringComplexNumbers.parseComplexNumber("-1.0 + -2i");
    assertEquals(-1.0, c3.getReal());
    assertEquals(-2.0, c3.getImaginary());

    ComplexNumber c4 = EnteringComplexNumbers.parseComplexNumber("1 + -2.0i");
    assertEquals(1.0, c4.getReal());
    assertEquals(-2.0, c4.getImaginary());

    ComplexNumber c5 = EnteringComplexNumbers.parseComplexNumber("-1");
    assertEquals(-1.0, c5.getReal());
    assertEquals(0.0, c5.getImaginary());

    ComplexNumber c6 = EnteringComplexNumbers.parseComplexNumber("-1i");
    assertEquals(0.0, c6.getReal());
    assertEquals(-1.0, c6.getImaginary());

    ComplexNumber c7 = EnteringComplexNumbers.parseComplexNumber("-1+-2i");
    assertEquals(-1.0, c7.getReal());
    assertEquals(-2.0, c7.getImaginary());

    ComplexNumber c8 = EnteringComplexNumbers.parseComplexNumber("-1+ 2i");
    assertEquals(-1.0, c8.getReal());
    assertEquals(2.0, c8.getImaginary());

    ComplexNumber c9 = EnteringComplexNumbers.parseComplexNumber("1 +-2i");
    assertEquals(1.0, c9.getReal());
    assertEquals(-2.0, c9.getImaginary());
  }

  @Test
  void testInvalidInput()
  {
    assertThrows(NullPointerException.class, () -> {
      EnteringComplexNumbers.parseComplexNumber(null);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      EnteringComplexNumbers.parseComplexNumber("");
    });

    assertThrows(NumberFormatException.class, () -> {
      EnteringComplexNumbers.parseComplexNumber(" ");
    });

    assertThrows(NumberFormatException.class, () -> {
      EnteringComplexNumbers.parseComplexNumber("abc + 4.0");
    });

    assertThrows(NumberFormatException.class, () -> {
      EnteringComplexNumbers.parseComplexNumber("+");
    });
    
    assertThrows(NumberFormatException.class, () -> {
      EnteringComplexNumbers.parseComplexNumber("1 * 2i");
    });
  }

  /*
   * 
   * INTEGRATION TESTS
   * 
   */
  
  @Test
  void testIntegration()
  {
    ComplexNumber num1 = EnteringComplexNumbers.parseComplexNumber("2 + 3i");
    ComplexNumber num2 = EnteringComplexNumbers.parseComplexNumber("4 + 2i");
    ComplexNumber num3 = Operation.add(num1, num2);

    assertEquals(6, num3.getReal());
    assertEquals(5, num3.getImaginary());

    num1 = EnteringComplexNumbers.parseComplexNumber("0 + 0i");
    num2 = EnteringComplexNumbers.parseComplexNumber("0 + 0i");

    num3 = Operation.add(num1, num2);

    assertEquals(0, num3.getReal());
    assertEquals(0, num3.getImaginary());

  }

}
