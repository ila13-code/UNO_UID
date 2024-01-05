package model;

import view.PlayerFieldView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GamePvsCPU
{
    private PlayerFieldView PvsCPU;
    private Deck d;
    private CardsInHand cards;
    private CardsInHand opponentCards;

    private Card currentCard;
    private boolean isTurnOf; //true se è il turno del giocatore, false se è quello della CPU

    private boolean PlayerOne; //true se il giocatore ha fatto UNO


    private int state; //0 se la partita è ancora in corso, 1 se ha vinto la CPU, 2 se ha vinto il giocatore

    private int redCards;
    private int yellowCards;
    private int greenCards;
    private int blueCards;

    public GamePvsCPU() throws IOException {
        d=new Deck();
        cards=new CardsInHand(d);
        opponentCards=new CardsInHand(d);
        yellowCards=0;
        redCards=0;
        blueCards=0;
        greenCards=0;
        state=0;
        for(int k=0; k<=6; k++) //entrambi i giocatori hanno in mano, a inizio partita, 7 carte.
        {
            cards.addCasualCard();
            opponentCards.addCasualCard();
        }
        currentCard=d.generateCasualCard(); //la carta da cui parte la partita.. (non può essere speciale o bonus)..

    }


    public CardsInHand getCards() {
        return cards;
    }

    private void setColor(String c)
    {
        switch (c)
        {
            case "rosso": redCards++; break;
            case "giallo": yellowCards++; break;
            case "blu": blueCards++; break;
            case "verde": greenCards++; break;
            default: break;
        }
    }

    public int SelectionIsValid(Card selectionCard)
    {
        if(selectionCard.getBonus()) {
            return 2;
        }
        else if(selectionCard.getColor().equals(currentCard.getColor()) || selectionCard.getNumber().equals(currentCard.getNumber())) {
            if(selectionCard.getSpecial())
                return 3;
            return 1;
        }
        return 0;
    }

    public void drawPCard() //il giocatore pesca una nuova carta
    {
        cards.addCasualCard();
    }

    public void drawCPUcard() //la cpu pesca una nuova carta
    {
        opponentCards.addCasualCard();
    }

    public int PlayerDropCard(Card c)
    {
        int ris = SelectionIsValid(c);
        if(ris==1 || ris==3)
            setColor(c.getColor());
        if(cards.size()-1==0)
            state=2;
        else if(d.size()==0 && opponentCards.size()<=cards.size())
            state=2;
        if (ris == 2) //carta bonus con effetto +4
        {
            if (c.getName().equals("+4")) {
                opponentCards.addCasualCard();
                opponentCards.addCasualCard();
                opponentCards.addCasualCard();
                opponentCards.addCasualCard();
            }
        }else if (ris == 1) {
            if (c.getNumber().equals("+2")) {
                opponentCards.addCasualCard();
                opponentCards.addCasualCard();
            }
        } else if (ris == 3) //carte speciali con effetti: stop, cambia giro
            isTurnOf = true; //se è tiro stop devo ritirare
        return ris;
    }

    public boolean PlayerCanPlay()  //controllo se il giocatore ha una carta da lanciare, altrimenti DEVE pescare
    {
        for(int k=0; k<=cards.size()-1; k++)
        {
            if(SelectionIsValid(cards.get(k))!=0)
                return true;
        }
        return false;
    }

    private boolean checkCard(Card type)
    {
        boolean b=false;
        int ris=SelectionIsValid(type);
        if(ris!=0) {
            if (ris == 2) {
                Random r = new Random();
                switch (r.nextInt(0, 3)) {
                    case 0: {
                        type.setColorBonus("giallo");
                    }
                    break;
                    case 1: {
                        type.setColorBonus("verde");
                    }
                    break;
                    case 2: {
                        type.setColorBonus("blu");
                    }
                    break;
                    case 3: {
                        type.setColorBonus("rosso");
                    }
                    break;
                    default:
                        break;
                }
            }
            opponentCards.removeCard(type);
            currentCard = type;
            if (type.getNumber().equals("+2")) {
                cards.addCasualCard();
                cards.addCasualCard();
            }
            else if (type.getName().equals("+4")) {
                cards.addCasualCard();
                cards.addCasualCard();
                cards.addCasualCard();
                cards.addCasualCard();
            }
            isTurnOf = true;
            if(type.getNumber().equals("stop") || type.getNumber().equals("cambia"))
                isTurnOf=false;
            b=true;
        }
        return b;
    }

    public boolean CPUdropCard() //la cpu lancia una carta
    {
        for (int k = 0; k <= opponentCards.size() - 1; k++) {
            Card type = opponentCards.get(k);
            if (checkCard(type)) {
                if(opponentCards.size()==0)
                    state=1;
                else if(d.size()==0 && opponentCards.size()>cards.size())
                    state=1;
                return true; //ho trovato una carta valida...
            }
        }
        //se il for finisce e non ho fatto return perchè CPU non ha carte valide
        drawCPUcard(); //CPU pesca una carta in automatico...
        if (checkCard(opponentCards.get(opponentCards.size() - 1))) //controllo se la carta pescata è valida...
            return true; //la "nuova carta" è valida e faccio return...
        //se non faccio return nessuna delle carte in mano a CPU è valida, nemmeno la "nuova"
        //il turno passa al giocatore...

        isTurnOf = true;
        return false;
    }

    public int getSizeOpponentCards()
    {
        return opponentCards.size();
    }

    public boolean getIsTurnOf()
    {
        return isTurnOf;
    }

    public void setIsTurnOf(boolean i)
    {
        isTurnOf=i;
    }

    public Card getCurrentCard()
    {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public boolean setPlayerOne()
    {
        if(cards.size()==1) //se non ha davvero fatto uno PlayerOne rimane false..
            PlayerOne = true;
        else PlayerOne=false;
        return PlayerOne;
    }

    public int GetEndGame()
    {
        return state;
    }

    public boolean isPlayerOne() {return cards.size()==1;}
    public boolean isCPUone() {return opponentCards.size()==1;}

    public ArrayList<Integer> getColorPlayed()
    {
        ArrayList<Integer> ris=new ArrayList<>();
        ris.add(redCards);
        ris.add(greenCards);
        ris.add(yellowCards);
        ris.add(blueCards);
        return ris;
    }
}
