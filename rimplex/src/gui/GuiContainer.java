package gui;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.RimplexDriver;
import color.ColorScheme;
import math.ComplexNumber;
import visualization.CartesianPlane;

/**
 * @author Seth Roper
 * @version 3/27/2020
 * 
 *          A class that contains all the gui components in a gui layout. a GuiContainer object is a
 *          singleton object that creates a window and holds the JPanel Pane and all components.
 *
 */
@SuppressWarnings("unused")
public class GuiContainer
{
  // keeps track if a GUIContainer exists or not.
  private static boolean exists = false;

  private final int jframeWidth = 340;
  private final int jframeHeight = 510;

  // holds the frame
  private JFrame frame = new JFrame("Rimplex");

  // holds all of the buttons(add subtract reset etc.)
  // private HashMap<String, JButton> buttons = new HashMap<String, JButton>();

  private static final ResourceBundle STRINGS = ResourceBundle.getBundle("languages.Strings");

  private InputField inputField = InputField.createInstance();

  private DisplayComponent display;

  private CartesianPlane plane;

  private JWindow historyWindow;

  private JWindow planeWindow;

  private JWindow settingWindow;

  private JWindow stepWindow;
  
  private ColorScheme scheme;
  
  private JLabel stepLabel;
  
  private StepDisplay steps;

  /**
   * creates the GUI container object with the proper gridbagLayout.
   */
  private GuiContainer(ColorScheme scheme)
  {
    this.scheme = scheme;
    this.createPlaneWindow();
    this.createHistoryWindow();
    this.createStepWindow();
    this.createSettingsWindow();
    this.addComponetsToPane();
  }

  /**
   * creates a GuiContainer object if one doesn't already exist, or throws an IllegalStateException
   * otherwise.
   * 
   * @return a GUI container containing all of the gui components.
   */
  public static GuiContainer createInstance(ColorScheme scheme)
  {
    if (exists)
    {
      throw new IllegalStateException("GuiContainer already exists");
    }

    else
    {
      exists = true;
      return new GuiContainer(scheme);
    }
  }

  /**
   * gets the GUI's display.
   * 
   * @return DisplayComponent
   */
  public DisplayComponent getDisplay()
  {
    return this.display;
  }

  /**
   * gets the GUI's plane.
   * 
   * @return CartesianPlane
   */
  public CartesianPlane getPlane()
  {
    return this.plane;
  }
  
  /**
   * gets the step display.
   * 
   * @return steps
   */
  public StepDisplay getSteps()
  {
    return this.steps;
  }

  /**
   * gets GUI's inputfield.
   * 
   * @return InputField
   */
  public InputField getInputField()
  {
    return this.inputField;
  }

  /**
   * @return the jframe for the gui.
   */
  public JFrame getJframe()
  {
    return this.frame;
  }

  /**
   * Sets the style for the buttons
   * 
   * @param button
   *          a button
   */
  private void setButton(JButton button)
  {
    button.setFocusPainted(false);
    button.setFont(new Font("TimesRoman", Font.PLAIN, 16));
    button.setBorder(BorderFactory.createLineBorder(scheme.getButtonBorderColor(), 2));
    button.setBackground(scheme.getButtonBackgroundColor());
  }

