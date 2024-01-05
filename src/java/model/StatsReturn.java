package model;

import java.sql.*;

public class StatsReturn {

    public StatsReturn() {}

    private String max(int a, int b, int c, int d) {
        String color;
        int max = a;
        color="RED";
        if (b > max) {
            max = b;
            color="YELLOW";
        }
        if (c > max) {
            max = c;
            color="GREEN";
        }
        if (d > max) {
            max = d;
            color="BLUE";
        }
        return color;
    }

    public int GamesWon() throws SQLException {
        String url="jdbc:sqlite:uid.db";
        Connection con = DriverManager.getConnection(url);
        int ris=0;
        if(con!=null && !con.isClosed()) {
            String query = "SELECT COUNT(*) as c FROM partita WHERE username=? and stato=2;";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, Constants.USER);
            ResultSet rs = stmt.executeQuery();
            ris = rs.getInt("c");
            con.close();
        }
        return ris;
    }

    public int GamesLost() throws SQLException {
        String url="jdbc:sqlite:uid.db";
        Connection con = DriverManager.getConnection(url);
        int ris=0;
        if(con!=null && !con.isClosed()) {
            String query = "SELECT COUNT(*) as c FROM partita WHERE username=? and stato=1;";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, Constants.USER);
            ResultSet rs = stmt.executeQuery();
            ris = rs.getInt("c");
            con.close();
        }
        return ris;
    }

    public int GamesPlayed() throws SQLException {
        return GamesWon()+GamesLost();
    }

    public String MostColorUsed() throws SQLException {
        String url="jdbc:sqlite:uid.db";
        Connection con = DriverManager.getConnection(url);
        int rossi, gialli, verdi, blu;
        String ris="";
        if(con!=null && !con.isClosed()) {
            String query = "SELECT sum(rossi) as r, sum(gialli) as g, sum(verdi) as v, sum(blu) as b FROM partita WHERE username=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, Constants.USER);
            ResultSet rs = stmt.executeQuery();
            rossi = rs.getInt("r");
            gialli = rs.getInt("g");
            verdi = rs.getInt("v");
            blu = rs.getInt("b");
            con.close();
            ris=max(rossi, gialli, verdi, blu);
        }
        return ris;
    }

    public int TotalTimeGame() throws SQLException
    {
        String url="jdbc:sqlite:uid.db";
        Connection con = DriverManager.getConnection(url);
        int ris=0;
        if(con!=null && !con.isClosed()) {
            String query = "SELECT sum(numMinuti) as c FROM partita WHERE username=?;";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, Constants.USER);
            ResultSet rs = stmt.executeQuery();
            ris = rs.getInt("c");
            con.close();
        }
        return ris;
    }


}
