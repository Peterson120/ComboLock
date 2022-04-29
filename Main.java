import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main
{
    private static BufferedReader br;
    public static void main(String[] args) throws FileNotFoundException {
        game();
    }

    public static void game() // Wordle
    {
        Wordle wordGame = new Wordle();

        clearScreen();
        System.out.println("Welcome to Walter's Word Guessing game!\n\nYou will get extra tokens depending on how many turns you take\nYour goal is to guess the secret word\nIf the letter is in the correct spot it is represented by ✓.\nIf the letter is in the word, it is represented by +\nIf the letter is not in the word it is represented by a *\n\nType 'quit' to exit");
        //Object with specified word
        
        int numGuesses = 0;
        // Determines if the user input matches the word
        while(!(wordGame.getGuess().toLowerCase()).equals(wordGame.getWord()))
        {
            wordGame.getInput(); // Get user guess
            if(wordGame.getGuess().equals("quit"))
            {
                System.out.println("\nYou quit the game!");
                Wordle.playAgain();
            }
            System.out.println(wordGame); // Print hints
            numGuesses++; // Increment number of guesses
        }
        int score = (21-numGuesses) * 1500; // Calculate score of player
        log("Log.txt",score);
        System.out.println("\nYour score was " + score + "\n\nThe average score was " + average("Log.txt") + "\n");
        System.out.println(score >= largest("Log.txt") ? "You have the highest score" : largest("Log.txt") + "\n");
        try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        String[] highScores = Arrays.copyOf(addToSortedArray("Log.txt"),5);
        String print = Arrays.toString(highScores)
            .replace("[","")
            .replace("]","")
            .replace(", ","\n")
            .trim();
        System.out.println(print);
        try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        Wordle.playAgain();
    }

    static String[] addToSortedArray(String file)
    {
        ArrayList<String> list = new ArrayList<String>();
        try
        {
            br = new BufferedReader(new FileReader(file));

            String nextLine;
            do
            {
                nextLine = br.readLine();
                if(nextLine != "null")
                    list.add(nextLine);
            }while(nextLine != "null");
            br.close();
        }catch(Exception e){e.printStackTrace();}
        list.trimToSize();
        String[] result = new String[list.size()];
        int index = 0;
        for(String i:list) 
        {
            result[index++]=i;
        }
        Arrays.sort(result,Collections.reverseOrder());
        return result;
    }

    static void log(String file, Object message) // Add things to log
    {
        try 
        {
            FileWriter write = new FileWriter(file,true);
            write.write("\r\n");
            write.write(String.valueOf(message));
            write.close();
        }
        catch (IOException e) // File not found 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }  
    }
    
    static int largest(String file)
    {
        int largest = Integer.MIN_VALUE;
        try 
        {
            br = new BufferedReader(new FileReader(file));

            String temp;
            do{
                temp = String.valueOf(br.readLine());
                if(temp.matches("[0-9]+") && Integer.valueOf(temp) > largest) largest = Integer.valueOf(temp);
            }while(temp!="null");
            br.close();
        }catch(Exception e){e.printStackTrace();}
        return largest;
    }

    static int average(String file) // Determine file length
    {
        int total = 0; // Total var
        try
        {
            br = new BufferedReader(new FileReader(file));
            String line = String.valueOf(br.readLine()); // Define line to read next line
            do
            {
                if(line.matches("[0-9]+")) total+=Integer.valueOf(line); // Add integer value of word if file has next line
                line = String.valueOf(br.readLine());
            }while(line!="null"); // Check if file has a value
            br.close();
        }
        catch(Exception e) {e.printStackTrace();} // Print error
        return total/fileLength(file); // Return length
    }

    static String readFile(String file, int line) // Choose a name from the list
    {
        int length = fileLength(file);
        
        if(line > length) // Check if line is outside file
        {
            System.out.println("Index Out of Bounds");
            return "";
        }
        try {return Files.readAllLines(Paths.get(file)).get(line);} // Try getting a line in the list
        catch (IOException e) {System.out.println(file + " not found");} // File is not found Exception
        return "";
    }

    static int fileLength(String file) // Determine file length
    {
        int length = 0; // length var
        try
        {
            br = new BufferedReader(new FileReader(file));
            while (br.readLine() != null) length++; // Increment length if file has next line
            br.close(); // Close reader
        }
        catch(Exception e) {System.out.println("File not found");} // File not found
        return length; // Return length
    }

    static void clearScreen() // Clear Screen
    {
        System.out.print("\033[H\033[2J");  // Clear Screen
        System.out.flush();                 // Flush Screen / Memory
        System.out.print("\u001b[H");       // Set cursor to top
    }
}