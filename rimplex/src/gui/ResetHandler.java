package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import math.ComplexNumber;
import math.OperationType;

/**
 * @author Seth Roper
 * @version 3/31/2021
 * Handles actions for the reset button.
 *
 */
public class ResetHandler implements ActionListener
{

  private DisplayComponent display;
  private InputField input;
  private ArrayList<ComplexNumber> nums;
  private ArrayList<OperationType> operations;
  
  /**
   * Creates a rest handler.
   * 
   * @param display the system's display
   * @param nums the list of complex numbers
   * @param operations the list of operations
   */
  public ResetHandler(DisplayComponent display, InputField input, ArrayList<ComplexNumber> nums, ArrayList<OperationType> operations)
  {
    this.display = display;
    this.nums = nums;
    this.operations = operations;
    this.input = input;
  }
  
  /**
   * When clicked, the text from display and input field is cleared, and the 
   * list of complex numbers and operations is emptied.
   * 
   * @param e when user clicks the reset button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // resets display text, input field text, complex numbers list, and operations list
    display.reset();
    input.clear();
    nums.clear();
    operations.clear();
    
  }

}
