package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

public class SimonGame implements Initializable {
	
	private int ranNum;
	private ArrayList<Integer> toCallPolygons;
	private HashMap<Integer, Polygon> mapPolygons;
	@FXML
	public Controller pController;
	private int totalClicks = 0;
	private int score = 0;
	private Sound sound = new Sound();
	private Thread thrGlowOn;
	public boolean gameStart = false;
	
	
	public SimonGame() {
		this.toCallPolygons = new ArrayList<>();
		this.mapPolygons = new HashMap<>();
	}
	public void initParentController(Controller controller) {
		 this.pController = controller;
    }
	//should be called outside 'this' class
	public void setMap() {
		this.mapPolygons.put(1, pController.poly1);
		this.mapPolygons.put(2, pController.poly2);
		this.mapPolygons.put(3, pController.poly3);
		this.mapPolygons.put(4, pController.poly4);
		this.mapPolygons.put(5, pController.poly5);
		this.mapPolygons.put(6, pController.poly6);
	}
	
	public void userPolygonSelect(Polygon p, MouseEvent e, int id){	
		if(gameStart){
			pController.lable.setText("SCORE\n"+"\t"+score);
			++this.totalClicks;
			processSelection(totalClicks, p);
		}
	}
	
	private synchronized void processSelection(int tc, Polygon p) {
		int val = toCallPolygons.get(tc-1);
		if(mapPolygons.get(val).equals(p)) {
			if(tc == toCallPolygons.size()) {
				totalClicks = 0;
				++this.score;
				pController.lable.setText("SCORE\n"+"\t"+score);
				pController.lable.setVisible(true);
				var helpMeToDeley = new Helper();
				helpMeToDeley.start();
			}
		}
		else {
			sound.playGameOverSound();
			totalClicks = 0;
			this.score = 0;
			pController.lable.setText("SCORE\n"+"\t"+score);
			pController.lable.setVisible(true);
			toCallPolygons.clear();
			try {
				Thread.sleep(2100);
			} catch (InterruptedException e) { }
			pController.button(null);
		}
		if(thrGlowOn.isAlive()) { // when glowing (generating) process is not over
			sound.playGameOverSound();
			totalClicks = 0;
			this.score = 0;
			pController.lable.setText("SCORE\n"+"\t"+score);
			toCallPolygons.clear();
			try {
				Thread.sleep(2100);
			} catch (InterruptedException e){ }
			pController.button(null);	
		} 
	}

	public void generate() {
		this.ranNum = (int)(1+Math.random()*6);	
		toCallPolygons.add(ranNum);
		glowPolyToClick();
	}
	
	private void glowPolyToClick() {
	     this.thrGlowOn = new Thread( () -> {
			for(int i=0; i<toCallPolygons.size(); ++i) {
				int val = toCallPolygons.get(i);
		    	glowLevelSet(mapPolygons.get(val));
		    	sound.playForPolygon(val);
		    	try {
					Thread.sleep(400);	
					glowOff(mapPolygons.get(val));
					Thread.sleep(400);
				} catch (InterruptedException e) { }
			} 
		});
		thrGlowOn.start();
	}
	public void glowPolyWhenClick(Polygon p, MouseEvent ev, int s) {	
		if(ev.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			glowLevelSet(p);
			sound.playForPolygon(s);
		}
	}

	private void glowLevelSet(Polygon poly){
		pController.glow = new Glow();
		pController.glow.setLevel(1);
		poly.setEffect(pController.glow);	
	}
	
	public void glowOff(Polygon p) {
		pController.glow = new Glow();
		pController.glow.setLevel(0);
		p.setEffect(pController.glow);
	}

	public ArrayList<Integer> get_toCallPoly() {
		return this.toCallPolygons;
	}
	
	//Safely execute generate method with a delay
	class Helper extends Thread {
		@Override
		public  void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { }
			generate();
		}
	}
	// Initializable interface -- somehow communicates with initialize method in Controller class?
	// This helps me solve issue of Label not showing lable.setText() after given argument
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){ }
}
