package simulatorGUI;


import java.awt.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class SimulatorFrame extends JFrame
{

	public SimulatorFrame()
	{
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(600, 600));
		
		SimulatorPanel panel = new SimulatorPanel();
		
		
		JComponent b1 = new JButton("Button 1");
		JComponent b2 = new JButton("Button 2");
		JComponent b3 = new JButton("Button 3");
		JComponent b4 = new JButton("Button 4");
		JComponent b5 = new JButton("Button 5");
		JComponent b6 = new JButton("Button 6");
		
		JComponent l1 = new JLabel("Label 1");
		JComponent l2 = new JLabel("Label 2");
		JComponent l3 = new JLabel("Label 3");
		JComponent l4 = new JLabel("Label 4");
		JComponent l5 = new JLabel("Label 5");
		JComponent l6 = new JLabel("Label 6");
				
		JPanel pane = new JPanel();
		
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
				
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(l1).addComponent(b1));
		
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(l2).addComponent(b2));
		
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(l3).addComponent(b3));
		
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(l4).addComponent(b4));
		
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(l5).addComponent(b5));
		
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(l6).addComponent(b6));
				
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(l1).addComponent(l2).addComponent(l3).addComponent(l4).addComponent(l5).addComponent(l6));
		
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(b1).addComponent(b2).addComponent(b3).addComponent(b4).addComponent(b5).addComponent(b6));
		
		layout.setVerticalGroup(vGroup);
		
		pane.add(panel);
		
		setContentPane(pane);
		panel.setLayout(layout);

		setResizable(true);
		
	}
	
}
