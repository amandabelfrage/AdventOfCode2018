import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day5Test {

    @Test
    public void TestCharComparison(){
        String input = "dabAcCaCBAcCcaDA";

        int answer = Day5.compareCharacters(input);

        Assert.assertEquals(10, answer);
    }

    @Test
    public void TestRemoveUnits(){
        String input = "dabAcCaCBAcCcaDA";

        int answer = Day5.createPolyByRemovingUnits(input);

        Assert.assertEquals(4, answer);
    }

}
