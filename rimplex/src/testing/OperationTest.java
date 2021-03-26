package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.ComplexNumber;
import math.Operation;

class OperationTest
{

  @Test
  void testAdd()
  {
    ComplexNumber num1 = new ComplexNumber(2,3);
    ComplexNumber num2 = new ComplexNumber(4,2);
    ComplexNumber num3 = Operation.add(num1, num2);
    
    assertEquals(num3.getReal(), 6);
    assertEquals(num3.getImaginary(), 5);

  }

  @Test
  void testSubtract()
  {
    ComplexNumber num1 = new ComplexNumber(5,5);
    ComplexNumber num2 = new ComplexNumber(3,2);
    ComplexNumber num3 = Operation.subtract(num1, num2);
    
    assertEquals(num3.getReal(), 2);
    assertEquals(num3.getImaginary(), 3);

  }

  
}
