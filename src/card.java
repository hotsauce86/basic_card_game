public class card {

    private String suit;
    private String rank;
    private Integer rankInt;

    public card(String suit, String rank){
        this.suit = suit;
        this.rank = rank;
    }

    public card(String suit, String rank, Integer rankInt){
        this.suit = suit;
        this.rank = rank;
        this.rankInt = rankInt;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setRankInt(Integer rankInt){ this.rankInt = rankInt;}

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public Integer getRankInt() { return rankInt;}

    public String getCard(){
                            String theCard = "[" + this.getSuit() + "," + this.getRank()+ "]";
                            return theCard;
                            }

}
