package foker;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URISyntaxException;

public class Main extends Application {

    public static VBox root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/view/view_name_change.fxml"));
        primaryStage.setTitle("Foker");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            if(GameSocketConnection.CONNECTION_OPENED){
                StartConnection.sendMessage("CLOSE");
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) throws URISyntaxException {
        new StartConnection(); //start connection to the server
        launch(args);
    }
}
