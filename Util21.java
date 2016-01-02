import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;


public class Util21
{

    /** The Random object used by nextRandomInt and setRandomSeed. **/
    private static Random randy = null;         

    /*************************************************************
     * This function sets the seed for the random
     * number generator implemented in this class.
     * @param seed The random number seed
     *************************************************************/
    public static void setRandomSeed(long seed)
    {
        Util21.randy = new Random(seed);
    }

    /*************************************************************
     * This function returns a random integer in the range 
     * 1 to <code>range</code>.
     * 
     * @param range Specifies the range of the random number.
     * @return        A random integer between 1 and <code>range</code>.
     *************************************************************/
    public static int nextRandomInt(int range)
    {
        if (Util21.randy == null)
            Util21.randy = new Random();
        return Util21.randy.nextInt(range) + 1;
    }

    /*************************************************************
     * Method purpose: this function prompts the user for a yes
     * or no input and returns true if the user responds affirmatively,
     * and return false if the user responds negatively.  A response 
     * staring with "Y" or "y" iss an affirmative response, anything
     * else is a negative response.
     * 
     * @param stream Input stream the input is to be read from.
     * @param prompt The prompt output before trying to read.
     * @return         A boolean indicating if the user responded affirmatively.
     *************************************************************/
    public static boolean readYesNo(Scanner stream, String prompt)
    {
        String ans = null;
        char response = 'n';
        
        System.out.print(prompt);
        if (stream.hasNextLine())
        {
            ans = stream.nextLine();
            if (ans != null && ans.length() > 0)
                response = ans.charAt(0);
        }

        return response == 'y'  ||  response == 'Y';
    }


    /**********************************************************
     * This method moves around the objects in the array,
     * param origArray The array of objects to randomize
     *********************************************************/
    public static void shuffle(Object [] origArray)
    {
        int       length = origArray.length;
        Object [] tempArray = new Object [length];
        int       newIndx;    // random index for where to put next object
        int       indexStart;  // value newIndex started at

        //--------------------------------------
        // Mark all elements as being "empty".
        //--------------------------------------
        for (int ii = 0; ii < length; ii++)
            tempArray[ii] = null;
        
        for (int oldIndx = 0; oldIndx < length; oldIndx++) // For each object
        {
            //---------------------------------------------------------------
            // Copy each object in origArray to a random location in tempArray.
            // If the new location is already occupied, increase the newIndx
            // location by one uintil an empty element in tempArray is found.
            //---------------------------------------------------------------
            newIndx = Util21.nextRandomInt(length) - 1;
            indexStart = newIndx;
            while(tempArray[newIndx] != null)
            {
                newIndx = (newIndx + 1) % length;
                if (newIndx == indexStart)
                {
                    System.err.printf("Bug: %d %d %d, %s\n", newIndx,
                      indexStart, length, Arrays.toString(tempArray));
                    System.exit(1);
                }
            }
            tempArray[newIndx] = origArray[oldIndx];
        }

        //-----------------------------------------------------------
	// Copy randomized temporary array back into original array
        //-----------------------------------------------------------
        for (int indx = 0; indx < length; indx++) // For each object
            origArray[indx] = tempArray[indx];
    }
}
