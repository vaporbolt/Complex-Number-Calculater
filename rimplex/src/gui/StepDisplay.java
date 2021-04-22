package gui;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

public class StepDisplay
{
  private JTextPane stepWindow;
  private String newLanguage = "English";
  private String oldLanguage = null;
  
  public StepDisplay()
  {
    stepWindow = new JTextPane();
  }
  
  public JTextPane getPane()
  {
    return stepWindow;
  }
  
  public String getLanguage()
  {
    return newLanguage;
  }
  
  public void setLanguage(String language)
  {
    oldLanguage = this.newLanguage;
    this.newLanguage = language;
  }
  
  /**
   * Replaces all i characters in display with italicized i characters.
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
   * Replaces all i characters in display with italicized i characters.
   * 
   * @param start the offset of the string to typeset
   * @param end the end of the string to typeset
   */
  public void applyLanguage()
  {
    if (oldLanguage == null) return;
    
    if (oldLanguage.equals("English"))
    {
      if (newLanguage.equals("English")) applyLanguage("Step", "Step");
      if (newLanguage.equals("Spanish")) applyLanguage("Step", "Paso");
      if (newLanguage.equals("French")) applyLanguage("Step", "Pas ");
    }
    else if (oldLanguage.equals("Spanish"))
    {
      if (newLanguage.equals("English")) applyLanguage("Paso", "Step");
      if (newLanguage.equals("Spanish")) applyLanguage("Paso", "Paso");
      if (newLanguage.equals("French")) applyLanguage("Paso", "Pas");
    }
  }
  
  private void applyLanguage(String step, String step2)
  {
    try
    { 
      stepWindow.setText(stepWindow.getText().replaceAll(step, step2));
    }
    catch (Exception e)
    {
      
    }
  }

}
