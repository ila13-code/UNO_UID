package model;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.regex.Pattern;

public class Registration {

    //private String cod;
    public Registration() {}

    /*public String getCod()
    {
        return cod;
    }*/

    public boolean checkUsername(String username) throws SQLException {
        String url="jdbc:sqlite:uid.db";
        Connection con = DriverManager.getConnection(url);
        boolean res=false;
        if(con!=null && !con.isClosed())
        {
            String query="SELECT count(*) as c FROM utente WHERE username=?;";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet ris=stmt.executeQuery();
            res=ris.getBoolean("c");
            con.close();
        }
        return res;
    }

    public boolean checkEmail(String email) throws SQLException {
        if (Pattern.matches("^[A-Za-z0-9]+(\\.||\\S)*[A-Za-z0-9]*@[A-Za-z0-9]+(\\.)*([A-Za-z0-9])*\\.[A-Za-z]{2,}$", email)) {
            boolean rs = false;
            String url = "jdbc:sqlite:uid.db";
            Connection con = DriverManager.getConnection(url);
            if (con != null && !con.isClosed()) {
                String query = "SELECT count(*) as c FROM utente WHERE email=?;";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, email);
                ResultSet ris = stmt.executeQuery();
                rs=ris.getBoolean("c");
                con.close();
            }
            return !rs;
        }
        return false;
    }

    public boolean checkPassword(String password)
    {
        return Pattern.matches("^(?=.*[A-Z])(?=.*[().!_\\%$£&?\\-^*@#]).{8,}$", password);
    }

    public int add(String nome, String cognome, Date data, String email,String username, String Password) throws SQLException {
        if(checkPassword(Password))
        {
            if(!checkUsername(username))
            {
                if(checkEmail(email))
                {
                    String url = "jdbc:sqlite:uid.db";
                    Connection con = DriverManager.getConnection(url);
                    //cod= UUID.randomUUID().toString();
                    //cod è un codice univoco; da implementare IN CASO per la verifica dell'email.. (da aggiungere anche alla query per inserirlo nel db)
                    if (con != null && !con.isClosed())
                    {
                        String query = "INSERT INTO utente values(?,?,?,?,?,?,?,?);";
                        PreparedStatement stmt = con.prepareStatement(query);
                        stmt.setString(1, username);
                        stmt.setString(2, nome);
                        stmt.setString(3, cognome);
                        stmt.setDate(4, data);
                        stmt.setString(5, email);
                        stmt.setString(6, BCrypt.hashpw(Password, BCrypt.gensalt(12)));
                        stmt.setString(7, "skin.png"); //inizialmente ha la skin normale
                        stmt.setBoolean(8, false); //inizialmente si presume che l'utente non sia daltonico
                        stmt.execute();
                        con.close();
                        return 1;
                    }
                }
                else
                    return 4;
            }
            else
                return 3;
        }
        else
            return 2;
        return 0;
    }
}
