package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;

public class Controller {
	@FXML
	public AnchorPane pane;
	@FXML
	public Glow glow;
	@FXML
	public Polygon poly1;
	@FXML
	public Polygon poly2;
	@FXML
	public Polygon poly3;
	@FXML
	public Polygon poly4;
	@FXML
	public Polygon poly5;
	@FXML
	public Polygon poly6;
	@FXML
	public Button startButton;
	//note:method initialize gets automatically called
	//by FXMLLoader after injection
	@FXML
	public void initialize() {
		simongame.initParentController(this);
		simongame.setMap();
	}
	@FXML
	public Label lable;
	SimonGame simongame = new SimonGame();
	
	
	public void polygon_1(MouseEvent e) {
		int id = 1;
		simongame.glowPolyWhenClick(poly1, e, id);
		simongame.userPolygonSelect(poly1, e, id);
	}
	
	public void polygon_2(MouseEvent e) {
		int id = 2;
		simongame.glowPolyWhenClick(poly2, e, id);
		simongame.userPolygonSelect(poly2, e, id);
	}
	
	public void polygon_3(MouseEvent e) {
		int id = 3;
		simongame.glowPolyWhenClick(poly3, e, id);
		simongame.userPolygonSelect(poly3, e, id);
	}
	
	public void polygon_4(MouseEvent e) {
		int id = 4;
		simongame.glowPolyWhenClick(poly4, e, id);
		simongame.userPolygonSelect(poly4, e, id);
	}
	
	public void polygon_5(MouseEvent e) {
		int id = 5;
		simongame.glowPolyWhenClick(poly5, e, id);
		simongame.userPolygonSelect(poly5, e, id);
	}
	public void polygon_6(MouseEvent e) {
		int id = 6;
		simongame.glowPolyWhenClick(poly6, e, id);
		simongame.userPolygonSelect(poly6, e, id);
	}
	public void button(MouseEvent e) {
		this.simongame = new SimonGame();
		initialize();
		
		simongame.gameStart = true;
		simongame.generate();
	}
	public void glowSetOff(MouseEvent e) {
		glow = new Glow();
		glow.setLevel(0);
		((Node) e.getSource()).setEffect(glow);
		System.out.println("Node: "+((Node) e.getSource()));
	}
}
