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
  void testAddTwoComplex()
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
  void testSubtractTwoComplex()
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
  void testMultiplyTwoComplex()
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
  void testDevideTwoComplex()
  {
    ComplexNumber num1 = new ComplexNumber(5, 5);
    ComplexNumber num2 = new ComplexNumber(3, 2);
    ComplexNumber num3 = Operation.divide(num1, num2);

    assertEquals(25.0 / 13, num3.getReal());
    assertEquals(5.0 / 13, num3.getImaginary());

    num1 = new ComplexNumber(-5, 5);
    num2 = new ComplexNumber(-3, 2);

    num3 = Operation.divide(num1, num2);

    assertEquals(25.0 / 13, num3.getReal());
    assertEquals(-5.0 / 13, num3.getImaginary());

    num1 = new ComplexNumber(5, -5);
    num2 = new ComplexNumber(3, -2);

    num3 = Operation.divide(num1, num2);

    assertEquals(25.0 / 13, num3.getReal());
    assertEquals(-5.0 / 13, num3.getImaginary());

    num1 = new ComplexNumber(-5, -5);
    num2 = new ComplexNumber(-3, -2);

    num3 = Operation.divide(num1, num2);

    assertEquals(25.0 / 13, num3.getReal());
    assertEquals(5.0 / 13, num3.getImaginary());

    ComplexNumber num4 = new ComplexNumber(0, 0);
    ComplexNumber num5 = new ComplexNumber(0, 0);

    assertThrows(IllegalArgumentException.class, () -> Operation.divide(num4, num5));
  }

  @Test
  void testAddTwoReal()
  {
    ComplexNumber num1 = new ComplexNumber(2, 0);
    ComplexNumber num2 = new ComplexNumber(4, 0);
    ComplexNumber num3 = Operation.add(num1, num2);

    assertEquals(6, num3.getReal());

  }

  @Test
  void testSubtractTwoReal()
  {
    ComplexNumber num1 = new ComplexNumber(5, 0);
    ComplexNumber num2 = new ComplexNumber(3, 0);
    ComplexNumber num3 = Operation.subtract(num1, num2);

    assertEquals(2, num3.getReal());

  }

  @Test
  void testMultiplyTwoReal()
  {
    ComplexNumber num1 = new ComplexNumber(5, 0);
    ComplexNumber num2 = new ComplexNumber(3, 0);
    ComplexNumber num3 = Operation.multiply(num1, num2);

    assertEquals(15, num3.getReal());

    num1 = new ComplexNumber(-5, 0);
    num2 = new ComplexNumber(-3, 0);

    num3 = Operation.multiply(num1, num2);

    assertEquals(15, num3.getReal());

  }

  @Test
  void testDevideTwoReal()
  {
    ComplexNumber num1 = new ComplexNumber(5, 0);
    ComplexNumber num2 = new ComplexNumber(3, 0);
    ComplexNumber num3 = Operation.divide(num1, num2);

    assertEquals(5.0 / 3, num3.getReal());

    num1 = new ComplexNumber(-5, 0);
    num2 = new ComplexNumber(-3, 0);

    num3 = Operation.divide(num1, num2);

    assertEquals(5.0 / 3, num3.getReal());

  }

  @Test
  void testAddOneRealOneComplex()
  {
    ComplexNumber num1 = new ComplexNumber(2, 3);
    ComplexNumber num2 = new ComplexNumber(4, 0);
    ComplexNumber num3 = Operation.add(num1, num2);

    assertEquals(6, num3.getReal());
    assertEquals(3, num3.getImaginary());

  }

  @Test
  void testSubtratOneRealOneComplex()
  {
    ComplexNumber num1 = new ComplexNumber(5, 3);
    ComplexNumber num2 = new ComplexNumber(3, 0);
    ComplexNumber num3 = Operation.subtract(num1, num2);

    assertEquals(2, num3.getReal());
    assertEquals(3, num3.getImaginary());

  }

  @Test
  void testMultiplyOneRealOneComplex()
  {
    ComplexNumber num1 = new ComplexNumber(5, 3);
    ComplexNumber num2 = new ComplexNumber(3, 0);
    ComplexNumber num3 = Operation.multiply(num1, num2);

    assertEquals(15, num3.getReal());
    assertEquals(9, num3.getImaginary());

    num1 = new ComplexNumber(-5, 3);
    num2 = new ComplexNumber(-3, 0);

    num3 = Operation.multiply(num1, num2);

    assertEquals(15, num3.getReal());
    assertEquals(-9, num3.getImaginary());

  }

  @Test
  void testDevideOneRealOneComplex()
  {
    ComplexNumber num1 = new ComplexNumber(5, 3);
    ComplexNumber num2 = new ComplexNumber(3, 0);
    ComplexNumber num3 = Operation.divide(num1, num2);

    assertEquals(5.0 / 3, num3.getReal());
    assertEquals(1.0, num3.getImaginary());

    num1 = new ComplexNumber(-5, 3);
    num2 = new ComplexNumber(-3, 0);

    num3 = Operation.divide(num1, num2);

    assertEquals(5.0 / 3, num3.getReal());
    assertEquals(-1.0, num3.getImaginary());

  }

}
