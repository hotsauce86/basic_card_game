 import java.sql.Statement;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 import java.lang.Math;
 import java.util.Scanner;

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

        StarterDeck = createDeck();

        shuffleCards(StarterDeck);
        shuffleCards(StarterDeck);
        shuffleCards(StarterDeck);

        gameOfBlackJack( StarterDeck);

    }

    public static ArrayList<card> createDeck(){

         ArrayList<card> GameDeck = new ArrayList<>();

        String[] cardRanks = new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        Integer[] cardRanksInt = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13};


        for(int i = 0; i <13; i++){
            card newCard1 =  new card("\u2660", cardRanks[i], cardRanksInt[i]);
            GameDeck.add(newCard1);
            card newCard2 =  new card("\u2663", cardRanks[i], cardRanksInt[i]);
            GameDeck.add(newCard2);
            card newCard3 =  new card("\u2666", cardRanks[i], cardRanksInt[i]);
            GameDeck.add(newCard3);
            card newCard4 =  new card("\u2665", cardRanks[i], cardRanksInt[i]);
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
            //System.out.print("["+temp.getRank()+","+temp.getSuit()+"]"+"  ");

            System.out.print(temp.getCard() + ", ");
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
                        listOfPairs.add("[" + tempCard.getRank()+"," +tempCard.getSuit()+":"+somePlayerCopy.get(i).getRank()+","+somePlayerCopy.get(i).getSuit()+"]");
                    }
                }
                seti++;
            }
        System.out.println(listOfPairs);
        return listOfPairs;
    }

    public static void gameOfBlackJack(List<card> someDeck){
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        Boolean aceinHand = false;
        Integer handValue = 0;
        Scanner scan = new Scanner(System.in);
        List<card> currentHand = new ArrayList();
        List<card> houseHand = new ArrayList();
        System.out.println("");
        System.out.println("Let's play some Blackjack!");

        //creating players hand
        for(int i =0; i < 2; i++){

            currentHand.add(someDeck.get(i));
            someDeck.remove(i);
        }
        //creating house hand
        for(int i =0; i < 2; i++){
            houseHand.add(someDeck.get(i));
            someDeck.remove(i);
        }

        //check if we have an ace
        if(currentHand.get(0).getRankInt() == 1 || currentHand.get(1).getRankInt() == 1 ){
            aceinHand = true;
        }

        //change face values to ten
        for(card temp : currentHand){
            if(temp.getRank().equals("K")){
                temp.setRankInt(10);
            }
            if(temp.getRank().equals("Q")){
                temp.setRankInt(10);
            }
            if(temp.getRank().equals("J")){
                temp.setRankInt(10);
            }
        }
        for(card temp : houseHand){
            if(temp.getRank().equals("K")){
                temp.setRankInt(10);
            }
            if(temp.getRank().equals("Q")){
                temp.setRankInt(10);
            }
            if(temp.getRank().equals("J")){
                temp.setRankInt(10);
            }
        }


        handValue = currentHand.get(0).getRankInt() + currentHand.get(1).getRankInt();



        System.out.println("Current hand:   [" + currentHand.get(0).getCard() +  "]   {" + currentHand.get(1).getCard() + "]");
        System.out.println("Current value: " + handValue);
        System.out.println("_-_-_-_-_-_-_-");
        System.out.println(" Play or stay? \' type y/n \'");
        String firstGuess = scan.next();

        if(firstGuess.equals("y")){
            //// WE ADD A CARD///////////
            currentHand.add(someDeck.get(0));
            someDeck.remove(0);

            System.out.println("Current hand:   [" + currentHand.get(0).getCard() +  "]   [" + currentHand.get(1).getCard() + "]   ["+ currentHand.get(2).getCard() +"]");
            if(currentHand.get(0).getRank() == "A" || currentHand.get(1).getRank() == "A"){
                System.out.println("Current value: " + (currentHand.get(0).getRankInt() + currentHand.get(1).getRankInt() + currentHand.get(2).getRankInt())  + " : " + (currentHand.get(0).getRankInt() + currentHand.get(1).getRankInt() + currentHand.get(2).getRankInt() + 10));
            }
            else{
                System.out.println("Current value: " + (currentHand.get(0).getRankInt( )+ currentHand.get(1).getRankInt() + currentHand.get(2).getRankInt()));
            }


            System.out.println("Comparing:...");
            System.out.println("your hand : " + (currentHand.get(0).getRankInt() + currentHand.get(1).getRankInt()));
            System.out.println("house hand: " + (houseHand.get(0).getRankInt() + houseHand.get(1).getRankInt()));

            if((currentHand.get(0).getRankInt() + currentHand.get(1).getRankInt()  + currentHand.get(2).getRankInt()) > (houseHand.get(0).getRankInt() + houseHand.get(1).getRankInt())){
                System.out.println("You Win!!!");
            }
            else{
                System.out.println("You Lose, Game Over");
            }

        }

    }

}
