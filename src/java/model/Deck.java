package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Deck extends ArrayList<Card>
{
    public Deck()
    {
        this.add(new Card("0rosso"));
        this.add(new Card("0verde"));
        this.add(new Card("0giallo"));
        this.add(new Card("0blu"));

        for(int k=0; k<=3; k++)
        {
            this.add(new Card("cambiaColore"));
            this.add(new Card("+4"));
        }

        String color="";
        for(int j=0; j<=1; j++)
            for(int k1=0; k1<=3; k1++)
            {
                if(k1==0)
                    color="rosso";
                else if(k1==1)
                    color="verde";
                else if(k1==2)
                    color="giallo";
                else
                    color="blu";
                for (int k = 1; k <= 12; k++)
                {
                    if(k<=9)
                        this.add(new Card(k+color));
                    else if(k==10)
                        this.add(new Card("stop"+color));
                    else if(k==11)
                        this.add(new Card("cambia"+color));
                    else
                        this.add(new Card("+2"+color));
                }
            }
    }

    public Card generateCasualCard() //metodo che uso per generare la carta a inizio gioco; la prima carta nel "piatto"...
    {
        Random r=new Random();
        int ris=0;
        do {
            ris=r.nextInt(0, this.size()-1);
        }while(this.get(ris).getBonus() || this.get(ris).getSpecial());
        //se non è un bonus o una carta "speciale" (cambiaGiro, stop, +2), esce dal while; la carta "pescata" va bene...
        this.remove(ris);
        return this.get(ris);
    }


}
