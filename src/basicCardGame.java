 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 import java.lang.Math;

public class basicCardGame {


    public static void main (String[] args){

        List<card> StarterDeck = new ArrayList<>();
        StarterDeck = createDeck();
        shuffleCards(StarterDeck);
        printDeck(StarterDeck);
        player1Hand(StarterDeck);
        player2Hand(StarterDeck);

    }

    public static ArrayList<card> createDeck(){

         ArrayList<card> GameDeck = new ArrayList<>();

        String[] cardRanks = new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};


        for(int i = 0; i <13; i++){
            card newCard1 =  new card("Spade", cardRanks[i]);
            GameDeck.add(newCard1);
            card newCard2 =  new card("Club", cardRanks[i]);
            GameDeck.add(newCard2);
            card newCard3 =  new card("Diamond", cardRanks[i]);
            GameDeck.add(newCard3);
            card newCard4 =  new card("Heart", cardRanks[i]);
            GameDeck.add(newCard4);
        }

        return GameDeck;
    }

    public static List<card> shuffleCards(List<card> someDeck){
        for(int i = 0; i < 52; i ++){
            int x = (int)(Math.random()*51);
            int y = (int)(Math.random()*51);
            if (x==y){
                y =(int)(Math.random()*51);
            }
            Collections.swap(someDeck, x, y);
        }

        return someDeck;
    }

    public static void printDeck(List<card> someDeck){
        for(card temp : someDeck){
            System.out.print("["+temp.getRank()+","+temp.getSuit()+"]"+"  ");
        }
    }

    public static List<card> player1Hand(List<card> someDeck){
        List<card> playerHand = new ArrayList<>();

        for(int i =0; i < 7; i++){
            playerHand.add(someDeck.get(i));
            someDeck.remove(i);
        }
        System.out.println(" ");
        System.out.println("Some deck length: " + someDeck.size());
        for(int i = 0; i < playerHand.size(); i++){
            System.out.print("["+ playerHand.get(i).getRank()+","+ playerHand.get(i).getSuit()+"]");
        }

        return playerHand;
    }
    public static List<card> player2Hand(List<card> someDeck){
        List<card> playerHand = new ArrayList<>();

        for(int i =0; i < 7; i++){
            playerHand.add(someDeck.get(i));
            someDeck.remove(i);
        }
        System.out.println(" ");
        System.out.println("Some deck length: " + someDeck.size());
        for(int i = 0; i < playerHand.size(); i++){
            System.out.print("["+ playerHand.get(i).getRank()+","+ playerHand.get(i).getSuit()+"]");
        }

        return playerHand;
    }


}
