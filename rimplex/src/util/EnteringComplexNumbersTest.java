package util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.ComplexNumber;

class EnteringComplexNumbersTest
{

  @Test
  void testAddition()
  {
    ComplexNumber c = EnteringComplexNumbers.parseComplexNumber("1 + 2i");
    assertEquals(1.0, c.getReal());
    assertEquals(2.0, c.getImaginary());
  }

  @Test
  void testSubtraction()
  {
    ComplexNumber c = EnteringComplexNumbers.parseComplexNumber("1 - 2i");
    assertEquals(1.0, c.getReal());
    assertEquals(2.0, c.getImaginary());
  }

  @Test
  void testMultiplication()
  {
    ComplexNumber c = EnteringComplexNumbers.parseComplexNumber("1 * 2i");
    assertEquals(1.0, c.getReal());
    assertEquals(2.0, c.getImaginary());
  }

  @Test
  void testDivision()
  {
    ComplexNumber c = EnteringComplexNumbers.parseComplexNumber("1 / 2i");
    assertEquals(1.0, c.getReal());
    assertEquals(2.0, c.getImaginary());
  }

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
  }

}
