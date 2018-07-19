
package pićaController;

import baza.konekcija;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DodajController implements Initializable {
    
    konekcija kon=new konekcija();
    Statement upit;
    
    
    @FXML
    private TextField naziv;
    @FXML
    private ComboBox<String> vrsta;
    @FXML
    private Label poruka;
    @FXML
    private TextField cijena;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vrsta.getItems().removeAll(vrsta.getItems());
        vrsta.getItems().addAll("Alkoholno", "Topli napitak","Sok");
        
        
    }    

    @FXML
    private void potvrdi(ActionEvent e) {
        String Naziv=naziv.getText();
        double Cijena=Double.parseDouble(cijena.getText());//ovdje
        
        String Vrsta=vrsta.getValue();
        
        //provjera jesu li unesena sva polja:
        if(Naziv.equals("")||cijena.getText().equals("")||Vrsta==null){
            
                poruka.setText("Morate unijeti sve podatke!");
        }else{
                //ako su sva polja unešena:
                
                
                if(provjeraNaziva(Naziv)){                               //ako postoje dva ista, napiši poruku:
                poruka.setText("Artikal sa tim nazivom već postoji!");
                }else{
                        
                            String novi=Vrsta;
                            //skočni prozor za potvrdu:
        
                            Stage stage=new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setTitle("Pažnja!");
                            Label pitanje=new Label("Jeste li sigurni?");
                            Button yes=new Button("Da");
                            Button no=new Button("Ne");
                            yes.setOnAction((ActionEvent event)->{
                                kon.spoji();    
                                try {
                                        this.upit=kon.konekcija.createStatement();      
                                } catch (SQLException ex) {
                                        Logger.getLogger(DodajController.class.getName()).log(Level.SEVERE, null, ex);
                                }
            
                                try {
                                        String query="INSERT INTO `napitak`(`vrsta`, `naziv`, `cijena`) VALUES ('"+novi+"','"+Naziv+"','"+Cijena+"')";
                
                                        int varijabla=upit.executeUpdate(query);
                
                                        upit.close();
                                        kon.odspoji();
                
                                } catch (SQLException ex) {
                                        Logger.getLogger(DodajController.class.getName()).log(Level.SEVERE, null, ex);
                                }
            
                                        stage.close();      
                                });
                                no.setOnAction(event->{
                                        stage.close();
                                });
        
                            VBox layout=new VBox();
                            layout.getChildren().addAll(pitanje,yes,no);
                            Scene scena=new Scene(layout, 200, 200);
                            stage.setScene(scena);
                            stage.show();
                }                           
        }
    }
    
    
    private boolean provjeraNaziva(String string){
        //ako postoji predmet sa istim imenom, vrati true
        kon.spoji();
        
        try {
            this.upit=kon.konekcija.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(DodajController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query="SELECT * FROM napitak";
        try {
            ResultSet rs=upit.executeQuery(query);
            while(rs.next()){
                if(rs.getString(3).equals(string)){
                    return true;
                }
                
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(KreirajController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    
    
    
}
