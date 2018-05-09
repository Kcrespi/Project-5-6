import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Balls extends Application {
	int [] xPos;
	int [] yPos;
	int [] deltaX;
	int [] deltaY;
	
	public void start(Stage primaryStage) throws Exception {
		Color ballColors[] = {Color.PURPLE, Color.GREEN, Color.RED};
		Pane drawPane = new Pane();
		IntegerProperty [] x = new SimpleIntegerProperty[3];
		IntegerProperty [] y = new SimpleIntegerProperty[3];
		xPos = new int[3];
		yPos = new int[3];
		deltaX = new int[3];
		deltaY = new int[3];
		
		for (int j = 0; j < 3; j++) {
			xPos[j] = 50;
			yPos[j] = 50;
			deltaX[j] = j + 1;
			deltaY[j] = j + 1;
			x[j] = new SimpleIntegerProperty();
			y[j] = new SimpleIntegerProperty();
		}
		
		Circle circles[] = new Circle[3];
		for (int i = 0; i < 3; i++) {	
			circles[i] = new Circle();
			circles[i].centerXProperty().bind(x[i]);
			circles[i].centerYProperty().bind(y[i]);
			circles[i].setRadius(10);
			circles[i].setFill(ballColors[i]);
			drawPane.getChildren().add(circles[i]);
		}
		
	
		Scene mainScene = new Scene(drawPane, 650, 500);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	
		new Thread (new Runnable() {		
	
			public void run() {
				
				while (true) {
				for (int i = 0; i < 3; i++) {
					xPos[i] = xPos[i] + deltaX[i];
					x[i].set(xPos[i]);
					yPos[i] = yPos[i] + deltaY[i];
					y[i].set(yPos[i]);
				}
				
				try {
					Thread.sleep(25);
				} catch (Exception e) 
				{  }
				
				for(int i = 0; i < 3; i++) {
					if (xPos[i] > 600) {
						deltaX[i] = -deltaX[i];
					}
					if (xPos[i] < 50) {
						deltaX[i] = -deltaX[i];
					}
					if (yPos[i] > 500) {
						deltaY[i] = -deltaY[i];
					}
					if (yPos[i] < 50) {
						deltaY[i] = -deltaY[i];
					}
				}
				}
			}
		}
		).start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
