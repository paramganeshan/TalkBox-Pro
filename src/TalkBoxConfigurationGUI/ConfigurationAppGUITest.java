package TalkBoxConfigurationGUI;


import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Window;
import java.io.File;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


class ConfigurationAppGUITest {
	
	ConfigurationAppGUI gui = new ConfigurationAppGUI();
	SoundEngine player;
	TalkBoxConfiguration talkbox;

	@BeforeEach 
	public void setUp() throws Exception {
		Thread.sleep(100);
		gui = new ConfigurationAppGUI();
		Thread.sleep(100);
	}
	
	/**
	 * Testing the findFiles method.
	 * Checks if the method returns the correct files when a suffix is passed.
	 * 
	 */
	
	
	@Test
	public void testFindFiles() {
		int i = 0;
		int j = 0;
		File sFile = new File("Sounds");
		assertTrue(sFile.isDirectory());
		
		String[] wavFiles = new String[sFile.listFiles().length];
		String[] allFiles = new String[sFile.listFiles().length];
		
		File[] files1 = sFile.listFiles();
		
		for (File file : files1)
		{
			if (file.getName().endsWith("wav"))
			{
				wavFiles[i] = file.getName(); 
				i++;
			}
			
			allFiles[j] = file.getName();
			j++;
			
		}
		
		String[] files = gui.findFiles("Sounds", "wav");
		String[] allFiles2 = gui.findFiles("Sounds", null);
	
		//Test if method correctly find only .wav files and all files respectively
		
		assertArrayEquals(files, wavFiles);
		assertArrayEquals(allFiles, allFiles2);
		
		//assert method returns null when an incorrect direct
		
		assertNull(gui.findFiles("Not a Directory", "wav"));
		
	}
	
	/**
	 * Testing whether play button works
	 */

	//@Disabled
	@Test
	public void testClickPlayButton() throws InterruptedException {
		Thread.sleep(1000);
		//click play button
		gui.playBtn.doClick();
		Thread.sleep(1000);
	}
	
	//@Disabled
	@Test
	public void testClickStopButton() throws InterruptedException {
		Thread.sleep(1000);
		//play the second audio file in the audio list
		gui.audioList.setSelectedIndex(1);
		gui.playBtn.doClick();
		Thread.sleep(700);
		//stop currently playing audio file
		gui.stopBtn.doClick();
		Thread.sleep(1000);
	}
	
	//@Disabled
	@Test
	public void testClickPauseButton() throws InterruptedException {
		Thread.sleep(100);
		//play second audio file in the audio list
		gui.audioList.setSelectedIndex(1);
		gui.playBtn.doClick();
		Thread.sleep(700);
		//pause currently playing audio file
		gui.pauseBtn.doClick();
		Thread.sleep(100);
	}
	
	//@Disabled
	@Test
	public void testClickResumeButton() throws InterruptedException {
		Thread.sleep(100);
		//play third audio file in list
		gui.audioList.setSelectedIndex(2);
		gui.playBtn.doClick();
		Thread.sleep(600);
		//pause currently playing audio file
		gui.pauseBtn.doClick();
		Thread.sleep(500);
		//resume currently paused audio file
		gui.resumeBtn.doClick();
		Thread.sleep(100);
	}
	
	//@Disabled
	@Test
	public void testClickResetButton() throws InterruptedException {
		Thread.sleep(100);
		//click reset button
		gui.resetBtn.doClick();
		
		assertEquals(gui.getNumberOfAudioSets(), 4);
		assertEquals(gui.getNumberOfAudioButtons(), 3);	
		String[][] defaultArray = gui.getAudioFileNames();
		//add two new buttons
		gui.addNewBtn.doClick();
		gui.addNewBtn.doClick();
		//add first and second elements to final list
		gui.audioList.setSelectedIndex(0);
		gui.addFinalBtn.doClick();
		gui.audioList.setSelectedIndex(1);
		gui.addFinalBtn.doClick();
		//click reset button 
		gui.resetBtn.doClick();
		String[][] resetArray = gui.getAudioFileNames();
		
		assertArrayEquals(defaultArray, resetArray);
		assertTrue(gui.finalListModel.isEmpty());
		assertEquals(gui.getNumberOfAudioSets(), 4);
		assertEquals(gui.getNumberOfAudioButtons(), 3);
		Thread.sleep(100);
	}
	
