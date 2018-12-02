
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    private static final String FILEPATH = "src/main/resources/Input_day1.txt";

    public static void Day1_out () {
        try{
            List<String> input = readWholeFile(FILEPATH);
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

    private static List<String> readWholeFile(String filePath) throws IOException {
        File f = new File(filePath);
        List<String> inputToRepeat = new ArrayList<>();

        try (BufferedReader b = new BufferedReader(new FileReader(f))) {
            String readline;
            while ((readline = b.readLine()) != null) {
                inputToRepeat.add(readline);
            }
        }
        return inputToRepeat;
    }

    private static boolean frequencyIsRepeated(List<Integer> numbersReached, int frequency) {
        return numbersReached.contains(frequency);
    }
}
