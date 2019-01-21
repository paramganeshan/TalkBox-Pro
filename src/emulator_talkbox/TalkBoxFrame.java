package emulator_talkbox;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.ParallelGroup;

@SuppressWarnings("serial")
public class TalkBoxFrame extends JFrame implements ActionListener {

	private JButton leftMost, leftCenter, leftRight, 
	rightLeft, rightCenter, rightMost;
	
	private JLabel label1, label2, label3, label4, label5, label6;
	
	public TalkBoxFrame() {
		super("TalkBoxApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		leftMost = new JButton("Sound 1");
		leftCenter = new JButton("Sound 2");
		leftRight = new JButton("Sound 3");
		rightLeft = new JButton("Sound 4");
		rightCenter = new JButton("Sound 5");
		rightMost = new JButton("Sound 6");
		
		label1 = new JLabel("Label 1");
		label2 = new JLabel("Label 2");
		label3 = new JLabel("Label 3");
		label4 = new JLabel("Label 4");
		label5 = new JLabel("Label 5");
		label6 = new JLabel("Label 6");
		
		leftMost.addActionListener(this);
		leftCenter.addActionListener(this);
		leftRight.addActionListener(this);
		rightLeft.addActionListener(this);
		rightCenter.addActionListener(this);
		rightMost.addActionListener(this);
		
		//GroupLayout audioButtons = new GroupLayout(getParent());
		
		Box buttons = Box.createHorizontalBox();
		buttons.add(leftMost);
		buttons.add(leftCenter);
		buttons.add(leftRight);
		buttons.add(rightLeft);
		buttons.add(rightCenter);
		buttons.add(rightMost);
		
		Box labels = Box.createHorizontalBox();
		labels.add(label1);
		labels.add(label2);
		labels.add(label3);
		labels.add(label4);
		labels.add(label5);
		labels.add(label6);
		
		Box vert1 = Box.createVerticalBox();
		vert1.add(label1);
		vert1.add();
		
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(combined, BorderLayout.CENTER);
		setContentPane(pane);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		
	}
}