	//NOT DONE, TEST FOR WHEN SWAPPING THE END OF THE LIST AND FOR WHEN 10 BUTTONS HAVE BEEN SELECTED ETC.
	//@Disabled
	@Test
	public void testClickSwapButton() throws InterruptedException {
		Thread.sleep(100);
		//get file names for audio files
		int j = 0;
		File soundFile = new File("Sounds");
		File[] files1 = soundFile.listFiles();
		String[] allFiles = new String[soundFile.listFiles().length];
		for (File file : files1)
			{				
				allFiles[j] = file.getName();	
				++j;
			}
		String[] initialList = {allFiles[0], allFiles[1], allFiles[2]};
		
		//add first 3 audio files to final list
		gui.resetBtn.doClick();
		gui.audioList.setSelectedIndex(2);
		gui.addFinalBtn.doClick();
		gui.audioList.setSelectedIndex(1);
		gui.addFinalBtn.doClick();
		gui.audioList.setSelectedIndex(0);
		gui.addFinalBtn.doClick();

		assertArrayEquals(initialList, gui.finalListModel.toArray());
		
		//press swap button
		int k = 3;
		gui.swapBtn.doClick();
		
		//change initial list to next 3 audio files
		for (int i = 0; i <= 2; i++)
		{
			initialList[i] = allFiles[k];
			k++;
		}
		
		assertArrayEquals(initialList, gui.finalListModel.toArray());
		
		//press swap button again
		gui.swapBtn.doClick();
		
		//change initial list to next 3 audio files
		for (int i = 0; i <= 2; i++)
		{
			initialList[i] = allFiles[k];
			k++;
		}
		
		Thread.sleep(100);
	}
	
	//NOT DONE, WEIRD EXCEPTION THROWN
	//@Disabled
	@Test
	public void testSaveChangesButton() throws InterruptedException {
		Thread.sleep(100);
		gui.saveChangesBtn.doClick();
		//click reset button
		gui.resetBtn.doClick();
		Thread.sleep(100);
		//add arbitrary audio files to final list
		gui.audioList.setSelectedIndex(4);
		Thread.sleep(100);
		gui.addFinalBtn.doClick();
		Thread.sleep(100);
		
		gui.saveChangesBtn.doClick();
		
		Thread.sleep(100);
		
		gui.audioList.setSelectedIndex(2);
		Thread.sleep(100);
		gui.addFinalBtn.doClick();
		Thread.sleep(100);
		gui.audioList.setSelectedIndex(6);
		Thread.sleep(100);
		gui.addFinalBtn.doClick();
		Thread.sleep(100);
		//store final list
		DefaultListModel<?> initialFinalList = gui.finalListModel;
		Thread.sleep(100);
		//click save changes button
		gui.saveChanges();
		Thread.sleep(100);
		//compare new initial list with old final list
		assertArrayEquals(gui.initialListModel.toArray(), initialFinalList.toArray());
		
		//System.exit(0);
		
		//ConfigurationAppGUI gui2 = new ConfigurationAppGUI();
		
		//assertArrayEquals(gui2.initialListModel.toArray(), initialFinalList.toArray());
		
	}
	
