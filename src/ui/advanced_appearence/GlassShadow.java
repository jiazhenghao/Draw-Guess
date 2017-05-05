package ui.advanced_appearence;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.WindowEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GlassShadow extends Shadow {
	
	private BorderPanel borderPanel = new BorderPanel();

	public GlassShadow(JFrame owner) {
		super(owner);
	}
	
	private void initPanel(int width, int height) {
		this.getContentPane().setLayout(null);
		this.getContentPane().add(borderPanel);
		
		borderPanel.setBounds(5, 5, width + arc + arc, height + arc + arc);
		borderPanel.setBackground(Color.RED);
		borderPanel.setBorder(new RoundLineBorder(new Color(255, 255, 255), 1, arc));
		borderPanel.setOpaque(false);
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		borderPanel.setSize(width + arc + arc, height + arc + arc);
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		super.windowOpened(e);
		initPanel(owner.getWidth(), owner.getHeight());
	}
	
	class BorderPanel extends JPanel {
		protected void paintComponent(java.awt.Graphics g) {
			Graphics2D g2d = (Graphics2D) g;

			Color oldColor = g2d.getColor();

			int width = getWidth();
			int height = getHeight();
			int size = arc << 1;
			int myArc = size - 1;

			Shape outer = new RoundRectangle2D.Float(0, 0, width, height, myArc, myArc);
			Shape inner = new Rectangle2D.Float(arc, arc, width - (arc << 1), height - (arc << 1));

			Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
			path.append(outer, false);
			path.append(inner, false);

			g2d.setColor(new Color(0, 0, 0, 1));
			g2d.fill(path);
			g2d.setColor(oldColor);
		};
	}
}
