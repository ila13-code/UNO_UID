package model;

public class Constants {

    public static String USER = null;

    public static String skin=null;

    public static boolean isDaltonic=false;

    public Constants()
    {
        SaveInDB s=new SaveInDB();
        skin=s.getCover();
        isDaltonic=s.getIsDaltonic();
    }

    public void setUsername(String us)
    {
        USER=us;
    }
    public void setSkin(String s){skin=s;}
    public void setIsDaltonic(boolean b) {isDaltonic=b;}

}
