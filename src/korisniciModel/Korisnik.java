
package korisniciModel;
import baza.konekcija;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Korisnik {
    konekcija kon=new konekcija();
    Statement upit;
    
    
    private int id;
    private String ime;
    private String lozinka;
    private String tip;
    
    //konstruktor:
    public Korisnik(int id){
        kon.spoji();
        try {
            this.upit=kon.konekcija.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(Korisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql="SELECT * FROM `korisnik` WHERE id='"+id+"'";
        
        try {
            ResultSet rs=upit.executeQuery(sql);
            rs.next();
            this.id=rs.getInt(1);
            this.ime=rs.getString(2);
            this.lozinka=rs.getString(3);
            this.tip=rs.getString(4);
            
            this.upit.close();
            kon.odspoji();
        } catch (SQLException ex) {
            //Logger.getLogger(Korisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    //geteri i seteri:
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    
    public String getIme(){
        return this.ime;
    }
    public void setIme(String ime){
        this.ime=ime;
    }
    
    public String getLozinka(){
        return this.lozinka;
    }
    public void setLozinka(String lozinka){
        this.lozinka=lozinka;
    }
    
    public String getTip(){
        return this.tip;
    }
    public void setTip(String tip){
        this.tip=tip;
    }
    
    
    
}
