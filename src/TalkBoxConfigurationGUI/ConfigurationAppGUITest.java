package TalkBoxConfigurationGUI;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.lang.String;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConfigurationAppGUITest {
	
	ConfigurationAppGUI gui = new ConfigurationAppGUI();
	SoundEngine player;
	TalkBoxConfiguration talkbox;

	@BeforeEach 
	public void setUp() throws Exception {
		gui = new ConfigurationAppGUI();
	}

	@Test
	public void TestGUISetUp() {
		assertEquals(gui.getTitle(), "TalkBox");
		assertTrue(gui.isResizable());
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

	@Test
	public void testClickPlayButton() throws InterruptedException {
		Thread.sleep(1000);
		gui.audioList.setSelectedIndex(0);
		gui.playBtn.doClick();
		Thread.sleep(1000);
	}
	
	@Test
	public void testClickStopButton() throws InterruptedException {
		Thread.sleep(1000);
		gui.audioList.setSelectedIndex(1);
		gui.playBtn.doClick();
		Thread.sleep(700);
		gui.stopBtn.doClick();
		Thread.sleep(1000);
	}
	
	@Test
	public void testClickPauseButton() throws InterruptedException {
		Thread.sleep(100);
		gui.audioList.setSelectedIndex(1);
		gui.playBtn.doClick();
		Thread.sleep(700);
		gui.pauseBtn.doClick();
		Thread.sleep(100);
	}
	
	@Test
	public void testClickResumeButton() throws InterruptedException {
		Thread.sleep(100);
		gui.audioList.setSelectedIndex(2);
		gui.playBtn.doClick();
		Thread.sleep(600);
		gui.pauseBtn.doClick();
		Thread.sleep(500);
		gui.resumeBtn.doClick();
		Thread.sleep(100);
	}
	
	@Test
	public void testClickResetButton() throws InterruptedException {
		Thread.sleep(100);
		gui.resetBtn.doClick();
		Thread.sleep(100);
	}

}
