import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day2Test {
    @Test
    public void charAppearedTwiceTest (){
        List<String> input = Arrays.asList("abbcde");
        int result = Day2.countCharAppeared(input);
        Assert.assertEquals(0, result);
    }

    @Test
    public void charAppearedTwiceUnorderedTest () {
        List<String> input = Arrays.asList("abkebt");
        int result = Day2.countCharAppeared(input);
        Assert.assertEquals(0, result);
    }

    @Test
    public void charAppearedThriceTest (){
        List<String> input = Arrays.asList("abcbteb");
        int result = Day2.countCharAppeared(input);
        Assert.assertEquals(0, result);
    }
    @Test
    public void bothDuplicatesAndTriplets () {
        List<String> input = Arrays.asList("abtbab");
        int result = Day2.countCharAppeared(input);
        Assert.assertEquals(1, result);
    }
}
