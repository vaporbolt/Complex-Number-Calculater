package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.ComplexNumber;
import math.Operation;

/**
 * Tests for Operation.
 * 
 * @author Sebastien Roy
 *
 */
class OperationTest
{

  @Test
  void testAdd()
  {
    ComplexNumber num1 = new ComplexNumber(2, 3);
    ComplexNumber num2 = new ComplexNumber(4, 2);
    ComplexNumber num3 = Operation.add(num1, num2);

    assertEquals(6, num3.getReal());
    assertEquals(5, num3.getImaginary());

    num1 = new ComplexNumber(0, 0);
    num2 = new ComplexNumber(0, 0);

    num3 = Operation.add(num1, num2);

    assertEquals(0, num3.getReal());
    assertEquals(0, num3.getImaginary());

  }

  @Test
  void testSubtract()
  {
    ComplexNumber num1 = new ComplexNumber(5, 5);
    ComplexNumber num2 = new ComplexNumber(3, 2);
    ComplexNumber num3 = Operation.subtract(num1, num2);

    assertEquals(2, num3.getReal());
    assertEquals(3, num3.getImaginary());

    num1 = new ComplexNumber(0, 0);
    num2 = new ComplexNumber(0, 0);

    num3 = Operation.subtract(num1, num2);

    assertEquals(0, num3.getReal());
    assertEquals(0, num3.getImaginary());

  }

  @Test
  void testMultiply()
  {
    ComplexNumber num1 = new ComplexNumber(5, 5);
    ComplexNumber num2 = new ComplexNumber(3, 2);
    ComplexNumber num3 = Operation.multiply(num1, num2);

    assertEquals(5, num3.getReal());
    assertEquals(25, num3.getImaginary());

    num1 = new ComplexNumber(0, 0);
    num2 = new ComplexNumber(0, 0);

    num3 = Operation.multiply(num1, num2);

    assertEquals(0, num3.getReal());
    assertEquals(0, num3.getImaginary());

    num1 = new ComplexNumber(-5, 5);
    num2 = new ComplexNumber(-3, 2);

    num3 = Operation.multiply(num1, num2);

    assertEquals(5, num3.getReal());
    assertEquals(-25, num3.getImaginary());

    num1 = new ComplexNumber(5, -5);
    num2 = new ComplexNumber(3, -2);

    num3 = Operation.multiply(num1, num2);

    assertEquals(5, num3.getReal());
    assertEquals(-25, num3.getImaginary());

    num1 = new ComplexNumber(-5, -5);
    num2 = new ComplexNumber(-3, -2);

    num3 = Operation.multiply(num1, num2);

    assertEquals(5, num3.getReal());
    assertEquals(25, num3.getImaginary());

  }

  @Test
  void testDevide()
  {
    ComplexNumber num1 = new ComplexNumber(5, 5);
    ComplexNumber num2 = new ComplexNumber(3, 2);
    ComplexNumber num3 = Operation.devide(num1, num2);

    assertEquals(25.0 / 13, num3.getReal());
    assertEquals(5.0 / 13, num3.getImaginary());

    num1 = new ComplexNumber(-5, 5);
    num2 = new ComplexNumber(-3, 2);

    num3 = Operation.devide(num1, num2);

    assertEquals(25.0 / 13, num3.getReal());
    assertEquals(-5.0 / 13, num3.getImaginary());

    num1 = new ComplexNumber(5, -5);
    num2 = new ComplexNumber(3, -2);

    num3 = Operation.devide(num1, num2);

    assertEquals(25.0 / 13, num3.getReal());
    assertEquals(-5.0 / 13, num3.getImaginary());

    num1 = new ComplexNumber(-5, -5);
    num2 = new ComplexNumber(-3, -2);

    num3 = Operation.devide(num1, num2);

    assertEquals(25.0 / 13, num3.getReal());
    assertEquals(5.0 / 13, num3.getImaginary());

    ComplexNumber num4 = new ComplexNumber(0, 0);
    ComplexNumber num5 = new ComplexNumber(0, 0);

    assertThrows(IllegalArgumentException.class, () -> Operation.devide(num4, num5));

  }

}
