import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day3Test {


    @Test
    public void TestNumberOfInches(){
        List<String> input = Arrays.asList("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2");

        List<String> answer = Day3.createGrid(input);

        Assert.assertEquals("4", answer.get(0));
    }

    @Test
    public void TestIdDoesNotOverlapSmall(){
        List<String> input = Arrays.asList("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2");

        List<String> answer = Day3.createGrid(input);

        Assert.assertEquals("#3", answer.get(1));
    }

    @Test
    public void TestIdDoesNotOverlapLarge(){
        List<String> input = Arrays.asList("#1 @ 0,0: 5x5", "#2 @ 3,3: 3x3", "#3 @ 4,4: 4x4", "#4 @ 10,10: 2x2");

        List<String> answer = Day3.createGrid(input);

        Assert.assertEquals("#4", answer.get(1));
    }



}