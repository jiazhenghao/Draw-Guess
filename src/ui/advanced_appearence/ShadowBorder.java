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
public class ShadowBorder extends AbstractBorder {

    protected Color shadowColor;
    protected int thickness;
    protected int arc;
    protected int gradient = 10;

    /**
     * Creates a shadow border with the black color, thickness and arc.
     * @param thickness the thickness of the border
     * @param arc the radius of the arc
     */
    @ConstructorProperties({"thickness", "arc"})
    public ShadowBorder(int thickness, int arc)  {
    	this(Color.BLACK, thickness, arc);
    }

    /**
     * Creates a shadow border with the specified color, thickness and arc.
     * @param color the color of the border
     * @param thickness the thickness of the border
     * @param arc the radius of the arc
     */
    @ConstructorProperties({"color", "thickness", "arc"})
    public ShadowBorder(Color color, int thickness, int arc)  {
    	this.shadowColor = color;
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
    		
    		for (int i = 0; i < this.thickness; i++) {
    			g2d.setColor(new Color(this.shadowColor.getRed(), this.shadowColor.getGreen(), this.shadowColor.getBlue(),
    					i == this.thickness - 1 ? 100 : this.gradient * i));
    			
    			int myWidth = width - (i << 1) - 1;
    			int myHeight = height - (i << 1) - 1;
    			int myArc = (this.arc + this.thickness - i << 1) - 1;
    			
    			RoundRectangle2D.Float out = new RoundRectangle2D.Float(x + i, y + i, myWidth, myHeight, myArc, myArc);
    			RoundRectangle2D.Float in = new RoundRectangle2D.Float(x + i + 1, y + i + 1, myWidth - 2, myHeight - 2, myArc - 2, myArc - 2);
    			
    			Path2D.Float path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
    			path.append(out, false);
    			path.append(in, false);
    			
    			g2d.fill(path);
    		}
    		
    		g2d.setColor(oldColor);
    	}
	}

    /**
     * Returns the color of the border.
     */
    public Color getShadowColor() {
        return shadowColor;
    }

    /**
     * Returns the thickness of the border.
     */
    public int getThickness() {
        return thickness;
    }
}
