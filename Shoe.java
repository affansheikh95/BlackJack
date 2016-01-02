
public class Shoe 
{
	public Card [] deck;
	private int currentIndex = 0;
	private int cardsLeft = 1;
	
	public Shoe(int decks)
	{
		cardsLeft = decks * 52;
		deck = new Card[decks*52];
		for (int i = 0; i < decks; i++)
		{
			for (CardSuit suit : CardSuit.values())
			{
				for (CardRank rank : CardRank.values())
				{
					deck[currentIndex] = new Card(suit, rank);
					currentIndex++;
				}
			}
		}
		//Util21.shuffle(deck);
	}
	
	public Card dealCard()
	{
		cardsLeft--;
		return deck[cardsLeft];
	}
	public String toString()
	{
		String string = "";
		for (int i = 0; i < deck.length; i++)
			string += deck[i].toString() + "\n";
		return string;
	}
}
