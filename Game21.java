import java.util.Scanner;
import java.io.InputStream;

/******************************************************************
 * This class implements a Blackjack game
 ********************************************************************/
public class Game21
{
	public final static String HIT_PROMPT = "Hit?\n";
	public final static String SHOW_CARD = "  %s\n";
	public final static String SHOW_HAND = "%s: %s\n";
	public final static String BUSTED = "%s Busted!\n";
	public final static String DEALER_CARD = "  Total is %d, dealer takes a card.\n";
	public final static String WINNER = "%s wins.\n";
	Scanner keyboard;
	Shoe shoe;

    /************************************************************
     * Method purpose: This constructor instantiates a blackjack 
     * game.
     * 
     * @param stream The input stream game input will be read from.
     * @param decks  Number of decks in the shoe
     ************************************************************/
    public Game21(Scanner stream, int decks)
    {
      keyboard = stream;
      shoe = new Shoe(decks);
      Util21.shuffle(shoe.deck);
      
    }

    /************************************************************
         * Method purpose: this method plays a single blackjack game
         * in this blackjack session.
     ************************************************************/
    public void playGame()
    {
    	Boolean playerNotBusted = true;
    	Boolean dealerNotBusted = true;
    	String playerHand = "";
    	String dealerHand = "";
    	Card dealerFirstHand;
    	Card dealerSecondHand;
    	Card playerFirstHand;
    	Card playerSecondHand;
    	Card hitCard;
    	Card hitCardDealer;
    	 Hand player = new Hand();
    	 Hand dealer = new Hand();
    	 playerFirstHand = shoe.dealCard();
    	 player.addCard(playerFirstHand);
    	 dealerFirstHand = shoe.dealCard();
    	 dealer.addCard(dealerFirstHand);
    	 playerSecondHand = shoe.dealCard();
    	 player.addCard(playerSecondHand);
    	 dealerSecondHand = shoe.dealCard();
    	 dealer.addCard(dealerSecondHand);
    	 System.out.printf(SHOW_CARD, playerFirstHand.toString());
    	 System.out.println("  ????/????");
    	 System.out.printf(SHOW_CARD, playerSecondHand.toString());
    	 System.out.printf(SHOW_CARD, dealerSecondHand.toString());
    	 playerHand = String.format("%2s pts, (%s)", player.getScore(), player.toString());
    	 System.out.printf(SHOW_HAND, "Player", playerHand);
    	 System.out.println(dealer.lastCard());
    	 while(playerNotBusted && Util21.readYesNo(keyboard, HIT_PROMPT))
    	{
    		 hitCard = shoe.dealCard();
    		 player.addCard(hitCard);
    		 System.out.printf(SHOW_CARD, hitCard.toString());
    		 if (player.getScore() > 21)
    		{
    			 System.out.printf(BUSTED, "Player");
    			 playerHand = String.format("%2s pts, (%s)", player.getScore(), player.toString());
    			 dealerHand = String.format("%2s pts, (%s)", dealer.getScore(), dealer.toString());
    	    	System.out.printf(SHOW_HAND, "Player", playerHand);
    	    	System.out.printf(SHOW_HAND, "Dealer", dealerHand);
    	    	playerNotBusted = false;
    	    	System.out.printf(WINNER, "Dealer");
    	    	//System.exit(0);
    		}
    	}
    	 playerHand = String.format("%2s pts, (%s)", player.getScore(), player.toString());
		 dealerHand = String.format("%2s pts, (%s)", dealer.getScore(), dealer.toString());
    	System.out.printf(SHOW_HAND, "Player", playerHand);
    	System.out.printf(SHOW_HAND, "Dealer", dealerHand);
    	 
    	while (dealer.getScore() < 17)
    	{
    		System.out.printf(DEALER_CARD, dealer.getScore());
    		hitCardDealer = shoe.dealCard();
    		dealer.addCard(hitCardDealer);
    		System.out.printf(SHOW_CARD, hitCardDealer.toString());
    		if (dealer.getScore() > 21)
    		{
    			System.out.printf(BUSTED, "Dealer");
    		 	playerHand = String.format("%2s pts, (%s)", player.getScore(), player.toString());
    		 	dealerHand = String.format("%2s pts, (%s)", dealer.getScore(), dealer.toString());
    		 	System.out.printf(SHOW_HAND, "Player", playerHand);
    		 	System.out.printf(SHOW_HAND, "Dealer", dealerHand);
    		 	dealerNotBusted = false;
    		 	System.out.printf(WINNER, "Player");
    		 	//System.exit(0);
    		}
    		 	else if (dealer.getScore() >= 17 && dealer.getScore() <= 21)
    		 	{
    		 		playerHand = String.format("%2s pts, (%s)", player.getScore(), player.toString());
    			 	dealerHand = String.format("%2s pts, (%s)", dealer.getScore(), dealer.toString());
    			 	System.out.printf(SHOW_HAND, "Player", playerHand);
    			 	System.out.printf(SHOW_HAND, "Dealer", dealerHand);
    		 	}
    		
    	}
    	
    
    	
    	if (dealerNotBusted && playerNotBusted)
    	{
    
    		if (player.getScore() > dealer.getScore())
    			System.out.printf(WINNER, "Player");
    		else if (player.getScore() < dealer.getScore())
    			System.out.printf(WINNER, "Dealer");
    		else
    			System.out.println("Nobody wins.");
    	}
    	
    		 
    	 
        
    }

    /************************************************************
         * Method purpose: creates a shuffled shoe, then outputs
         * the string representation of each card in the shoe, one per line.
         * in this blackjack session.
     ************************************************************/
    public void outputShoe()
    {
        System.out.print(shoe.toString());
    }

}
