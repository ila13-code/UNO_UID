package model;

public class Card
{
    private String name;
    private String color;
    private String number;
    private Boolean bonus;

    private Boolean special;

    public Card(String n)
    {
        color="";
        number="";
        bonus=false;
        special=false;
        name=n;
        if(Character.isDigit(name.charAt(0)))
        {
            number=String.valueOf(name.charAt(0));
            color=name.substring(1);
        }
        else if(name.equals("+4")||name.equals("cambiaColore"))
            bonus=true;
        else if(name.charAt(0)=='s')
        {
            number=name.substring(0,4);
            color=name.substring(4);
            special=true;
        }
        else if(name.charAt(0)=='c')
        {
            number=name.substring(0,6);
            color=name.substring(6);
            special=true;
        }
        else if(name.charAt(0)=='+')
        {
            number=name.substring(0,2);
            color=name.substring(2);
        }
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public Boolean getBonus() {
        return bonus;
    }

    public Boolean getSpecial() {
        return special;
    }

    public void setColorBonus(String c){
        bonus=true;
        color=c;
    }
}
