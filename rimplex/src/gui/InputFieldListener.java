package gui;


import javax.swing.event.*;
import javax.swing.text.*;

/**
 * @author Seth Roper
 * @version 3/25/2021 A listener class which listens for the InputField document events and updates
 *          it. Shouldn't need to be messed with. But could potentially used to filter inputs??
 */
public class InputFieldListener implements DocumentListener
{
  private String spacing = "\n\n";

  /**
   * constructs an inputField Listener.
   */
  public InputFieldListener()
  {
  }

  /**
   * Called when the style of some of the text in the listened-to document changes.
   * 
   * @param e
   *          document event.
   */
  public void changedUpdate(final DocumentEvent e)
  {
    Document source;

    source = e.getDocument();

    try
    {
      System.out
          .println("In changeUpdate:\n" + source.getText(0, source.getLength()) + this.spacing);
    }
    catch (BadLocationException ble)
    {
      // Shouldn't get here
    }
  }

  /**
   * Called when text is inserted into the listened-to document.
   * 
   * @param e
   *          the document event.
   */
  public void insertUpdate(final DocumentEvent e)
  {
    Document source;

    source = e.getDocument();

    try
    {
      System.out
          .println("In insertUpdate:\n" + source.getText(0, source.getLength()) + this.spacing);
    }
    catch (BadLocationException ble)
    {
      // Shouldn't get here
    }
  }

  /**
   * Called when text is removed from the listened-to document.
   * 
   * @param e
   *          the document event.
   */
  public void removeUpdate(final DocumentEvent e)
  {
    Document source;

    source = e.getDocument();

    try
    {
      System.out
          .println("In removeUpdate:\n" + source.getText(0, source.getLength()) + this.spacing);
    }
    catch (BadLocationException ble)
    {
      // Shouldn't get here
    }
  }

}
