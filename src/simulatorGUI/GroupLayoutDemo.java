package simulatorGUI;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
   
// creating a class GroupLayoutDemo 
public class GroupLayoutDemo { 
   
    // Declaration of objects  
    // of JFrame class 
    private JFrame mainFrame; 
   
    // Declaration of objects  
    // of JLabel class 
    @SuppressWarnings("unused")
	private JLabel headerLabel, statusLabel, msglabel; 
   
    // Declaration of objects  
    // of JPanel class 
    private JPanel controlPanel; 
   
    // create a class GroupLayoutDemo 
    public GroupLayoutDemo() 
    { 
           
        // used to prepare GUI 
        prepareGUI(); 
    } 
   
    public static void main(String[] args) 
    { 
           
        // Creating Object of "GroupLayoutDemo" class 
        GroupLayoutDemo GroupLayoutDemo = new GroupLayoutDemo(); 
   
        // to show the group layout demo 
        GroupLayoutDemo.showGroupLayoutDemo(); 
    } 
   
    private void prepareGUI() 
    { 
   
        // Initialization of object  
        // "mainframe" of JFrame class. 
        mainFrame = new JFrame("Java GroupLayout Examples"); 
   
        // Function to set the  
        // size of JFrame. 
        mainFrame.setSize(400, 400); 
   
        // Function to set the  
        // layout of JFrame. 
        mainFrame.setLayout(new GridLayout(3, 1)); 
   
        // Initialization of object  
        // "headerLabel" of JLabel class. 
        headerLabel = new JLabel("", JLabel.CENTER); 
   
        // Initialization of object  
        // "statusLabel" of JLabel class. 
        statusLabel = new JLabel("", JLabel.CENTER); 
   
        // Function to set the 
        // size of JFrame. 
        statusLabel.setSize(350, 100); 
   
        // to add action WindowListner in JFrame 
        mainFrame.addWindowListener(new WindowAdapter()  
        { 
            public void windowClosing(WindowEvent windowEvent) 
            { 
                System.exit(0); 
            } 
        }); 
   
        // Initialization of object  
        // "controlPanel" of JPanel class. 
        controlPanel = new JPanel(); 
   
        // Function to set the 
        // layout of JFrame. 
        controlPanel.setLayout(new FlowLayout()); 
   
        // Adding Jlabel "headerlabel" 
        // on JFrame. 
        mainFrame.add(headerLabel); 
   
        // Adding JPanel "controlPanel"  
        // on JFrame. 
        mainFrame.add(controlPanel); 
   
        // Adding JLabel "statusLabel"  
        // on JFrame. 
        mainFrame.add(statusLabel); 
   
        // Function to set the visible of JFrame. 
        mainFrame.setVisible(true); 
    } 
       
    private void showGroupLayoutDemo() 
    { 
   
        // Function to set the text 
        // on the header of JFrame. 
        headerLabel.setText("Layout in action: GroupLayout"); 
   
        // Creating Object of  
        // "Panel" class 
        JPanel panel = new JPanel(); 
   
        // Function to set the size of JFrame. 
        panel.setSize(200, 200); 
   
        // Creating Object of  
        // "layout" class 
        GroupLayout layout = new GroupLayout(panel); 
   
        // it used to set Auto 
        // Create Gaps 
        layout.setAutoCreateGaps(true); 
   
        // it used to set Auto  
        // Create Container Gaps 
        layout.setAutoCreateContainerGaps(true); 
   
        // Creating Object  
        // of "btn1" class 
        JButton btn1 = new JButton("Button 1"); 
   
        // Creating Object of  
        // "btn2" class 
        JButton btn2 = new JButton("Button 2"); 
   
        // Creating Object of "btn3" class 
        JButton btn3 = new JButton("Button 3"); 
   
        // It used to set the 
        // Horizontal group 
        layout.setHorizontalGroup(layout.createSequentialGroup() 
       
        // Adding the JButton "btn1" 
        .addComponent(btn1) 
      
        // Adding the sequential Group 
        .addGroup(layout.createSequentialGroup() 
       
        // Adding the Parallel Group 
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
       
        // Adding the JButton "btn2" 
        .addComponent(btn2) 
   
        // Adding the JButton "btn3" 
        .addComponent(btn3)))); 
   
        // set the vertical layout group 
        layout.setVerticalGroup(layout.createSequentialGroup() 
   
        // Adding the JButton "btn1" 
        .addComponent(btn1) 
   
        // Adding the JButton "btn2" 
        .addComponent(btn2) 
   
        // Adding the JButton "btn3" 
        .addComponent(btn3)); 
   
        // Function to set the Layout of JFrame. 
        panel.setLayout(layout); 
   
        // Adding the control panel 
        controlPanel.add(panel); 
   
        // Function to set the visible of JFrame. 
        mainFrame.setVisible(true); 
    } 
} 