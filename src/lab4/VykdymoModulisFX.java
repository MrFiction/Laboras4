package lab4;

import laborai.demo.*;
import java.util.Locale;
import javafx.application.Application;
import javafx.stage.Stage;

/*
 * Darbo atlikimo tvarka - čia yra JavaFX pradinė klasė.
 */
public class VykdymoModulisFX extends Application {

    public static void main(String [] args) {
        VykdymoModulisFX.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus 
        Test.Testing();
        Lab3WindowFX.createAndShowFXGUI(primaryStage);
    }
}