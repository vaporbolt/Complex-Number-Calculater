package visualization;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

import math.ComplexNumber;

/**
 * Draws a cartesian plane for the purpose of plotting complex numbers on it.
 * 
 * @author Endre Szakal
 *
 */
public class CartesianPlane extends JPanel
{
  
  private static final long serialVersionUID = -5340977851975906294L;
  
  private final double spacing = 20;
  private double height;
  private double width;
  private double axisX;
  private double axisY;
  
  private ArrayList<ComplexNumberPoint> points = new ArrayList<ComplexNumberPoint>();
  
  /**
   * Basic Constructor
   */
  public CartesianPlane()
  {
    this.setBackground(Color.WHITE);
  }
  
  /**
   * paints the axis's and points if there are any
   */
  public void paintComponent(Graphics g)
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
    
    // draw y lines
    for (double x = spacing; axisX + x <= width ; x += spacing)
    {
      g2.draw(new Line2D.Double(axisX + x, y1, axisX + x, y2));
      g2.draw(new Line2D.Double(axisX - x, y1, axisX - x, y2));
    }
    
    // draw x lines
    for (double y = spacing; axisY + y <= height; y += spacing)
    {
      g2.draw(new Line2D.Double(x1, axisY + y, x2, axisY + y));
      g2.draw(new Line2D.Double(x1, axisY - y, x2, axisY - y));
    }
    
    // draw x axis and y axis
    g2.setColor(Color.BLACK);
    g2.setStroke(new BasicStroke(2));
    g2.draw(new Line2D.Double(axisX, y1, axisX, y2));
    g2.draw(new Line2D.Double(x1, axisY, x2, axisY));
    
    // draw points
    points.forEach(p -> drawPoint(p, g2));
  }
  
  /**
   * Adds point to the points arrayList.
   * @param point ComplexNumberPoint
   */
  public void addPoint(ComplexNumber number)
  {
    points.add(new ComplexNumberPoint(number));
    repaint();
  }
  
  /**
   * draws point on the Cartesian plane.
   * @param point ComplexNumberPoint
   */
  public void drawPoint(ComplexNumberPoint point, Graphics2D g)
  {
    g.setStroke(new BasicStroke(7));
    g.setColor(Color.BLUE);
    double x = axisX + point.getX() * spacing;
    double y = axisY - point.getY() * spacing;
    g.draw(new Line2D.Double(x, y, x, y));
  }
  
}
