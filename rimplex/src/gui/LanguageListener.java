package gui;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Creates a listener to the language menu component.
 * 
 * @author Jackson Brantley
 *
 */
public class LanguageListener implements ListSelectionListener
{
  
  private JList<String> languageList;
  private DefaultListModel<String> model;
  private JButton close;
  private JLabel settings;
  private JLabel language;
  private JButton about;
  private JLabel stepLabel;
  private StepDisplay stepWindow;
  
  /**
   * Creates a language listener.
   * 
   * @param languageList the language menu
   * @param model the language menu
   * @param close the settings close button
   * @param settings the settings window
   * @param language the language label for settings
   * @param about the about button
   * @param stepLabel the label for the step window
   * @param stepWindow the step window
   */
  public LanguageListener(JList<String> languageList, DefaultListModel<String> model, JButton close, JLabel settings,
      JLabel language, JButton about, JLabel stepLabel, StepDisplay stepWindow)
  {
    this.languageList = languageList;
    this.model = model;
    this.close = close;
    this.settings = settings;
    this.language = language;
    this.about = about;
    this.stepLabel = stepLabel;
    this.stepWindow = stepWindow;
  }

  /**
   * Sets the new language for all of the items the language listener is parsed.
   * 
   * @param e the list selection event
   */
  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    if (!e.getValueIsAdjusting())
    {
      if (languageList.getSelectedValue().equals("Spanish") || languageList.getSelectedValue().equals("Español") 
          || languageList.getSelectedValue().equals("L'espagnol"))
      {
        model.setElementAt("Inglés", 0);
        model.setElementAt("Español", 1);
        model.setElementAt("Francés", 2);
        close.setText("Cerrar");
        settings.setText("Configuración");
        language.setText("Idioma:");
        about.setText("acerca de");
        stepLabel.setText("Pasos");
        stepWindow.setLanguage("Spanish");
        stepWindow.applyLanguage();
        stepWindow.displayTypesetting(0, stepWindow.getPane().getText().length());
      }
      else if (languageList.getSelectedValue().equals("English") || languageList.getSelectedValue().equals("Inglés") 
          || languageList.getSelectedValue().equals("Anglais"))
      {
        model.setElementAt("English", 0);
        model.setElementAt("Spanish", 1);
        model.setElementAt("French", 2);
        close.setText("Close");
        settings.setText("Settings");
        language.setText("Language:");
        about.setText("about");
        stepLabel.setText("Steps");
        stepWindow.setLanguage("English");
        stepWindow.applyLanguage();
        stepWindow.displayTypesetting(0, stepWindow.getPane().getText().length());
      }
      else 
      {
        model.setElementAt("Anglais", 0);
        model.setElementAt("L'espagnol", 1);
        model.setElementAt("Français", 2);
        close.setText("Fermer");
        settings.setText("Paramètres");
        language.setText("Langue:");
        about.setText("à propos de");
        stepLabel.setText("Des Pas");
        stepWindow.setLanguage("French");
        stepWindow.applyLanguage();
        stepWindow.displayTypesetting(0, stepWindow.getPane().getText().length());
      }
    }
  }

}
