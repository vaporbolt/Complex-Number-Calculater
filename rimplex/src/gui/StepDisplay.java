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
  
  /**
   * Constructs a step display.
   */
  public StepDisplay()
  {
    stepWindow = new JTextPane();
    if (Locale.getDefault().getLanguage().equals("en")) newLanguage = "English";
    else if (Locale.getDefault().getLanguage().equals("fr")) newLanguage = "French";
    else if (Locale.getDefault().getLanguage().equals("es")) newLanguage = "Spanish";
    else newLanguage = "English";
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
   * Sets the current language.
   * 
   * @param language the new language
   */
  public void setLanguage(String language)
  {
    oldLanguage = this.newLanguage;
    this.newLanguage = language;
  }
  
  /**
   * Replaces all i characters in stepDisplay with italicized i characters.
   * 
   * @param start the offset of the string to typeset
   * @param end the end of the string to typeset
   */
  public void displayTypesetting(int start, int end)
  {
    try
    { 
      // copy the text from display into placeholder string
      String s = stepWindow.getDocument().getText(start, end);
      
      // length of the text that has been removed from string s, for indexing purposes
      int length = 0;
      
      // continue looping over string s while there are still i characters
      while (s.contains("i"))
      {
        // sets the current index to the next i character in display
        int index = s.indexOf("i") + length;
        
        // removes the un-italic i from display
        stepWindow.getDocument().remove(index, 1);
        
        // adds the now italicized i to the location the un-italic i was in
        stepWindow.getDocument().insertString(index, "i", TypesettingStyle.applyTypesetting(true));
        
        // sets length to the length of text that has been removed from string s
        length += s.substring(0, s.indexOf("i") + 1).length();
        
        // removes everything in the string before and including the current i character
        s = s.substring(s.indexOf("i") + 1);
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
    if (oldLanguage == null) return;

    if (stepWindow.getText().contains("Step") && newLanguage.equals("French")) applyLanguage("Step", "Pas ");
    else if (stepWindow.getText().contains("Step") && newLanguage.equals("English")) applyLanguage("Step", "Step");
    else if (stepWindow.getText().contains("Step") && newLanguage.equals("Spanish")) applyLanguage("Step", "Paso");
    else if (stepWindow.getText().contains("Pas ") && newLanguage.equals("French")) applyLanguage("Pas ", "Pas ");
    else if (stepWindow.getText().contains("Pas ") && newLanguage.equals("English")) applyLanguage("Pas ", "Step");
    else if (stepWindow.getText().contains("Pas ") && newLanguage.equals("Spanish")) applyLanguage("Pas ", "Paso");
    else if (stepWindow.getText().contains("Paso") && newLanguage.equals("French")) applyLanguage("Paso", "Pas ");
    else if (stepWindow.getText().contains("Paso") && newLanguage.equals("English")) applyLanguage("Paso", "Step");
    else if (stepWindow.getText().contains("Paso") && newLanguage.equals("Spanish")) applyLanguage("Paso", "Paso");
  }
  
  /**
   * Helper method for the applyLanguage method.
   * 
   * @param step the old language to replace
   * @param step2 the new language to replace with
   */
  private void applyLanguage(String step, String step2)
  {
    stepWindow.setText(stepWindow.getText().replaceAll(step, step2));
  }

}
