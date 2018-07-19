
package korisniciController;

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

public class KreirajController implements Initializable {
    konekcija kon=new konekcija();
    Statement upit;
    
    
    @FXML
    private TextField ime;
    @FXML
    private ComboBox<String> tipKorisnika;
    @FXML
    private Label poruka;
    @FXML
    private PasswordField lozinka;
    @FXML
    private PasswordField potvrda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipKorisnika.getItems().removeAll(tipKorisnika.getItems());
        tipKorisnika.getItems().addAll("konobar", "Administrator");
        
    }    

    @FXML
    private void potvrdi(ActionEvent e) {
        String sIme=ime.getText();
        String sLozinka=lozinka.getText();
        String sPotvrda=potvrda.getText();
        String sTipKorisnika=tipKorisnika.getValue();
        
        //provjera jesu li unesena sva polja:
        if(sIme.equals("")||sLozinka.equals("")||sPotvrda.equals("")||sTipKorisnika==null){
            
                poruka.setText("Morate unijeti sva polja!");
        }else{
                //ako su sva polja unešena:
                //promjena stringa za vrstu korisnika:
                if(sTipKorisnika.equals("Administrator")){
                    sTipKorisnika="admin";
                
                }else{
                    sTipKorisnika="konobar";
                }
                
                
                if(provjeraKorisnika(sIme)){                               //ako postoje dva ista korisnika, napiši poruku:
                poruka.setText("Korisnik sa tim imenom već postoji!");
                }else{
                        if(sLozinka.equals(sPotvrda)){          //ako se lozinke podudaraju
                            String novi=sTipKorisnika;
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
                                        Logger.getLogger(KreirajController.class.getName()).log(Level.SEVERE, null, ex);
                                }
            
                                try {
                                        String query="INSERT INTO `korisnik`(`ime`, `lozinka`, `tip`) VALUES ('"+sIme+"','"+sLozinka+"','"+novi+"')";
                
                                        int varijabla=upit.executeUpdate(query);
                
                                        upit.close();
                                        kon.odspoji();
                
                                } catch (SQLException ex) {
                                        Logger.getLogger(KreirajController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
                        }else{                          //ako se lozinke ne podudaraju ispiši poruku:
                            poruka.setText("Lozinke se ne podudaraju!");
                        }
                }                           
        }
    }
    
    
    
    
    private boolean provjeraKorisnika(String string){
        //ako postoji predmet sa istim imenom, vrati true
        kon.spoji();
        
        try {
            this.upit=kon.konekcija.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(KreirajController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query="SELECT * FROM korisnik";
        try {
            ResultSet rs=upit.executeQuery(query);
            while(rs.next()){
                if(rs.getString(2).equals(string)){
                    return true;
                }
                
            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(KreirajController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
    
    
}
