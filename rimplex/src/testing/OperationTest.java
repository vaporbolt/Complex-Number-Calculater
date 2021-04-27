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
    new Operation();
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

  @Test
  void testInverseComplexNumber()
  {
    ComplexNumber num = new ComplexNumber(2, 4);
    num = Operation.inverse(num);
    assertEquals(0.1, num.getReal());
    assertEquals(-0.2, num.getImaginary());

    num = new ComplexNumber(2, 0);
    num = Operation.inverse(num);
    assertEquals(0.5, num.getReal());
    assertEquals(0, num.getImaginary());
  }

  @Test
  void testExponentPositive()
  {
    ComplexNumber num1 = new ComplexNumber(5, 3);
    ComplexNumber num2 = Operation.exponential(num1, 0);

    assertEquals(1, num2.getReal());
    assertEquals(0, num2.getImaginary());

    num2 = Operation.exponential(num1, 2);

    assertEquals(16, num2.getReal());
    assertEquals(30, num2.getImaginary());

    num2 = Operation.exponential(num1, 5);

    assertEquals(-6100, num2.getReal());
    assertEquals(2868, num2.getImaginary());

  }

  @Test
  void testExponentNegative()
  {
    ComplexNumber num1 = new ComplexNumber(5, 3);
    ComplexNumber num2 = Operation.exponential(num1, -1);

    assertEquals(5.0 / 34, num2.getReal());
    assertEquals(-3.0 / 34, num2.getImaginary());

    num2 = Operation.exponential(num1, -2);

    assertEquals(4.0 / 289, num2.getReal());
    assertEquals(-15.0 / 578, num2.getImaginary());

    num2 = Operation.exponential(num1, -3);

    assertEquals(-5.0 / 19652, num2.getReal());
    assertEquals(-99.0 / 19652, num2.getImaginary());

  }

  @Test
  void testSquareRoot()
  {
    ComplexNumber num1 = new ComplexNumber(8, -6);
    ComplexNumber num2 = Operation.squareRoot(num1);

    assertEquals(3, num2.getReal());
    assertEquals(-1, num2.getImaginary());
  }

  @Test
  void testLog()
  {
    ComplexNumber num1 = new ComplexNumber(8, -6);
    ComplexNumber num2 = Operation.log(num1);

    assertEquals(2.3026, num2.getReal(), .0001);
    assertEquals(-0.6435, num2.getImaginary(), .0001);
  }

  @Test
  void testSetSteps()
  {
    String test = "test";
    ComplexNumber num1 = new ComplexNumber(0, 0);

    num1.setSteps(test);

    assertEquals(test, num1.getSteps());

  }
}
