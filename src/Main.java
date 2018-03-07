import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.WEEKS;

public class Main {

    private static Random rand = new Random();
    private static Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter formatMMddyyyyhhmm = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm");
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
                LocalDateTime tempDateTime = randLocalDateTime(2017, 2000, 12, 1, 23, 0, 59, 0);
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
        yearSearchList = findTemporalFieldValue(dateTimeList, 2017, ChronoField.YEAR);
        System.out.println("The number of stored dates for the current year is " + yearCurrentList.size());
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
        int dayOfMonthValue = rand.nextInt(Month.of(monthValue).maxLength()) + 1;
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
        ArrayList<LocalDateTime> temp_list = new ArrayList<>();
        for (LocalDateTime date: list) {
            int temp = date.get(temporalField);
            if (temp == target){
                temp_list.add(date);
            }
        }
        return  temp_list;
    }
}
