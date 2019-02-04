package TalkBoxConfigurationGUI;
import javax.swing.*;
import java.awt.*;

public class ConfigrationFrame extends JFrame
{
    public ConfigrationFrame()
    {
//        JFrame frame= new JFrame();
        setTitle("TalkBox Configuration App");
        setSize(600,400);
        setResizable(false);//restricting the user to resize the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout( new FlowLayout());

        String [] audio = {"Happy", "Sad", "Excited", "Tired","Proud" ,"Sick", };
        JComboBox audioChoices = new JComboBox(audio);
        getContentPane().add(new JLabel("Feelings"));
        getContentPane().add(audioChoices);

        String [] order = {"1", "2", "3", "4", "5", "6"};
        JComboBox audioOrder = new JComboBox(order);
        getContentPane().add(new JLabel("Order"));
        getContentPane().add(audioOrder);

        JButton edit = new JButton();
        edit.setText("Edit");
        getContentPane().add(edit);


        JList initialList = new JList(audio);
        getContentPane().add(new JLabel("Initial List"));
        getContentPane().add(initialList);

        JList updatedList = new JList(audio);
        getContentPane().add(new JLabel("Final List"));
        getContentPane().add(updatedList);

        JButton saveChanges = new JButton();
        saveChanges.setText("Save Changes");
        getContentPane().add(saveChanges);


      
    }
}
