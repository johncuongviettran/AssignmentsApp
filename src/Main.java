import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.WEEKS;

public class Main {

    private static Random rand = new Random();
    private static Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter formatMMddyyyyhhmm = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("\n\nHello, AssignmentsApp!\n");

        //Outputs the current LocalDateTime.
        LocalDateTime today = LocalDateTime.now();
        System.out.println("The current date and time is " + today);

        //Outputs tommorrow's date using a formatter.
        LocalDateTime tommorrow = today.plus(1, DAYS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        String formatDateTime = tommorrow.format(formatter);
        System.out.println("The date and time one day from now is " + formatDateTime);

        //Outputs the LocalDateTime 5 weeks from now.
        LocalDateTime fiveWeeksLater = today.plus(5, WEEKS);
        String formatFiveWeeksLater = fiveWeeksLater.format(formatter);
        System.out.println("The date and time 5 weeks from now is " + formatFiveWeeksLater);

        //Outputs the LocalDateTime of the user's birthdate with the time, 12:35.
        System.out.println("Enter your birthdate in the format of yyyy mm dd: ");
        LocalDateTime birthDateTime = LocalDateTime.of(sc.nextInt(),sc.nextInt(),sc.nextInt(),12,35);
        String formatBirthDateTime = birthDateTime.format(formatter);
        System.out.println("The date of the user's birthdate with the time, 12:35, is " + formatBirthDateTime);

        //Outputs the day of the week that the user was born on.
        DayOfWeek birthDateDayOfWeek = birthDateTime.getDayOfWeek();
        System.out.println("The day of the week of the user's birthdate is " + birthDateDayOfWeek);

        //Outputs the number of days the user has been alive.
        Long daysAlive = DAYS.between(birthDateTime,today);
        System.out.println("The user has been alive for " + daysAlive + " days.");

        //Outputs the number of days between two dates.
        LocalDateTime date1 = randLocalDateTime(2017, 0, 12, 1, 23, 0, 59, 0);
        LocalDateTime date2 = randLocalDateTime(2017, 0, 12, 1, 23, 0, 59, 0);
        Long daysBetween = Math.abs(DAYS.between(date1,date2));
        System.out.println("The number of days between " + date1 + " and " + date2 + " is " + daysBetween + " days.");

        //Finds and outputs the earlier of the two previously generated dates.
        LocalDateTime earlierDate = findEarlierLocalDateTime(date1, date2);
        System.out.println("The earlier date is " + earlierDate);

        //Generates 100 random dates into a text file.
        File datesFile = new File("dates.txt");
        try (PrintWriter pw = new PrintWriter(datesFile)){
            for (int i = 0; i < 100; i++) {
                LocalDateTime tempDateTime = randLocalDateTime(today.getYear(), today.getYear() - 3, 12, 1, 23, 0, 59, 0);
                pw.println(tempDateTime.format(formatMMddyyyyhhmm));
            }
        }
        //Adds dates from text file to an ArrayList as an ArrayList of LocalDateTime objects:
        ArrayList<LocalDateTime> dateTimeList = new ArrayList<>();
        try (Scanner scFile = new Scanner(datesFile)){
            while( scFile.hasNext() ) {
                String date = scFile.next();
                String[] dateArray = date.split("/");
                Integer monthValue = Integer.valueOf(dateArray[0]);
                Integer dayOfMonthValue = Integer.valueOf(dateArray[1]);
                Integer yearValue = Integer.valueOf(dateArray[2]);
                String time = scFile.next();
                String[] timeArray = time.split(":");
                Integer hourValue = Integer.valueOf(timeArray[0]);
                Integer minuteValue = Integer.valueOf(timeArray[1]);
                LocalDateTime tempDateTime = LocalDateTime.of(yearValue, monthValue, dayOfMonthValue, hourValue, minuteValue);
                dateTimeList.add(tempDateTime);
            }
        }
        System.out.println("The elements of the ArrayList of the 100 generated dates as LocalDateTime Objects are " + dateTimeList);

        //Finds the number of stored dates with a specified year.
        System.out.println("Enter the year to search for: ");
        int yearSearch = sc.nextInt();
        ArrayList<LocalDateTime> yearSearchList = new ArrayList<>();
        yearSearchList = findTemporalFieldValue(dateTimeList, yearSearch, ChronoField.YEAR);
        System.out.println("The number of stored dates with the year, " + yearSearch + ", is " + yearSearchList.size());

        //Counts the number of stored dates for the current year.
        ArrayList<LocalDateTime> yearCurrentList = new ArrayList<>();
        yearCurrentList = findTemporalFieldValue(dateTimeList, today.getYear(), ChronoField.YEAR);
        System.out.println("The number of stored dates for the current year is " + yearCurrentList.size());

        //Counts the number of duplicate dates by using a set.
        Set<LocalDate> distinctLocalDateSet = new HashSet<>();
        ArrayList<LocalDateTime> dateTimeListDuplicates = new ArrayList<>();
        for (LocalDateTime dateTime:dateTimeList) {
            distinctLocalDateSet.add(dateTime.toLocalDate());
        }
        dateTimeListDuplicates = removeFirstOccurrences(dateTimeList, distinctLocalDateSet);
        System.out.println("The number of duplicate dates counted by using a set is " + dateTimeListDuplicates.size());

        //Creates a sorted copy of the dateTimeList ArrayList.
        ArrayList<LocalDateTime> dateTimeListSorted = new ArrayList<>();
        dateTimeListSorted.addAll(dateTimeList);
        Collections.sort(dateTimeListSorted);
        System.out.println("The elements of the sorted dateTimeList are " + dateTimeListSorted);

        //Counts the number of duplicate dates using a sorted list and without using a set.
        int countDuplicateDates = countDuplicatesFromSortedList(dateTimeListSorted);
        System.out.println("The number of duplicate dates counted without using a set is " + countDuplicateDates);

        //Finds the number of stored dates with a time afer evening(6pm or 18 in a 24 hour clock).
        ArrayList<LocalDateTime> dateTimeAfterEveningList = new ArrayList<>();
        dateTimeAfterEveningList = findGreaterThanTemporalFieldValue(dateTimeList, 18, ChronoField.HOUR_OF_DAY);
        System.out.println("The elements in the dateTimeList that have a time after evening(6pm or 18 in a 24 hour clock) are " + dateTimeAfterEveningList);

        //Counts the number of dates in each individual month without using a Map.
        ArrayList<Month> monthsList = new ArrayList<>();
        ArrayList<Integer> datesPerMonthList = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            monthsList.add(Month.of(i));
        }
        datesPerMonthList = countDatesInEachMonthInclusiveBounds(dateTimeList, 1, 12);
        System.out.println("The count of dates per a month are " + datesPerMonthList + " with the corresponding months being " + monthsList);

