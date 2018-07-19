
package kaficController;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projektkafic.ProjektKafic;


public class GlavniController implements Initializable {
    
    @FXML
    private Label poruka;
    
    @FXML
    private void pica() throws IOException{
        poruka.setText("");
        if(ProjektKafic.tipKorisnika.equals("admin")){
        String string="/pićaView/pića.fxml";
        ProjektKafic.layoutMain(string);
        }else{
            poruka.setText("Nemate pristup ovom sadržaju!");
        }
    }
    
    @FXML
    private void narudzbe()throws IOException{
        poruka.setText("");
        String string="/narudzbaView/narudzba.fxml";
        
        ProjektKafic.layoutMain(string);
        
    }
    
    @FXML
    private void korisnici() throws IOException{
        if(ProjektKafic.tipKorisnika.equals("admin")){
        poruka.setText("");
        String string="/korisniciView/korisnici.fxml";
        ProjektKafic.layoutMain(string);
        }else{
            poruka.setText("Nemate pristup ovom sadržaju!");
        }
    }
    
    @FXML
    private void odjava()throws IOException{
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Pažnja!");
        Label pitanje=new Label("Jeste li sigurni da se želite odjaviti?");
        Button yes=new Button("Da");
        Button no=new Button("Ne");
        yes.setOnAction(event->{
            try {
                ProjektKafic.layoutMenuLogIn();
            } catch (IOException ex) {
                Logger.getLogger(GlavniController.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.close();
        });
        no.setOnAction(event->{
            stage.close();
        });
        
        
        VBox layout=new VBox();
        layout.getChildren().addAll(pitanje,yes,no);
        Scene scena=new Scene(layout,200,200);
        stage.setScene(scena);
        stage.show();
    }
        
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
