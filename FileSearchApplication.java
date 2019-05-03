import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FileSearchApplication extends Application {
   
    @Override
    public void start(Stage stage) throws Exception {
        Parent root =
                FXMLLoader.load(getClass()
                          .getResource("FileSearchApplication.fxml"));

            // attach scene graph to scene
            Scene scene = new Scene(root);

            // displayed in window's title bar
            stage.setTitle("Daniel's File Search App");

            // attach scene to stage
            stage.setScene(scene);

            // display the stage
            stage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }   
}
