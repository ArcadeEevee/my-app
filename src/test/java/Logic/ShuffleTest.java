package Logic;

import org.eclipse.swt.widgets.Display;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShuffleTest {

    @Test
    public void shuffleCardsTest(){
        // Tests if the Shuffle function actually shuffles the cards

        // Gets an Array of Pairs
        ArrayList<Pair> pairs = getPairs();

        // Shuffles the Cards
        Shuffle shuffleTest = new Shuffle(pairs);
        int[] res = shuffleTest.shuffleCards();

        // Prints a String with the ID's of the cards in their shuffled order
        StringBuilder text = new StringBuilder();
        for(int i : res){
            text.append(i).append(", ");
        }
        System.out.println(text.substring(0, text.length() - 2));

        // Checks that every Card got shuffled
        assertEquals(10, res.length, "Ressult should be double the amount of pairs (here 5 pairs so it should be 10)");

    }

    private static ArrayList<Pair> getPairs() {
        //Creates an array of Pairs to Test with in the Method above

        Display display = new Display();

        Pair pair1 = new Pair("src/main/Images/Karte_1.png", "src/main/Images/blank.png", display);
        Pair pair2 = new Pair("src/main/Images/Karte_2.png", "src/main/Images/blank.png", display);
        Pair pair3 = new Pair("src/main/Images/Karte_3.png", "src/main/Images/blank.png", display);
        Pair pair4 = new Pair("src/main/Images/Karte_4.png", "src/main/Images/blank.png", display);
        Pair pair5 = new Pair("src/main/Images/Karte_5.png", "src/main/Images/blank.png", display);

        return new ArrayList<>(Arrays.asList(pair1, pair2, pair3, pair4, pair5));
    }
}
