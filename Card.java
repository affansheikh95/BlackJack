public class Card 
{

	private CardRank cardRank;
	private CardSuit cardSuit;

	public Card(CardSuit suit, CardRank rank)
	{
		cardSuit = suit;
		cardRank = rank;
		
	}
	public CardRank getRank()
	{
		return cardRank;
	}
	
	public CardSuit getSuit()
	{
		return cardSuit;
	}
	public String toString()
	{
		String answer;
		answer = cardRank + "/" + cardSuit;
		return answer;
	}

}
