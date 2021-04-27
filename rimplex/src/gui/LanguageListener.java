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
  private JLabel graphL;
  private JLabel xAxis;
  private AboutFrame aboutFrame;

  /**
   * Creates a language listener.
   * 
   * @param languageList
   *          the language menu
   * @param model
   *          the language menu
   * @param close
   *          the settings close button
   * @param settings
   *          the settings window
   * @param language
   *          the language label for settings
   * @param about
   *          the about button
   * @param stepLabel
   *          the label for the step window
   * @param stepWindow
   *          the step window
   * @param graphL
   *          the graph label
   * @param xAxis
   *          the x axis label
   * @param aboutFrame the frame which contains the about section,
   */
  public LanguageListener(final JList<String> languageList, final DefaultListModel<String> model,
      final JButton close, final JLabel settings, final JLabel language, final JButton about,
      final JLabel stepLabel, final StepDisplay stepWindow, final JLabel graphL, final JLabel xAxis,
      final AboutFrame aboutFrame)
  {
    this.languageList = languageList;
    this.model = model;
    this.close = close;
    this.settings = settings;
    this.language = language;
    this.about = about;
    this.stepLabel = stepLabel;
    this.stepWindow = stepWindow;
    this.graphL = graphL;
    this.xAxis = xAxis;
    this.aboutFrame = aboutFrame;
  }

  /**
   * Sets the new language for all of the items the language listener is parsed.
   * 
   * @param e
   *          ListSelectionEvent
   */
  @Override
  public void valueChanged(final ListSelectionEvent e)
  {
    final String english = "English";
    final String spanish = "Spanish";
    final String french = "French";
    final String espanol = "Español";
    final String lespagnol = "L'espagnol";
    final String ingles = "Inglés";
    final String anglais = "Anglais";
    if (!e.getValueIsAdjusting())
    {
      if (languageList.getSelectedValue().equals(spanish)
          || languageList.getSelectedValue().equals(espanol)
          || languageList.getSelectedValue().equals(lespagnol))
      {
        model.setElementAt(ingles, 0);
        model.setElementAt(espanol, 1);
        model.setElementAt("Francés", 2);
        close.setText("Cerrar");
        settings.setText("Configuración");
        language.setText("Idioma:");
        about.setText("acerca de");
        stepLabel.setText("Pasos");
        stepWindow.setLanguage(spanish);
        stepWindow.applyLanguage();
        stepWindow.displayTypesetting(0, stepWindow.getPane().getText().length());
        graphL.setText("Gráfico de Números Complejos");
        aboutFrame.changeText(AboutFrame.SPANISH);
      }
      else if (languageList.getSelectedValue().equals(english)
          || languageList.getSelectedValue().equals(ingles)
          || languageList.getSelectedValue().equals(anglais))
      {
        model.setElementAt(english, 0);
        model.setElementAt(spanish, 1);
        model.setElementAt(french, 2);
        close.setText("Close");
        settings.setText("Settings");
        language.setText("Language:");
        about.setText("about");
        stepLabel.setText("Steps");
        stepWindow.setLanguage(english);
        stepWindow.applyLanguage();
        stepWindow.displayTypesetting(0, stepWindow.getPane().getText().length());
        graphL.setText("Complex Number Graph");
        xAxis.setText("real(x)");
        aboutFrame.changeText(AboutFrame.ENGLISH);
      }
      else
      {
        model.setElementAt(anglais, 0);
        model.setElementAt(lespagnol, 1);
        model.setElementAt("Français", 2);
        close.setText("Fermer");
        settings.setText("Paramètres");
        language.setText("Langue:");
        about.setText("à propos de");
        stepLabel.setText("Des Pas");
        stepWindow.setLanguage(french);
        stepWindow.applyLanguage();
        stepWindow.displayTypesetting(0, stepWindow.getPane().getText().length());
        graphL.setText("Graphique des Nombres Complexes");
        xAxis.setText("réel(x)");
        aboutFrame.changeText(AboutFrame.FRENCH);
      }
    }
  }

}
