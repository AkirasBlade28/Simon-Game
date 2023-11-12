package application;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	private File s1 = new File("src/Sound Resources/sound_1.wav");
	private File s2 = new File("src/Sound Resources/sound_2.wav");
	private File s3 = new File("src/Sound Resources/sound_3.wav");
	private File s4 = new File("src/Sound Resources/sound_4.wav");
	private File s5 = new File("src/Sound Resources/sound_5.wav");
	private File s6 = new File("src/Sound Resources/sound_6.wav");
	private File gameOver = new File("src/Sound Resources/GameKindOfOver.wav");
	
	public void playGameOverSound() {
		playSound(gameOver);
	}
	
	public void playForPolygon(int polygonId) {
		switch (polygonId) {
		case 1: 
			playSound1();
		case 2:
			playSound2();
		case 3:
			playSound3();
		case 4:
			playSound4();
		case 5:
			playSound5();
		case 6:
			playSound6();
		default: ;
		}
	}
	
	private void playSound1() {
		playSound(s1);
	}
	private void playSound2() {
		playSound(s2);
	}
	private void playSound3() {
		playSound(s3);
	}
	private void playSound4() {
		playSound(s4);
	}
	private void playSound5() {
		playSound(s5);
	}
	private void playSound6() {
		playSound(s6);
	}
	
	private static synchronized void playSound(File soundPath) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundPath);
					Clip clip = AudioSystem.getClip();
					clip.open(audioStream);
					clip.start();
				} catch (Exception e) { }		
			}
		}).start();
	}			
}
