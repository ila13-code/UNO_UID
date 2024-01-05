package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CardsInHand extends ArrayList<Card>
{
    private Deck myDeck;
    public CardsInHand(Deck d) //aggiunge casualmente 7 carte in mano al giocatore appena inizia la partita...
    {
        myDeck=d;
    }

    public void addCasualCard() //pesca una carta casualmente e l'aggiunge alla mano del giocatore...
    {
        Random r=new Random();
        int ris=r.nextInt(1, myDeck.size());
        this.add(myDeck.get(ris));
        myDeck.remove(ris);
    }


    public void removeCard(Card type)
    {
        for(int k=0; k<=this.size()-1; k++)
        {
            if(this.get(k).getName()==type.getName()) {
                this.remove(k);
                break;
            }
        }
    }

}
