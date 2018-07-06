package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    static final double SCENE_SIZE_COEFF = 0.5;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Общие дружани VK");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Scene mainScene = new Scene(root, screenSize.width * SCENE_SIZE_COEFF, screenSize.height * SCENE_SIZE_COEFF);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
