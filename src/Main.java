import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
        System.out.println("The number of days you have been alive is " + ChronoUnit.DAYS.between(birthDate,today) + " days.");

        //Output the number of days between two dates.
        LocalDateTime obamaInauguration = LocalDateTime.of(2009,2,20, 12,0);
        System.out.println("The number of days between your birthdate and Obama's inauguration is " + ChronoUnit.DAYS.between(birthDate,obamaInauguration) + " days.");

        //Given two dates, output the earlier. Honestly, I don't understand the need to use all three methods (isBefore(), isAfter(), compareTo()) is necessary.
        LocalDateTime earlierDate = FindEarlierDate(birthDate, today);
        System.out.println("The earlier date is " + earlierDate);

        }

    private static LocalDateTime FindEarlierDate(LocalDateTime date1, LocalDateTime date2) {
        LocalDateTime earlyDate = date1;
        int comparedValue = date1.compareTo(date2);
        if (date1.isBefore(date2) && comparedValue < 0){
            earlyDate = date1;
        }else if (date1.isAfter(date2) && comparedValue > 0) {
            earlyDate = date2;
        }
        return earlyDate;
    }

}
