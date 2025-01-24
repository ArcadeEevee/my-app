package Logic;

import java.util.ArrayList;
import java.util.Random;

public class Shuffle {

    private final int idCount;
    private final Random rand;


    // Shuffle needs the amount of total cards
    public Shuffle(ArrayList<Pair> pairs){
        idCount = pairs.size()*2;
        rand = new Random();
    }

    // Shuffles get Shuffled
    public int[] shuffleCards(){

        // Creating an array with the total amount of cards
        int[] idList = new int[idCount];

        // fills that array with a shuffled list of ID's
        for(int i = 0; i < idList.length - 1; i++){

            boolean check = true;

            // Get a random number in range of available cards
            int randomNumber = rand.nextInt(idCount);

            // Checking randomNumber against list to look if it's already in there
            for(int j : idList){
                if(j == randomNumber){
                    check = false;
                    break;
                }
            }

            // Checking for duplicate
            if(check){
                // No duplicate -> continue with next position
                idList[i] = randomNumber;
            }else{
                // Duplicate -> repeat this position
                i--;
            }
        }

        return idList;
    }
}
