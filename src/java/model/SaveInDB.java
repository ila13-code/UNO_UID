package model;

import java.sql.*;

public class SaveInDB
{
    public SaveInDB()
    {}

    public void setIsDaltonic(boolean b) throws SQLException {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                String url = "jdbc:sqlite:uid.db";
                Connection con = null;
                try {
                    con = DriverManager.getConnection(url);
                    if (con != null && !con.isClosed()) {
                        String query = "UPDATE utente SET daltonico=? WHERE username=?";
                        PreparedStatement stmt = con.prepareStatement(query);
                        stmt.setBoolean(1, b);
                        stmt.setString(2, Constants.USER);
                        stmt.execute();
                        con.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }

    public boolean getIsDaltonic()
    {
        boolean ris=false;
        String url = "jdbc:sqlite:uid.db";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            if (con != null && !con.isClosed()) {
                String query = "SELECT daltonico FROM utente WHERE username=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, Constants.USER);
                ResultSet r=stmt.executeQuery();
                ris=r.getBoolean("daltonico");
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ris;
    }

    public void changeCover(String path) throws SQLException {
        String url = "jdbc:sqlite:uid.db";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            if (con != null && !con.isClosed()) {
                String query = "UPDATE utente SET skin=? WHERE username=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, path);
                stmt.setString(2, Constants.USER);
                stmt.execute();
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCover()
    {
        String ris="skin.png";
        String url = "jdbc:sqlite:uid.db";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            if (con != null && !con.isClosed()) {
                String query = "SELECT skin FROM utente WHERE username=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, Constants.USER);
                ResultSet r=stmt.executeQuery();
                ris=r.getString("skin");
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ris;
    }

    public void registerGame(int stato, int numMinuti, int redCard, int greenCard, int yellowCard, int blueCard) throws SQLException
    {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                String url = "jdbc:sqlite:uid.db";
                Connection con = null;
                try {
                    con = DriverManager.getConnection(url);
                    int ris = 0;
                    if (con != null && !con.isClosed()) {
                        String query = "INSERT INTO partita (stato, rossi, verdi, gialli, blu, numMinuti, username) VALUES (?,?,?,?,?,?,?);";
                        PreparedStatement stmt = con.prepareStatement(query);
                        stmt.setInt(1, stato);
                        stmt.setInt(2, redCard);
                        stmt.setInt(3, greenCard);
                        stmt.setInt(4, yellowCard);
                        stmt.setInt(5, blueCard);
                        stmt.setInt(6, numMinuti);
                        stmt.setString(7, Constants.USER);
                        stmt.execute();
                        con.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }


}