package simulatorGUI;

import java.awt.Dimension;

import javax.swing.*;

@SuppressWarnings("serial")
public class SimulatorPanel extends JPanel
{

	public final int WIDTH = 700;
	public final int HEIGHT = 700;
	
	public SimulatorPanel() 
	{
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	
	}
	
}
