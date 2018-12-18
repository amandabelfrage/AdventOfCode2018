
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    private static final String FILEPATH = "src/main/resources/Input_day1.txt";

    public static void performDay1 () {
        try{
            System.out.println("**** DAY 1 ****");
            List<String> input = Helper.readWholeFile(FILEPATH);
            int frequency = findRepeatedFrequency(input);
            System.out.println("First frequency reached: " + frequency);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int findRepeatedFrequency(List<String> input) {
        List<Integer> numbersReached = new ArrayList<>();
        int frequency = 0;
        numbersReached.add(0);
        boolean foundFrequency = false;

        while (!foundFrequency) {

            for (String line : input) {
                frequency += Integer.parseInt(line);
                if (frequencyIsRepeated(numbersReached, frequency)) {
                    foundFrequency = true;
                    break;
                }
                numbersReached.add(frequency);
            }
        }
        return frequency;
    }



    private static boolean frequencyIsRepeated(List<Integer> numbersReached, int frequency) {
        return numbersReached.contains(frequency);
    }
}
