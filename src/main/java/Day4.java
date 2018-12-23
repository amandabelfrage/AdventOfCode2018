import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collector;

public class Day4 {

    private static final String FILEPATH = "src/main/resources/Input_day4.txt";

    //First I need to sort the input based on date.

    public static void performDay4() {
        try {
            System.out.println("**** DAY 4 ****");
            List<String> input = Helper.readWholeFile(FILEPATH);
            input.sort(Comparator.naturalOrder());
            Map<String, List<String>> sleepingScheme = calculateSleepingScheme(input);
            String guardId = getGuardWhoSleepsMost(sleepingScheme);
            int bestMinuteOfMostTiredGuard = getMinute(sleepingScheme.get(guardId))[0];
            int bestMinute = getMinuteForEveryGuard(sleepingScheme);
            System.out.println("Strategy 1 product: " + Integer.parseInt(guardId.substring(1))*bestMinuteOfMostTiredGuard);
            System.out.println("Strategy 2 product: " + bestMinute + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static int getMinuteForEveryGuard(Map<String, List<String>> sleepingScheme){
        int maxMinutes = Integer.MIN_VALUE;
        int idealMinute = 0;
        String idealGuardId = "";
        for(Map.Entry<String, List<String>> entry : sleepingScheme.entrySet()) {
            String guardId = entry.getKey();
            int[] minutes = getMinute(entry.getValue());
            if(minutes[1] > maxMinutes){
                maxMinutes = minutes[1];
                idealMinute = minutes[0];
                idealGuardId = guardId;
            }

        }
        return idealMinute * Integer.parseInt(idealGuardId.substring(1));
    }

    static int[] getMinute(List<String> guardScheme){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        int[] eachMinute = new int[60];
        for (int i = 0; i < guardScheme.size(); i=i+2){
            LocalDateTime timeA = LocalDateTime.parse(guardScheme.get(i), formatter);
            LocalDateTime timeB = LocalDateTime.parse(guardScheme.get(i+1), formatter);
            if(timeA.getMinute() > timeB.getMinute()) {
                for (int a = timeA.getMinute(); a < 60; a++)
                    eachMinute[a] += 1;
                for (int b = 0; b < timeB.getMinute(); b++)
                    eachMinute[b] += 1;
            }else{
                for(int j = timeA.getMinute(); j < timeB.getMinute(); j++)
                    eachMinute[j] += 1;
            }
        }
        return calculateMax(eachMinute);
    }

    static int[] calculateMax(int[] eachMinute){
        int idealMinuteCount = Integer.MIN_VALUE;
        int idealMinute = 0;
        for(int i = 0; i < eachMinute.length; i++){
            if(eachMinute[i] > idealMinuteCount) {
                idealMinuteCount = eachMinute[i];
                idealMinute = i;
            }
        }
        int[] minutes = new int[2];
        minutes[0] = idealMinute;
        minutes[1] = idealMinuteCount;
        return minutes;
    }

    static String getGuardWhoSleepsMost(Map<String, List<String>> sleepingScheme){
        int maxSleepingMinutes = Integer.MIN_VALUE;
        String guardIdWithMaxSleepingMinutes = "";
        for(Map.Entry<String, List<String>> entry : sleepingScheme.entrySet()){
            int sleepingMinutes = 0;
            String guardId = entry.getKey();
            List<String> numberOfElem= entry.getValue();
            for (int i = 0; i < numberOfElem.size()-1; i=i+2){
                String elemA = numberOfElem.get(i);
                String elemB = numberOfElem.get(i+1);
                int timeDiff = getTimeDiff(elemA, elemB);
                sleepingMinutes += timeDiff;
            }
            if(sleepingMinutes > maxSleepingMinutes){
                maxSleepingMinutes = sleepingMinutes;
                guardIdWithMaxSleepingMinutes = guardId;
            }
        }
        return guardIdWithMaxSleepingMinutes;
    }

    private static int getTimeDiff(String elemA, String elemB) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeA = LocalDateTime.parse(elemA, formatter);
        LocalDateTime dateTimeB = LocalDateTime.parse(elemB, formatter);

        return Math.toIntExact(Duration.between(dateTimeA, dateTimeB).toMinutes());
    }

    static Map<String, List<String>> calculateSleepingScheme(List<String> input){
        Map<String, List<String>> guardsSleepingTime = new HashMap<>();
        String guardId = null;

        for(String line : input){
            String[] temp = line.split("] ");
            if(temp[1].contains("Guard")){
                guardId = getGuardId(temp[1]);
            }else{
                if(!guardsSleepingTime.containsKey(guardId)){
                    List<String> initGuard = new ArrayList<>();
                    if(temp[1].contains("falls")) {
                        initGuard.add(temp[0].substring(1)); //Add whole date
                        guardsSleepingTime.put(guardId, initGuard);
                    }else if (temp[1].contains("wakes")){
                        initGuard.add(temp[0].substring(1)); //Add whole date
                        guardsSleepingTime.put(guardId, initGuard);
                    }
                }else {
                    List<String> oldValues = guardsSleepingTime.get(guardId);
                    if(temp[1].contains("falls")) {
                        String dateTime = temp[0].substring(1);
                        oldValues.add(dateTime);
                    }else if(temp[1].contains("wakes")){
                        String dateTime = temp[0].substring(1);
                        oldValues.add(dateTime);
                    }
                }
            }
        }
        return guardsSleepingTime;
    }

    static String getGuardId(String line){
        String[] splittedLine = line.split(" ");
        return splittedLine[1];
    }

}
