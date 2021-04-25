package gui;

import java.util.Locale;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

/**
 * The step window class.
 * 
 * @author Jackson Brantley
 *
 */
public class StepDisplay
{
  private JTextPane stepWindow;
  private String newLanguage;
  private String oldLanguage;
  
  private final String english = "English";
  private final String french = "French";
  private final String spanish = "Spanish";

  /**
   * Constructs a step display.
   */
  public StepDisplay()
  {
    stepWindow = new JTextPane();
    if (Locale.getDefault().getLanguage().equals("en"))
      newLanguage = english;
    else if (Locale.getDefault().getLanguage().equals("fr"))
      newLanguage = french;
    else if (Locale.getDefault().getLanguage().equals("es"))
      newLanguage = spanish;
    else
      newLanguage = english;
    oldLanguage = null;
  }

  /**
   * Gets the text area for the step window.
   * 
   * @return the text pane
   */
  public JTextPane getPane()
  {
    return stepWindow;
  }

  /**
   * Gets the current language for the window.
   * 
   * @return the language
   */
  public String getLanguage()
  {
    return newLanguage;
  }

  /**
   * Clears the display.
   */
  public void reset()
  {
    stepWindow.setText("");
  }

  /**
   * Sets the current language.
   * 
   * @param language
   *          the new language
   */
  public void setLanguage(final String language)
  {
    oldLanguage = this.newLanguage;
    this.newLanguage = language;
  }

  /**
   * Replaces all i characters in stepDisplay with italicized i characters.
   * 
   * @param start
   *          the offset of the string to typeset
   * @param end
   *          the end of the string to typeset
   */
  public void displayTypesetting(final int start, final int end)
  {
    try
    {
      // copy the text from display into placeholder string
      String s = stepWindow.getDocument().getText(start, end);

      // length of the text that has been removed from string s, for indexing purposes
      int length = 0;

      // continue looping over string s while there are still i characters
      final String i = "i";
      while (s.contains(i))
      {
        // sets the current index to the next i character in display
        int index = s.indexOf(i) + length;

        // removes the un-italic i from display
        stepWindow.getDocument().remove(index, 1);

        // adds the now italicized i to the location the un-italic i was in
        stepWindow.getDocument().insertString(index, i, TypesettingStyle.applyTypesetting(true));

        // sets length to the length of text that has been removed from string s
        length += s.substring(0, s.indexOf(i) + 1).length();

        // removes everything in the string before and including the current i character
        s = s.substring(s.indexOf(i) + 1);
      }
    }
    catch (BadLocationException e)
    {

    }
  }

  /**
   * Replaces all words with the correct language.
   */
  public void applyLanguage()
  {
    final String step = "Step";
    final String pas = "Pas ";
    final String real = "real";
    final String réel = "réel";
    final String paso = "Paso";
    
    if (oldLanguage == null)
      return;

    if (stepWindow.getText().contains(step) && newLanguage.equals(french))
    {
      applyLanguage(step, pas);
      applyLanguage(real, réel);
    }
    else if (stepWindow.getText().contains(step) && newLanguage.equals(spanish))
    {
      applyLanguage(step, paso);
    }
    else if (stepWindow.getText().contains(pas) && newLanguage.equals(english))
    {
      applyLanguage(pas, step);
      applyLanguage(réel, real);

    }
    else if (stepWindow.getText().contains(pas) && newLanguage.equals(spanish))
    {
      applyLanguage(pas, paso);
      applyLanguage(réel, real);
    }
    else if (stepWindow.getText().contains(paso) && newLanguage.equals(french))
    {
      applyLanguage(paso, pas);
      applyLanguage(real, réel);
    }
    else if (stepWindow.getText().contains(paso) && newLanguage.equals(english))
    {
      applyLanguage(paso, step);
    }
  }

  /**
   * Helper method for the applyLanguage method.
   * 
   * @param step
   *          the old language to replace
   * @param step2
   *          the new language to replace with
   */
  private void applyLanguage(final String step, final String step2)
  {
    stepWindow.setText(stepWindow.getText().replaceAll(step, step2));
  }

}
