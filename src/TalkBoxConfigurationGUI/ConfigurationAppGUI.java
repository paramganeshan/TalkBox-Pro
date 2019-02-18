package TalkBoxConfigurationGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.util.List;
import java.util.ArrayList;

import java.io.*;
/**
 * A simple TalkBox. To start, create an instance of this class.
 *
 * The TalkBox can play sound clips in WAV, AU and AIFF formats
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
    private JList finalList;
    private JComboBox <Integer> order;
    private DefaultListModel initialListModel;
    private DefaultListModel finalListModel;
    private DefaultListModel audioListModel;
    private Integer[] orderButtons = {1, 2, 3};
    private DefaultComboBoxModel orderModel;

    //Main method for starting the player from a command line.
    public static void main(String[] args){ ConfigurationAppGUI gui = new ConfigurationAppGUI(); }

    //Create a TalkBox and display its GUI on screen.
    public ConfigurationAppGUI() {
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
    private void play() {
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
    private void showInfo(String message){ infoLabel.setText(message);}

    //Stop the currently playing sound file (if there is one playing).
    private void stop(){ player.stop();}

    //Stop the currently playing sound file (if there is one playing).
    private void pause() { player.pause();}

    //Resume a previously suspended sound file.
    private void resume() { player.resume();}

    //Quit function: quit the application.
    private void quit() {System.exit(0);}

    //About function: show the 'about' box.
    private void showAbout() {
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
            if(suffix == null)
                return allFiles;
            else {
                List<String> selected = new ArrayList<String>();
                for(String filename : allFiles) {
                    if(filename.endsWith(suffix))
                        selected.add(filename);
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
    public void stateChanged(ChangeEvent evt){player.seek(slider.getValue());}

    // ------- ActionListener interface (for ComboBox) -------

    /**
     * ActionListener method for changes of format combo box.
     * When this methosd is called, the user has made a new selection
     * in the combo box.
     * @param evt The event details.
     */
    public void actionPerformed(ActionEvent evt) {
        JComboBox cb = (JComboBox)evt.getSource();
        String format = (String)cb.getSelectedItem();
        if(format.equals("all formats"))
            format = null;
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
        contentPane.setPreferredSize(new Dimension(910, 500));
        makeMenuBar();

        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(8, 8));

        // Create the left side with combobox and scroll list
        JPanel leftPane = new JPanel(); {
            leftPane.setLayout(new BorderLayout(8, 8));

            String[] formats = {"all formats", ".wav", ".au", ".aif"};

            // Create the combo box.
            JComboBox formatList = new JComboBox(formats);
            formatList.setBackground(Color.BLACK);
            formatList.setForeground(new Color(140, 171, 226));
            formatList.addActionListener(this);
            leftPane.add(formatList, BorderLayout.NORTH);

            // Create the scrolled list for file names
            audioListModel = new DefaultListModel();
            audioList = new JList(audioListModel);
            for(int i = 0; i < audioFiles.length; i++)
                audioListModel.addElement(audioFiles[i]);
            setBackground(audioList);
            JScrollPane scrollPane = new JScrollPane(audioList);
            scrollPane.setColumnHeaderView(new JLabel("Audio files"));
            leftPane.add(scrollPane, BorderLayout.CENTER);
            //leftPane.setBackground(Color.BLACK);
        }
        contentPane.add(leftPane, BorderLayout.CENTER);

        //Create the right side with the Initial list and Updated list
        JPanel rightPane = new JPanel(); {
            //Create the Button # label and comboBox
            JPanel orderPanel = new JPanel(); {
                orderPanel.setLayout(new FlowLayout());

                Font font = new Font("Verdana", Font.BOLD,12);

                JLabel orderLabel = new JLabel("Button #: ");
                orderLabel.setFont(font);
                orderModel = new DefaultComboBoxModel();
                order = new JComboBox<>(orderModel);
                for(int i = 1; i <= orderButtons.length; i++)
                    orderModel.addElement(i);
                order.setBackground(Color.BLACK);
                order.setForeground(new Color(140, 171, 226));
                orderPanel.add(orderLabel);
                orderPanel.add(order);

                JLabel initialLabel = new JLabel("Initial List: ");
                initialLabel.setFont(font);
                orderPanel.add(initialLabel);

                //Add button to add new button in simulator app.
                JButton addNewBtn = new JButton();
                ImageIcon addNewIcn = new ImageIcon("Icons/Add.png");
                setButtonIcon(addNewBtn, addNewIcn);
                addNewBtn.setToolTipText("Add New Button");
                addNewBtn.addActionListener(e -> addInitialBtn(audioFiles));
                orderPanel.add(addNewBtn);

                //Add remove button to new remove the last button created in simulator app.
                JButton removeNewBtn = new JButton();
                ImageIcon removeIcn = new ImageIcon("Icons/Remove.png");
                setButtonIcon(removeNewBtn, removeIcn);
                removeNewBtn.setToolTipText("Remove from Initial List");
                removeNewBtn.addActionListener(e -> removeInitialBtn());
                orderPanel.add(removeNewBtn);


                //orderPanel.setBackground(Color.BLACK);

                JLabel finalLabel = new JLabel("Final List: ");
                finalLabel.setFont(font);
                orderPanel.add(finalLabel);

                //Add Edit button to add audio files to final list.
                JButton addFinalBtn = new JButton();
                ImageIcon addFinalIcn = new ImageIcon("Icons/Add.png");
                setButtonIcon(addFinalBtn, addFinalIcn);
                addFinalBtn.setToolTipText("Add to Final List");
                addFinalBtn.addActionListener(e -> addFinalBtn());
                orderPanel.add(addFinalBtn);

                //Add remove button to new remove the last button created in simulator app.
                JButton removeFinalBtn = new JButton();
                ImageIcon removeFinalIcn = new ImageIcon("Icons/Remove.png");
                setButtonIcon(removeFinalBtn, removeFinalIcn);
                removeFinalBtn.setToolTipText("Remove from Final List");
                removeFinalBtn.addActionListener(e -> removeFinalBtn());
                orderPanel.add(removeFinalBtn);

                //Add Record Button to record new audio files.
                JButton recordBtn = new JButton();
                ImageIcon recordIcn = new ImageIcon("Icons/Record.png");
                setButtonIcon(recordBtn, recordIcn);
                recordBtn.setToolTipText("Record");
                recordBtn.addActionListener(e -> {
                    recordBtn.setVisible(false);
                    ImageIcon stopRcdIcn = new ImageIcon("Icons/Stop.png");
                    JButton stopRcdBtn = new JButton();
                    setButtonIcon(stopRcdBtn, stopRcdIcn);
                    stopRcdBtn.setToolTipText("Stop Recording");
                    stopRcdBtn.addActionListener(e1 -> {
                        stopRcdBtn.setVisible(false);
                        recordBtn.setVisible(true);
                        stopRecording();
                    });
                    startRecording();
                    orderPanel.add(stopRcdBtn);
                });
                orderPanel.add(recordBtn);
            }
            rightPane.setLayout(new BorderLayout(8, 8));
            rightPane.add(orderPanel, BorderLayout.CENTER);
            initialListModel = new DefaultListModel();
            initialList = new JList(initialListModel);

            // Create the scrolled list for Initial List
            for(int i = 0; i < order.getItemCount(); i++)
                initialListModel.addElement(audioFiles[i]);
            setBackground(initialList);
            initialList.setPrototypeCellValue("XXXXXXXXXXXXXXXXXXX");
            JScrollPane leftScrollPane = new JScrollPane(initialList);
            leftScrollPane.setColumnHeaderView(new JLabel("Initial List"));
            rightPane.add(leftScrollPane, BorderLayout.WEST);

            //Create the scrolled list for Updated List
            finalListModel = new DefaultListModel();
            finalList = new JList(finalListModel);
            setBackground(finalList);
            finalList.setPrototypeCellValue("XXXXXXXXXXXXXXXXXXXX");
            JScrollPane rightScrollPane = new JScrollPane(finalList);

            //Add all elements from initial list to final list when app is started.
            for (int i=0; i<initialListModel.getSize(); i++)
                finalListModel.addElement(initialListModel.elementAt(i));

            rightScrollPane.setColumnHeaderView(new JLabel("Final List"));
            rightPane.add(rightScrollPane, BorderLayout.EAST);
            //rightPane.setBackground(Color.BLACK);
        }
        contentPane.add(rightPane, BorderLayout.SOUTH);

        // Create the center with image, text label, and slider
        JPanel centerPane = new JPanel(); {
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
            slider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    player.setVolume(slider.getValue());
                }
            });
            centerPane.add(slider, BorderLayout.SOUTH);
            //centerPane.setBackground(Color.BLACK);
        }
        contentPane.add(centerPane, BorderLayout.EAST);

        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel(); {
            toolbar.setLayout(new GridLayout(1, 0));

            JButton playBtn = new JButton();
            ImageIcon playIcn = new ImageIcon("Icons/Play.png");
            playBtn.setToolTipText("Play");
            setButtonIcon(playBtn, playIcn);
            playBtn.addActionListener(e -> play());
            toolbar.add(playBtn);

            JButton stopBtn = new JButton();
            ImageIcon stopIcn = new ImageIcon("Icons/Stop.png");
            setButtonIcon(stopBtn, stopIcn);
            stopBtn.setToolTipText("Stop");
            stopBtn.addActionListener(e -> stop());
            toolbar.add(stopBtn);

            JButton pauseBtn = new JButton();
            ImageIcon pauseIcn = new ImageIcon("Icons/Pause.png");
            setButtonIcon(pauseBtn, pauseIcn);
            pauseBtn.setToolTipText("Pause");
            pauseBtn.addActionListener(e -> pause());
            toolbar.add(pauseBtn);

            JButton resumeBtn = new JButton();
            ImageIcon resumeIcn = new ImageIcon("Icons/Play.png");
            setButtonIcon(resumeBtn, resumeIcn);
            resumeBtn.setToolTipText("Resume");
            resumeBtn.addActionListener(e -> resume());
            toolbar.add(resumeBtn);

            JButton resetBtn = new JButton();
            ImageIcon resetIcn = new ImageIcon("Icons/Reset.png");
            setButtonIcon(resetBtn, resetIcn);
            resetBtn.setToolTipText("Reset");
            resetBtn.addActionListener(e -> reset(audioFiles));
            toolbar.add(resetBtn);

            JButton swapBtn = new JButton();
            ImageIcon swapIcn = new ImageIcon("Icons/Swap.png");
            setButtonIcon(swapBtn, swapIcn);
            swapBtn.setToolTipText("Swap");
            swapBtn.addActionListener(e -> swap(audioFiles));
            toolbar.add(swapBtn);

            JButton saveChangesBtn = new JButton();
            ImageIcon saveChangesIcn = new ImageIcon("Icons/Save.png");
            setButtonIcon(saveChangesBtn, saveChangesIcn);
            saveChangesBtn.setToolTipText("Save Changes");
            saveChangesBtn.addActionListener(e -> saveChanges());
            toolbar.add(saveChangesBtn);
            //toolbar.setBackground(Color.BLACK);
        }

        contentPane.add(toolbar, BorderLayout.NORTH);
        //contentPane.setBackground(Color.black);

        // building is done - arrange the components
        pack();

        // place this frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
        setVisible(true);
    }

    //Create the main frame's menu bar.
    private void makeMenuBar() {
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
        item.addActionListener(e -> quit());
        menu.add(item);

        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);

        item = new JMenuItem("About TalkBox...");
        item.addActionListener(e -> showAbout());
        menu.add(item);
    }

    //Background for the lists that will be used in the GUI
    private void setBackground(JList list) {
        list.setForeground(new Color(140, 171, 226));
        list.setBackground(new Color(0, 0, 0));
        list.setSelectionBackground(new Color(87, 49, 134));
        list.setSelectionForeground(new Color(140, 171, 226));
    }

    //Added the edit button functionality to add audio files to final list.
    private void addFinalBtn() {
        if(audioList.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select an audio from audio list");
        }
        else {
            String filename = (String)audioList.getSelectedValue();
            if (finalListModel.size() < order.getItemCount())
                if(finalListModel.size() < order.getSelectedIndex())
                    JOptionPane.showMessageDialog(null, "Sorry, try to pick the previous buttons first");
                else {
                    if (finalListModel.contains(filename))
                        JOptionPane.showMessageDialog(null, "Sorry the item already exists");
                    else
                        finalListModel.add(order.getSelectedIndex(), filename);
                }
            else
                JOptionPane.showMessageDialog(null, "Sorry you have exceeded the " +
                        "number of available buttons, please add a new button, then try.");
        }
    }

    private void removeFinalBtn()
    {
        if (finalListModel.size() <= orderButtons.length)
            JOptionPane.showMessageDialog(null, "Cannot remove more buttons");
        else
            if(finalListModel.size() <= order.getSelectedIndex())
                JOptionPane.showMessageDialog(null, "Sorry, try to pick the previous buttons first ");
            else
                finalListModel.remove(order.getSelectedIndex());
    }

    //Added the swap button functionality to swap existing set of audio files with the next set.
    private void swap(String[] audioFiles) {
        int index = audioListModel.indexOf(finalListModel.lastElement());
        int j = audioListModel.indexOf(audioListModel.lastElement());
        int length = order.getItemCount();
        //initialListModel.removeAllElements();
        finalListModel.removeAllElements();
        for(int i = 1; i <= length; i++) {
            int c = ++index;
            if(c > j) {
                c = 0;
                index = 0;
                finalListModel.addElement(audioFiles[c]);
            }
            else {
                finalListModel.addElement(audioFiles[c]);
            }
        }
    }

    //Added the reset button functionality to reset the initial list and final list
    private void reset(String[] audioFiles) {
        finalListModel.removeAllElements();
        initialListModel.removeAllElements();
        order.removeAllItems();
        for(int i = 1; i <= orderButtons.length; i++) {
            initialListModel.addElement(audioFiles[i - 1]);
            orderModel.addElement(i);
        }
    }

    //Added functionality to add button to add new button to simulator app.
    private void addInitialBtn(String[] audioFiles) {
        int index = audioListModel.indexOf(initialListModel.lastElement());
        if(orderModel.getSize() < audioFiles.length) {
            orderModel.addElement(order.getItemCount() + 1);
            initialListModel.addElement(audioFiles[++index]);
        }
        else
            JOptionPane.showMessageDialog(null, "Sorry you can't add more buttons");
    }

    //Added functionality to remove button to remove the last created button in simulator app.
    private void removeInitialBtn() {
        if(order.getItemCount() > orderButtons.length) {
            if(finalListModel.size() == initialListModel.size() || finalListModel.size() >= initialListModel.size()) {
                finalListModel.removeElementAt(order.getItemCount() - 1);
                orderModel.removeElementAt(order.getItemCount() - 1);
                initialListModel.removeElementAt(order.getItemCount());
            }
            else {
                orderModel.removeElementAt(order.getItemCount() - 1);
                initialListModel.removeElementAt(order.getItemCount());
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Sorry, you can't remove more buttons");
    }
    //Added functionality to save changes.
    private void saveChanges() {
        if(finalListModel.isEmpty() || finalListModel.size() < initialListModel.size() ) {
            JOptionPane.showMessageDialog(null, "Sorry, number of buttons should be " +
                    "equal to the number of files in final list");
        }
        else if((finalListModel.toString()).equals(initialListModel.toString())) {
            JOptionPane.showMessageDialog(null, "Sorry, make different selections as " +
                    "both final and initial list are same");
        }
        else {
            initialListModel.removeAllElements();
            for (int i = 0; i < order.getItemCount(); i++) {
                initialListModel.addElement(finalListModel.getElementAt(i));
            }
        }
    }

    //Method to start recording
    private void startRecording() {

    }

    //Method to stop recording
    private void stopRecording() {

    }

    //Method to set the Button Icon
    private void setButtonIcon(JButton button, ImageIcon myIcon1) {
        //button.setBackground(Color.black);
        button.setBorderPainted(false);
        button.setBorder(null);
        button.setFocusable(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setIcon(myIcon1);
    }
}