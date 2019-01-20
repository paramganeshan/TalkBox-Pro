package simulatorGUI;

import java.awt.Dimension;

import javax.swing.*;

@SuppressWarnings("serial")
public class SimulatorPanel extends JPanel
{

	public final int WIDTH = 600;
	public final int HEIGHT = 600;
	
	public SimulatorPanel() 
	{
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	
	}
	
}
