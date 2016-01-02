import java.util.*;

public class Hand 
{
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Integer> values = new ArrayList<Integer>();
	private int score = 0;
	private boolean hole = true;
	CardRank cardRank;
	
	public void addCard (Card card)
	{
		hand.add(card);
		cardRank = card.getRank();
		if (card.getRank() == CardRank.ACE)
			values.add(11);
		else if (card.getRank() == CardRank.JACK || card.getRank() == CardRank.KING || card.getRank() == CardRank.QUEEN)
		{
			values.add(10);
		}
		else
		{
			values.add(cardRank.ordinal()+1);
		}
		
		calculateScore();
	
		
	}
	public String lastCard()
	{
		Card lastDealer;
		String string = "Dealer: ?? pts, (????/????";
		lastDealer = hand.get(hand.size()-1);
		string += " " + lastDealer.toString() + ")";
		return string;
		
	}

	public String toString()
	{
		 String string = "";
		         for (Card c : hand)
		         {
		        	 if (hand.indexOf(c)-hand.size() == -1)
		        		 string += c.toString();
		        	 else
		        		 string += c.toString() + " ";
		         }
		         return string;

	}
	public int getScore()
	{
		return score;
	}
	public void calculateScore()
	{
		int total = 0;
		score += values.get(values.size()-1);
		for (int i = 0; i < values.size(); i++)
		{
			if (score > 21 && values.get(i) == 11)
				values.set(i, 1); 
		}
		for (Integer num: values)
		{
			total += num;
		}
		score = total;
	}
}
