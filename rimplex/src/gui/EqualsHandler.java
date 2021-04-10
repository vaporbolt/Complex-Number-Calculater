package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import math.ComplexNumber;
import math.Operation;
import math.OperationType;
import util.EnteringComplexNumbers;

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
  private InputField input;
  
  /**
   * Creates an equals handler.
   * 
   * @param display the system's display
   * @param nums the list of complex numbers
   * @param operations the list of operations
   */
  public EqualsHandler(DisplayComponent display, InputField input, ArrayList<ComplexNumber> nums, ArrayList<OperationType> operations)
  {
    this.display = display;
    this.nums = nums;
    this.operations = operations;
    this.input = input;
  }
  
  /**
   * When clicked, the system calculates the result of the
   * list of complex numbers based on the operations it was given, 
   * and adds the result to the display.
   * 
   * @param e when the user clicks the "=" button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // Tries to calculate the result
    try
    {
      ComplexNumber result;
      
      // gets the result of the equation in the input field
      result = EnteringComplexNumbers.parseEquation(input.getTextField().getText());
      
      // adds the result to the display
      display.addText(input.getTextField().getText() + " = " + result.toString() + "\n");
      
      // sets the input field to the result to use for the next calculation
      input.getTextField().setText(result.toString());
      
      // apply typestting for display
      display.displayTypesetting(0, display.getText().length());
    }
    catch (Exception ex)
    {
      // if result could not be calculated, beep
      Toolkit.getDefaultToolkit().beep();
    }
  }

}
