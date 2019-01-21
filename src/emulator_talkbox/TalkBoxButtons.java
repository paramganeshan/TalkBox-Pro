package emulator_talkbox;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class TalkBoxButtons extends JPanel{
	
	private JButton leftMost;
	private JButton leftCenter;
	private JButton leftRight;
	private JButton rightLeft;
	private JButton rightCenter;
	private JButton rightMost;
	
	public TalkBoxButtons() {
		super();
		leftMost = new JButton();
		leftCenter = new JButton();
		leftRight = new JButton();
		rightLeft = new JButton();
		rightCenter = new JButton();
		rightMost = new JButton();
	}

}
