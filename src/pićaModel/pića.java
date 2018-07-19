
package pićaModel;

import baza.konekcija;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class pića {
    static konekcija kon=new konekcija();
    static Statement upit;
    
    //id vrsta naziv cijena
    private int id;
    private String vrsta;
    private String naziv;
    private double cijena;// ovdje
    
    //konstruktor:
    public pića(int id){
        
        kon.spoji();
        try {
            this.upit=kon.konekcija.createStatement();
        } catch (SQLException ex) {
            //Logger.getLogger(pića.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql="SELECT * FROM `napitak` WHERE id='"+id+"'";
        
        try {
            ResultSet rs=upit.executeQuery(sql);
            rs.next();
            this.id=rs.getInt(1);
            this.vrsta=rs.getString(2);
            this.naziv=rs.getString(3);
            this.cijena=rs.getDouble(4);//ovdje
            
            this.upit.close();
            kon.odspoji();
        } catch (SQLException ex) {
            //Logger.getLogger(pića.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    //geteri i seteri:
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    
    public String getVrsta (){
        return this.vrsta;
    }
    public void setVrsta (String vrsta){
        this.vrsta=vrsta;
    }
    
    public String getNaziv (){
        return this.naziv;
    }
    public void setNaziv (String naziv){
        this.naziv=naziv;
    }
    
    public double getCijena (){
        return this.cijena;
    }
    public void setCijena (double cijena){
        this.cijena=cijena;
    }
    
    
    public static ObservableList<pića> podatci(String string){
        ObservableList<pića> Podatci=FXCollections.observableArrayList();
        
        kon.spoji();
        try {
            upit=kon.konekcija.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(pića.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rs = upit.executeQuery(string);
            while(rs.next()){
            Podatci.add(new pića(rs.getInt(1)));
            }
            
            
            upit.close();
            kon.odspoji();
            
        } catch (SQLException ex) {
            Logger.getLogger(pića.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Funkcija za dobavljanje podataka u kontroleru nije uspjela izvršiti upit");
        }
        
        
        return Podatci;
    }
    
    
    
    public String toString(){
        return this.naziv;
    }
    
    
    
    
    
    
}

