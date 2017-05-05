package ui.part.head_portrait;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import thread.animation.ComponentMover;
import util.Config;
import util.leap.LeapEvent;
import util.leap.LeapListener;
import util.leap.LeapManager;

import com.leapmotion.leap.CircleGesture;

@SuppressWarnings("serial")
public class HeadPortraitPanel extends JPanel implements MouseListener, LeapListener {
	
	private int index;
	private float progress;
	private long frame;
	
	private HeadPortraitLabel chosen;
	private HeadPortraitLabel chooser;
	private HeadPortraitLabel[] headPortraits = new HeadPortraitLabel[14];
	
	private ComponentMover mover;
	
	public HeadPortraitPanel() {
		setOpaque(false);
		setLayout(null);

		chooser = new HeadPortraitLabel(headPortraits.length + 1, 0);
		chooser.setBounds(200, 0, 100, 100);
		add(chooser);
		for (int i = 0; i < headPortraits.length; i++) {
			headPortraits[i] = new HeadPortraitLabel(i + 1, 0, true);
			if (i < 4) {
				headPortraits[i].setBounds((i + 2) * 100, 0, 100, 100);
			} else if (i < 8) {
				headPortraits[i].setBounds((i - 2) * 100, 100, 100, 100);
			} else {
				headPortraits[i].setBounds((i - 8) * 100, 200, 100, 100);
			}
			headPortraits[i].addMouseListener(this);
			add(headPortraits[i]);
		}
		headPortraits[0].choose();
		chosen = new HeadPortraitLabel(1, 0, true);
		chosen.setBounds(0, 0, 200, 200);
		add(chosen);
		
		mover = new ComponentMover(chooser);
		
		LeapManager.getDefaultLeapManager().addLeapListener(this, this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		HeadPortraitLabel label = (HeadPortraitLabel) e.getSource();
		if (label != chooser) {
			Config.headPortrait = label.getI();
			mover.setTarget(label.getX(), label.getY());
			label.choose();
			chosen.setI(label.getI());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void fingerExist(LeapEvent e) {}

	@Override
	public void circled(LeapEvent e) {
		CircleGesture circle = (CircleGesture) e.getData();
		if (circle.frame().id() != frame) {
			frame = circle.frame().id();
			if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/4) {
				progress = circle.progress();
			} else {
				progress = -circle.progress();
			}
			if (circle.state() == CircleGesture.State.STATE_START) {
				index = HeadPortraitLabel.getChoosen() - 1;
			}
			int i = (int) (index + progress + headPortraits.length + 0.5) % headPortraits.length;
			HeadPortraitLabel label = headPortraits[i];
			mover.setTarget(label.getX(), label.getY());
			label.choose();
			chosen.setI(i + 1);
			Config.headPortrait = i + 1;
		}
	}

	@Override
	public void swiped(LeapEvent e) {}

	@Override
	public void screenTapped(LeapEvent e) {}

	@Override
	public void keyTapped(LeapEvent e) {}
}
