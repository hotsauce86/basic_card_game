 import javax.swing.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.sql.Statement;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 import java.lang.Math;
 import java.util.Scanner;
 import java.awt.*;
 import javax.swing.*;

public class basicCardGame{
    static JLabel somenewlabel =  new JLabel("");
    JButton button;
    static String hello = "";

    public void actionPerformed(javafx.event.ActionEvent event){
        somenewlabel.setText("wafflesmmmmmmmmmm");
        hello = "MMMMMMMMMMM";
    }
    private static String answer2 = "wtfellow";
    private static JLabel answerLabel;

    public static void gameWindow(){
        JFrame frame = new JFrame("blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel textLabelHand = new JLabel("Current Hand: ", SwingConstants.CENTER);
        JLabel textLabelHandSplit = new JLabel("...", SwingConstants.CENTER);
        JLabel textLabelHandValue = new JLabel("Hand Value: ", SwingConstants.CENTER);
        JLabel textLabelHandValueSplit = new JLabel("...", SwingConstants.CENTER);


        frame.setPreferredSize(new Dimension(640, 480));

        JButton buttonPlay = new JButton("Play");
        JButton buttonStay = new JButton("Stay");
        buttonPlay.setLayout(null);
        buttonStay.setLayout(null);
        buttonPlay.setSize(70,50);
        buttonStay.setSize(70,50);
        buttonPlay.setLocation(50,125);
        buttonStay.setLocation(150, 125);

        JPanel panel = new JPanel();
        panel.add(buttonPlay);
        panel.add(buttonStay);

        frame.add(panel);
        panel.add(textLabelHand, BorderLayout.CENTER);
        panel.add(textLabelHandSplit, BorderLayout.CENTER);
        panel.add(textLabelHandValue, BorderLayout.CENTER);
        panel.add(textLabelHandValueSplit, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);


        buttonPlay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("clicked play");
                somenewlabel.setText("y");
            }
        });

        buttonStay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("clicked stay");
                somenewlabel.setText("N");
            }
        });

    }

    public static void updateLables(){

    }




    public static void main (String[] args){

        JFrame frame = new JFrame("blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel textLabelHand = new JLabel("Current Hand: ", SwingConstants.CENTER);
        JLabel textLabelHandSplit = new JLabel("...", SwingConstants.CENTER);
        JLabel textLabelHandValue = new JLabel("Hand Value: ", SwingConstants.CENTER);
        JLabel textLabelHandValueSplit = new JLabel("...", SwingConstants.CENTER);


        frame.setPreferredSize(new Dimension(640, 480));

        JButton buttonPlay = new JButton("Play");
        JButton buttonStay = new JButton("Stay");
        buttonPlay.setLayout(null);
        buttonStay.setLayout(null);
        buttonPlay.setSize(70,50);
        buttonStay.setSize(70,50);
        buttonPlay.setLocation(50,125);
        buttonStay.setLocation(150, 125);

        JPanel panel = new JPanel();
        panel.add(buttonPlay);
        panel.add(buttonStay);

        frame.add(panel);
        panel.add(textLabelHand, BorderLayout.CENTER);
        panel.add(textLabelHandSplit, BorderLayout.CENTER);
        panel.add(textLabelHandValue, BorderLayout.CENTER);
        panel.add(textLabelHandValueSplit, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);


        List<card> StarterDeck = new ArrayList<>();
        StarterDeck = createDeck();
        shuffleCards(StarterDeck);
        shuffleCards(StarterDeck);
        shuffleCards(StarterDeck);
        // basic deck, starting cash $200, buy-in multipier level 1
        gameOfBlackJack(StarterDeck, 200, 1);

    }

    public static ArrayList<card> createDeck(){

         ArrayList<card> GameDeck = new ArrayList<>();

        String[] cardRanks = new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        Integer[] cardRanksInt = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13};

        // for split scenario
       // String[] cardRanks = new String[]{"2","4","2","4","2","4","2","4","2","4","2","4","2","4",};
        //Integer[] cardRanksInt = new Integer[]{2,4,2,4,2,4,2,4,2,4,2,4,2,4,};


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


    public static void gameOfBlackJack(List<card> someDeck, double wallet, double buyin){
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");

        Boolean aceinHand = false;
        Boolean aceinHouse = false;
        Boolean doublecards = false;
        Boolean goforsplit = false;
        Integer handValue = 0;
        Integer handValueSplit = 0;
        Integer houseValue = 0;
        double buyinlevel = buyin;
        double cash = wallet;
        String newgame = "";
        String answer = "";
        Scanner scan = new Scanner(System.in);
        List<card> currentHand = new ArrayList();
        List<card> currentHandSplit = new ArrayList<>();
        List<card> houseHand = new ArrayList();
        System.out.println("");
        System.out.println("Let's play some Blackjack!");
        System.out.println("level: " + buyinlevel);

        answer = somenewlabel.toString();
        /*
                BEFORE PLAY SECTION
         */
        if(wallet < 1){
            System.out.println("Hey, come back when you get some money huh.");

            System.exit(0);
        }
        //creating players hand
        for(int i =0; i < 2; i++){

            //make sure double aces dose not mean 22!
            if(someDeck.get(i).getRankInt().equals(1)){
                if(aceinHand == false){
                    handValue = handValue +10;
                    aceinHand = true;
                }
            }
            currentHand.add(someDeck.get(i));
            someDeck.remove(i);
        }
        if(currentHand.get(0).getRankInt() == currentHand.get(1).getRankInt()){
            doublecards =true;
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




        blackJackFaceCard(currentHand);
        blackJackFaceCard(houseHand);
        handValue = handValue + currentHand.get(0).getRankInt() + currentHand.get(1).getRankInt();
        houseValue = houseValue + houseHand.get(0).getRankInt() + houseHand.get(1).getRankInt();
        /*
                PLAY SECTION
        */
        // we give the user information about their hand
        System.out.println("Cash: " + cash  );
        System.out.println("Current hand : " + currentHand.get(0).getCard() +  "\t" + currentHand.get(1).getCard() + "");
        System.out.println("Current value: " + handValue);
        System.out.println("_-_-_-_-_-_-_-");



        if(doublecards == true){
            System.out.println("DOUBLE CARDS: Do you want to split? \' type y/n \'");
            answer = scan.nextLine();
            if(answer.equals("y") || answer.equals("Y")){
                goforsplit = true;
            }
        }
        if(goforsplit == true){



            currentHandSplit.add(currentHand.get(1));
            currentHand.remove(1);

            currentHand.add(someDeck.get(0));
            currentHandSplit.add(someDeck.get(1));
            someDeck.remove(0);
            someDeck.remove(1);

            blackJackFaceCard(currentHand);
            blackJackFaceCard(currentHandSplit);

            //left
            handValue = currentHand.get(0).getRankInt()+ someDeck.get(0).getRankInt();
            //right
            handValueSplit = currentHandSplit.get(0).getRankInt() +someDeck.get(1).getRankInt();
            System.out.println("Cash: " + cash  );
            System.out.println("Current hand  RIGHT: " + currentHand.get(0).getCard() +  "\t" + currentHand.get(1).getCard() + "");
            System.out.println("Current hand  LEFT : " + currentHandSplit.get(0).getCard() +  "\t" + currentHandSplit.get(1).getCard() + "");
            System.out.println("Current value RIGHT: " + handValue);
            System.out.println("Current value LEFT : " + handValueSplit);
            System.out.println("_-_-_-_-_-_-_-");
            System.out.println(" SPLIT : Play or stay? \' type y/n \'");
            answer = scan.nextLine();

            System.out.println("answer is : " + answer);

            while(answer.equals("y") || answer.equals("Y")){

                currentHand.add(someDeck.get(0));
                currentHandSplit.add(someDeck.get(1));
                someDeck.remove(0);
                someDeck.remove(1);

                blackJackFaceCard(currentHand);
                blackJackFaceCard(currentHandSplit);

                handValue = 0;
                handValueSplit = 0;


                System.out.print("RIGHT:");
                for(card temp : currentHand){
                    handValue += temp.getRankInt();
                    System.out.print(temp.getCard());
                }
                System.out.println("");
                System.out.print("LEFT :");
                for(card temp : currentHandSplit){
                    handValueSplit += temp.getRankInt();
                    System.out.print(temp.getCard());
                }

                System.out.println("");
                System.out.println("Cash: " + cash  );
                System.out.println("Current hand  RIGHT: " + currentHand.get(0).getCard() +  "\t" + currentHand.get(1).getCard() + "");
                System.out.println("Current hand  LEFT : " + currentHandSplit.get(0).getCard() +  "\t" + currentHandSplit.get(1).getCard() + "");
                System.out.println("Current value RIGHT: " + handValue);
                System.out.println("Current value LEFT : " + handValueSplit);
                System.out.println(" Play or stay? \' type y/n \'");
                answer = scan.next();

            }
            //split close
            if (handValue > houseValue && handValue < 22 && handValueSplit > houseValue && handValueSplit < 22) {
                System.out.print("houseHand  : " + houseValue + "\t\t\t");
                System.out.println(houseHand.get(0).getCard() + " " + houseHand.get(1).getCard());
                System.out.print("currentHand RIGHT: " + handValue + "\t");

                for (card temp : currentHand) {
                    System.out.print(temp.getCard() + " ");
                }
                System.out.println("");
                System.out.print("currentHand LEFT : " + handValueSplit + "\t");
                for (card temp : currentHandSplit) {
                    System.out.print(temp.getCard() + " ");
                }
                System.out.println("");
                System.out.println("Double Win!!! +$150");
                cash += 150*buyinlevel;
            }
            else if (handValue > houseValue && handValue < 22) {
                System.out.print("houseHand  : " + houseValue + "\t\t\t");
                System.out.println(houseHand.get(0).getCard() + " " + houseHand.get(1).getCard());
                System.out.print("currentHand RIGHT: " + handValue + "\t");

                for (card temp : currentHand) {
                    System.out.print(temp.getCard() + " ");
                }
                System.out.println("");
                System.out.print("currentHand LEFT : " + handValueSplit + "\t");
                for (card temp : currentHandSplit) {
                    System.out.print(temp.getCard() + " ");
                }
                System.out.println("");
                System.out.println("You Win +$50");
                cash += 50*buyinlevel;
            } else if (handValueSplit > houseValue && handValueSplit < 22){
                System.out.print("houseHand  : " + houseValue + "\t\t\t");
                System.out.println(houseHand.get(0).getCard() + " " + houseHand.get(1).getCard());

                System.out.print("currentHand RIGHT: " + handValue + "\t");

                for (card temp : currentHand) {
                    System.out.print(temp.getCard() + " ");
                }
                System.out.println("");
                System.out.print("currentHand LEFT : " + handValueSplit + "\t");
                for (card temp : currentHandSplit) {
                    System.out.print(temp.getCard() + " ");
                }
                System.out.println("");
                System.out.println("You Win +$50");
                cash += 50*buyinlevel;
            }
            else {
                System.out.print("houseHand  : " + houseValue + "\t\t\t");
                System.out.println(houseHand.get(0).getCard() + " " + houseHand.get(1).getCard());
                System.out.print("currentHand RIGHT: " + handValue + "\t");
                for (card temp : currentHand) {
                    System.out.print(temp.getCard() + " ");
                }
                System.out.println("");
                System.out.print("currentHand LEFT : " + handValueSplit + "\t");
                for (card temp : currentHandSplit) {
                    System.out.print(temp.getCard() + " ");
                }
                System.out.println("");

                System.out.println("You Lose -$100");
                cash -= 100*buyinlevel;
            }



            scan.close();
        }




        /*
            BASIC BLACKJACK
         */




        else {

            System.out.println(" Play or stay? \' type y/n \'");
            answer = scan.nextLine();

            System.out.println("answer is : " + answer);
            while (answer.equals("y") || answer.equals("Y")) {
                System.out.println("_-_-_-_-_-_-_-");
                currentHand.add(someDeck.get(0));

                blackJackFaceCard(currentHand);
                handValue += someDeck.get(0).getRankInt();
                someDeck.remove(0);

                //checking for ace
                if (handValue > 21 && aceinHand == true) {
                    handValue = handValue - 10;
                }


                //print hand
                print_currentHand(currentHand, handValue);
                /*
                System.out.print("Current hand:   ");
                for (card temp : currentHand) {
                    System.out.print(temp.getCard());
                }
                System.out.println("");
                System.out.println("Current value: " + handValue);
                */




                //loss state
                if (handValue > 21) {
                    System.out.println("You lose -$100");
                    break;
                }
                //System.out.println("houseHand :  " + houseValue);



                System.out.println("Cash: " + cash  );
                System.out.println("currentHand: " + handValue);
                System.out.println(" Play or stay? \' type y/n \'");
                answer = scan.next();


            }

            if (handValue > houseValue && handValue < 22) {

                print_both_currentHand_and_houseHand(houseValue,handValue, houseHand, currentHand);
                /*
                System.out.print("houseHand  : " + houseValue + "\t");
                System.out.println(houseHand.get(0).getCard() + " " + houseHand.get(1).getCard());
                System.out.print("currentHand: " + handValue + "\t");
                for (card temp : currentHand) {
                    System.out.print(temp.getCard() + " ");
                }
                System.out.println("");
                */
                System.out.println("You Win");
                cash +=150*buyinlevel;

            } else {
                /*
                System.out.print("houseHand  : " + houseValue + "\t");
                System.out.println(houseHand.get(0).getCard() + " " + houseHand.get(1).getCard());
                System.out.print("currentHand: " + handValue + "\t");
                for (card temp : currentHand) {
                    System.out.print(temp.getCard() + " ");
                }
                System.out.println("");
                */

                System.out.println("You Lose");
                cash -= 100*buyinlevel;
            }

            buyinlevel += 0.1;
            System.out.println("Cash: " + cash);
            System.out.println(" Play Blackjack again? (buyin $100) \' type y/n \' \t\t(might need to type answer twice, sorry java is being weird)");

            //scanner doesn't want to wake up the first time?
            //newgame = scan.nextLine();
            newgame = scan.nextLine();

            System.out.println("answer is : " + newgame);

            if(newgame.equals("y") || newgame.equals("Y")){
                cash -= 50;
                someDeck = createDeck();
                shuffleCards(someDeck);
                gameOfBlackJack(someDeck, cash, buyinlevel);
            }
            System.out.println("Goodnight");
            scan.close();
        }


    }

    public void add_card_to_hand(ArrayList<card> currentHand, ArrayList<card> someDeck, int handValue){

        System.out.println("_-_-_-_-_-_-_-");
        currentHand.add(someDeck.get(0));

        blackJackFaceCard(currentHand);
        handValue += someDeck.get(0).getRankInt();
        someDeck.remove(0);
    }

    public int check_for_ace(boolean aceinHand, int handValue){
        if (handValue > 21 && aceinHand == true) {
             handValue = handValue - 10;
        }

        return handValue;
    }

    public static void print_currentHand(List<card> currentHand, int handValue){

        System.out.print("Current hand:   ");
        for (card temp : currentHand) {
            System.out.print(temp.getCard());
        }
        System.out.println("");
        System.out.println("Current value: " + handValue);

    }

    public static void print_both_currentHand_and_houseHand(int houseValue, int handValue, List<card> houseHand, List<card> currentHand){
        System.out.print("houseHand  : " + houseValue + "\t");
        System.out.println(houseHand.get(0).getCard() + " " + houseHand.get(1).getCard());
        System.out.print("currentHand: " + handValue + "\t");
        for (card temp : currentHand) {
            System.out.print(temp.getCard() + " ");
        }
        System.out.println("");
    }






}
