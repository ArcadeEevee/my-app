package Logic;

import java.util.ArrayList;
import java.util.Random;

public class Shuffle {

    private int idCount;
    private Random rand;


    public Shuffle(ArrayList<Pair> pairs){
        idCount = pairs.size()*2;
        rand = new Random();
    }

    public int[] shuffleCards(){

        int[] idList = new int[idCount];

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

            // No duplicate -> continue with next position
            // Duplicate -> repeat this position
            if(check){
                idList[i] = randomNumber;
            }else{
                i--;
            }
        }

        return idList;
    }
}
