
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {

    private static final String FILEPATH = "src/main/resources/Input_day1.txt";

    public static void performDay1 () {
        try{
            System.out.println("**** DAY 1 ****");
            List<String> input = Helper.readWholeFile(FILEPATH);
            int frequency = findRepeatedFrequency(input);
            System.out.println("First frequency reached: " + frequency + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int findRepeatedFrequency(List<String> input) {
        Map<Integer, Integer> numbersReached = new HashMap<>();
        int frequency = 0;
        numbersReached.put(0, 1);
        boolean foundFrequency = false;

        while (!foundFrequency) {

            for (String line : input) {
                frequency += Integer.parseInt(line);
                if (frequencyIsRepeated(numbersReached, frequency)) {
                    foundFrequency = true;
                    break;
                }
                numbersReached.put(frequency, 1);
            }
        }
        return frequency;
    }



    private static boolean frequencyIsRepeated(Map<Integer, Integer> numbersReached, int frequency) {
        return numbersReached.containsKey(frequency);
    }
}
