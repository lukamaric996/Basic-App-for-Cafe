
package logInController;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.sql.ResultSet;
import baza.konekcija;

import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import projektkafic.ProjektKafic;
import java.io.IOException;

public class LogInController implements Initializable {
    konekcija kon=new konekcija();
    public Statement upit;
    
    @FXML
    private TextField ime;
    @FXML
    private PasswordField lozinka;
    @FXML
    private Label poruka;
    
    
    @FXML
    private void prijava(ActionEvent e) throws IOException{
        String sIme=ime.getText();
        String sLozinka=lozinka.getText();
        if(sIme.equals("")||sLozinka.equals("")){
            poruka.setText("Morate unijeti oba polja!");
        }else{
        kon.spoji();
        try {
            this.upit=kon.konekcija.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Nije se uspio kreirati upit!");
        }
        try {
            String string="SELECT * FROM korisnik where ime='"+sIme+"'";
            ResultSet rs=this.upit.executeQuery(string);
            rs.next();
                
                
                if(sLozinka.equals(rs.getString(3))){
                    
                        ProjektKafic.korisnik=sIme;
                        ProjektKafic.tipKorisnika=rs.getString(4);
                        ProjektKafic.layoutMenu();
                       
                }else{
                    poruka.setText("Pogrešno ste unijeli lozinku!");
                }
                             
            upit.close();
            kon.odspoji();
        } catch (SQLException ex) {
            //Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            
            poruka.setText("Korisnik sa tim imenom ne postoji");
            
        }
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

