package Logic;

import org.eclipse.swt.widgets.Display;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShuffleTest {

    @Test
    public void shuffleCardsTest(){

        ArrayList<Pair> pairs = getPairs();

        Shuffle shuffleTest = new Shuffle(pairs);
        int[] res = shuffleTest.shuffleCards();

        StringBuilder text = new StringBuilder();
        for(int i : res){
            text.append(i).append(", ");
        }
        System.out.println(text.substring(0, text.length() - 2));

        assertEquals(10, res.length, "Ressult should be double the amount of pairs (here 5 pairs so it should be 10)");

    }

    private static ArrayList<Pair> getPairs() {

        Display display = new Display();

        Pair pair1 = new Pair("src/main/Images/Karte_1.png", "src/main/Images/blank.png", display);
        Pair pair2 = new Pair("src/main/Images/Karte_2.png", "src/main/Images/blank.png", display);
        Pair pair3 = new Pair("src/main/Images/Karte_3.png", "src/main/Images/blank.png", display);
        Pair pair4 = new Pair("src/main/Images/Karte_4.png", "src/main/Images/blank.png", display);
        Pair pair5 = new Pair("src/main/Images/Karte_5.png", "src/main/Images/blank.png", display);

        return new ArrayList<>(Arrays.asList(pair1, pair2, pair3, pair4, pair5));
    }
}
