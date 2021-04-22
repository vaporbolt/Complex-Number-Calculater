package gui;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LanguageListener implements ListSelectionListener
{
  
  private JList<String> languageList;
  private DefaultListModel<String> model;
  private JButton close;
  private JLabel settings;
  private JLabel language;
  private JButton about;
  private JLabel stepLabel;
  
  public LanguageListener(JList<String> languageList, DefaultListModel<String> model, JButton close, JLabel settings, JLabel language, JButton about, JLabel stepLabel)
  {
    this.languageList = languageList;
    this.model = model;
    this.close = close;
    this.settings = settings;
    this.language = language;
    this.about = about;
    this.stepLabel = stepLabel;
  }

  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    if (!e.getValueIsAdjusting())
    {
      if (languageList.getSelectedValue().equals("Spanish") || languageList.getSelectedValue().equals("Español") || languageList.getSelectedValue().equals("L'espagnol"))
      {
        model.setElementAt("Inglés", 0);
        model.setElementAt("Español", 1);
        model.setElementAt("Francés", 2);
        close.setText("Cerrar");
        settings.setText("Configuración");
        language.setText("Idioma:");
        about.setText("acerca de");
        stepLabel.setText("Pasos");
      }
      else if (languageList.getSelectedValue().equals("English") || languageList.getSelectedValue().equals("Inglés") || languageList.getSelectedValue().equals("Anglais"))
      {
        model.setElementAt("English", 0);
        model.setElementAt("Spanish", 1);
        model.setElementAt("French", 2);
        close.setText("Close");
        settings.setText("Settings");
        language.setText("Language:");
        about.setText("about");
        stepLabel.setText("Steps");
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
        stepLabel.setText("Pas");
      }
    }
  }

}
