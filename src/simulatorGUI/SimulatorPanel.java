package simulatorGUI;

import java.awt.Dimension;

import javax.swing.*;

@SuppressWarnings("serial")
public class SimulatorPanel extends JPanel
{

	public final int WIDTH = 650;
	public final int HEIGHT = 650;
	
	public SimulatorPanel() 
	{
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	
	}
	
}
