
package narudzbaController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import narudzbaController.NarudzbaController;

public class ZavrsiController implements Initializable {

    @FXML
    private Label iznos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String novi=Double.toString(NarudzbaController.ukupanIznos);
        iznos.setText(""+novi);
    }    

    @FXML
    private void uredu(ActionEvent e) {
        final Node source = (Node) e.getSource();
        final Stage sstage = (Stage) source.getScene().getWindow();
        sstage.close();
        
        
    }
    
}
