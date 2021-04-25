package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Number button action handler.
 * 
 * @author Jackson Brantley
 *
 */
public class NumActionHandler implements ActionListener
{
  private InputField input;
  private int num;

  /**
   * Creates num handler with the given number.
   * 
   * @param input
   *          the input field
   * @param num
   *          the number to display
   */
  public NumActionHandler(final InputField input, final int num)
  {
    this.input = input;
    this.num = num;
  }

  /**
   * Adds the number to the input field.
   * 
   * @param e
   *          ActionEvent
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    input.getTextField().setText(input.getTextField().getText() + num);
    input.inputTypesetting(0, input.getTextField().getText().length());
  }

}
