package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import math.OperationType;

/**
 * @author Seth Roper
 * @version 3/31/2021
 * Handles actions for the addition button.
 *
 */
public class AdditionHandler implements ActionListener
{
  private ArrayList<OperationType> operations;
  private InputField input;
  
  /**
   * Creates an addition handler
   * 
   * @param display the system's display
   * @param operations the operations list
   */
  public AdditionHandler(InputField input, ArrayList<OperationType> operations)
  {
    this.operations = operations;
    this.input = input;
  }
  
  /**
   * When clicked, adds addition to the operation list
   * and adds a "+" to the input field.
   * 
   * @param e when the user clicks the "+" button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // try to use button
    try
    {
      // add addition to operation list
      operations.add(OperationType.ADDITION);
      
      // adds addition sign to input field
      input.getTextField().setText(input.getTextField().getText() + " + ");
    }
    catch(Exception ex)
    {
      // beep if button doesn't work
      Toolkit.getDefaultToolkit().beep();
    }
    
  }

}
