package model;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class CheckLogin
{
    public CheckLogin() {}
    public boolean check(String Password) throws SQLException {
        Constants c=new Constants();
        String url="jdbc:sqlite:uid.db";
        Connection con = DriverManager.getConnection(url);
        if(con!=null && !con.isClosed())
        {
            String query="SELECT password FROM utente WHERE username=?;";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setString(1, Constants.USER);
            ResultSet rs=stmt.executeQuery();
            String ris= rs.getString("password");
            con.close();
            boolean check=false;
            if(ris!=null)
                check=BCrypt.checkpw(Password,ris);
            return check;
        }
        return false;
    }
}
