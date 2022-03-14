
/**
 * Used in unison with CombinationLockTester to check if the user can guess the 4 letter combination
 *
 * @Peterson Guo (your name)
 * @Feb 14, 2022
 */
public class CombinationLock
{
    // instance variables
    private String word;
    private String clue;

    /**
     * Constructor for objects of class CombinationLock
     */
    public CombinationLock(String lword)
    {
        // initialise instance variables
        word = lword;
    }
    
    //Initialize clue variable depending on length
    public void getClue(String lclue)
    {
        if(lclue.length() == 4)
        {
            clue = lclue;
        }
        else if(lclue.length() < 4)
        {
            int a = 4-lclue.length();
            clue = lclue;
            for(int i = 0; i < a; i++)
            {
                clue += " ";
            }
        }
        else
        {
            clue = lclue;
        }
    }
    
    //returns word for objects
    public String getWord()
    {
        return word;
    }
    
    public String clue()
    {
        return clue;
    }
    
    public String check()
    {
        if(clue.length() <= 4)
        {
            if(clue != word)
            {
                return "Not Quite";
            }
            return "You guessed the Combination!";
        }
        return "Max of 4 Characters";
    }
    
    //returns string of what characters guesses were right
    public String toString()
    {
        String result = "";
        for(int i = 0; i < 4; i++)
        {
            char c = clue.charAt(i);
            String s = String.valueOf(c);
            if(word.charAt(i) == clue.charAt(i))
            {
                result += word.charAt(i);
            }
            else if(word.contains(s))
            {
                result += "+";
            }
            else
            {
                result += "*";
            }
        }
        return result;
    }
}