package TalkBoxConfigurationGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import java.io.*;
/**
 * A simple sound player. To start, create an instance of this class.
 *
 * The sound player can play sound clips in WAV, AU and AIFF formats
 * with standard sample rates.
 */
public class ConfigurationAppGUI extends JFrame
        implements ChangeListener, ActionListener
{
    private static final String VERSION = "Version 1.0";
    private static final String AUDIO_DIR = "Sounds";

    private JList audioList;
    private JSlider slider;
    private JLabel infoLabel;
    private SoundEngine player;
    private JList initialList;
    private JList updatedList;
    private JComboBox <Integer> order;
    DefaultListModel initialListModel;
    DefaultListModel finalListModel;
    DefaultListModel audioListModel;

    /**
     * Main method for starting the player from a command line.
     */
    public static void main(String[] args)
    {
        ConfigurationAppGUI gui = new ConfigurationAppGUI();
    }

    /**
     * Create a TalkBox and display its GUI on screen.
     */
    public ConfigurationAppGUI()
    {
        super("TalkBox");
        player = new SoundEngine();
        String[] audioFileNames = findFiles(AUDIO_DIR, null);
        makeFrame(audioFileNames);
    }

    /**
     * Play the sound file currently selected in the file list. If there is no
     * selection in the list, or if the selected file is not a sound file,
     * do nothing.
     */
    private void play()
    {
        String filename = (String)audioList.getSelectedValue();
        if(filename == null) {  // nothing selected
            return;
        }
        slider.setValue(0);
        boolean successful = player.play(new File(AUDIO_DIR, filename));
        if(successful) {
            showInfo(filename + " (" + player.getDuration() + " seconds)");
        }
        else {
            showInfo("Could not play file - unknown format");
        }
    }

    /**
     * Display information about a selected sound file (name and clip length).
     * @param message The message to display.
     */
    private void showInfo(String message)
    {
        infoLabel.setText(message);
    }

    /**
     * Stop the currently playing sound file (if there is one playing).
     */
    private void stop()
    {
        player.stop();
    }

    /**
     * Stop the currently playing sound file (if there is one playing).
     */
    private void pause()
    {
        player.pause();
    }

    /**
     * Resume a previously suspended sound file.
     */
    private void resume()
    {
        player.resume();
    }

    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }


    /**
     * About function: show the 'about' box.
     */
    private void showAbout()
    {
        JOptionPane.showMessageDialog(this,
                "TalkBox\n" + VERSION,
                "About TalkBox",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Load the file names of all files in the given directory.
     * @param dirName Directory (folder) name.
     * @param suffix File suffix of interest.
     * @return The names of files found.
     */
    private String[] findFiles(String dirName, String suffix)
    {
        File dir = new File(dirName);
        if(dir.isDirectory()) {
            String[] allFiles = dir.list();
            if(suffix == null) {
                return allFiles;
            }
            else {
                List<String> selected = new ArrayList<String>();
                for(String filename : allFiles) {
                    if(filename.endsWith(suffix)) {
                        selected.add(filename);
                    }
                }
                return selected.toArray(new String[selected.size()]);
            }
        }
        else {
            System.out.println("Error: " + dirName + " must be a directory");
            return null;
        }
    }

    // ------- ChangeListener interface (for Slider) -------

    /**
     * ChangeListener method for slider changes. This method is called
     * when the slider value is changed by the user.
     * @param evt The event details.
     */
    public void stateChanged(ChangeEvent evt)
    {
        player.seek(slider.getValue());
    }

    // ------- ActionListener interface (for ComboBox) -------

    /**
     * ActionListener method for changes of format combo box.
     * When this methosd is called, the user has made a new selection
     * in the combo box.
     * @param evt The event details.
     */
    public void actionPerformed(ActionEvent evt)
    {
        JComboBox cb = (JComboBox)evt.getSource();
        String format = (String)cb.getSelectedItem();
        if(format.equals("all formats")) {
            format = null;
        }
        audioList.setListData(findFiles(AUDIO_DIR, format));
    }

    // ---- Swing stuff to build the frame and all its components and menus ----

    /**
     * Create the complete application GUI.
     * @param audioFiles The file names to display.
     */
    private void makeFrame(String[] audioFiles) {
        // the following makes sure that our application exits when
        // the user closes its window
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 10, 10, 10));

        makeMenuBar();

        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(8, 8));

        // Create the left side with combobox and scroll list
        JPanel leftPane = new JPanel();
        {
            leftPane.setLayout(new BorderLayout(8, 8));

            String[] formats = {"all formats", ".wav", ".au", ".aif"};


            // Create the combo box.
            JComboBox formatList = new JComboBox(formats);
            formatList.addActionListener(this);
            leftPane.add(formatList, BorderLayout.NORTH);

            // Create the scrolled list for file names
            audioList = new JList(audioFiles);
            audioList.setForeground(new Color(140, 171, 226));
            audioList.setBackground(new Color(0, 0, 0));
            audioList.setSelectionBackground(new Color(87, 49, 134));
            audioList.setSelectionForeground(new Color(140, 171, 226));
            JScrollPane scrollPane = new JScrollPane(audioList);
            scrollPane.setColumnHeaderView(new JLabel("Audio files"));
            leftPane.add(scrollPane, BorderLayout.CENTER);
        }
        contentPane.add(leftPane, BorderLayout.CENTER);

        //Create the right side with the Initial list and Updated list

        JPanel rightPane = new JPanel();
        {
            Integer[] orderButtons = {1,2,3,4,5,6};
            //Create the Button # label and comboBox
            JPanel orderPanel = new JPanel();
            {
                orderPanel.setLayout(new FlowLayout());
                JLabel orderLabel = new JLabel("Button #: ");
                order = new JComboBox<Integer>(orderButtons);
                orderPanel.add(orderLabel);
                orderPanel.add(order);
            }
            rightPane.setLayout(new BorderLayout(8, 8));
            rightPane.add(orderPanel, BorderLayout.CENTER);
            initialListModel = new DefaultListModel();
            initialList = new JList(initialListModel);
            // Create the scrolled list for Initial List
            for(int i = 0; i < orderButtons.length; i++)
            {
                initialListModel.addElement(audioFiles[i]);
            }
            initialList.setForeground(new Color(140, 171, 226));
            initialList.setBackground(new Color(0, 0, 0));
            initialList.setSelectionBackground(new Color(87, 49, 134));
            initialList.setSelectionForeground(new Color(140, 171, 226));
            JScrollPane leftScrollPane = new JScrollPane(initialList);
            leftScrollPane.setColumnHeaderView(new JLabel("Initial List"));
            rightPane.add(leftScrollPane, BorderLayout.WEST);

            //Create the scrolled list for Updated List
            updatedList = new JList();
            updatedList.setForeground(new Color(140, 171, 226));
            updatedList.setBackground(new Color(0, 0, 0));
            updatedList.setSelectionBackground(new Color(87, 49, 134));
            updatedList.setSelectionForeground(new Color(140, 171, 226));
            JScrollPane rightScrollPane = new JScrollPane(updatedList);
            rightScrollPane.setColumnHeaderView(new JLabel("Final List"));
            rightPane.add(rightScrollPane, BorderLayout.EAST);
        }
        contentPane.add(rightPane, BorderLayout.SOUTH);

        // Create the center with image, text label, and slider
        JPanel centerPane = new JPanel();
        {
            centerPane.setLayout(new BorderLayout(8, 8));

            JLabel image = new JLabel(new ImageIcon("title.jpg"));
            centerPane.add(image, BorderLayout.NORTH);
            centerPane.setBackground(Color.BLACK);

            infoLabel = new JLabel("  ");
            infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoLabel.setForeground(new Color(140,171,226));
            centerPane.add(infoLabel, BorderLayout.CENTER);

            slider = new JSlider(0, 100, 0);
            TitledBorder border = new TitledBorder("Seek");
            border.setTitleColor(Color.white);
            slider.setBorder(new CompoundBorder(new EmptyBorder(6, 10, 10, 10), border));
            slider.addChangeListener(this);
            slider.setBackground(Color.BLACK);
            slider.setMajorTickSpacing(25);
            slider.setPaintTicks(true);
            centerPane.add(slider, BorderLayout.SOUTH);
        }
        contentPane.add(centerPane, BorderLayout.EAST);






        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
        {
            toolbar.setLayout(new GridLayout(1, 0));

            JButton button = new JButton("Play");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { play(); }
            });
            toolbar.add(button);

            button = new JButton("Stop");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { stop(); }
            });
            toolbar.add(button);

            button = new JButton("Pause");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { pause(); }
            });
            toolbar.add(button);

            button = new JButton("Resume");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { resume(); }
            });
            toolbar.add(button);

            button = new JButton("Edit");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { resume(); }
            });
            toolbar.add(button);

            button = new JButton("Swap");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { resume(); }
            });
            toolbar.add(button);

            button = new JButton("Save Changes");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { resume(); }
            });
            toolbar.add(button);
        }

        contentPane.add(toolbar, BorderLayout.NORTH);

        // building is done - arrange the components
        pack();

        // place this frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
        setVisible(true);
    }

    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar()
    {
        final int SHORTCUT_MASK =
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu menu;
        JMenuItem item;

        // create the File menu
        menu = new JMenu("File");
        menubar.add(menu);

        item = new JMenuItem("Quit");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { quit(); }
        });
        menu.add(item);

        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);

        item = new JMenuItem("About TalkBox...");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { showAbout(); }
        });
        menu.add(item);
    }
}