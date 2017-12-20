package lab4;

import java.util.Locale;
import javafx.application.Application;
import javafx.stage.Stage;
import laborai.gui.fx.Lab4WindowFX;

/*
 * Darbo atlikimo tvarka - čia yra pradinė klasė.
 */
public class VykdymoModulisFX extends Application {

    public static void main(String[] args) {
   //     GreitaveikosTyrimas a = new GreitaveikosTyrimas();
     //   a.pradetiTyrima();
        VykdymoModulisFX.launch(args);
        
    }

    @Override
    public void start(Stage primaryStage) {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus 
        Test.atvaizdzioTestas();
        setUserAgentStylesheet(STYLESHEET_MODENA);    //Nauja FX išvaizda
        //setUserAgentStylesheet(STYLESHEET_CASPIAN); //Sena FX išvaizda
        Lab4WindowFX.createAndShowFXGUI(primaryStage);
    }
}
