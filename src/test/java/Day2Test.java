import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class Day2Test {
    @Test
    public void charAppearedExactlyTwiceTest (){
        String input = "abbcht";

        Map<Character, Integer> result = Day2.createCharCountMap(input);

        assertTrue(Day2.charAppearsExactlyNTimes(result, 2));
        assertFalse(Day2.charAppearsExactlyNTimes(result, 3));
    }

    @Test
    public void charAppearedExactlyThriceTest (){
        String input = "abdbcbh";

        Map<Character, Integer> result = Day2.createCharCountMap(input);

        assertTrue(Day2.charAppearsExactlyNTimes(result, 3));
        assertFalse(Day2.charAppearsExactlyNTimes(result, 2));
    }

    @Test
    public void charAppearedLessThanTwoTimesTest (){
        String input = "bcadijk";

        Map<Character, Integer> result = Day2.createCharCountMap(input);

        assertFalse(Day2.charAppearsExactlyNTimes(result, 2));
        assertFalse(Day2.charAppearsExactlyNTimes(result, 3));
    }

    @Test
    public void charAppearedMoreThanThreeTimesTest (){
        String input = "abcabcabcabcaba";

        Map<Character, Integer> result = Day2.createCharCountMap(input);

        assertFalse(Day2.charAppearsExactlyNTimes(result, 2));
        assertFalse(Day2.charAppearsExactlyNTimes(result, 3));
    }

    @Test
    public void charAppearedBothTwiceAndThriceTest (){
        String input = "abcaba";

        Map<Character, Integer> result = Day2.createCharCountMap(input);

        assertTrue(Day2.charAppearsExactlyNTimes(result, 2));
        assertTrue(Day2.charAppearsExactlyNTimes(result, 3));
    }

    @Test
    public void charAppearedTwice_ProductTest () {
        List<String> input = Arrays.asList("abkebt");

        int result = Day2.createChecksum(input);

        assertEquals(0, result);
    }

    @Test
    public void charAppearedThrice_ProductTest (){
        List<String> input = Arrays.asList("abcbteb");

        int result = Day2.createChecksum(input);

        assertEquals(0, result);
    }

    @Test
    public void bothDuplicatesAndTriplets_ProductTest () {
        List<String> input = Arrays.asList("abtbab");

        int result = Day2.createChecksum(input);

        assertEquals(1, result);
    }

    @Test
    public void multipleDuplicatesAndTriplets_productTest() {
        List<String> input = Arrays.asList("abtbab", "abtbab", "abtbab");

        int result = Day2.createChecksum(input);

        assertEquals(9, result);
    }
}
