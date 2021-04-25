package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.ComplexNumber;
import math.Operation;
import util.EnteringComplexNumbers;

class EnteringComplexNumbersTest
{

  /*
   * 
   * UNIT TESTS
   * 
   */

  private final String testOne = "4 + 2i";
  private final String testTwo = "0 + 0i";
  
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
    ComplexNumber c = EnteringComplexNumbers.parseComplexNumber("-1 - 2i");
    assertEquals(-1.0, c.getReal());
    assertEquals(-2.0, c.getImaginary());

    ComplexNumber c2 = EnteringComplexNumbers.parseComplexNumber("1.0 - -2.0i");
    assertEquals(1.0, c2.getReal());
    assertEquals(2.0, c2.getImaginary());

    ComplexNumber c3 = EnteringComplexNumbers.parseComplexNumber("-1.0 - -2i");
    assertEquals(-1.0, c3.getReal());
    assertEquals(2.0, c3.getImaginary());

    ComplexNumber c4 = EnteringComplexNumbers.parseComplexNumber("1 - -2.0i");
    assertEquals(1.0, c4.getReal());
    assertEquals(2.0, c4.getImaginary());

    ComplexNumber c5 = EnteringComplexNumbers.parseComplexNumber("-1");
    assertEquals(-1.0, c5.getReal());
    assertEquals(0.0, c5.getImaginary());

    ComplexNumber c6 = EnteringComplexNumbers.parseComplexNumber("-1i");
    assertEquals(0.0, c6.getReal());
    assertEquals(-1.0, c6.getImaginary());

    ComplexNumber c7 = EnteringComplexNumbers.parseComplexNumber("-1--2i");
    assertEquals(-1.0, c7.getReal());
    assertEquals(2.0, c7.getImaginary());

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
    assertThrows(NullPointerException.class, () -> 
    {
      EnteringComplexNumbers.parseComplexNumber(null);
    });

    assertThrows(IllegalArgumentException.class, () -> 
    {
      EnteringComplexNumbers.parseComplexNumber("");
    });

    assertThrows(NumberFormatException.class, () -> 
    {
      EnteringComplexNumbers.parseComplexNumber(" ");
    });

    assertThrows(NumberFormatException.class, () -> 
    {
      EnteringComplexNumbers.parseComplexNumber("abc + 4.0");
    });

    assertThrows(NumberFormatException.class, () -> 
    {
      EnteringComplexNumbers.parseComplexNumber("+");
    });

