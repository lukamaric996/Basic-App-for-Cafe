
package narudzbaController;

import baza.konekcija;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import pićaModel.pića;


public class NarudzbaController implements Initializable {
    konekcija kon=new konekcija();
    Statement upit;
    ObservableList<String> lista=FXCollections.observableArrayList();
    ObservableList<pića> napitci=FXCollections.observableArrayList();
    pića napitak;
    public static double ukupanIznos;//ovdje
    @FXML
    private TableView<pića> tablica;
    @FXML
    private TableColumn<pića, String> naziv;
    @FXML
    private TableColumn<pića, String> cijena;
    @FXML
    private ListView<pića> listView;
    
    
    @FXML
    private void topli(){
        naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        cijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        String string="SELECT * FROM `napitak` WHERE vrsta='Topli napitak'";
        tablica.setItems(pića.podatci(string));
    }
    @FXML
    private void sokovi(){
        naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        cijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        String string="SELECT * FROM `napitak` WHERE vrsta='Sok'";
        tablica.setItems(pića.podatci(string));
    }
    @FXML
    private void alkoholna(){
        naziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        cijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        String string="SELECT * FROM `napitak` WHERE vrsta='Alkoholno'";
        tablica.setItems(pića.podatci(string));
    }
    @FXML
    private void zavrsi() throws IOException{
        ukupanIznos=0.0d;
        int i=0;
        for(pića p: napitci){
            ukupanIznos+=napitci.get(i).getCijena();
            i++;
        }
        napitci.clear();
        initListView();
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Ukupan iznos!");
        
        Parent root = FXMLLoader.load(getClass().getResource("/narudzbaView/zavrsi.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        
        
    }
    @FXML
    private void dodaj(){
        
        napitak=tablica.getSelectionModel().getSelectedItem();
        napitci.add(napitak);
        
        
        initListView();
        
    }
    @FXML
    private void ukloni(){
        
        napitak=listView.getSelectionModel().getSelectedItem();
        
        boolean uklon = napitci.remove(napitak);
        initListView();
        
    }
    @FXML
    private void ocisti(){
        napitci.clear();
        initListView();
    }
    
    
    
    private void initListView(){
        
        listView.getItems().setAll(napitci);
    }
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
}