        //Counts the number of dates in each individual month by using a Map
        Map<Month, Integer> dateCountMap = new HashMap<>();
        dateCountMap = mapDateCount(dateTimeList);
        System.out.println("The map of the counts of the dates per a month is " + dateCountMap);

        //Determine the indices of elements with the latest LocalDateTime.
        ArrayList<Integer> indicesLatestLocalDateTime = new ArrayList<>();
        indicesLatestLocalDateTime = findIndices(dateTimeList, dateTimeListSorted.get(dateTimeListSorted.size()-1));
        System.out.println("The indices of the elements with the latest LocalTimeDate are " + indicesLatestLocalDateTime);

        //Determines the indices of the dates with the earliest starting time.
        ArrayList<Integer> indicesEarliestLocalTime = new ArrayList<>();
        indicesEarliestLocalTime = findIndices(dateTimeList, findEarliestLocalTime(dateTimeList));
        System.out.println("The indices of the elements with the earliest starting time are " + indicesEarliestLocalTime);

        //Outputs a date in the format "January 1st, 2018".
        String suffix = getOrdinalSuffix(today.getDayOfMonth());
        System.out.println("Today's date in the format of \"January 1st, 2018\" is " + today.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + today.getDayOfMonth() + suffix + ", " + today.getYear());

        //Tests the Day enumerated type.
        EnumTest enumTestDay = new EnumTest(Day.randomDay());
        enumTestDay.stateDay();

        //Tests the Course enumerated type.
        EnumTest enumTestCourse = new EnumTest(Course.randomCourse());
        enumTestCourse.stateCourse();

