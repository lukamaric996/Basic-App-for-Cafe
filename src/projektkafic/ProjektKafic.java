
package projektkafic;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.stage.Modality;
import korisniciModel.Korisnik;
import pićaModel.pića;


public class ProjektKafic extends Application {
    
    private static Stage window;
    
    private static BorderPane mainLayout;
    private static BorderPane podLayout;
    
    public static String korisnik;
    public static String tipKorisnika;
    
    public static Korisnik odabraniKorisnik;
    public static pića odabranoPiće;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        layoutMenuLogIn();
        //layoutMenu();
        
    }
    
    public static void layoutMenuLogIn() throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(ProjektKafic.class.getResource("/logInView/logIn.fxml"));
        mainLayout=loader.load();
        Scene scena=new Scene(mainLayout);
        window.setScene(scena);
        window.show();
    }
    
    public static void layoutMenu() throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(ProjektKafic.class.getResource("/kaficView/glavni.fxml"));
        mainLayout=loader.load();
        Scene scena=new Scene(mainLayout);
        window.setScene(scena);
        window.show();
        
        
        
    }
    public static void layoutMain(String string) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(ProjektKafic.class.getResource(string));
        podLayout=loader.load();
        mainLayout.setCenter(podLayout);
        
    }
    public static void podLayout(String string) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(ProjektKafic.class.getResource(string));
        BorderPane podCentar=loader.load();
        podLayout.setCenter(podCentar);
        
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
