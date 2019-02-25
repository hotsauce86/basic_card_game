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
       // player1Hand(StarterDeck);
       // player2Hand(StarterDeck);
        findPairs(player1Hand(StarterDeck));
        findPairs(player2Hand(StarterDeck));

    }

    public static ArrayList<card> createDeck(){

         ArrayList<card> GameDeck = new ArrayList<>();

        String[] cardRanks = new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        Integer[] cardRanksInt = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13};


        for(int i = 0; i <13; i++){
            card newCard1 =  new card("Spade", cardRanks[i], cardRanksInt[i]);
            GameDeck.add(newCard1);
            card newCard2 =  new card("Club", cardRanks[i], cardRanksInt[i]);
            GameDeck.add(newCard2);
            card newCard3 =  new card("Diamond", cardRanks[i], cardRanksInt[i]);
            GameDeck.add(newCard3);
            card newCard4 =  new card("Heart", cardRanks[i], cardRanksInt[i]);
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

    public static List<String> findPairs(List<card> somePlayer){
        System.out.println("/////////Checking for Pairs///////////");
        List<String> listOfPairs = new ArrayList<>();
        List<card> somePlayerCopy = somePlayer;
            card tempCard = somePlayerCopy.get(0);
            //skip the first one we already have it
            int seti = 1;
            for(int j = 0; j < somePlayerCopy.size()-1; j++){
                tempCard=somePlayerCopy.get(j);
                for(int i = seti; i < somePlayerCopy.size(); i++){
                    if(somePlayerCopy.get(i).getRankInt() == tempCard.getRankInt()){
                        listOfPairs.add("[Pair:" + tempCard.getRank()+"," +tempCard.getSuit()+":"+somePlayerCopy.get(i).getRank()+","+somePlayerCopy.get(i).getSuit()+"]");
                    }
                }
                seti++;
            }
        System.out.println(listOfPairs);
        return listOfPairs;
    }

}
