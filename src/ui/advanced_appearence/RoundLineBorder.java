package ui.advanced_appearence;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.beans.ConstructorProperties;

import javax.swing.border.AbstractBorder;

/**
 * A class which implements a shadow border of arbitrary thickness
 * and of a single color.
 * <p>
 * @author 601625333@qq.com
 */
@SuppressWarnings("serial")
public class RoundLineBorder extends AbstractBorder {

    protected Color lineColor;
    protected int thickness;
    protected int arc;

    /**
     * Creates a round line border with the specified color, thickness, and arc.
     * @param color the color of the border
     * @param thickness the thickness of the border
     * @param arc the arc of the border
     */
    @ConstructorProperties({"lineColor", "thickness", "arc"})
    public RoundLineBorder(Color color, int thickness, int arc)  {
        lineColor = color;
        this.thickness = thickness;
        this.arc = arc;
    }

    /**
     * Paints the border for the specified component with the
     * specified position and size.
     * @param c the component for which this border is being painted
     * @param g the paint graphics
     * @param x the x position of the painted border
     * @param y the y position of the painted border
     * @param width the width of the painted border
     * @param height the height of the painted border
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        if ((this.thickness > 0) && (g instanceof Graphics2D)) {
            Graphics2D g2d = (Graphics2D) g;
    		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            Color oldColor = g2d.getColor();

            int offs = this.thickness;
            int size = offs << 1;
			int myArc = (this.arc + this.thickness << 1) - 1;
            
			RoundRectangle2D.Float outer = new RoundRectangle2D.Float(x, y, width, height, myArc, myArc);
			RoundRectangle2D.Float inner = new RoundRectangle2D.Float(x + offs, y + offs, width - size, height - size, myArc - size, myArc - size);
            
            Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
            path.append(outer, false);
            path.append(inner, false);

            g2d.setColor(this.lineColor);
            g2d.fill(path);
            g2d.setColor(oldColor);
        }
    }

    /**
     * Returns the color of the border.
     */
    public Color getLineColor()     {
        return lineColor;
    }

    /**
     * Returns the thickness of the border.
     */
    public int getThickness()       {
        return thickness;
    }

}
