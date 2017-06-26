package foker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class Main extends Application {

    Button button;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/view_name_change.fxml"));
        primaryStage.setTitle("Foker");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();

        button = new Button("Click");
        button.setOnAction(e -> System.out.println("hello"));
    }

    public static void main(String[] args) throws URISyntaxException {
        new StartConnection(); //start connection to the server
        launch(args);
    }
}