package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import math.ComplexNumber;
import math.Operation;
import math.OperationType;

/**
 * @author Seth Roper
 * @version 3/31/2021
 * Handles actions for the equals button.
 *
 */
public class EqualsHandler implements ActionListener
{

  private DisplayComponent display;
  private ArrayList<ComplexNumber> nums;
  private ArrayList<OperationType> operations;
  
  /**
   * Creates an equals handler.
   * 
   * @param display the system's display
   * @param nums the list of complex numbers
   * @param operations the list of operations
   */
  public EqualsHandler(DisplayComponent display, ArrayList<ComplexNumber> nums, ArrayList<OperationType> operations)
  {
    this.display = display;
    this.nums = nums;
    this.operations = operations;
  }
  
  /**
   * When clicked, the system calculates the result of the
   * list of complex numbers based on the operations it was given, 
   * and adds the result to the display.
   * The list of complex numbers and operations is then cleared,
   * and the result is added to the list of complex numbers to be used in the next calculation.
   * 
   * @param e when the user clicks the "=" button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // Tries to calculate the result
    try
    { 
      // declares result
      ComplexNumber result;
      
      // if the size of nums is two, only perform operations that require two operands
      if (nums.size() == 2)
      {
        // checks the first operation that is going to be performed, 
        // then performs it on the first two complex numbers
        if (operations.get(0) == OperationType.ADDITION) result = Operation.add(nums.get(0), nums.get(1));
        else if (operations.get(0) == OperationType.SUBTRACTION) result = Operation.subtract(nums.get(0), nums.get(1));
        else if (operations.get(0) == OperationType.MULTIPLICATION) result = Operation.multiply(nums.get(0), nums.get(1));
        else if (operations.get(0) == OperationType.DIVISION) result = Operation.divide(nums.get(0), nums.get(1));
        else result = null;
      }
      // if the size of nums is one, only perform operations that require one operand
      else if (nums.size() == 1)
      {
        // checks what operation is going to be performed,
        // then performs it on the first complex number
        if (operations.get(0) == OperationType.INVERSE) result = Operation.inverse(nums.get(0));
        else result = null;
      }
      // if nums is empty, result is set to null
      else result = null;
      
      // adds the result to display and fixes the typesetting
      display.addText(" = " + result.toString());
      display.displayTypesetting(0, display.getText().length());
      
      // clears the current operations
      operations.clear();
      
      //clears the complexNumber list
      nums.clear();
      
      // add result to nums so that it can be used in the next expression
      nums.add(result);
    }
    catch (Exception ex)
    {
      // if result could not be calculated, beep
      Toolkit.getDefaultToolkit().beep();
    }
  }

}
