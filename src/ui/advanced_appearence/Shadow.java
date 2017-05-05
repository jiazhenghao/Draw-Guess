package ui.advanced_appearence;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Shadow extends AuxiliaryWindow {
	protected final int arc = 5;
	protected final int shadowThickness = 5;
	
	private JPanel shadowPanel = new JPanel();
	
	public Shadow(JFrame owner) {
		super(owner);
		
		shadowPanel.setBorder(new ShadowBorder(5, arc));
		shadowPanel.setOpaque(false);

		this.setContentPane(shadowPanel);
	}
	
	@Override
	public void setSize(int width, int height) {
		int size = (arc + shadowThickness << 1) + 1;
		super.setSize(width + size, height + size);
	}
	
	@Override
	public void setLocation(int parentX, int parentY) {
		int offs = arc + shadowThickness;
		super.setLocation(parentX - offs, parentY - offs);
	}
}
