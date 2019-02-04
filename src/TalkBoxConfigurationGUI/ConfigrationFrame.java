package TalkBoxConfigurationGUI;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

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
        String path = "Sounds\\";
        File sounds = new File(path);
        File[] listOfFiles = sounds.listFiles();

        List<String> fileNames = new ArrayList<String>();
        for (File f: listOfFiles) {
            fileNames.add(f.getName().replace(path, ""));
        }

        JList audioList = new JList(fileNames.toArray());
        getContentPane().add(new JLabel("Feelings"));
        getContentPane().add(audioList);

        String [] audio = {"Happy", "Sad", "Excited", "Tired","Proud" ,"Sick", };

        String [] order = {"1", "2", "3", "4", "5", "6", "+"};
        JComboBox audioOrder = new JComboBox(order);
        getContentPane().add(new JLabel("Order"));
        getContentPane().add(audioOrder);

        JButton edit = new JButton();
        edit.setText("Edit");
        getContentPane().add(edit);


        JList initialList = new JList(fileNames.toArray());
        getContentPane().add(new JLabel("Initial List"));
        getContentPane().add(initialList);

        JList updatedList = new JList(fileNames.toArray());
        getContentPane().add(new JLabel("Final List"));
        getContentPane().add(updatedList);

        JButton swap = new JButton();
        swap.setText("Swap");
        getContentPane().add(swap);

        JButton saveChanges = new JButton();
        saveChanges.setText("Save Changes");
        getContentPane().add(saveChanges);
    }
}