        //Tests the Category enumerated type.
        EnumTest enumTestCategory = new EnumTest(Category.randomCategory());
        enumTestCategory.stateCategory();

        //Generates 2 random Assignment objects named assign1 and assign2.
        Assignment assign1 = randomAssignment(0,3,today.getYear(),today.getYear(),
                today.getMonthValue(),today.getMonthValue(),today.getHour(),today.getHour(),
                today.getMinute(),today.getMinute());
        Assignment assign2 = randomAssignment(0,3,today.getYear(),today.getYear(),
                today.getMonthValue(),today.getMonthValue(),today.getHour(),today.getHour(),
                today.getMinute(),today.getMinute());
        System.out.println("assign1 is " + assign1.toString());
        System.out.println("assign2 is " + assign2.toString());

        //Copies assign1 to assign3.
        Assignment assign3 = new Assignment(assign1);
        System.out.println("assign3 is " + assign3.toString());

        //Checks if assign3 is equal to assign1.
        if (assign3.equals(assign1)){
            System.out.println("assign1 is equal to assign3");
        }

    }
    //Function to generate a random integer within a range with both bounds being inclusive.
    private static int randomPositiveIntInInclusiveRange(int lowerBound, int upperBound){
        if (lowerBound <= upperBound){
            return rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
        }
        else {
            return -1;
        }
    }
    //Function to generate a random LocalDateTime object from bounds for the year, month value, hour, and minute.
    private static LocalDateTime randLocalDateTime (int yearUpperBound, int yearLowerBound, int monthValueUpperBound, int monthValueLowerBound, int hourUpperBound, int hourLowerBound, int minuteUpperBound, int minuteLowerBound){
        int yearValue = randomPositiveIntInInclusiveRange(yearLowerBound, yearUpperBound);
        int monthValue = randomPositiveIntInInclusiveRange(monthValueLowerBound, monthValueUpperBound);
        int dayOfMonthValue = Month.of(monthValue).minLength();
        if (yearValue % 4 != 0) {
            dayOfMonthValue = rand.nextInt(Month.of(monthValue).length(false)) + 1;
        }
        else {
            dayOfMonthValue = rand.nextInt(Month.of(monthValue).length(true)) + 1;
        }
        int hourValue = randomPositiveIntInInclusiveRange(hourLowerBound,hourUpperBound);
        int minuteValue = randomPositiveIntInInclusiveRange(minuteLowerBound, minuteUpperBound);
        LocalDateTime tempDateTime = LocalDateTime.of(yearValue, monthValue, dayOfMonthValue, hourValue, minuteValue);
        return tempDateTime;
    }
    //Function to find the earlier LocalDateTime object from 2 LocalDateTime objects.
    private static LocalDateTime findEarlierLocalDateTime (LocalDateTime date1, LocalDateTime date2){
        LocalDateTime tempDateTime = null;
        if (date1.isAfter(date2)){
            tempDateTime = date2;
        }
        else if (date2.isAfter(date1)){
            tempDateTime = date1;
        }
        return tempDateTime;
    }
    //Function to find the elements in a LocalDateTime Arraylist that have a specific value for a specific temporal field.
    private static ArrayList<LocalDateTime> findTemporalFieldValue (ArrayList<LocalDateTime> list, int target, TemporalField temporalField){
        ArrayList<LocalDateTime> tempList = new ArrayList<>();
        for (LocalDateTime date: list) {
            int temp = date.get(temporalField);
            if (temp == target){
                tempList.add(date);
            }
        }
        return  tempList;
    }
    //Function to find the elements in a LocalDateTime ArrayList that are over a specific value for a specific temportal field.
    private static ArrayList<LocalDateTime> findGreaterThanTemporalFieldValue(ArrayList<LocalDateTime> list, int target, TemporalField temporalField){
        ArrayList<LocalDateTime> tempList = new ArrayList<>();
        for (LocalDateTime date: list) {
            int temp = date.get(temporalField);
            if (temp > target){
                tempList.add(date);
            }
        }
        return  tempList;
    }
    //Function to find the indices of elements within a LocalDateTime ArrayList with a specific LocalDateTime.
    private static ArrayList<Integer> findIndices (ArrayList<LocalDateTime> list, LocalDateTime target){
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            LocalDateTime temp = list.get(i);
            if (temp == target){
                tempList.add(i);
            }
        }
        return tempList;
    }
    //Function to remove the first occurrence of a LocalDate value from a LocalDateTime ArrayList using a LocalDate Set.
    private static ArrayList<LocalDateTime> removeFirstOccurrences (ArrayList<LocalDateTime> list, Set<LocalDate> set){
        ArrayList<LocalDateTime> temp = new ArrayList<>();
        temp.addAll(list);
        for (LocalDate date:set) {
            for (LocalDateTime dateTime:temp) {
                if (dateTime.toLocalDate() == date){
                    temp.remove(dateTime);
                    break;
                }
            }
        }
        return temp;
    }
    //Function to count duplicate dates in a LocalDateTime ArrayList.
    private static int countDuplicatesFromSortedList (ArrayList<LocalDateTime> list){
        int temp = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).toLocalDate().equals(list.get(i + 1).toLocalDate())){
                temp++;
            }
        }
        return temp;
    }
    //Function to create an Integer ArrayList where each index represents the month values in order and the elements represent counts of dates in each month.
    private static ArrayList<Integer> countDatesInEachMonthInclusiveBounds (ArrayList<LocalDateTime> list, int lowerBound, int upperBound){
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = lowerBound; i < upperBound + 1; i++) {
            int count = 0;
            for (LocalDateTime dateTime:list) {
                if (dateTime.getMonthValue() == i) {
                    count++;
                }
            }
            tempList.add(count);
        }
        return tempList;
    }
    //Function to count the number of times an integer appears in an integer array list.
    private static Map<Month, Integer> mapDateCount (ArrayList<LocalDateTime> list){
        Map<Month, Integer> tempMap = new TreeMap<>();
        ArrayList<LocalDateTime> tempList = new ArrayList<>();
        for (LocalDateTime dateTime:list) {
            int count = tempMap.getOrDefault(dateTime.getMonth(), 0);
            tempMap.put(dateTime.getMonth(), count + 1);
        }
        return tempMap;
    }
    //Function to find the earliest LocalTime from an ArrayLIst of LocalDateTime objects.
    private static LocalTime findEarliestLocalTime (ArrayList<LocalDateTime> list){
        LocalTime temp = LocalTime.MAX;
        for (LocalDateTime date:list) {
            LocalTime temp2 = date.toLocalTime();
            if (temp2.isBefore(temp)){
                temp = temp2;
            }
        }
        return temp;
    }
    //Function to find the indices of elements within a LocalDateTime ArrayList with a specific LocalTime.
    private static ArrayList<Integer> findIndices (ArrayList<LocalDateTime> list, LocalTime target){
        ArrayList<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            LocalTime temp = list.get(i).toLocalTime();
            if (temp == target){
                tempList.add(i);
            }
        }
        return tempList;
    }
    //Function to obtain the ordinal suffix of an integer as a string.
    private static String getOrdinalSuffix (int template){
        String[] temp = Integer.toString(template).split("");
        int stringLength = temp.length;
        String suffix = "";
        if (temp[stringLength - 1].equals("1")){
            suffix = "st";
        }
        else if (temp[stringLength - 1].equals("2")){
            suffix = "nd";
        }
        else if (temp[stringLength - 1].equals("3")){
            suffix = "rd";
        }
        else {
            suffix = "th";
        }
        return suffix;
    }
    //Function to generate a random Assignment object within the specifications stated.
    private static Assignment randomAssignment(int priorityLowerBound, int priorityUpperBound, int yearUpperBound,
                                               int yearLowerBound, int monthValueUpperBound, int monthValueLowerBound,
                                               int hourUpperBound, int hourLowerBound, int minuteUpperBound, int minuteLowerBound){
        Assignment tempAssignment = new Assignment(randLocalDateTime(yearUpperBound, yearLowerBound,
                monthValueUpperBound, monthValueLowerBound, hourUpperBound,hourLowerBound,minuteUpperBound,
                minuteLowerBound), Category.randomCategory(), Course.randomCourse(),
                randomPositiveIntInInclusiveRange(priorityLowerBound, priorityUpperBound));
        return tempAssignment;
    }
}
