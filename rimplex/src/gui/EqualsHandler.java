package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JTextPane;

import math.ComplexNumber;
import util.EnteringComplexNumbers;
import visualization.CartesianPlane;

/**
 * @author Seth Roper
 * @version 3/31/2021
 * Handles actions for the equals button.
 *
 */
public class EqualsHandler implements ActionListener
{

  private DisplayComponent display;
  private InputField input;
  private CartesianPlane plane;
  private StepDisplay stepWindow;
  
  /**
   * Creates an equals handler.
   * 
   * @param display the system's display
   * @param input the input field
   * @param plane the graph
   */
  public EqualsHandler(DisplayComponent display, InputField input, CartesianPlane plane, StepDisplay stepWindow)
  {
    this.display = display;
    this.input = input;
    this.plane = plane;
    this.stepWindow = stepWindow;
  }
  
  /**
   * When clicked, the system calculates the result of the
   * operation and adds the result to the display.
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
      Locale locale = Locale.getDefault();
      String answer = result.toString() + "\n";
      String format = String.format(locale, input.getTextField().getText() + " = %s", answer);
      display.addText(format);
      stepWindow.getPane().setText(result.getSteps());
      stepWindow.displayTypesetting(0, stepWindow.getPane().getText().length());
      
      // sets the input field to the result to use for the next calculation
      input.getTextField().setText(String.format(locale, "%s", result.toString()));
      
      // apply typestting for display
      display.displayTypesetting(0, display.getText().length());
      input.inputTypesetting(0, input.getTextField().getText().length());
      plane.addPoint(result);
      input.getTextField().requestFocus();

    }
    catch (Exception ex)
    {
      // if result could not be calculated, beep
      Toolkit.getDefaultToolkit().beep();
    }
  }

}
