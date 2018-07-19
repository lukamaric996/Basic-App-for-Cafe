
package korisniciController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import korisniciModel.Korisnik;
import baza.konekcija;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projektkafic.ProjektKafic;


public class KorisniciController implements Initializable {
    konekcija kon=new konekcija();
    Statement upit;
    
    
    @FXML
    private TableView<Korisnik> tablica;
    @FXML
    private TableColumn<Korisnik, String> ime;
    
    
    @FXML
    private void odaberi() throws IOException{
        Korisnik korisnik=tablica.getSelectionModel().getSelectedItem();
        ProjektKafic.odabraniKorisnik=korisnik;
        
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Izmjena ili brisanje");
        
        Parent root = FXMLLoader.load(getClass().getResource("/korisniciView/odabraniKorisnik.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        
        
    }
    
    @FXML
    private void kreiraj() throws IOException{
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Kreiraj novog korisnika");
        
        Parent root = FXMLLoader.load(getClass().getResource("/korisniciView/kreiraj.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ime.setCellValueFactory(new PropertyValueFactory<>("ime"));
        
        tablica.setItems(podatci());
    
    }    
    private ObservableList<Korisnik> podatci(){
        ObservableList<Korisnik> Podatci=FXCollections.observableArrayList();
        kon.spoji();
        try {
            this.upit=kon.konekcija.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(KorisniciController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rs = this.upit.executeQuery("SELECT * FROM `korisnik`");
            while(rs.next()){
            Podatci.add(new Korisnik(rs.getInt(1)));
            }
            
            
            this.upit.close();
            kon.odspoji();
            
        } catch (SQLException ex) {
            System.out.println("Funkcija za dobavljanje podataka u kontroleru nije uspjela izvršiti upit");
        }
        
        
        
        
        return Podatci;
    }
}
