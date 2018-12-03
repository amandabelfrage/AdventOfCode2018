import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day1Test {

    @Test
    public void test1() {
        // GIVEN
        List<String> input = Arrays.asList("+1", "-1");

        // WHEN
        int answer = Day1.findRepeatedFrequency(input);

        // THEN
        assertEquals(0, answer);
    }

    @Test
    public void test2() {
        // GIVEN
        List<String> input = Arrays.asList("+3", "+3", "+4", "-2", "-4");

        // WHEN
        int answer = Day1.findRepeatedFrequency(input);

        // THEN
        Assert.assertEquals(10, answer);
    }

    @Test
    public void test3() {
        // GIVEN
        List<String> input = Arrays.asList("-6", "+3", "+8", "+5", "-6");

        // WHEN
        int answer = Day1.findRepeatedFrequency(input);

        // THEN
        assertEquals(5, answer);
    }

    @Test
    public void test4() {
        // GIVEN
        List<String> input = Arrays.asList("+7", "+7", "-2", "-7", "-4");

        // WHEN
        int answer = Day1.findRepeatedFrequency(input);

        // THEN
        assertEquals(14, answer);
    }
}
