package emulator_talkbox;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class TalkBoxLabels extends JPanel{
	
	public final int WIDTH = 100;
	public final int HEIGHT = 100;
	private Color changeColour;
	
	public TalkBoxLabels() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		changeColour = Color.DARK_GRAY;
	}
	
	public void setColor(Color c) {
		changeColour = c;
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(changeColour);
		g.fillRect(20, 50, 200, 180);
	}
}
