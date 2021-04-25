package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Seth Roper
 * @version 3/31/2021 Handles actions for the clear button.
 *
 */
public class ClearHandler implements ActionListener
{

  private InputField field;

  /**
   * Creates a clear handler.
   * 
   * @param field
   *          the input field
   */
  public ClearHandler(final InputField field)
  {
    this.field = field;
  }

  /**
   * Clears the input field.
   * 
   * @param e
   *          when the user click the clear button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // clears input field
    field.clear();
    field.getTextField().requestFocus();
  }

}
