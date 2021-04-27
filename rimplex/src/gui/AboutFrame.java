package gui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import pictures.RimplexIcon;

/**
 * @author Seth Roper
 * @version 4/26/2021
 * holds the jframe for the about window.
 *
 */
public class AboutFrame
{
  // English translation
  // I know these should honestly be read by a file, but Time is a little low so
  // this is what is happening.
  static final String ENGLISH = "  Rimplex is a  complex  number calculator software product"
      + "  developed by  Sagacious Media. Although developed primarily to handle complex "
      + "numbers, Rimplex can also perform operations on real numbers. Rimplex offers a "
      + "plethora of features that are useful when performing computations on real and complex "
      + "numbers. In addition to the ability to enter complex numbers into the calculator,"
      + "  It has support for the addition, subtraction, division, multiplication, division"
      + ",  exponentiation, logarithm,  converse, and polar form operators. Rimplex also"
      + " allows for the extraction of the real or imaginary parts of a complex number,"
      + " so that those can be calculated in isolation if desired. Rimplex can be operated"
      + " using a physical keyboard or with the provided virtual number pad.  The current"
      + " input can be cleared with the clear button, and the entire input can be reset"
      + " using the reset button.  Rimplex also automatically graphs all complex numbers"
      + " calculated onto a complex number plane, visible via the complex number plane display."
      + "  Rimplex automatically displays all calculations performed into the history display."
      + " The history can be printed to a printer through the print button. For the more complex"
      + " operations, Rimplex offers a display detailing the step by step processes used in order"
      + " to calculate the result. Rimplex also offers support for various different languages."
      + " Rimplex is a versatile complex number calculator with powerful computation features."
      + " We hope you enjoy using it.\r\n"
      + "";
  
  // French translation
  static final String FRENCH = "  Rimplex est un logiciel de calcul de nombre complexes, créé"
      + " par Sagacious Media. Même si c'était construit essentiellement pour se charger des"
      + " nombres complexes, Rimplex peut aussi réaliser des opérations sur des nombres réels."
      + " Rimplex offre une pléthore de fonctions qui sont utiles pour des computations avec"
      + " des nombres réels et complexes. En plus de pouvoir mettre des numéros complexes dans"
      + " la calculatrice, vous pouvez aussi faire de l’addition, soustraction, division,"
      + " multiplication, d'exponentiation, logarithme, réciproque, et utiliser les opérateurs"
      + " a form polar. Rimplex permet également les extractions des parties réelles ou imaginaires"
      + " d’un numéro complexe, pour que vous puissiez le calculer tout seul, si vous le voulez."
      + " Rimplex peut fonctionner avec un vrai clavier ou virtuellement avec un pavé numérique. "
      + "L'entrée actuelle peut être dégagée avec le bouton \"dégagé\", et toutes les entrées "
      + "peuvent être remises à zéro en utilisant le bouton \"réinitialiser\".  Rimplex aussi "
      + "trace automatiquement tous les nombres complexes calculés sur un plan numérique complexe,"
      + " visible sur l'écran de nombres complexes. La mémoire peut être imprimée avec le bouton"
      + " “imprimer”. Pour les opérations plus complexes, Rimplex offre un écran qui explique étape"
      + " par étape le processus utilisé pour calculer le résultat. Rimplex peut aussi être utilisé"
      + " avec beaucoup de langues différentes. Rimplex est une calculatrice de nombres complexes "
      + "a multiusage avec des éléments de computations très puissants. "
      + "Nous espérons que vous l'aimerez! \r\n" + "";
  
  // SPanish translation
  static final String SPANISH = "  Rimplex es un calculadora de numeros complejos produce por"
      + " Sagacious Media. Aún que creado por numeros largos, Rimplex támbien tiene la capacidad "
      + "performa operaciónes on numeros verdaderos. Rimplex ofrecé un pletorá de featuros que son"
      + " muy facil para utilzar cuándo performarando computaciones muy dificiles. Támbien tiene"
      + " la abilidad para addicion, subtracion, divison, multiplicasion, exponentatacion, "
      + "logorithmas, conversos, y formas polares operaciones. Rimplex permite para la extraciones"
      + " de numeros imaginarios y verdaderos, entonces pueden islar la parte de numeros si quieres"
      + ".Rimplex Graphear todos los numeros complexos por el numero complexo. Rimplex mostra todos"
      + " los calculaciones en la historía. La Historía puede ser imprimido utilizando la butón de "
      + "imprimir. Rimplex ofrece un ejemplo de los calculaciones más complicados Rimplex tiene "
      + "ayuda entre los lenguajes diferente. ¡Nosostros Ojálamos que ustedes disfrutan "
      + "utilizando!\r\n"
      + "";

  private int width = 350;
  private int height = 200;
  private JTextPane pane;
  private JFrame frame;
  // the location of the calculator frame.
  private JFrame parentFrame;
  


  /**
   * creates a new about frame for the about window  with a JtextPane inside.
   * @param text the text for the about box.
   * @param parentFrame the main calculator's frame.
   */
  public AboutFrame(final String text, final JFrame parentFrame)
  {
    this.parentFrame = parentFrame;
    this.frame = new JFrame();
    this.pane = new JTextPane();
    this.pane.setEditable(false);
    this.pane.setBackground(Color.GRAY);
    JScrollPane scrollDisplay = new JScrollPane(this.pane);
    scrollDisplay.setViewportBorder(null);
    scrollDisplay.setBorder(null);
   // scrollDisplay.setPreferredSize(new Dimension(this.scrollWidth,this.scrollHeight));
    pane.setText(text);
   // this.frame.getContentPane().add(pane);
    this.frame.getContentPane().add(scrollDisplay);
  }
  

  /**
   * create the JFrame and makes it visible.
   */
  public void createFrame()
  {
    // set to dispose on close. 
    this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.frame.setSize(width, height);
    this.frame.setResizable(false);
    ImageIcon icon = new ImageIcon(RimplexIcon.class.getResource("/pictures/logoRimplex.png"));
    Image newimg = icon.getImage().getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);
    frame.setLocation(this.parentFrame.getX(),
        (int) (this.parentFrame.getY() + 0.5 * this.parentFrame.getHeight()));
    frame.setIconImage(newimg);
    frame.setVisible(true);
  }

  /**
   * @param text
   *          the about menu to change
   */
  public void changeText(final String text)
  {
    this.pane.setText(text);
  }
  
  /**
   * @param title the title of the jframe
   */
  public void changeTitle(final String title)
  {
    this.frame.setTitle(title);
  }
  
  
}
