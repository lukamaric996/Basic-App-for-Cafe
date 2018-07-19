
package baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class konekcija {
    public Connection konekcija;
    
    
    public void spoji(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.konekcija=DriverManager.getConnection("jdbc:mysql://localhost:3306/kafic?zeroDateTimeBehavior=convertToNull","root","");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Sustav nije mogao naći klasu");
        } catch (SQLException ex) {
            System.out.println("Sustav nije uspio ostvariti konekciju");
        }
        
    }
    public void odspoji(){
        try {
            this.konekcija.close();
        } catch (SQLException ex) {
            System.out.println("Sustav se nije uspio odspojiti");
            
        }
    }
    
}
