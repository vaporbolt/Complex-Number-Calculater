package visualization;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

import math.ComplexNumber;
import math.PolarForm;

/**
 * Draws a cartesian plane for the purpose of plotting complex numbers on it.
 * 
 * @author Endre Szakal
 *
 */
public class CartesianPlane extends JPanel
{

  private static final long serialVersionUID = -5340977851975906294L;

  private final String i = "i";

  private final double spacing = 10;
  private double height;
  private double width;
  private double axisX;
  private double axisY;

  private ArrayList<ComplexNumberPoint> points = new ArrayList<ComplexNumberPoint>();

  /**
   * Basic Constructor.
   */
  public CartesianPlane()
  {
    this.setBackground(Color.WHITE);
  }

  /**
   * paints the axis's and points if there are any.
   * 
   * @param g
   *          Graphics
   */
  public void paintComponent(final Graphics g)
  {
    super.paintComponent(g);

    this.height = getHeight();
    this.width = getWidth();
    this.axisX = width / 2.0;
    this.axisY = height / 2.0;
    final double x1 = 0;
    final double y1 = 0;
    final double x2 = width;
    final double y2 = height;

    Graphics2D g2 = (Graphics2D) g;

    g2.setColor(Color.GRAY);
    g2.setStroke(new BasicStroke(1));

    // draw vertical lines with real number axis numbers
    for (double x = spacing; axisX + x <= width; x += spacing)
    {
      g2.setColor(Color.GRAY);
      g2.draw(new Line2D.Double(axisX + x, y1, axisX + x, y2));
      g2.draw(new Line2D.Double(axisX - x, y1, axisX - x, y2));

      if (x / spacing % 2 == 0)
      {
        g2.setColor(Color.BLACK);
        g2.drawString(Integer.toString((int) (x / spacing)), (int) (axisX + x), (int) (axisX));
        g2.drawString(Integer.toString((int) ((-x) / spacing)), (int) (axisX - x), (int) (axisX));
      }
    }

    // draw horizontal lines with imaginary number axis numbers
    for (double y = spacing; axisY + y <= height; y += spacing)
    {
      g2.setColor(Color.GRAY);
      g2.draw(new Line2D.Double(x1, axisY + y, x2, axisY + y));
      g2.draw(new Line2D.Double(x1, axisY - y, x2, axisY - y));

      if (y / spacing % 2 == 0)
      {
        g2.setColor(Color.BLACK);
        g2.drawString(Integer.toString((int) ((-y) / spacing)) + i, (int) (axisY),
            (int) (axisY + y));
        g2.drawString(Integer.toString((int) (y / spacing)) + i, (int) (axisY), (int) (axisY - y));
      }
    }

    // draw x axis and y axis
    g2.setStroke(new BasicStroke(2));
    // x axis
    g2.setColor(Color.BLUE);
    g2.draw(new Line2D.Double(axisX, y1, axisX, y2));
    // y axis
    g2.setColor(Color.RED);
    g2.draw(new Line2D.Double(x1, axisY, x2, axisY));

    // draw points
    for (ComplexNumberPoint p : points)
    {
      drawPoint(p, g2);
      if (p.getComplexNumber() instanceof PolarForm)
      {
        drawPolarArrow(p, g2);
      }
    }

  }

  /**
   * Adds point to the points arrayList.
   * 
   * @param number
   *          ComplexNumberPoint
   */
  public void addPoint(final ComplexNumber number)
  {
    points.add(new ComplexNumberPoint(number));
    repaint();
  }

  /**
   * draws point on the Cartesian plane.
   * 
   * @param point
   *          ComplexNumberPoint
   * @param g
   *          Graphics
   */
  private void drawPoint(final ComplexNumberPoint point, final Graphics2D g)
  {
    g.setStroke(new BasicStroke(5));
    g.setColor(Color.BLUE);
    double x = axisX + point.getX() * spacing;
    double y = axisY - point.getY() * spacing;
    g.draw(new Line2D.Double(x, y, x, y));

  }

  /**
   * draws an arrow to a Polar Complex Number.
   * 
   * @param point
   *          Polar ComplexNumberPoint
   * @param g
   *          Graphics2D
   */
  private void drawPolarArrow(final ComplexNumberPoint point, final Graphics2D g)
  {
    double x = axisX + point.getX() * spacing;
    double y = axisY - point.getY() * spacing;

    g.setStroke(new BasicStroke(2));
    g.setColor(new Color(87, 186, 97));
    g.draw(new Line2D.Double(axisX, axisY, x, y)); // draws hypotenuse

    g.setColor(Color.ORANGE);
    g.draw(new Line2D.Double(x, y, x, getHeight() / 2)); // draws vertical component
  }

  /**
   * clear plane of drawn points.
   */
  public void reset()
  {
    points.removeAll(points);
    repaint();
  }

}
