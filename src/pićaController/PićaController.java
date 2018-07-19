
package pićaController;

import baza.konekcija;
import java.io.IOException;
import java.net.URL;

import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


import pićaModel.pića;
import projektkafic.ProjektKafic;


public class PićaController implements Initializable {
    konekcija kon=new konekcija();
    Statement upit;
    
    @FXML
    private TableView<pića> tablica;
    @FXML
    private TableColumn<pića, String> naziv;
    @FXML
    private TableColumn<pića, String> cijena;
    @FXML
    private TableColumn<pića, String> vrsta;
    
    @FXML
    private void odaberi() throws IOException{
        pića napitak=tablica.getSelectionModel().getSelectedItem();
        ProjektKafic.odabranoPiće=napitak;
        
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Izmjena ili brisanje");
        
        Parent root = FXMLLoader.load(getClass().getResource("/pićaView/odabranoPiće.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void dodaj() throws IOException{
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Dodaj novi artikal");
        
        Parent root = FXMLLoader.load(getClass().getResource("/pićaView/dodaj.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        cijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        vrsta.setCellValueFactory(new PropertyValueFactory<>("vrsta"));
        String string="SELECT * FROM `napitak`";
        tablica.setItems(pića.podatci(string));
        
        
        
    }    
    
    
}
