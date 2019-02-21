package simulatorGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class SimulatorFrame extends JFrame implements ActionListener
{
	protected JButton b1, b2, b3, b4, b5, b6;
	protected JLabel l1, l2, l3, l4, l5, l6;
	
	Scanner input = new Scanner(System.in);

	public SimulatorFrame()
	{
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(600, 600));
		
		SimulatorPanel panel = new SimulatorPanel();
		
		
		
		b1 = new JButton("Button 1");
		b2 = new JButton("Button 2");
		b3 = new JButton("Button 3");
		b4 = new JButton("Button 4");
		b5 = new JButton("Button 5");
		b6 = new JButton("Button 6");
		
		l1 = new JLabel("Label 1");
		l2 = new JLabel("Label 2");
		l3 = new JLabel("Label 3");
		l4 = new JLabel("Label 4");
		l5 = new JLabel("Label 5");
		l6 = new JLabel("Label 6");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
				
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

	@Override
	public void actionPerformed(ActionEvent e)
	{
	
			if (e.getSource() == b1)
			{
				/*try {
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("").getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				}*/
			}
			
			if (e.getSource() == b2)
			{
				/*try {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("").getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}*/
			}
		input.close();
	}
}
