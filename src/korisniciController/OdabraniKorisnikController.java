
package korisniciController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import baza.konekcija;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projektkafic.ProjektKafic;



public class OdabraniKorisnikController implements Initializable {
    konekcija kon=new konekcija();
    Statement upit;
    
    
    
    @FXML
    private Label ime;
    @FXML
    private Label lozinka;
    @FXML
    private Label tip;
    @FXML
    private TextField novoIme;
    @FXML
    private TextField novaLozinka;
    @FXML
    private Label poruka;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ime.setText(ProjektKafic.odabraniKorisnik.getIme());
        lozinka.setText(ProjektKafic.odabraniKorisnik.getLozinka());
        tip.setText(ProjektKafic.odabraniKorisnik.getTip());
        
    }    

    @FXML
    private void potvrdi(ActionEvent event) {
        
        String Ime="";
        String Lozinka="";
        if(novoIme.getText().equals("")){
            Ime=ProjektKafic.odabraniKorisnik.getIme();
        }else{
            Ime=novoIme.getText();
        }
        if(novaLozinka.getText().equals("")){
            Lozinka=ProjektKafic.odabraniKorisnik.getLozinka();
        }else{
            Lozinka=novaLozinka.getText();
        }
        
        
        
        kon.spoji();
        String sqlUpit="UPDATE `korisnik` SET `ime`='"
                +Ime+"',`lozinka`='"
                +Lozinka+"' WHERE id='"
                +ProjektKafic.odabraniKorisnik.getId()+"'";
        
        try {
            upit=kon.konekcija.createStatement();
        } catch (SQLException ex) {
            System.out.println("Nije se mogao kreirati upit!");
        }
        try {
            int povratnaVrijenost=upit.executeUpdate(sqlUpit);
            
            
            poruka.setText("Uspješno ste unijeli izmjene!");
            
            
            novoIme.clear();
            novaLozinka.clear();
            
            ProjektKafic.odabraniKorisnik.setIme(Ime);
            ProjektKafic.odabraniKorisnik.setLozinka(Lozinka);
            
            ime.setText(ProjektKafic.odabraniKorisnik.getIme());
            lozinka.setText(ProjektKafic.odabraniKorisnik.getLozinka());
            
            
            upit.close();
            kon.odspoji();
        } catch (SQLException ex) {
            System.out.println("Nije se mogao izvršiti upit!");
        }
        
    }
    
    @FXML
    private void izbriši(ActionEvent e){
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
                                        Logger.getLogger(OdabraniKorisnikController.class.getName()).log(Level.SEVERE, null, ex);
                                }
            
                                try {
                                        String query="DELETE FROM `korisnik` WHERE id='"+ProjektKafic.odabraniKorisnik.getId()+"'";
                
                                        int varijabla=upit.executeUpdate(query);
                
                                        upit.close();
                                        kon.odspoji();
                
                                } catch (SQLException ex) {
                                        Logger.getLogger(OdabraniKorisnikController.class.getName()).log(Level.SEVERE, null, ex);
                                }
            
                                        stage.close();
                                        final Node source = (Node) e.getSource();
                                        final Stage sstage = (Stage) source.getScene().getWindow();
                                        sstage.close();
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
