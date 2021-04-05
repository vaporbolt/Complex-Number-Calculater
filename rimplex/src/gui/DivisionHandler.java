package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import math.OperationType;

/**
 * @author Seth Roper
 * @version 3/31/2021
 * Handles actions for the division button.
 *
 */
public class DivisionHandler implements ActionListener
{

  private DisplayComponent display;
  private ArrayList<OperationType> operations;
  
  /**
   * Creates a division handler
   * 
   * @param display the system's display
   * @param operations the operations list
   */
  public DivisionHandler(DisplayComponent display, ArrayList<OperationType> operations)
  {
    this.operations = operations;
    this.display = display;
  }
  
  /**
   * When clicked, adds division to the operation list
   * and adds a "/" to the display.
   * 
   * @param e when the user clicks the division button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // tries to click button
    try
    {
      // adds division to operations list
      operations.add(OperationType.DIVISION);
      
      // add a "/" to display
      display.addText(" / ");
      display.displayTypesetting(0, display.getText().length());
    }
    catch (Exception ex)
    {
      // beep if button doesn't work
      Toolkit.getDefaultToolkit().beep();
    }
  }
}
