import java.util.Scanner;
/**
 * Tests for Combination lock class
 *
 * @Peterson Guo (your name)
 * 1.0 Feb 14 2022
 */
public class CombinationLockTester
{
    public static void main(String[] args)
    {
        //Scanner for user input
        Scanner input = new Scanner(System.in);
        //Object with specified word
        CombinationLock comboLock = new CombinationLock("frog");
        
        String code = "";
        //determines if the user input matches the word
        while(!code.equals(comboLock.getWord()))
        {
            //userInput and text
            System.out.print("Enter a 4 digit code: ");
            code = input.nextLine();
            
            comboLock.getClue(code);
            if(!comboLock.check().equals("Max of 4 Characters"))
            {
                String[] combo = comboLock.toString().split("<");
                System.out.println("Current Clue:   " + combo[0]);
                if(!combo[0].equals(combo[1])) {System.out.println("Previous Clues: " + combo[1]);}
            }
            System.out.println(comboLock.check());
        }
    }
}
