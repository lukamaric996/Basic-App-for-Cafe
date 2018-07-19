
package pićaController;

import baza.konekcija;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import korisniciController.OdabraniKorisnikController;
import projektkafic.ProjektKafic;


public class OdabranoPićeController implements Initializable {
    konekcija kon=new konekcija();
    Statement upit;
    
    
    
    @FXML
    private Label naziv;
    @FXML
    private Label vrsta;
    @FXML
    private Label cijena;//
    @FXML
    private TextField noviNaziv;
    @FXML
    private TextField novaCijena;//
    @FXML
    private Label poruka;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        naziv.setText(ProjektKafic.odabranoPiće.getNaziv());
        vrsta.setText(ProjektKafic.odabranoPiće.getVrsta());
        cijena.setText(Double.toString(ProjektKafic.odabranoPiće.getCijena()));//ovdje
    }    

    @FXML
    private void izbriši(ActionEvent e) {
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
                                        String query="DELETE FROM `napitak` WHERE id='"+ProjektKafic.odabranoPiće.getId()+"'";
                
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

    @FXML
    private void potvrdi(ActionEvent event) {
        
        String Naziv="";
        double Cijena=0;//ovdje
        if(noviNaziv.getText().equals("")){
            Naziv=ProjektKafic.odabranoPiće.getNaziv();
        }else{
            Naziv=noviNaziv.getText();
        }
        if(novaCijena.getText().equals("")){
            Cijena=ProjektKafic.odabranoPiće.getCijena();
        }else{
            Cijena=Double.parseDouble(novaCijena.getText());//ovddje
        }
        
        
        
        kon.spoji();
        String sqlUpit="UPDATE `napitak` SET `naziv`='"
                +Naziv+"',`cijena`='"
                +Cijena+"' WHERE id='"
                +ProjektKafic.odabranoPiće.getId()+"'";
        
        try {
            upit=kon.konekcija.createStatement();
        } catch (SQLException ex) {
            System.out.println("Nije se mogao kreirati upit!");
        }
        try {
            int povratnaVrijenost=upit.executeUpdate(sqlUpit);
            
            //poruka:
            poruka.setText("Uspješno ste unijeli izmjene!");
            
            //čišćenje unosa:
            noviNaziv.clear();
            novaCijena.clear();
            //update statičnog objekta:
            ProjektKafic.odabranoPiće.setNaziv(Naziv);
            ProjektKafic.odabranoPiće.setCijena(Cijena);
            //update podataka u prozoru:
            naziv.setText(ProjektKafic.odabranoPiće.getNaziv());
            cijena.setText(Double.toString(ProjektKafic.odabranoPiće.getCijena()));
            
            
            upit.close();
            kon.odspoji();
        } catch (SQLException ex) {
            System.out.println("Nije se mogao izvršiti upit!");
        }
        
    }
    
}
