 import java.sql.Statement;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 import java.lang.Math;
 import java.util.Scanner;

public class basicCardGame {


    public static void main (String[] args){

        List<card> StarterDeck = new ArrayList<>();
       // StarterDeck = createDeck();
       // shuffleCards(StarterDeck);
       // printDeck(StarterDeck);
       // player1Hand(StarterDeck);
       // player2Hand(StarterDeck);
       // findPairs(player1Hand(StarterDeck));
       // findPairs(player2Hand(StarterDeck));

        StarterDeck = createDeck();

        shuffleCards(StarterDeck);
        shuffleCards(StarterDeck);
        shuffleCards(StarterDeck);

        gameOfBlackJack(StarterDeck);

    }

    public static ArrayList<card> createDeck(){

         ArrayList<card> GameDeck = new ArrayList<>();

       // String[] cardRanks = new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
       // Integer[] cardRanksInt = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13};

        String[] cardRanks = new String[]{"A","2","A","4","A","A","A","A","A","10","J","Q","K"};
        Integer[] cardRanksInt = new Integer[]{1,2,1,4,1,1,1,1,1,10,11,12,13};


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

    public static List<card> blackJackFaceCard(List<card> someHand){
        for(card temp : someHand){
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
        return someHand;
    }


    public static void gameOfBlackJack(List<card> someDeck){
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        Boolean aceinHand = false;
        Boolean aceinHouse = false;
        Boolean doubleaces = false;
        Boolean goforsplit = false;
        Integer handValue = 0;
        Integer handValueSplit = 0;
        Integer houseValue = 0;
        Scanner scan = new Scanner(System.in);
        List<card> currentHand = new ArrayList();
        List<card> currentHandSplit = new ArrayList<>();
        List<card> houseHand = new ArrayList();
        System.out.println("");
        System.out.println("Let's play some Blackjack!");

        /*
                BEFORE PLAY SECTION
         */

        //creating players hand
        for(int i =0; i < 2; i++){

            if(someDeck.get(i).getRankInt().equals(1)){
                if(aceinHand == false){
                    handValue = handValue +10;
                    aceinHand = true;
                }
                else{
                    doubleaces = true;
                }

            }
            currentHand.add(someDeck.get(i));

            someDeck.remove(i);
        }
        //creating house hand
        for(int i =0; i < 2; i++){
            if(someDeck.get(i).getRankInt().equals(1)){
                if(aceinHouse == false){
                    houseValue = houseValue + 10;
                    aceinHouse = true;
                }

            }
            houseHand.add(someDeck.get(i));
            someDeck.remove(i);
        }

        //check if we have an ace

        /*
        if(currentHand.get(0).getRankInt().equals(1) || currentHand.get(1).getRankInt().equals(1)){
            System.out.println("We got an Ace!");
            aceinHand = true;
            handValue += 10;
        }
        */


        blackJackFaceCard(currentHand);
        blackJackFaceCard(houseHand);



        handValue = handValue + currentHand.get(0).getRankInt() + currentHand.get(1).getRankInt();
        houseValue = houseValue + houseHand.get(0).getRankInt() + houseHand.get(1).getRankInt();


        /*
                PLAY SECTION
        */
        // we give the user information about their hand
        System.out.println("Current hand : " + currentHand.get(0).getCard() +  "\t" + currentHand.get(1).getCard() + "");
        System.out.println("Current value: " + handValue);
        System.out.println("_-_-_-_-_-_-_-");

        if(doubleaces == true){
            System.out.println("DOUBLE ACES: Do you want to split? \' type y/n \'");
            String answer = scan.nextLine();
            if(answer.equals("y") || answer.equals("Y")){
                goforsplit = true;
            }


        }
        if(goforsplit == true){
            System.out.println(" Play or stay SPLIT? \' type y/n \'");
            String answer = scan.nextLine();

            System.out.println("answer is : " + answer);
            while(answer.equals("y") || answer.equals("Y")){

                currentHandSplit.add(currentHand.get(0));
                currentHand.remove(0);

                currentHand.add(someDeck.get(0));
                currentHandSplit.add(someDeck.get(1));

                blackJackFaceCard(currentHand);
                blackJackFaceCard(currentHandSplit);

                handValue += someDeck.get(0).getRankInt();
                handValueSplit += someDeck.get(1).getRankInt();

                System.out.print("Split: RIGHT:");
                for(card temp : currentHand){
                    System.out.print(temp.getCard());
                }
                System.out.print("\t LEFT:");
                for(card temp : currentHandSplit){
                    System.out.print(temp.getCard());
                }

                someDeck.remove(0);
                someDeck.remove(1);
                System.out.println("");
                System.out.println("currentHand: " + handValue);
                System.out.println("currentHandSPLIT: " + handValueSplit);
                System.out.println(" Play or stay? \' type y/n \'");
                answer = scan.next();

            }
            scan.close();
        }
        else {

            System.out.println(" Play or stay? \' type y/n \'");
            String answer = scan.nextLine();

            System.out.println("answer is : " + answer);
            while (answer.equals("y") || answer.equals("Y")) {
                System.out.println("_-_-_-_-_-_-_-");
                currentHand.add(someDeck.get(0));

                blackJackFaceCard(currentHand);
                handValue += someDeck.get(0).getRankInt();
                someDeck.remove(0);


                if (handValue > 21 && aceinHand == true) {
                    handValue = handValue - 10;
                }

                System.out.print("Current hand:   ");
                for (card temp : currentHand) {
                    System.out.print(temp.getCard());
                }
                System.out.println("");
                System.out.println("Current value: " + handValue);

                if (handValue > 21) {
                    System.out.println("Bust: You lose");
                    break;
                }
                //System.out.println("houseHand :  " + houseValue);
                System.out.println("currentHand: " + handValue);
                System.out.println(" Play or stay? \' type y/n \'");
                answer = scan.next();


            }

            if (handValue > houseValue && handValue < 22) {
                System.out.print("houseHand  : " + houseValue + "\t");
                System.out.println(houseHand.get(0).getCard() + " " + houseHand.get(1).getCard());
                System.out.print("currentHand: " + handValue + "\t");
                for (card temp : currentHand) {
                    System.out.print(temp.getCard() + " ");
                }
                System.out.println("");
                System.out.println("You Win");
            } else {
                System.out.print("houseHand  : " + houseValue + "\t");
                System.out.println(houseHand.get(0).getCard() + " " + houseHand.get(1).getCard());
                System.out.print("currentHand: " + handValue + "\t");
                for (card temp : currentHand) {
                    System.out.print(temp.getCard() + " ");
                }
                System.out.println("");

                System.out.println("You Lose");
            }
            scan.close();
        }
    }

}