	//NOT DONE, TEST FOR EXTRA CLICK OF INITIAL BUTTON, EXCEPTION THROWN?
	//@Disabled
	@Test
	public void testAddInitialButton() throws InterruptedException {
		Thread.sleep(100);
		gui.resetBtn.doClick();
	
		int i = gui.initialListModel.getSize();
		gui.addNewBtn.doClick();
		
		assertEquals(gui.initialListModel.getSize(), i + 1);
		i++;
		
		gui.addNewBtn.doClick();
		
		gui.addNewBtn.doClick();
		
		assertEquals(gui.initialListModel.getSize(), i + 2);
		
		
		gui.resetBtn.doClick();
		
		
		gui.addNewBtn.doClick();
		
		gui.addNewBtn.doClick();
		
		gui.addNewBtn.doClick();
		
		gui.addNewBtn.doClick();
		
		gui.addNewBtn.doClick();
		
		gui.addNewBtn.doClick();
		
		
		gui.addNewBtn.doClick();
		
		File soundFile = new File("Sounds");		
		File[] files1 = soundFile.listFiles();
		
		assertEquals(gui.initialListModel.getSize(), files1.length);
		
		Thread.sleep(100);
			
	}
	
	//CHECK IF MORE TESTING IS REQUIRED
	//@Disabled
	@Test
	public void testRemoveInitialButton() throws InterruptedException {
		Thread.sleep(100);
		gui.resetBtn.doClick();
		gui.addNewBtn.doClick();
		gui.addNewBtn.doClick();
		gui.addNewBtn.doClick();
		
		int i = gui.initialListModel.getSize();
		
		gui.removeNewBtn.doClick();
		
		assertEquals(gui.initialListModel.getSize(), i - 1);
		i--;
		
		gui.removeNewBtn.doClick();
		gui.removeNewBtn.doClick();
		
		assertEquals(gui.initialListModel.getSize(), i - 2);
		Thread.sleep(100);
	}
	
	//CHECK IF MORE TESTING IS REQUIRED
	//@Disabled
	@Test
	public void testAddFinalButton() throws InterruptedException {
		Thread.sleep(100);
		int i = 0;
		gui.resetBtn.doClick();
		gui.audioList.setSelectedIndex(0);
		gui.addFinalBtn.doClick();
		gui.audioList.setSelectedIndex(1);
		gui.addFinalBtn.doClick();
		
		assertEquals(gui.finalListModel.getSize(), i + 2);
		i+=2;
		
		gui.audioList.setSelectedIndex(2);
		gui.addFinalBtn.doClick();
		gui.audioList.setSelectedIndex(3);
		gui.addFinalBtn.doClick();		
		
		if (gui.finalListModel.getSize() > gui.initialListModel.getSize()) {
			fail();
		}
		Thread.sleep(100);
	}
	
	//@Disabled
	@Test
	public void testRemoveFinalButton() throws InterruptedException {
		Thread.sleep(100);
		gui.resetBtn.doClick();
		
		for (int i = 0; i < 3; i++) {
		gui.audioList.setSelectedIndex(i);
		gui.addFinalBtn.doClick();
		}
		
		int finalListSize = gui.finalListModel.getSize();
		
		gui.removeFinalBtn.doClick();
		assertEquals(gui.finalListModel.getSize(), finalListSize - 1);
		
		finalListSize-=1;
		gui.removeFinalBtn.doClick();
		gui.removeFinalBtn.doClick();
		assertEquals(gui.finalListModel.getSize(), finalListSize - 2);
		
		gui.removeFinalBtn.doClick();
		if (gui.finalListModel.getSize() < 0) {
			fail();
		}
		Thread.sleep(100);
	}
	
	@Test
	public void testLaunchSimulator() throws InterruptedException {
		Thread.sleep(100);
		gui.resetBtn.doClick();
		gui.launchSimApp.doClick();
		Thread.sleep(100);
		assertTrue(gui.myFrame.isActive());
		assertTrue(gui.myFrame.isVisible());
		assertEquals(gui.myFrame.getNumberOfAudioButtons(), 3);
		assertEquals(gui.myFrame.getNumberOfAudioSets(), 4);
		assertEquals(gui.myFrame.getTotalNumberOfButtons(), 12);
		Thread.sleep(100);
		
		gui.myFrame.swapButtons[0].doClick();
		gui.myFrame.audioButtons[0].doClick();
		
		Thread.sleep(100);
	
	
	}

}