  /**
   * sets the gui to visible for display.
   */
  public void showGUI()
  {
    // frame 
    frame.getContentPane().setBackground(scheme.getBackgroundColor());
    frame.setMaximumSize(new Dimension(400, 1000));
    frame.setSize(this.jframeWidth, this.jframeHeight);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocation(500, 200);
    frame.addComponentListener(
        new FrameListener(historyWindow, planeWindow, settingWindow, stepWindow));
    
    // history window
    this.historyWindow.getContentPane().setBackground(scheme.getHistoryBackgroundColor());
    this.historyWindow.getRootPane()
        .setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, scheme.getBackgroundColor()));
    this.historyWindow.setSize(this.jframeWidth / 2 + 100, this.jframeHeight - 180);
    this.historyWindow.setLocation(frame.getX() + 305, frame.getY() + 135);
    this.historyWindow.setVisible(false);
    this.historyWindow.setAlwaysOnTop(true);
    
    // plane window
    this.planeWindow.getContentPane().setBackground(scheme.getPlaneBackgroundColor());
    this.planeWindow.getRootPane()
        .setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, scheme.getBackgroundColor()));
    this.planeWindow.setSize(this.jframeWidth / 2 + 100, this.jframeHeight - 180);
    this.planeWindow.setLocation(frame.getX() - 300, frame.getY() + 135);
    this.planeWindow.setVisible(false);
    this.planeWindow.setAlwaysOnTop(true);
    
    // settings window
    this.settingWindow.getContentPane().setBackground(scheme.getSettingsBackgroundColor());
    this.settingWindow.getRootPane()
        .setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, scheme.getBackgroundColor()));
    this.settingWindow.setSize(this.jframeWidth / 2 + 50, this.jframeHeight - 310);
    this.settingWindow.setLocation(frame.getX() + 300, frame.getY() + 35);
    this.settingWindow.setVisible(false);
    this.settingWindow.setAlwaysOnTop(true);
    
    // step window
    this.stepWindow.getContentPane().setBackground(scheme.getStepBackgroundColor());
    this.stepWindow.getRootPane()
        .setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, scheme.getBackgroundColor()));
    this.stepWindow.setSize(this.jframeWidth - 20, this.jframeHeight - 270);
    // this.stepWindow.setVisible(true);
    this.stepWindow.setLocation(frame.getX() + 10, frame.getY() + frame.getHeight() - 45);
    this.stepWindow.setVisible(false);
    this.stepWindow.setAlwaysOnTop(true);
    frame.addWindowStateListener(new WindowStateListener()
    {
      @Override
      public void windowStateChanged(WindowEvent e)
      {
        historyWindow.setVisible(false);
        planeWindow.setVisible(false);
        settingWindow.setVisible(false);
        stepWindow.setVisible(false);

      }
    });
    frame.addWindowListener(new WindowListener()
    {

      @Override
      public void windowOpened(WindowEvent e)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowClosing(WindowEvent e)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowClosed(WindowEvent e)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowIconified(WindowEvent e)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowDeiconified(WindowEvent e)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowActivated(WindowEvent e)
      {
        // TODO Auto-generated method stub

      }

      @Override
      public void windowDeactivated(WindowEvent e)
      {
        historyWindow.setVisible(false);
        planeWindow.setVisible(false);
        settingWindow.setVisible(false);
        stepWindow.setVisible(false);

      }

    });
  }

  /**
   * helper method adds the componets to the pane.
   */
  @SuppressWarnings("static-access")
  private void addComponetsToPane()
  {

    Container contentPane = frame.getContentPane();
    GridBagLayout gbl = new GridBagLayout();
    contentPane.setLayout(gbl);
    GridBagConstraints gbc = new GridBagConstraints();
    JButton button;

    // rimplex icon
    ImageIcon logo = new ImageIcon(this.getClass().getResource("/pictures/logoRimplex.png"));
    Image image = logo.getImage();
    Image newimg = image.getScaledInstance(175, 55, java.awt.Image.SCALE_SMOOTH);
    logo = new ImageIcon(newimg);
    JLabel label = new JLabel(logo);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = gbc.REMAINDER;
    gbc.gridheight = 1;
    // gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 20, 0, 0);
    gbl.setConstraints(label, gbc);
    contentPane.add(label);

    // Settings button
    ImageIcon settingIcon = new ImageIcon(this.getClass().getResource("/pictures/settingIcon.png"));
    image = settingIcon.getImage();
    newimg = image.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
    settingIcon = new ImageIcon(newimg);
    button = new JButton(settingIcon);
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.gridwidth = gbc.REMAINDER;
    gbc.gridheight = 1;
    gbc.anchor = GridBagConstraints.NORTHEAST;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 20, 0, 10);
    setButton(button);
    button.setBorderPainted(false);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {

        if (settingWindow.isVisible())
        {
          settingWindow.setVisible(false);
        }
        else
        {
          settingWindow.setVisible(true);
        }
      }

    });
    gbl.setConstraints(button, gbc);
    contentPane.add(button);

    // InputField/ display.
    JTextPane textField = this.inputField.getTextField();
    textField.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    JScrollPane inputScroll = new JScrollPane(textField);
    inputScroll.setViewportBorder(null);
    inputScroll.setBorder(null);
    inputScroll.setPreferredSize(new Dimension(40, 40));

    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.gridwidth = gbc.REMAINDER - 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 0, 0, 0);
    gbl.setConstraints(inputScroll, gbc);
    contentPane.add(inputScroll);

    // clear button
    button = new JButton("C");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0.12;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    setButton(button);
    button.setForeground(scheme.getButtonTertiary());
    button.addActionListener(new ClearHandler(inputField));
    contentPane.add(button);

    // sign Button
    button = new JButton("+/-");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0.08;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new SignHandler(inputField));
    setButton(button);
    button.setForeground(scheme.getButtonTertiary());
    contentPane.add(button);

    // backspace Button
    button = new JButton("<-");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.weightx = 0.08;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {

        String s = "";
        if (textField.getText().length() != 0)
          s = textField.getText().substring(0, textField.getText().length() - 1);
        textField.setText(s);
        inputField.inputTypesetting(0, textField.getText().length());
      }

    });
    setButton(button);
    button.setForeground(scheme.getButtonTertiary());
    contentPane.add(button);

    // addition Button
    button = new JButton("+");
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0.12;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new AdditionHandler(inputField));
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // reset button
    button = new JButton("R");
    gbc = new GridBagConstraints();
    gbc.gridx = 6;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0.12;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new ResetHandler(display, inputField, plane));
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // open parentheses button
    button = new JButton("(");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {

        textField.setText(textField.getText() + "(");
        inputField.inputTypesetting(0, textField.getText().length());
      }

    });
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // close parentheses button
    button = new JButton(")");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {

        textField.setText(textField.getText() + ")");
        inputField.inputTypesetting(0, textField.getText().length());
      }

    });
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // conjugate button
    button = new JButton("con");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {

        textField.setText("con"+textField.getText());
        inputField.inputTypesetting(0, textField.getText().length());
      }

    });
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // division symbol.
    button = new JButton("÷");
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new DivisionHandler(inputField));
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // equals button
    button = new JButton("=");
    gbc = new GridBagConstraints();
    gbc.gridx = 6;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new EqualsHandler(display, inputField, plane, steps));
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // complex number plane button
    button = new JButton("<");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 10, 10, 10);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (!planeWindow.isVisible())
        {
          int w = 325;
          planeWindow.setSize(0, planeWindow.getHeight());
          planeWindow.setLocation(frame.getX() + 35, planeWindow.getY());
          planeWindow.setVisible(true);

          Timer timer = new Timer(1, new ActionListener()
          {
            public void actionPerformed(ActionEvent evt)
            {
              if (planeWindow.getWidth() < w)
              {
                planeWindow.setSize(planeWindow.getWidth() + 15, planeWindow.getHeight());
                planeWindow.setLocation(planeWindow.getX() - 15, planeWindow.getY());
              }
              else
              {
                ((Timer) evt.getSource()).stop();
              }
            }
          });
          timer.start();

        }
      }

    });
    setButton(button);
    button.setBorderPainted(false);
    contentPane.add(button);

    // 2 button
    button = new JButton("2");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 2));
    setButton(button);
    contentPane.add(button);

    // exponet button
    button = new JButton("x²");
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {

        textField.setText(textField.getText() + "^");
        inputField.inputTypesetting(0, textField.getText().length());
      }

    });
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // 3 button
    button = new JButton("3");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 3));
    setButton(button);
    contentPane.add(button);

    // history button
    button = new JButton(">");
    gbc = new GridBagConstraints();
    gbc.gridx = 7;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 10, 10, 10);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (!historyWindow.isVisible())
        {
          int w = 320;
          historyWindow.setSize(0, historyWindow.getHeight());
          historyWindow.setVisible(true);

          Timer timer = new Timer(1, new ActionListener()
          {
            public void actionPerformed(ActionEvent evt)
            {
              if (historyWindow.getWidth() < w)
              {
                historyWindow.setSize(historyWindow.getWidth() + 16, historyWindow.getHeight());
              }
              else
              {
                ((Timer) evt.getSource()).stop();
              }
            }
          });
          settingWindow.setVisible(false);
          timer.start();

        }
      }

    });
    setButton(button);
    button.setBorderPainted(false);
    contentPane.add(button);

    // step button
    button = new JButton(" ^ ");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 9;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 0, 0, 0);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (!stepWindow.isVisible())
        {
          int h = 240;
          stepWindow.setSize(stepWindow.getWidth(), 0);
          stepWindow.setVisible(true);

          Timer timer = new Timer(1, new ActionListener()
          {
            public void actionPerformed(ActionEvent evt)
            {
              if (stepWindow.getHeight() < h)
              {
                stepWindow.setSize(stepWindow.getWidth(), stepWindow.getHeight() + 10);
              }
              else
              {
                ((Timer) evt.getSource()).stop();
              }
            }
          });
          timer.start();
        }
      }

    });
    setButton(button);
    button.setBorderPainted(false);
    contentPane.add(button);

    // 4 button
    button = new JButton("4");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 4));
    setButton(button);
    contentPane.add(button);

    // 5 button
    button = new JButton("5");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 5));
    setButton(button);
    contentPane.add(button);

    // 6 button
    button = new JButton("6");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 6));
    setButton(button);
    contentPane.add(button);

    // multiplication button
    button = new JButton("x");
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new MultiplicationHandler(inputField));
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // inverse button
    button = new JButton("inv");
    gbc = new GridBagConstraints();
    gbc.gridx = 6;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {

        textField.setText(textField.getText() + " ^-1 ");
        inputField.inputTypesetting(0, textField.getText().length());
      }

    });
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // 7 button
    button = new JButton("7");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 7;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 7));
    setButton(button);
    contentPane.add(button);

    // 8 button
    button = new JButton("8");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 7;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 8));
    setButton(button);
    contentPane.add(button);

    // 9 button
    button = new JButton("9");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 7;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 9));
    setButton(button);
    contentPane.add(button);

    // i button
    button = new JButton("i");
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 7;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {

        textField.setText(textField.getText() + "i");
        inputField.inputTypesetting(0, textField.getText().length());
      }

    });
    setButton(button);
    button.setFont(new Font("TimesRoman", Font.ITALIC, 16));
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // real part button
    button = new JButton("real");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        textField.setText("real"+textField.getText());
        inputField.inputTypesetting(0, textField.getText().length());
      }

    });
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // imaginary part button
    button = new JButton("im");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        textField.setText("im"+textField.getText());
        inputField.inputTypesetting(0, textField.getText().length());
      }

    });
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // polar form button
    button = new JButton("pol");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        textField.setText("pol"+textField.getText());
        inputField.inputTypesetting(0, textField.getText().length());
      }

    });
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // square root button
    button = new JButton("sqt");
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        textField.setText("sqrt"+textField.getText());
        inputField.inputTypesetting(0, textField.getText().length());
      }

    });
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // log button
    button = new JButton("log");
    gbc = new GridBagConstraints();
    gbc.gridx = 6;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        textField.setText("log"+textField.getText());
        inputField.inputTypesetting(0, textField.getText().length());
      }

    });
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // decimal button
    button = new JButton(".");
    gbc = new GridBagConstraints();
    gbc.gridx = 6;
    gbc.gridy = 7;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {

        textField.setText(textField.getText() + ".");
        inputField.inputTypesetting(0, textField.getText().length());
      }

    });
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // 0 button
    button = new JButton("0");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 8;
    gbc.gridwidth = 3;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 0));
    setButton(button);
    contentPane.add(button);

    // subtraction button
    button = new JButton("-");
    gbc = new GridBagConstraints();
    gbc.gridx = 6;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new SubtractionHandler(inputField));
    setButton(button);
    button.setForeground(scheme.getButtonSecondary());
    contentPane.add(button);

    // 1 button
    button = new JButton("1");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 1));
    setButton(button);
    contentPane.add(button);

  }

  /**
   * creates a JWindow containing the history component and a button that makes it invisible.
   */
  private void createHistoryWindow()
  {
    this.historyWindow = new JWindow();
    Container contentPane = this.historyWindow.getContentPane();
    GridBagLayout gbl = new GridBagLayout();
    contentPane.setLayout(gbl);
    GridBagConstraints gbc = new GridBagConstraints();
    JButton button;
    // Display
    display = DisplayComponent.createInstance(scheme.getHistoryBackgroundColor());
    display.getPanel().setFont(new Font("TimesRoman", Font.PLAIN, 16));
    // create scroll pane for the display/history and set a restricting size
    JScrollPane scrollDisplay = new JScrollPane(display.getPanel());
    scrollDisplay.setViewportBorder(null);
    scrollDisplay.setBorder(null);
    scrollDisplay.setPreferredSize(new Dimension(200, 200));

    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.ipadx = 200;
    gbc.ipady = 200;
    gbc.weightx = 0.9;
    gbc.weighty = 1;
    gbl.setConstraints(scrollDisplay, gbc);
    contentPane.add(scrollDisplay);

    button = new JButton("<");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0.1;
    gbc.weighty = 0;
    gbl.setConstraints(button, gbc);
    button.setBackground(scheme.getHistoryBackgroundColor());
    button.setBorderPainted(false);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (historyWindow.isVisible())
        {
          historyWindow.setSize(320, historyWindow.getHeight());
          Timer timer = new Timer(1, new ActionListener()
          {
            public void actionPerformed(ActionEvent evt)
            {
              if (historyWindow.getWidth() > 0)
              {
                historyWindow.setSize(historyWindow.getWidth() - 16, historyWindow.getHeight());
              }
              else
              {
                historyWindow.setVisible(false);
                ((Timer) evt.getSource()).stop();
              }
            }
          });
          settingWindow.setVisible(false);
          timer.start();
        }
      }

    });
    contentPane.add(button);

    // print button
    ImageIcon printIcon = new ImageIcon(this.getClass().getResource("/pictures/printIcon.png"));
    Image image = printIcon.getImage();
    Image newimg = image.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
    printIcon = new ImageIcon(newimg);
    button = new JButton(printIcon);
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTHEAST;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbl.setConstraints(button, gbc);
    button.setBackground(scheme.getHistoryBackgroundColor());
    button.setBorderPainted(false);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {

      }

    });
    contentPane.add(button);

    /*
     * JButton start = new JButton("start"); start.setVisible(true); gbc = new GridBagConstraints();
     * gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 1; gbc.gridheight = 1; gbc.fill =
     * GridBagConstraints.HORIZONTAL; gbc.anchor = GridBagConstraints.NORTH; gbc.weightx = 0;
     * gbc.weighty = 0; gbc.insets = new Insets(25, 0, 0, 0); gbl.setConstraints(start, gbc);
     * start.setBackground(new Color(199, 238, 255)); start.setBorderPainted(false);
     * start.setFont(new Font("TimesRoman", Font.PLAIN, 13)); contentPane.add(start);
     * 
     * JButton stop = new JButton("stop"); stop.setVisible(false); gbc = new GridBagConstraints();
     * gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 1; gbc.gridheight = 1; gbc.fill =
     * GridBagConstraints.HORIZONTAL; gbc.anchor = GridBagConstraints.NORTH; gbc.weightx = 0;
     * gbc.weighty = 0; gbc.insets = new Insets(25, 0, 0, 0); gbl.setConstraints(stop, gbc);
     * stop.setBackground(new Color(199, 238, 255)); stop.setBorderPainted(false); stop.setFont(new
     * Font("TimesRoman", Font.PLAIN, 13)); stop.addActionListener((ActionListener) new
     * ActionListener() {
     * 
     * @Override public void actionPerformed(ActionEvent e) { stop.setVisible(false);
     * start.setVisible(true); }
     * 
     * }); contentPane.add(stop);
     * 
     * start.addActionListener((ActionListener) new ActionListener() {
     * 
     * @Override public void actionPerformed(ActionEvent e) { start.setVisible(false);
     * stop.setVisible(true); }
     * 
     * });
     */

  }

  /**
   * creates a window to hold the complex number plane.
   */
  private void createPlaneWindow()
  {
    this.planeWindow = new JWindow();
    Container contentPane = this.planeWindow.getContentPane();
    GridBagLayout gbl = new GridBagLayout();
    contentPane.setLayout(gbl);
    GridBagConstraints gbc = new GridBagConstraints();
    JButton button;
    // Cartesian Plane
    plane = new CartesianPlane();
    JScrollPane scrollPlane = new JScrollPane(plane);
    scrollPlane.setPreferredSize(new Dimension(200, 200));
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 5;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.ipadx = 200;
    gbc.ipady = 200;
    gbc.weightx = .9;
    gbc.weighty = 0;
    gbl.setConstraints(scrollPlane, gbc);
    contentPane.add(scrollPlane);

    button = new JButton(">");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0.1;
    gbc.weighty = 0;
    gbc.insets = new Insets(90, 0, 0, 0);
    gbl.setConstraints(button, gbc);
    button.setBackground(scheme.getPlaneBackgroundColor());
    button.setBorderPainted(false);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (planeWindow.isVisible())
        {
          planeWindow.setSize(325, planeWindow.getHeight());
          Timer timer = new Timer(1, new ActionListener()
          {
            public void actionPerformed(ActionEvent evt)
            {
              if (planeWindow.getWidth() > 0)
              {
                planeWindow.setSize(planeWindow.getWidth() - 15, planeWindow.getHeight());
                planeWindow.setLocation(planeWindow.getX() + 15, planeWindow.getY());
              }
              else
              {
                planeWindow.setVisible(false);
                ((Timer) evt.getSource()).stop();
              }
            }
          });
          timer.start();
        }
      }

    });
    contentPane.add(button);
  }

  /**
   * creates a window to hold the settings.
   */
  private void createSettingsWindow()
  {
    this.settingWindow = new JWindow();
    Container contentPane = this.settingWindow.getContentPane();
    GridBagLayout gbl = new GridBagLayout();
    contentPane.setLayout(gbl);
    GridBagConstraints gbc = new GridBagConstraints();
    JButton button;

    JButton closeButton = new JButton(STRINGS.getString("Close"));
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTHEAST;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 170, 190, 0);
    gbl.setConstraints(closeButton, gbc);
    closeButton.setBackground(scheme.getSettingsBackgroundColor());
    closeButton.setBorderPainted(false);
    closeButton.setFont(new Font("TimesRoman", Font.PLAIN, 13));
    closeButton.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        settingWindow.setVisible(false);
      }

    });
    contentPane.add(closeButton);

    JLabel l = new JLabel(STRINGS.getString("Settings"));
    l.setFont(new Font("TimesRoman", Font.BOLD, 14));
    l.setBackground(scheme.getSettingsBackgroundColor());
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 0, 135, 0);
    gbl.setConstraints(l, gbc);
    contentPane.add(l);

    JLabel l2 = new JLabel(STRINGS.getString("Language") + ":");
    l2.setFont(new Font("TimesRoman", Font.BOLD, 13));
    l2.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK));
    l2.setBackground(scheme.getSettingsBackgroundColor());
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(-65, 0, 30, 0);
    gbl.setConstraints(l2, gbc);
    contentPane.add(l2);

    button = new JButton(STRINGS.getString("about"));
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.anchor = GridBagConstraints.SOUTH;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 0, 15, 0);
    gbl.setConstraints(button, gbc);
    button.setBackground(scheme.getSettingsBackgroundColor());
    button.setFont(new Font("TimesRoman", Font.PLAIN, 13));
    button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    contentPane.add(button);

    String languages[] = {"English", "Spanish", "French"};
    DefaultListModel<String> model = new DefaultListModel<String>();
    // JList<String> languageList = new JList<String>(languages);
    JList<String> languageList = new JList<String>(model);
    model.add(0, STRINGS.getString("English"));
    model.add(1, STRINGS.getString("Spanish"));
    model.add(2, STRINGS.getString("French"));
    languageList.setFont(new Font("TimesRoman", Font.PLAIN, 13));
    languageList.setBackground(new Color(199, 238, 255));
    languageList.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, Color.BLACK));
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(20, 0, 30, 0);
    gbl.setConstraints(languageList, gbc);
    languageList.addListSelectionListener(
        new LanguageListener(languageList, model, closeButton, l, l2, button, stepLabel, steps));
    contentPane.add(languageList);
  }

  /**
   * Constructs the step window.
   */
  private void createStepWindow()
  {
    this.stepWindow = new JWindow();
    Container contentPane = this.stepWindow.getContentPane();
    GridBagLayout gbl = new GridBagLayout();
    contentPane.setLayout(gbl);
    GridBagConstraints gbc = new GridBagConstraints();
    JButton button;

    button = new JButton(" ^ ");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.SOUTH;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 0, 0, 0);
    gbl.setConstraints(button, gbc);
    button.setBackground(scheme.getStepBackgroundColor());
    button.setBorderPainted(false);
    button.addActionListener((ActionListener) new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (stepWindow.isVisible())
        {
          stepWindow.setSize(stepWindow.getWidth(), 240);
          Timer timer = new Timer(1, new ActionListener()
          {
            public void actionPerformed(ActionEvent evt)
            {
              if (stepWindow.getHeight() > 0)
              {
                stepWindow.setSize(stepWindow.getWidth(), stepWindow.getHeight() - 10);
              }
              else
              {
                stepWindow.setVisible(false);
                ((Timer) evt.getSource()).stop();
              }
            }
          });
          timer.start();
        }
      }

    });
    contentPane.add(button);
    
    stepLabel = new JLabel("Steps");
    stepLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 125, 0, 0);
    gbl.setConstraints(stepLabel, gbc);
   contentPane.add(stepLabel);
    
    steps = new StepDisplay();
    steps.getPane().setBackground(scheme.getFieldColor());
    steps.getPane().setFont(new Font("TimesRoman", Font.PLAIN, 16));
    JScrollPane scrollDisplay = new JScrollPane(steps.getPane());
    scrollDisplay.setViewportBorder(null);
    scrollDisplay.setBorder(null);
    scrollDisplay.setPreferredSize(new Dimension(300, 170));
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(5, 0, 0, 0);
    gbl.setConstraints(scrollDisplay, gbc);
    contentPane.add(scrollDisplay);
  }

}
