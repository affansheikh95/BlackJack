import java.util.Scanner;

public class Driver21
{
    /*************************************************************
     * Function purpose: Plays a blackjack game using a shoe with
     * 2 decks of cards.  An integer argument
     * may be specified for the program, if present it serves as the
     * the seed to the random number generator used by the program.
     * If the argument is negative, the program just outputs the cards
     * in the shuffled shoe, bottom to top.
     *
     * @param args args[0]: An optional integer to use as the random number generator.
     *************************************************************/
    public static void main (String args[])
    {
        Game21    game;
        long      randomSeed;
        Scanner   stdin;
        boolean   testingShuffle = false;

        if (args.length > 0)
        {
            if (args[0].charAt(0) == '-')
            {
                Util21.setRandomSeed(Integer.parseInt(args[0].substring(1)));
                testingShuffle = true;
            }
            else
                Util21.setRandomSeed(Integer.parseInt(args[0]));
        }
        stdin = new Scanner(System.in);
        game = new Game21(stdin, 2);
        if (testingShuffle)
            game.outputShoe();
        else
            game.playGame();
    }
}