    assertThrows(NumberFormatException.class, () -> 
    {
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
    ComplexNumber num2 = EnteringComplexNumbers.parseComplexNumber(testOne);
    ComplexNumber num3 = Operation.add(num1, num2);

    assertEquals(6, num3.getReal());
    assertEquals(5, num3.getImaginary());

    num1 = EnteringComplexNumbers.parseComplexNumber(testTwo);
    num2 = EnteringComplexNumbers.parseComplexNumber(testTwo);

    num3 = Operation.add(num1, num2);

    assertEquals(0, num3.getReal());
    assertEquals(0, num3.getImaginary());

  }

  @Test
  void testToString()
  {
    ComplexNumber num = new ComplexNumber(1, 2);
    ComplexNumber num2 = EnteringComplexNumbers.parseComplexNumber(testOne);
    ComplexNumber num3 = EnteringComplexNumbers.parseComplexNumber("4.5 + 2.7i");
    assertEquals("(1 + 2i)", num.toString());
    assertEquals("(4 + 2i)", num2.toString());
    assertEquals("(4.5 + 2.7i)", num3.toString());
  }

  @Test
  void testIsComplexNumber()
  {
    assertTrue(EnteringComplexNumbers.isComplexNumber(testOne));
    assertTrue(EnteringComplexNumbers.isComplexNumber("-4 - -2i"));
    assertFalse(EnteringComplexNumbers.isComplexNumber("hi"));
    assertTrue(EnteringComplexNumbers.isComplexNumber("4"));
  }

  @Test
  void testParseEquation()
  {
    ComplexNumber num = EnteringComplexNumbers.parseEquation("(3 + 2i) + (5 + 7i)");
    assertEquals(8, num.getReal());
    assertEquals(9, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(3 + 2i) - (5 + 7i)");
    assertEquals(-2, num.getReal());
    assertEquals(-5, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(5 + 5i) * (3 + 2i)");
    assertEquals(5, num.getReal());
    assertEquals(25, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(5 + 5i) / (3 + 2i)");
    assertEquals(25.0 / 13, num.getReal());
    assertEquals(5.0 / 13, num.getImaginary());

  }

  @Test
  void testParseEquationNoSpaces()
  {
    ComplexNumber num = EnteringComplexNumbers.parseEquation("(3+2i)+(5+7i)");
    assertEquals(8, num.getReal());
    assertEquals(9, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(3+2i)-(5+7i)");
    assertEquals(-2, num.getReal());
    assertEquals(-5, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(5+5i)*(3+2i)");
    assertEquals(5, num.getReal());
    assertEquals(25, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(5+5i)/(3+2i)");
    assertEquals(25.0 / 13, num.getReal());
    assertEquals(5.0 / 13, num.getImaginary());
  }

  @Test
  void testParseEquationNegativeComplex()
  {
    ComplexNumber num = EnteringComplexNumbers.parseEquation("-(3+2i)+0");
    assertEquals(-3, num.getReal());
    assertEquals(-2, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("-(3+2i)+-(1+2i)");
    assertEquals(-4, num.getReal());
    assertEquals(-4, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("-(3+2i)--(1+2i)");
    assertEquals(-2, num.getReal());
    assertEquals(0, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(3+2i)+-(5)");
    assertEquals(-2, num.getReal());
    assertEquals(2, num.getImaginary());

  }

  @Test
  void testParseEquationNonComplexOperands()
  {
    ComplexNumber num = EnteringComplexNumbers.parseEquation("(5) + (3 + 2i)");
    assertEquals(8, num.getReal());
    assertEquals(2, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("3 + (5 + 7i)");
    assertEquals(8, num.getReal());
    assertEquals(7, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("3i + (5 + 7i)");
    assertEquals(5, num.getReal());
    assertEquals(10, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(3 + 2i) + 5");
    assertEquals(8, num.getReal());
    assertEquals(2, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(3 + 2i) + 5i");
    assertEquals(3, num.getReal());
    assertEquals(7, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("-3 + (5 + 7i)");
    assertEquals(2, num.getReal());
    assertEquals(7, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("-3i + (5 + 7i)");
    assertEquals(5, num.getReal());
    assertEquals(4, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(3 + 2i) + -5");
    assertEquals(-2, num.getReal());
    assertEquals(2, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(3 + 2i) + -5i");
    assertEquals(3, num.getReal());
    assertEquals(-3, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(3 + 2i)-5i");
    assertEquals(3, num.getReal());
    assertEquals(-3, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("5+5");
    assertEquals(10, num.getReal());
    assertEquals(0, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("5+5i");
    assertEquals(5, num.getReal());
    assertEquals(5, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("5i+5i");
    assertEquals(0, num.getReal());
    assertEquals(10, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("-5+-5");
    assertEquals(-10, num.getReal());
    assertEquals(0, num.getImaginary());

  }

  @Test
  void testParseEquationSingleOperand()
  {
    ComplexNumber num = EnteringComplexNumbers.parseEquation("(3 + 2i)^0");
    assertEquals(1, num.getReal());
    assertEquals(0, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(3 + 2i)^1");
    assertEquals(3, num.getReal());
    assertEquals(2, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(3 + 2i)^3");
    assertEquals(-9, num.getReal());
    assertEquals(46, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(3 + 2i)^-1");
    assertEquals(3.0 / 13, num.getReal());
    assertEquals(-2.0 / 13, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("(3 + 2i)^-3");
    assertEquals(-9.0 / 2197, num.getReal());
    assertEquals(-46.0 / 2197, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("con(3 + 2i)");
    assertEquals(3, num.getReal());
    assertEquals(-2, num.getImaginary());

    num = EnteringComplexNumbers.parseEquation("sqrt(8 - 6i)");
    assertEquals(3, num.getReal());
    assertEquals(-1, num.getImaginary());

  }

  @Test
  void testParseEquationInvalid()
  {
    assertThrows(NumberFormatException.class,
        () -> EnteringComplexNumbers.parseEquation("3 + 2i + 5"));

    assertThrows(NumberFormatException.class,
        () -> EnteringComplexNumbers.parseEquation("3 + 2 + 5i"));

    assertThrows(NumberFormatException.class,
        () -> EnteringComplexNumbers.parseEquation("3 - 2i + 5"));

    assertThrows(NumberFormatException.class,
        () -> EnteringComplexNumbers.parseEquation("3 + 2i - 5"));

    assertThrows(NumberFormatException.class,
        () -> EnteringComplexNumbers.parseEquation("con2+3i"));

    assertThrows(NumberFormatException.class,
        () -> EnteringComplexNumbers.parseEquation("con(2+3i)+2"));
  }

}
