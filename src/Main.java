import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n\nHello, AssignmentsApp!\n");

        //Output the current date-time.
        LocalDateTime today = LocalDateTime.now();
        System.out.println("\nThe current date-time is " + today);

        //Output tomorrow's date using a formatter.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        String formatDateTime = today.format(formatter);
        System.out.println("Tomorrow's formatted date is " + formatDateTime);

        //Add 5 weeks to today's LocalDateTime.
        LocalDateTime fiveWeeksLater = today.plusWeeks(5);
        System.out.println("The five weeks, the date will be " + fiveWeeksLater);

        //Initialize a LocalDateTime object to your birthdate and the time 12:35 PM.
        LocalDateTime birthDate = LocalDateTime.of(1999, 2, 7, 12, 35);
        System.out.println("Your birthdate is " + birthDate);

        //Output the day of the week (Sunday-Saturday) that you were born.
        System.out.println("The day of the week of your birthdate was " + birthDate.getDayOfWeek());

        //Output the number of days you've been alive.
        System.out.println("The number of days you have been alive is " + ChronoUnit.DAYS.between(birthDate, today) + " days.");

        //Output the number of days between two dates.
        LocalDateTime obamaInauguration = LocalDateTime.of(2009, 2, 20, 12, 0);
        System.out.println("The number of days between your birthdate and Obama's inauguration is " + ChronoUnit.DAYS.between(birthDate, obamaInauguration) + " days.");

        //Given two dates, output the earlier.
        FindEarlierDate(today, obamaInauguration);

        //Create a file with 100 random "month/day/year  hour:minutes" (in that format) on each line.
        ArrayList<String> hundredRandomDates = randomDateArray(100);
        for (int i = 0; i < hundredRandomDates.size(); i++) System.out.println("Date " + (i + 1) + " is " + hundredRandomDates.get(i));
    }

    private static ArrayList<String> randomDateArray (int NumElements){
        ArrayList<String> returnArray = new ArrayList<>();
        for (int i = 0; i < NumElements; i++) {
            returnArray.add(randomDateGenerator());
        }
        return returnArray;
    }

    private static String randomDateGenerator (){
        LocalDateTime today =  LocalDateTime.now();
        ArrayList<Integer> monthsWith30days = new ArrayList<>();
        monthsWith30days.add(4);
        monthsWith30days.add(6);
        monthsWith30days.add(9);
        monthsWith30days.add(11);
        int day;
        int year = randomNumGenerator(today.getYear());
        int month = randomNumGenerator(12) + 1;
        if (month == 2 && year /4 == 0){
            day = randomNumGenerator(29) + 1;
        } else if (month == 2 && year/4 != 0){
            day = randomNumGenerator(28) + 1;
        } else if (monthsWith30days.contains(month)){
            day = randomNumGenerator(30) + 1;
        } else {
            day = randomNumGenerator(31) + 1;
        }
        LocalDateTime generatedDate = LocalDateTime.of(year, month, day,randomNumGenerator(24), randomNumGenerator(60));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        return generatedDate.format(formatter);
    }

    private static int randomNumGenerator (int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }

    private static void FindEarlierDate(LocalDateTime date1, LocalDateTime date2) {
        if (date1 == date2) {
            System.out.println("\nThe dates are the same.");
        } else if (date1.isBefore(date2)) {
            System.out.println("The earlier date is " + date1);
        } else {
            System.out.println("The earlier date is " + date2);
        }
    }
}
