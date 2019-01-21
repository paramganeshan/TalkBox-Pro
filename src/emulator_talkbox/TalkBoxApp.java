package emulator_talkbox;

import java.awt.Dimension;

public class TalkBoxApp {
	
	public static void main(String[] args) {
		
		TalkBoxFrame frame = new TalkBoxFrame();
		frame.setPreferredSize(new Dimension(1000, 500));
		frame.pack();
		frame.setVisible(true);
	}

}
