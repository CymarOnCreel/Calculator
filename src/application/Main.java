package application;
	
import application.controller.CalculatorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			SplitPane root = (SplitPane) FXMLLoader.load(getClass().getResource("/application/view/CalculatorWindow.fxml"));
			System.out.println(root);
			CalculatorController controller = new CalculatorController();
			controller.setPrimaryStage(primaryStage);
			System.out.println(root);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
