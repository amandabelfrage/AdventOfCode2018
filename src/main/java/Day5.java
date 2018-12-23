import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day5 {

    private static final String FILEPATH = "src/main/resources/Input_day5.txt";

    public static void performDay5(){
        System.out.println("**** DAY 5 ****");
        try {
            List<String> input = Helper.readWholeFile(FILEPATH);
            int answer = compareCharacters(input.get(0));
            System.out.println("Number of units in string: " + answer);
            int shortestAnswer = createPolyByRemovingUnits(input.get(0));
            System.out.println("Number of units in the shortest string: " + shortestAnswer + "\n");
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    static int compareCharacters(String input){
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        int i = 0;
        while (i < sb.length() - 1) {
            char c1 = sb.charAt(i);
            char c2 = sb.charAt(i + 1);
            if (Character.toUpperCase(c1) == Character.toUpperCase(c2) &&
                ((Character.isUpperCase(c1) && Character.isLowerCase(c2)) ||
                (Character.isLowerCase(c1) && Character.isUpperCase(c2)))) {
                    sb.deleteCharAt(i+1);
                    sb.deleteCharAt(i);
                    if(i > 0)
                        i = i-1;
                    else
                        i = 0;
            }else
                i++;
        }
        return sb.length();
    }

    static int createPolyByRemovingUnits(String input){
        int minValue = Integer.MAX_VALUE;
        char[] chars = input.toLowerCase().toCharArray();
        Stream<Character> cStream = IntStream.range(0, chars.length).mapToObj(i -> chars[i]);
        List<Character> distinctChars = cStream.distinct().collect(Collectors.toList());

        for(char c : distinctChars){
            String lowerCaseRemoved = input.replaceAll(String.valueOf(c), "");
            String reducedInput = lowerCaseRemoved.replaceAll(String.valueOf(Character.toUpperCase(c)), "");
            int stringValue = compareCharacters(reducedInput);
            if(stringValue<minValue)
                minValue = stringValue;

        }
        return minValue;
    }
}
