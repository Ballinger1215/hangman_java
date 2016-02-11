package hangman;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author mark bollinger
 */
public class HangMan {


    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int input = 0;

        do {
            System.out.println("Choose one of the options" + "\n 1. Start game" + "\n 2. Show scores" + "\n 3. Add Words" + "\n 4. Exit");
            input = scan.nextInt();

            if (input == 1) {
                gameController();
            } else if (input == 2) {
                scores();
            } else if (input == 3) {
                addWords();
            }

            } while (input != 4);

    }

    public static void gameController() throws IOException {

        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int input = 0;
        int playerOne = 1;
        int playerTwo = 2;
        int newGame = 0;
        int[] scores = new int[2];
        int playerController = -1; // player1 == -1 and player2 == +1
        int w = 0;
        char index;
        String[] pNames = new String[2];
        String nextRound;
        boolean correctGuess = false;
        boolean notRevealed = true;

        String filename = "scores.txt";
        FileWriter fw = new FileWriter(filename, true);
        PrintWriter outputfile = new PrintWriter(fw);

        do {
            //to get the player's name
            System.out.println("Hello player one! What's your name:\t");
            pNames[0] = scan.next();

            System.out.println("Hello player two! What's your name:\t");
            pNames[1] = scan.next();

            String wordList[] = new String[100]; //to get a random word
            String word = new String();

            //read a file into an array of strings
            File myFile = new File("words.txt");
            Scanner inputFile = new Scanner(myFile);
            while (inputFile.hasNext() && w < wordList.length) {
                wordList[w] = inputFile.nextLine();
                w++;
            }
            word = wordList[rand.nextInt(w)]; //select a random number in that array range
            //System.out.println(word);
            
            //select the word from the array
            char[] guessWord = new char[word.length()];
            for (int i = 0; i < word.length(); i++) {
                guessWord[i] = '-';
            }

            System.out.println(word);
            System.out.println(guessWord);

            while (notRevealed) {
                if (correctGuess == false) {

                    playerController++;

                    if (playerController == 2) {
                        playerController = 0;
                    }
                }
                correctGuess = false;

                System.out.printf("%s, please guess:", pNames[playerController]);
                String guess = scan.next(); //asking for their guess
                notRevealed = false;

                for (int i = 0; i < word.length(); i++) { //check to see if it is in the word

                    if (word.charAt(i) == guess.charAt(0)) {

                        guessWord[i] = guess.charAt(0); //replace the dashes with correct letter
                        correctGuess = true;
                        scores[playerController]++;
                    }
                    if (guessWord[i] == '-') {
                        notRevealed = true;
                    }

                }
                System.out.println(guessWord);

            }//ask again (loop)
            System.out.printf("%s: %d\n%s: %d\n", pNames[0], scores[0], pNames[1], scores[1]);

            System.out.println("Do you want another round?\t"); //ask them to see if they want another round
            nextRound = new String(scan.next());
            
             //if no save the winner in score tables
                if (scores[0] > scores[1]) {
                    System.out.println(pNames[0] + " wins");
                //outputfile.println(pNames[0] + " wins");
                } else {
                    System.out.println(pNames[1] + " wins");
                //outputfile.println(pNames[1] + " wins");
                }
            outputfile.printf("%s: %d\n%s: %d\n", pNames[0], scores[0], pNames[1], scores[1]);
            outputfile.close();
            inputFile.close();

        } while (nextRound.charAt(0) == 'y'); //if yes go to a new word

    }
    
    private static void scores() throws IOException {
        
                String filename = "scores.txt"; // filename
                
                File file = new File(filename); // open the file
                Scanner inputFile = new Scanner(file);
                
                while(inputFile.hasNext()){
                
                String line = inputFile.nextLine();
                System.out.println(line);
                
                }
                
                inputFile.close(); // close the file
                
    }
    
    private static void addWords() throws IOException {
        
        int input = 0;
        String word = new String();
        String answer;
        String gamewords = "words.txt"; // filename
        
        Scanner ip = new Scanner(System.in);
        Scanner checkWord = new Scanner(System.in);
        
        do {
                System.out.println("Choose one of the options" + "\n 1. Add Word" + "\n 2. Show added words" + "\n 3. exit");
                input = ip.nextInt();
                word = (ip.nextLine());

                if (input == 1) {

                 do{
                        FileWriter fw = new FileWriter(gamewords, true);
                        PrintWriter outputFile = new PrintWriter(fw);

                        // to add word to the hangman game
                        System.out.println("Add word to the the Hangman game: ");
                        word = (ip.nextLine());

                        outputFile.println(word);
                        outputFile.close();

                        System.out.println("Do you want to add another word to the Game: ");
                        answer = new String(checkWord.next());

                    }while(answer.charAt(0) == 'y');

                }else if(input == 2) {

                        File file = new File(gamewords);
                        Scanner inputFile = new Scanner(file);

                        while(inputFile.hasNext()){

                            String line = inputFile.nextLine();
                            System.out.println(line);
                        }

                        inputFile.close();

                }

            }while(input != 3);                      
        
    }
    
}
